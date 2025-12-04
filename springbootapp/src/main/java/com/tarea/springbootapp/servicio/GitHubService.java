package com.tarea.springbootapp.servicio;
import com.tarea.springbootapp.modelo.dto.*;
import com.tarea.springbootapp.modelo.entidad.EntregaRama;
import com.tarea.springbootapp.modelo.entidad.enumerado.*;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class GitHubService {

    private static final String GITHUB_API_BASE = "https://api.github.com";

    private final HttpClient httpClient;
    private final String githubToken;

    /**
     * El token se inyecta desde application.properties o variable de entorno.
     * Ejemplo:
     *   github.token=${GITHUB_TOKEN}
     */
    public GitHubService(@Value("${github.token:}") String githubToken) {
        this.githubToken = githubToken == null ? "" : githubToken.trim();
        this.httpClient = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(5))
                .build();
    }

    /**
     * Comprueba si una rama existe en el repositorio dado.
     *
     * @param usuario GitHub user/owner (ej. "josemiguelzerpagarcia")
     * @param repositorio Nombre del repo (ej. "SpringBootApp")
     * @param rama Nombre de la rama (ej. "ej01-hola-thymeleaf")
     * @return true si la rama existe (HTTP 200), false en el resto de casos.
     */
    public boolean existsBranch(String usuario, String repositorio, String rama) {
        return getEstadoRama(usuario, repositorio, rama) == EstadoEntrega.ENTREGADA;
    }

    /**
     * Devuelve un estado más rico que un simple boolean:
     *  - ENTREGADA → la rama existe (200)
     *  - NO_ENTREGADA → 404 rama/repositorio
     *  - REPOSITORIO_NO_ENCONTRADO → 404 al nivel de repo
     *  - ERROR_API → cualquier otro error (403 rate limit, 500 GitHub, timeout…)
     */
    public EstadoEntrega getEstadoRama(String usuario, String repositorio, String rama) {

        // Primero comprobamos el repositorio, para poder diferenciar bien 404 de repo y de rama
        if (!existsRepository(usuario, repositorio)) {
            return EstadoEntrega.REPOSITORIO_NO_ENCONTRADO;
        }

        String url = "%s/repos/%s/%s/branches/%s"
                .formatted(GITHUB_API_BASE, usuario, repositorio, rama);

        HttpRequest.Builder builder = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .timeout(Duration.ofSeconds(5))
                .header("Accept", "application/vnd.github+json")
                .header("User-Agent", "springboot-github-checker");

        if (!githubToken.isBlank()) {
            builder.header("Authorization", "Bearer " + githubToken);
        }

        HttpRequest request = builder.build();

        try {
            HttpResponse<Void> response =
                    httpClient.send(request, HttpResponse.BodyHandlers.discarding());

            int status = response.statusCode();

            return switch (status) {
                case 200 -> EstadoEntrega.ENTREGADA;
                case 404 -> EstadoEntrega.NO_ENTREGADA;      // repo existe, rama no
                case 403 -> EstadoEntrega.ERROR_API;         // típico rate limit
                default -> EstadoEntrega.ERROR_API;
            };

        } catch (IOException e) {
            // Problemas de red, DNS, etc.
            return EstadoEntrega.ERROR_API;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return EstadoEntrega.ERROR_API;
        }
    }

    /**
     * Comprueba si el repositorio existe.
     * Nos ayuda a diferenciar "repo inexistente" de "rama inexistente".
     */
    public boolean existsRepository(String usuario, String repositorio) {

        String url = "%s/repos/%s/%s".formatted(GITHUB_API_BASE, usuario, repositorio);

        HttpRequest.Builder builder = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .timeout(Duration.ofSeconds(5))
                .header("Accept", "application/vnd.github+json")
                .header("User-Agent", "springboot-github-checker");

        if (!githubToken.isBlank()) {
            builder.header("Authorization", "Bearer " + githubToken);
        }

        HttpRequest request = builder.build();

        try {
            HttpResponse<Void> response =
                    httpClient.send(request, HttpResponse.BodyHandlers.discarding());

            int status = response.statusCode();
            return status == 200;

        } catch (IOException | InterruptedException e) {
            if (e instanceof InterruptedException ie) {
                Thread.currentThread().interrupt();
            }
            return false;
        }
    }
}
