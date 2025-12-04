package com.tarea.springbootapp;

import java.io.InputStream;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.tarea.springbootapp.modelo.dao.AlumnoRepository;
import com.tarea.springbootapp.modelo.entidad.Alumno;

import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;
@Configuration
public class CargarDatos {

    @Bean
    CommandLineRunner cargarAlumnos(AlumnoRepository repo) {
        return args -> {
            try {
                ClassPathResource resource =
                        new ClassPathResource("data/alumnos.json");

                if (!resource.exists()) {
                    System.err.println("❌ ERROR: alumnos.json NO encontrado en src/main/resources/static/data/");
                    return;
                }

                ObjectMapper mapper = new ObjectMapper();

                List<Alumno> alumnos = mapper.readValue(
                        resource.getInputStream(),
                        new TypeReference<List<Alumno>>() {}
                );

                alumnos.forEach(a ->
                    repo.findByGithubUser(a.getGithubUser())
                            .orElseGet(() -> repo.save(a))
                );

                System.out.println("✔ Alumnos cargados correctamente.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
    }
}
