package com.example.My_Pet.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

// @Entity le indica a Spring Boot que esta clase se mapeará como una tabla en la base de datos
@Entity
// @Table define el nombre exacto de la tabla física dentro de MySQL
@Table(name = "emergencia")
public class Emergencia {

    // @Id define que este atributo es la Llave Primaria (Primary Key)
    @Id
    // @GeneratedValue establece que el ID se creará de forma autoincrementable (AUTO_INCREMENT)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // @Column amarra el atributo al nombre real del campo en la base de datos
    @Column(name = "id_emergencia")
    private Integer idEmergencia;

    // length restringe la longitud y nullable=false marca el campo como obligatorio (NOT NULL)
    @Column(length = 150, nullable = false)
    private String tipo; // Ej: "Mascota perdida", "Accidente", "Intoxicación"

    @Column(length = 300, nullable = false)
    private String descripcion; // Detalles clave sobre lo que está pasando

    // updatable = false asegura que una vez registrada la emergencia, el tiempo original no se altere
    @Column(name = "fecha", nullable = false, updatable = false)
    private LocalDateTime fecha;

    // @ManyToOne indica que muchas emergencias pueden ser reportadas por un mismo Usuario
    @ManyToOne
    // @JoinColumn crea la relación de llave foránea (Foreign Key) apuntando a la tabla usuario
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario", nullable = false)
    private Usuario usuario;

    // Constructor vacío exigido por el motor de Hibernate para sus operaciones internas
    public Emergencia() {
    }

    // Constructor parametrizado para crear reportes de emergencia rápidamente en el sistema
    public Emergencia(Integer idEmergencia, String tipo, String descripcion, LocalDateTime fecha, Usuario usuario) {
        this.idEmergencia = idEmergencia;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.usuario = usuario;
    }

    // --- MÉTODOS GETTERS Y SETTERS (Permiten la lectura y escritura segura de la información) ---

    public Integer getIdEmergencia() { return idEmergencia; }
    public void setIdEmergencia(Integer idEmergencia) { this.idEmergencia = idEmergencia; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public LocalDateTime getFecha() { return fecha; }
    public void setFecha(LocalDateTime fecha) { this.fecha = fecha; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
}