package com.example.My_Pet.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "evento")
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_evento")
    private Integer idEvento;

    @Column(name = "tipo_evento", length = 150, nullable = false)
    private String tipoEvento;

    // Registra automáticamente fecha y hora al crear el evento
    @Column(insertable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime fecha;

    @Column(length = 300)
    private String descripcion;

    // Relación Muchos a Uno con Mascota
    @ManyToOne
    @JoinColumn(name = "id_mascota", referencedColumnName = "id_mascota", nullable = false)
    private Mascota mascota;

    // --- CONSTRUCTORES ---
    public Evento() {
    }

    public Evento(Integer idEvento, String tipoEvento, LocalDateTime fecha, String descripcion, Mascota mascota) {
        this.idEvento = idEvento;
        this.tipoEvento = tipoEvento;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.mascota = mascota;
    }

    // --- GETTERS Y SETTERS ---
    public Integer getIdEvento() { return idEvento; }
    public void setIdEvento(Integer idEvento) { this.idEvento = idEvento; }

    public String getTipoEvento() { return tipoEvento; }
    public void setTipoEvento(String tipoEvento) { this.tipoEvento = tipoEvento; }

    public LocalDateTime getFecha() { return fecha; }
    public void setFecha(LocalDateTime fecha) { this.fecha = fecha; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public Mascota getMascota() { return mascota; }
    public void setMascota(Mascota mascota) { this.mascota = mascota; }
}