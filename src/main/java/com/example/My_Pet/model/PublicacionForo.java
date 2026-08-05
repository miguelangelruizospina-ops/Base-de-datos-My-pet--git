package com.example.My_Pet.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

// @Entity le indica a Spring Boot que esta clase se comportará como una tabla en la base de datos
@Entity
// @Table define el nombre exacto que tendrá la tabla física en MySQL
@Table(name = "publicacion_foro")
public class PublicacionForo {

    // @Id marca este campo como la Llave Primaria (Primary Key) de la tabla
    @Id
    // @GeneratedValue configura el campo para que sea autoincrementable (AUTO_INCREMENT)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // @Column vincula la variable con el nombre real de la columna en MySQL
    @Column(name = "id_publicacion")
    private Integer idPublicacion;

    // length limita los caracteres en base de datos y nullable=false obliga a que no quede vacío (NOT NULL)
    @Column(length = 200, nullable = false)
    private String titulo;

    @Column(length = 500, nullable = false)
    private String contenido;

    // updatable = false evita que se altere la fecha de creación original en futuras actualizaciones
    @Column(name = "fecha", nullable = false, updatable = false)
    private LocalDateTime fecha;

    // @ManyToOne indica la relación: Muchas publicaciones pueden ser escritas por un mismo Usuario
    @ManyToOne
    // @JoinColumn crea la llave foránea física (Foreign Key) en la tabla publicacion_foro
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario", nullable = false)
    private Usuario usuario;

    // Constructor vacío exigido por Hibernate para poder procesar los datos internamente
    public PublicacionForo() {
    }

    // Constructor con parámetros para instanciar una publicación con datos rápidamente
    public PublicacionForo(Integer idPublicacion, String titulo, String contenido, LocalDateTime fecha, Usuario usuario) {
        this.idPublicacion = idPublicacion;
        this.titulo = titulo;
        this.contenido = contenido;
        this.fecha = fecha;
        this.usuario = usuario;
    }

    // --- MÉTODOS GETTERS Y SETTERS (Controlan el acceso y la modificación segura de los atributos) ---

    public Integer getIdPublicacion() { return idPublicacion; }
    public void setIdPublicacion(Integer idPublicacion) { this.idPublicacion = idPublicacion; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getContenido() { return contenido; }
    public void setContenido(String contenido) { this.contenido = contenido; }

    public LocalDateTime getFecha() { return fecha; }
    public void setFecha(LocalDateTime fecha) { this.fecha = fecha; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
}