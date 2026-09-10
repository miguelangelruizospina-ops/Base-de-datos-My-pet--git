package com.example.My_Pet.model;

import jakarta.persistence.*;

@Entity
@Table(name = "servicio")
public class Servicio {

    // ID del servicio
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_servicio")
    private Integer idServicio;

    // Nombre del servicio
    @Column(length = 200, nullable = false)
    private String nombre;

    // Tipo de servicio
    @Column(length = 200, nullable = false)
    private String tipo;

    // Ubicación del servicio
    @Column(length = 300, nullable = false)
    private String ubicacion;

    // Descripción del servicio
    @Column(length = 300)
    private String descripcion;

    // Calificación del servicio
    @Column(name = "calificacion")
    private Integer calificacion;

    // Usuario que registra el servicio
    @ManyToOne
    @JoinColumn(
        name = "id_usuario",
        referencedColumnName = "id_usuario",
        nullable = false
    )
    private Usuario usuario;


    // Constructor vacío
    public Servicio() {
    }


    // Constructor completo
    public Servicio(
            Integer idServicio,
            String nombre,
            String tipo,
            String ubicacion,
            String descripcion,
            Integer calificacion,
            Usuario usuario) {

        this.idServicio = idServicio;
        this.nombre = nombre;
        this.tipo = tipo;
        this.ubicacion = ubicacion;
        this.descripcion = descripcion;
        this.calificacion = calificacion;
        this.usuario = usuario;
    }


    // GETTERS Y SETTERS

    public Integer getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(Integer idServicio) {
        this.idServicio = idServicio;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }


    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }


    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }


    public Integer getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(Integer calificacion) {
        this.calificacion = calificacion;
    }


    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}