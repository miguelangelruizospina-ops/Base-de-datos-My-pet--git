package com.example.My_Pet.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "publicacion_foro")
public class PublicacionForo {

    // ID de la publicación
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_publicacion")
    private Integer idPublicacion;

    // Título de la publicación
    @Column(length = 200, nullable = false)
    private String titulo;

    // Contenido de la publicación
    @Column(length = 500, nullable = false)
    private String contenido;

    // Fecha en la que se creó la publicación
    @Column(name = "fecha", nullable = false, updatable = false)
    private LocalDateTime fecha;

    // Usuario que creó la publicación
    @ManyToOne
    @JoinColumn(
        name = "id_usuario",
        referencedColumnName = "id_usuario",
        nullable = false
    )
    private Usuario usuario;


    // Constructor vacío
    public PublicacionForo() {
    }


    // Constructor completo
    public PublicacionForo(
            Integer idPublicacion,
            String titulo,
            String contenido,
            LocalDateTime fecha,
            Usuario usuario) {

        this.idPublicacion = idPublicacion;
        this.titulo = titulo;
        this.contenido = contenido;
        this.fecha = fecha;
        this.usuario = usuario;
    }


    // GETTERS Y SETTERS

    public Integer getIdPublicacion() {
        return idPublicacion;
    }

    public void setIdPublicacion(Integer idPublicacion) {
        this.idPublicacion = idPublicacion;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
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