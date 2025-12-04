package com.tarea.springbootapp.modelo.entidad;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "alumnos")
public class Alumno {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 100)
    @Column(name = "github_user", nullable = false, unique = true, length = 100)
    private String githubUser;

    @NotBlank
    @Size(max = 100)
    @Column(name = "ipasen", nullable = false, unique = true, length = 100)
    private String ipasen;

    @JsonIgnore  // ⛔ evita recursión infinita si usas REST
    @OneToMany(
            mappedBy = "alumno",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private List<EntregaRama> entregas = new ArrayList<>();


    // ========= MÉTODOS HELPER PARA LA RELACIÓN =========

    public void addEntrega(EntregaRama entrega) {
        entregas.add(entrega);
        entrega.setAlumno(this);
    }

    public void removeEntrega(EntregaRama entrega) {
        entregas.remove(entrega);
        entrega.setAlumno(null);
    }


    // ========= GETTERS & SETTERS =========

    public Long getId() {
        return id;
    }

    public String getGithubUser() {
        return githubUser;
    }

    public void setGithubUser(String githubUser) {
        this.githubUser = githubUser;
    }

    public String getIpasen() {
        return ipasen;
    }

    public void setIpasen(String ipasen) {
        this.ipasen = ipasen;
    }

    public List<EntregaRama> getEntregas() {
        return entregas;
    }

    public void setEntregas(List<EntregaRama> entregas) {
        this.entregas = entregas;
    }
}
