package com.example.My_Pet.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "emergencia")
public class Emergencia {

    // ID de la emergencia
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_emergencia")
    private Integer idEmergencia;

    // Tipo de emergencia
    @Column(length = 150, nullable = false)
    private String tipo;

    // Descripción de la emergencia
    @Column(length = 300, nullable = false)
    private String descripcion;

    // Fecha y hora de la emergencia
    @Column(name = "fecha", nullable = false, updatable = false)
    private LocalDateTime fecha;

    // Usuario relacionado con la emergencia
    @ManyToOne
    @JoinColumn(
        name = "id_usuario",
        referencedColumnName = "id_usuario",
        nullable = false
    )
    private Usuario usuario;

    // Constructor vacío
    public Emergencia() {
    }

    // Constructor completo
    public Emergencia(
            Integer idEmergencia,
            String tipo,
            String descripcion,
            LocalDateTime fecha,
            Usuario usuario) {

        this.idEmergencia = idEmergencia;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.usuario = usuario;
    }

    // Getters y setters

    public Integer getIdEmergencia() {
        return idEmergencia;
    }

    public void setIdEmergencia(Integer idEmergencia) {
        this.idEmergencia = idEmergencia;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}