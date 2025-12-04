package com.tarea.springbootapp.modelo.dto;

public class AlumnoDto {

    private Long id;
    private String githubUser;
    private String ipasen;

    // Constructor vacío
    public AlumnoDto() {}

    // Constructor completo
    public AlumnoDto(Long id, String githubUser, String ipasen) {
        this.id = id;
        this.githubUser = githubUser;
        this.ipasen = ipasen;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
