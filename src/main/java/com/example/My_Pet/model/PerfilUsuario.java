package com.example.My_Pet.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "perfil_usuario")
public class PerfilUsuario {

    @Id
    @Column(name = "id_usuario")
    private Integer idUsuario;

    @Column(name = "nombre_usuario", nullable = false, length = 100)
    private String nombreUsuario;

    @Column(name = "foto_perfil", nullable = false, length = 300)
    private String fotoPerfil;

    @Column(nullable = false, length = 300)
    private String biografia;

    @Column(nullable = false, length = 20)
    private String telefono;

    @Column(nullable = false, length = 100)
    private String ciudad;

    @Column(nullable = false, length = 50)
    private String genero;

    @Column(name = "fecha_nacimiento", nullable = false)
    private LocalDate fechaNacimiento;

    // Conexión Uno a Uno con la tabla Usuario y el ON DELETE CASCADE
    @OneToOne
    @MapsId
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    // --- CONSTRUCTORES ---
    public PerfilUsuario() {
    }

    public PerfilUsuario(Integer idUsuario, String nombreUsuario, String fotoPerfil, String biografia, String telefono, String ciudad, String genero, LocalDate fechaNacimiento) {
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
        this.fotoPerfil = fotoPerfil;
        this.biografia = biografia;
        this.telefono = telefono;
        this.ciudad = ciudad;
        this.genero = genero;
        this.fechaNacimiento = fechaNacimiento;
    }

    // --- GETTERS Y SETTERS ---
    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getFotoPerfil() {
        return fotoPerfil;
    }

    public void setFotoPerfil(String fotoPerfil) {
        this.fotoPerfil = fotoPerfil;
    }

    public String getBiografia() {
        return biografia;
    }

    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}