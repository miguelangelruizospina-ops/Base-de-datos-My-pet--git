package com.example.My_Pet.service;

import com.example.My_Pet.model.HistorialMedico;
import com.example.My_Pet.model.Mascota;
import com.example.My_Pet.repository.HistorialMedicoRepository;
import com.example.My_Pet.repository.MascotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistorialMedicoService {

    @Autowired
    private HistorialMedicoRepository historialMedicoRepository;

    @Autowired
    private MascotaRepository mascotaRepository;

    public List<HistorialMedico> obtenerTodos() {
        return historialMedicoRepository.findAll();
    }

    public List<HistorialMedico> obtenerPorMascota(Integer idMascota) {
        return historialMedicoRepository.findByMascotaIdMascota(idMascota);
    }

    public HistorialMedico guardar(HistorialMedico historial) {
        if (historial.getMascota() == null || historial.getMascota().getIdMascota() == null) {
            throw new IllegalArgumentException("No se puede registrar un historial sin asociarlo a una mascota.");
        }

        Integer idMascota = historial.getMascota().getIdMascota();
        
        // Coherencia: Validar la existencia de la mascota antes de guardar
        Mascota mascotaExistente = mascotaRepository.findById(idMascota)
                .orElseThrow(() -> new IllegalArgumentException("La mascota con ID " + idMascota + " no existe."));

        historial.setMascota(mascotaExistente);
        return historialMedicoRepository.save(historial);
    }

    public void eliminar(Integer id) {
        historialMedicoRepository.deleteById(id);
    }
}