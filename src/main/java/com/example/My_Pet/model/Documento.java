package com.example.My_Pet.model;

import jakarta.persistence.*;

@Entity
@Table(name = "documento")
public class Documento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_documento")
    private Integer idDocumento;

    @Column(name = "tipo_documento", length = 50, nullable = false)
    private String tipoDocumento;

    @Column(length = 300, nullable = false)
    private String archivo;

    // Un documento pertenece obligatoriamente a un usuario
    @ManyToOne
    @JoinColumn(
            name = "id_usuario",
            referencedColumnName = "id_usuario",
            nullable = false
    )
    private Usuario usuario;

    // Un documento puede estar relacionado con una mascota
    @ManyToOne
    @JoinColumn(
            name = "id_mascota",
            referencedColumnName = "id_mascota",
            nullable = true
    )
    private Mascota mascota;


    // Constructor vacío
    public Documento() {
    }


    // Constructor completo
    public Documento(
            Integer idDocumento,
            String tipoDocumento,
            String archivo,
            Usuario usuario,
            Mascota mascota) {

        this.idDocumento = idDocumento;
        this.tipoDocumento = tipoDocumento;
        this.archivo = archivo;
        this.usuario = usuario;
        this.mascota = mascota;
    }


    // GETTERS Y SETTERS

    public Integer getIdDocumento() {
        return idDocumento;
    }

    public void setIdDocumento(Integer idDocumento) {
        this.idDocumento = idDocumento;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getArchivo() {
        return archivo;
    }

    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Mascota getMascota() {
        return mascota;
    }

    public void setMascota(Mascota mascota) {
        this.mascota = mascota;
    }
}