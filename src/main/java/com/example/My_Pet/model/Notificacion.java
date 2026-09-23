// Definición del paquete: ubica esta clase dentro de la capa de modelo (entidades de la base de datos)
package com.example.My_Pet.model;

// Importaciones de la API de persistencia de Jakarta y utilidades de tiempo de Java
import jakarta.persistence.*;
import java.time.LocalDateTime;

// @Entity: Le indica al framework de Hibernate/JPA que esta clase representa una entidad persistente
@Entity
// @Table: Especifica el nombre exacto de la tabla relacional en la base de datos MySQL con la que se mapea esta clase
@Table(name = "notificacion")
public class Notificacion {

    // @Id: Define el atributo como la llave primaria (Primary Key) de la entidad
    @Id
    // @GeneratedValue: Define la estrategia de generación de la llave, configurando el campo como AUTO_INCREMENT en la base de datos
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // @Column: Mapea de forma explícita el atributo con el nombre físico de la columna en la tabla relacional
    @Column(name = "id_notificacion")
    private Integer idNotificacion;

    // @Column con restricciones: Define una longitud máxima de 300 caracteres y la restricción NOT NULL (obligatorio)
    @Column(length = 300, nullable = false)
    private String mensaje; 

    // @Column con restricción de actualización: Asegura la integridad del dato impidiendo modificaciones en la fecha tras su inserción
    @Column(name = "fecha", nullable = false, updatable = false)
    private LocalDateTime fecha;

    // @Column estándar: Mapea el atributo con una longitud de 100 caracteres para almacenar estados del ciclo de vida de la alerta
    @Column(length = 100)
    private String estado; 

    // @ManyToOne: Configura una relación de asociación polimórfica (Muchos a Uno). Múltiples notificaciones pertenecen a un único Usuario
    @ManyToOne
    // @JoinColumn: Define la columna de unión que actúa como llave foránea (Foreign Key) apuntando a la tabla de referencia
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario", nullable = false)
    private Usuario usuario;

    // Constructor por defecto: Requerido obligatoriamente por JPA/Hibernate para la instanciación de objetos mediante reflexión
    public Notificacion() {
    }

    // Constructor con parámetros: Facilita la instanciación rápida de la entidad con todos sus atributos requeridos
    public Notificacion(Integer idNotificacion, String mensaje, LocalDateTime fecha, String estado, Usuario usuario) {
        this.idNotificacion = idNotificacion;
        this.mensaje = mensaje;
        this.fecha = fecha;
        this.estado = estado;
        this.usuario = usuario;
    }

    // --- MÉTODOS GETTERS Y SETTERS: Métodos de acceso encapsulado para la lectura y escritura segura de los atributos ---

    public Integer getIdNotificacion() { return idNotificacion; }
    public void setIdNotificacion(Integer idNotificacion) { this.idNotificacion = idNotificacion; }

    public String getMensaje() { return mensaje; }
    public void setMensaje(String mensaje) { this.mensaje = mensaje; }

    public LocalDateTime getFecha() { return fecha; }
    public void setFecha(LocalDateTime fecha) { this.fecha = fecha; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
}