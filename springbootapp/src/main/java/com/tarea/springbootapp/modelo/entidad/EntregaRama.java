package com.tarea.springbootapp.modelo.entidad;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

import com.tarea.springbootapp.modelo.entidad.enumerado.EstadoEntrega;

@Entity
@Table(
        name = "entregas_ramas",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_alumno_rama",
                        columnNames = {"alumno_id", "rama"}
                )
        }
)
public class EntregaRama {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relación con Alumno (muchas entregas -> un alumno)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "alumno_id", nullable = false)
    private Alumno alumno;

    @NotBlank
    @Column(nullable = false)
    private String rama;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoEntrega estado;

    @Column(nullable = false)
    private LocalDateTime fechaComprobacion;

    @Column(nullable = false)
    private boolean entregada;

    public EntregaRama() {}

    public EntregaRama(Alumno alumno,
                       String rama,
                       EstadoEntrega estado,
                       boolean entregada) {
        this.alumno = alumno;
        this.rama = rama;
        this.estado = estado;
        this.entregada = entregada;
        this.fechaComprobacion = LocalDateTime.now();
    }

    // ===== Getters y Setters =====

    public Long getId() {
        return id;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public String getRama() {
        return rama;
    }

    public void setRama(String rama) {
        this.rama = rama;
    }

    public EstadoEntrega getEstado() {
        return estado;
    }

    public void setEstado(EstadoEntrega estado) {
        this.estado = estado;
    }

    public LocalDateTime getFechaComprobacion() {
        return fechaComprobacion;
    }

    public void setFechaComprobacion(LocalDateTime fechaComprobacion) {
        this.fechaComprobacion = fechaComprobacion;
    }

    public boolean isEntregada() {
        return entregada;
    }

    public void setEntregada(boolean entregada) {
        this.entregada = entregada;
    }

  
}
