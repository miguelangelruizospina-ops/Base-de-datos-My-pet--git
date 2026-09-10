package com.example.My_Pet.model;

import jakarta.persistence.*;

@Entity
@Table(name = "administrador")
public class Administrador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_admin")
    private Integer idAdmin;

    @Column(length = 200, nullable = false)
    private String permisos;


    // Relación entre el administrador y el usuario
    @ManyToOne
    @JoinColumn(
            name = "id_usuario",
            referencedColumnName = "id_usuario",
            nullable = false
    )
    private Usuario usuario;


    // Constructor vacío
    public Administrador() {
    }


    // Constructor completo
    public Administrador(
            Integer idAdmin,
            String permisos,
            Usuario usuario) {

        this.idAdmin = idAdmin;
        this.permisos = permisos;
        this.usuario = usuario;
    }


    // GETTERS Y SETTERS

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