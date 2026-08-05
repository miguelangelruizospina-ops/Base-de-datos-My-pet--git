package com.example.My_Pet.model;

import jakarta.persistence.*;

// @Entity le indica a Spring Boot que esta clase se comportará como una tabla en la base de datos
@Entity
// @Table define el nombre exacto que tendrá la tabla física en MySQL
@Table(name = "servicio")
public class Servicio {

    // @Id marca este campo como la Llave Primaria (Primary Key) de la tabla
    @Id
    // @GeneratedValue configura el campo para que sea autoincrementable (AUTO_INCREMENT)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // @Column vincula la variable con el nombre real de la columna en MySQL
    @Column(name = "id_servicio")
    private Integer idServicio;

    // length limita los caracteres en base de datos y nullable=false obliga a que no quede vacío (NOT NULL)
    @Column(length = 200, nullable = false)
    private String nombre;

    @Column(length = 200, nullable = false)
    private String tipo; // Almacena la categoría (ej: "Veterinaria", "Paseador", "Peluquería")

    @Column(length = 300, nullable = false)
    private String ubicacion; // Dirección o zona de cobertura del servicio

    @Column(length = 300)
    private String descripcion; // Detalle opcional de lo que ofrece el negocio

    // Esta columna almacena la nota o estrellas del servicio (mapea tu TINYINT)
    @Column(name = "calificacion")
    private Integer calificacion; 

    // @ManyToOne indica la relación: Muchos servicios pueden ser publicados o gestionados por un mismo Usuario
    @ManyToOne
    // @JoinColumn crea la llave foránea física (Foreign Key) en la tabla servicio
    // name es el nombre de la columna local y referencedColumnName apunta a la llave de la tabla usuario
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario", nullable = false)
    private Usuario usuario;

    // Constructor vacío exigido por Hibernate para poder procesar los datos internamente
    public Servicio() {
    }

    // Constructor con parámetros para instanciar o crear un servicio con todos sus datos rápidamente
    public Servicio(Integer idServicio, String nombre, String tipo, String ubicacion, String descripcion, Integer calificacion, Usuario usuario) {
        this.idServicio = idServicio;
        this.nombre = nombre;
        this.tipo = tipo;
        this.ubicacion = ubicacion;
        this.descripcion = descripcion;
        this.calificacion = calificacion;
        this.usuario = usuario;
    }

    // --- MÉTODOS GETTERS Y SETTERS (Controlan el acceso y la modificación segura de los atributos) ---

    public Integer getIdServicio() { return idServicio; }
    public void setIdServicio(Integer idServicio) { this.idServicio = idServicio; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public String getUbicacion() { return ubicacion; }
    public void setUbicacion(String ubicacion) { this.ubicacion = ubicacion; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public Integer getCalificacion() { return calificacion; }
    public void setCalificacion(Integer calificacion) { this.calificacion = calificacion; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
}