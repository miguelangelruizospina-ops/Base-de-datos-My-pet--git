package com.example.My_Pet.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "recordatorio")
public class Recordatorio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_recordatorio")
    private Integer idRecordatorio;

    @Column(length = 300, nullable = false)
    private String mensaje;

    // Registra la fecha y hora automáticamente
    @Column(insertable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime fecha;

    @Column(length = 100)
    private String estado;

    // Relación Muchos a Uno con Usuario
    @ManyToOne
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario", nullable = false)
    private Usuario usuario;

    // --- CONSTRUCTORES ---
    public Recordatorio() {
    }

    public Recordatorio(Integer idRecordatorio, String mensaje, LocalDateTime fecha, String estado, Usuario usuario) {
        this.idRecordatorio = idRecordatorio;
        this.mensaje = mensaje;
        this.fecha = fecha;
        this.estado = estado;
        this.usuario = usuario;
    }

    // --- GETTERS Y SETTERS ---
    public Integer getIdRecordatorio() { return idRecordatorio; }
    public void setIdRecordatorio(Integer idRecordatorio) { this.idRecordatorio = idRecordatorio; }

    public String getMensaje() { return mensaje; }
    public void setMensaje(String mensaje) { this.mensaje = mensaje; }

    public LocalDateTime getFecha() { return fecha; }
    public void setFecha(LocalDateTime fecha) { this.fecha = fecha; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
}