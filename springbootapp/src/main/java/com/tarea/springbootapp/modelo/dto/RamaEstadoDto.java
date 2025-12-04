package com.tarea.springbootapp.modelo.dto;

public class RamaEstadoDto {

    private String usuario;
    private String rama;
    private boolean entregada;

    // ===== CONSTRUCTORES =====

    public RamaEstadoDto() {}

    public RamaEstadoDto(String usuario, String rama, boolean entregada) {
        this.usuario = usuario;
        this.rama = rama;
        this.entregada = entregada;
    }

    // ===== GETTERS & SETTERS =====

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getRama() {
        return rama;
    }

    public void setRama(String rama) {
        this.rama = rama;
    }

    public boolean isEntregada() {
        return entregada;
    }

    public void setEntregada(boolean entregada) {
        this.entregada = entregada;
    }
}

