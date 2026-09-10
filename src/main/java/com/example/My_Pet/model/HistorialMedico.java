package com.example.My_Pet.model;

import jakarta.persistence.*;


// Esta clase representa la tabla historial_medico
// que tenemos creada en la base de datos.
@Entity
@Table(name = "historial_medico")
public class HistorialMedico {

    // ID principal del historial médico.
    // Se genera automáticamente cuando se registra un nuevo historial.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_historial")
    private Integer idHistorial;


    // Diagnóstico que presenta o presentó la mascota.
    @Column(length = 100)
    private String diagnosticos;


    // Antecedentes médicos que tenga la mascota.
    @Column(name = "antecedentes_medicos", length = 200)
    private String antecedentesMedicos;


    // Información sobre las alergias de la mascota.
    @Column(length = 100)
    private String alergias;


    // Tratamientos médicos realizados o que esté recibiendo
    // actualmente la mascota.
    @Column(length = 300)
    private String tratamientos;


    // Relación Muchos a Uno.
    // Un historial médico pertenece a una mascota,
    // pero una mascota puede tener varios historiales médicos.
    @ManyToOne
    @JoinColumn(
            name = "id_mascota",
            referencedColumnName = "id_mascota",
            nullable = false
    )
    private Mascota mascota;


    // ==========================================
    // CONSTRUCTOR VACÍO
    // ==========================================

    // Se necesita para que JPA pueda crear los objetos.
    public HistorialMedico() {
    }


    // ==========================================
    // CONSTRUCTOR COMPLETO
    // ==========================================

    // Permite crear un historial utilizando todos sus datos.
    public HistorialMedico(
            Integer idHistorial,
            String diagnosticos,
            String antecedentesMedicos,
            String alergias,
            String tratamientos,
            Mascota mascota) {

        this.idHistorial = idHistorial;
        this.diagnosticos = diagnosticos;
        this.antecedentesMedicos = antecedentesMedicos;
        this.alergias = alergias;
        this.tratamientos = tratamientos;
        this.mascota = mascota;
    }


    // ==========================================
    // GETTERS Y SETTERS
    // ==========================================

    // Permite obtener el ID del historial.
    public Integer getIdHistorial() {
        return idHistorial;
    }

    // Permite modificar el ID del historial.
    public void setIdHistorial(Integer idHistorial) {
        this.idHistorial = idHistorial;
    }


    // Permite obtener los diagnósticos.
    public String getDiagnosticos() {
        return diagnosticos;
    }

    // Permite modificar los diagnósticos.
    public void setDiagnosticos(String diagnosticos) {
        this.diagnosticos = diagnosticos;
    }


    // Permite obtener los antecedentes médicos.
    public String getAntecedentesMedicos() {
        return antecedentesMedicos;
    }

    // Permite modificar los antecedentes médicos.
    public void setAntecedentesMedicos(String antecedentesMedicos) {
        this.antecedentesMedicos = antecedentesMedicos;
    }


    // Permite obtener las alergias.
    public String getAlergias() {
        return alergias;
    }

    // Permite modificar las alergias.
    public void setAlergias(String alergias) {
        this.alergias = alergias;
    }


    // Permite obtener los tratamientos.
    public String getTratamientos() {
        return tratamientos;
    }

    // Permite modificar los tratamientos.
    public void setTratamientos(String tratamientos) {
        this.tratamientos = tratamientos;
    }


    // Permite obtener la mascota relacionada
    // con este historial médico.
    public Mascota getMascota() {
        return mascota;
    }

    // Permite modificar la mascota relacionada.
    public void setMascota(Mascota mascota) {
        this.mascota = mascota;
    }
}