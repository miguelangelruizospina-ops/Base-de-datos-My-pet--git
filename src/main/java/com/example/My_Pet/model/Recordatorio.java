package com.example.My_Pet.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "recordatorio")
public class Recordatorio {

    // ID del recordatorio
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_recordatorio")
    private Integer idRecordatorio;

    // Mensaje del recordatorio
    @Column(length = 300, nullable = false)
    private String mensaje;

    // Fecha y hora de creación
    @Column(
        insertable = false,
        updatable = false,
        columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP"
    )
    private LocalDateTime fecha;

    // Estado del recordatorio
    @Column(length = 100)
    private String estado;

    // Usuario relacionado con el recordatorio
    @ManyToOne
    @JoinColumn(
        name = "id_usuario",
        referencedColumnName = "id_usuario",
        nullable = false
    )
    private Usuario usuario;

    // Constructor vacío
    public Recordatorio() {
    }

    // Constructor completo
    public Recordatorio(
            Integer idRecordatorio,
            String mensaje,
            LocalDateTime fecha,
            String estado,
            Usuario usuario) {

        this.idRecordatorio = idRecordatorio;
        this.mensaje = mensaje;
        this.fecha = fecha;
        this.estado = estado;
        this.usuario = usuario;
    }

    // Getters y setters

    public Integer getIdRecordatorio() {
        return idRecordatorio;
    }

    public void setIdRecordatorio(Integer idRecordatorio) {
        this.idRecordatorio = idRecordatorio;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}