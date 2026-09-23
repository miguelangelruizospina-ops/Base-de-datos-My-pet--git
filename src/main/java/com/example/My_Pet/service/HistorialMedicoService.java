package com.example.My_Pet.service;

import com.example.My_Pet.model.HistorialMedico;
import com.example.My_Pet.model.Mascota;
import com.example.My_Pet.repository.HistorialMedicoRepository;
import com.example.My_Pet.repository.MascotaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class HistorialMedicoService {

    @Autowired
    private HistorialMedicoRepository historialMedicoRepository;

    @Autowired
    private MascotaRepository mascotaRepository;


    // LISTAR TODOS LOS HISTORIALES
    public List<HistorialMedico> obtenerTodos() {

        return historialMedicoRepository.findAll();
    }


    // BUSCAR HISTORIALES POR MASCOTA
    public List<HistorialMedico> obtenerPorMascota(Integer idMascota) {

        return historialMedicoRepository
                .findByMascotaIdMascota(idMascota);
    }


    // CREAR HISTORIAL MÉDICO
    @Transactional
    public HistorialMedico guardar(HistorialMedico historial) {

        // Verificamos que el historial tenga una mascota asociada
        if (historial.getMascota() == null ||
                historial.getMascota().getIdMascota() == null) {

            throw new IllegalArgumentException(
                    "No se puede registrar un historial sin asociarlo a una mascota."
            );
        }

        // Obtenemos el ID de la mascota
        Integer idMascota =
                historial.getMascota().getIdMascota();

        // Buscamos la mascota existente
        Mascota mascotaExistente =
                mascotaRepository.findById(idMascota)
                        .orElseThrow(() ->
                                new IllegalArgumentException(
                                        "La mascota con ID " +
                                        idMascota +
                                        " no existe."
                                )
                        );

        // Asociamos la mascota encontrada
        historial.setMascota(mascotaExistente);

        // Guardamos el nuevo historial
        return historialMedicoRepository.save(historial);
    }


    // ACTUALIZAR HISTORIAL MÉDICO
    @Transactional
    public HistorialMedico actualizar(
            Integer id,
            HistorialMedico datos) {

        // Buscamos el historial que vamos a actualizar
        HistorialMedico historialExistente =
                historialMedicoRepository.findById(id)
                        .orElseThrow(() ->
                                new IllegalArgumentException(
                                        "El historial médico con ID " +
                                        id +
                                        " no existe."
                                )
                        );

        // Verificamos que tenga una mascota asociada
        if (datos.getMascota() == null ||
                datos.getMascota().getIdMascota() == null) {

            throw new IllegalArgumentException(
                    "La mascota es obligatoria."
            );
        }

        // Obtenemos el ID de la mascota
        Integer idMascota =
                datos.getMascota().getIdMascota();

        // Buscamos la mascota existente
        Mascota mascotaExistente =
                mascotaRepository.findById(idMascota)
                        .orElseThrow(() ->
                                new IllegalArgumentException(
                                        "La mascota con ID " +
                                        idMascota +
                                        " no existe."
                                )
                        );

        // Actualizamos los datos del historial
        historialExistente.setDiagnosticos(
                datos.getDiagnosticos()
        );

        historialExistente.setAntecedentesMedicos(
                datos.getAntecedentesMedicos()
        );

        historialExistente.setAlergias(
                datos.getAlergias()
        );

        historialExistente.setTratamientos(
                datos.getTratamientos()
        );

        // Actualizamos la mascota asociada
        historialExistente.setMascota(
                mascotaExistente
        );

        // Guardamos los cambios
        return historialMedicoRepository.save(
                historialExistente
        );
    }


    // ELIMINAR HISTORIAL MÉDICO
    @Transactional
    public void eliminar(Integer id) {

        // Verificamos que el historial exista
        HistorialMedico historialExistente =
                historialMedicoRepository.findById(id)
                        .orElseThrow(() ->
                                new IllegalArgumentException(
                                        "El historial médico con ID " +
                                        id +
                                        " no existe."
                                )
                        );

        // Eliminamos el historial
        historialMedicoRepository.delete(
                historialExistente
        );
    }
}