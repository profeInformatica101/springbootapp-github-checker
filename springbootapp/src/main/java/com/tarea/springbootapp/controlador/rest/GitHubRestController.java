package com.tarea.springbootapp.controlador.rest;
import com.tarea.springbootapp.servicio.*;
import com.tarea.springbootapp.modelo.dto.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//controlador/rest/GitHubRestController.java
@RestController
@RequestMapping("/api/github")
public class GitHubRestController {

 private final GitHubService gitHubService;

 public GitHubRestController(GitHubService gitHubService) {
     this.gitHubService = gitHubService;
 }

 @GetMapping("/branch")
 public RamaEstadoDto checkBranch(
         @RequestParam String user,
         @RequestParam String rama) {

     boolean ok = gitHubService.existsBranch(user, "SpringBootApp", rama);
     RamaEstadoDto dto = new RamaEstadoDto();
     dto.setUsuario(user);
     dto.setRama(rama);
     dto.setEntregada(ok);
     return dto;
 }
 
}
