package com.example.My_Pet.model;

import jakarta.persistence.*;

@Entity
@Table(name = "historial_medico")
public class HistorialMedico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_historial")
    private Integer idHistorial;

    @Column(length = 100)
    private String diagnosticos;

    @Column(name = "antecedentes_medicos", length = 200)
    private String antecedentesMedicos;

    @Column(length = 100)
    private String alergias;

    @Column(length = 300)
    private String tratamientos;

    // Relación Muchos a Uno con tu modelo Mascota existente
    @ManyToOne
    @JoinColumn(name = "id_mascota", referencedColumnName = "id_mascota", nullable = false)
    private Mascota mascota;

    // --- CONSTRUCTORES ---
    public HistorialMedico() {
    }

    public HistorialMedico(Integer idHistorial, String diagnosticos, String antecedentesMedicos, String alergias, String tratamientos, Mascota mascota) {
        this.idHistorial = idHistorial;
        this.diagnosticos = diagnosticos;
        this.antecedentesMedicos = antecedentesMedicos;
        this.alergias = alergias;
        this.tratamientos = tratamientos;
        this.mascota = mascota;
    }

    // --- GETTERS Y SETTERS ---
    public Integer getIdHistorial() { return idHistorial; }
    public void setIdHistorial(Integer idHistorial) { this.idHistorial = idHistorial; }

    public String getDiagnosticos() { return diagnosticos; }
    public void setDiagnosticos(String diagnosticos) { this.diagnosticos = diagnosticos; }

    public String getAntecedentesMedicos() { return antecedentesMedicos; }
    public void setAntecedentesMedicos(String antecedentesMedicos) { this.antecedentesMedicos = antecedentesMedicos; }

    public String getAlergias() { return alergias; }
    public void setAlergias(String alergias) { this.alergias = alergias; }

    public String getTratamientos() { return tratamientos; }
    public void setTratamientos(String tratamientos) { this.tratamientos = tratamientos; }

    public Mascota getMascota() { return mascota; }
    public void setMascota(Mascota mascota) { this.mascota = mascota; }
}