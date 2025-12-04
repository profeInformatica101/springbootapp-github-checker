package com.tarea.springbootapp.modelo.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tarea.springbootapp.modelo.entidad.Alumno;

public interface AlumnoRepository extends JpaRepository<Alumno, Long> {

    Optional<Alumno> findByGithubUser(String githubUser);

    Optional<Alumno> findByIpasen(String ipasen);
}
