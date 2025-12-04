package com.tarea.springbootapp.modelo.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tarea.springbootapp.modelo.entidad.Alumno;
import com.tarea.springbootapp.modelo.entidad.EntregaRama;

public interface EntregaRamaRepository extends JpaRepository<EntregaRama, Long> {

    List<EntregaRama> findByAlumno(Alumno alumno);

    Optional<EntregaRama> findByAlumnoAndRama(Alumno alumno, String rama);
}
