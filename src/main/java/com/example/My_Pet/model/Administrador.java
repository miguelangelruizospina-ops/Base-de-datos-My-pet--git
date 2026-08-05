package com.example.My_Pet.model;

import jakarta.persistence.*;

// @Entity le dice a Spring Boot que esta clase representa una tabla de la base de datos
@Entity
// @Table especifica el nombre real de la tabla en MySQL
@Table(name = "administrador")
public class Administrador {

    // @Id define que esta variable es la Clave Primaria (Primary Key)
    @Id
    // @GeneratedValue indica que el ID es autoincrementable (AUTO_INCREMENT) en MySQL
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // @Column mapea la variable al nombre real de la columna física en la tabla
    @Column(name = "id_admin")
    private Integer idAdmin;

    // @Column define restricciones; aquí el texto no supera 200 caracteres y no puede ser nulo
    @Column(length = 200, nullable = false)
    private String permisos;

    // @ManyToOne define la relación: muchos administradores pueden mapearse a un usuario base
    @ManyToOne
    // @JoinColumn configura la llave foránea (Foreign Key)
    // name = columna física en la tabla administrador, referencedColumnName = columna clave en la tabla usuario
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario", nullable = false)
    private Usuario usuario;

    // Constructor vacío obligatorio por Hibernate para poder crear instancias internamente
    public Administrador() {
    }

    // Constructor con parámetros para crear un administrador con datos directamente
    public Administrador(Integer idAdmin, String permisos, Usuario usuario) {
        this.idAdmin = idAdmin;
        this.permisos = permisos;
        this.usuario = usuario;
    }

    // --- MÉTODOS GETTERS Y SETTERS (Permiten leer y modificar los datos de forma segura) ---

    public Integer getIdAdmin() { 
        return idAdmin; 
    }
    public void setIdAdmin(Integer idAdmin) { 
        this.idAdmin = idAdmin; 
    }

    public String getPermisos() { 
        return permisos; 
    }
    public void setPermisos(String permisos) { 
        this.permisos = permisos; 
    }

    public Usuario getUsuario() { 
        return usuario; 
    }
    public void setUsuario(Usuario usuario) { 
        this.usuario = usuario; 
    }
}