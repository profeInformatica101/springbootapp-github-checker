package com.tarea.springbootapp.servicio;

import org.springframework.stereotype.Service;

import com.tarea.springbootapp.modelo.dao.AlumnoRepository;
import com.tarea.springbootapp.modelo.dto.AlumnoDto;

@Service
public class AlumnoService {

    private final AlumnoRepository alumnoRepository;

    public AlumnoService(AlumnoRepository alumnoRepository) {
        this.alumnoRepository = alumnoRepository;
    }

    public AlumnoDto buscarPorGithub(String githubUser) {
        return alumnoRepository.findByGithubUser(githubUser)
                .map(a -> new AlumnoDto(a.getId(), a.getGithubUser(), a.getIpasen()))
                .orElse(null);
    }
}