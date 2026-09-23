package com.example.My_Pet.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "evento")
public class Evento {

    // Identificador del evento
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_evento")
    private Integer idEvento;

    // Tipo de evento
    @Column(name = "tipo_evento", length = 150, nullable = false)
    private String tipoEvento;

    // Fecha y hora en la que se registra el evento
    @Column(insertable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime fecha;

    // Descripción del evento
    @Column(length = 300)
    private String descripcion;

    // Mascota a la que pertenece el evento
    @ManyToOne
    @JoinColumn(name = "id_mascota", referencedColumnName = "id_mascota", nullable = false)
    private Mascota mascota;


    // Constructor vacío
    public Evento() {
    }


    // Constructor con todos los datos del evento
    public Evento(Integer idEvento, String tipoEvento, LocalDateTime fecha, String descripcion, Mascota mascota) {
        this.idEvento = idEvento;
        this.tipoEvento = tipoEvento;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.mascota = mascota;
    }


    // Obtener y modificar el ID del evento
    public Integer getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(Integer idEvento) {
        this.idEvento = idEvento;
    }


    // Obtener y modificar el tipo de evento
    public String getTipoEvento() {
        return tipoEvento;
    }

    public void setTipoEvento(String tipoEvento) {
        this.tipoEvento = tipoEvento;
    }


    // Obtener y modificar la fecha del evento
    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }


    // Obtener y modificar la descripción del evento
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }


    // Obtener y modificar la mascota relacionada con el evento
    public Mascota getMascota() {
        return mascota;
    }

    public void setMascota(Mascota mascota) {
        this.mascota = mascota;
    }
}