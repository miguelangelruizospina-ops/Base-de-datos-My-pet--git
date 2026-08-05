package com.example.My_Pet.service;

import com.example.My_Pet.model.Evento;
import com.example.My_Pet.model.Mascota;
import com.example.My_Pet.repository.EventoRepository;
import com.example.My_Pet.repository.MascotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventoService {

    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private MascotaRepository mascotaRepository;

    public List<Evento> obtenerTodos() {
        return eventoRepository.findAll();
    }

    public List<Evento> obtenerPorMascota(Integer idMascota) {
        return eventoRepository.findByMascotaIdMascota(idMascota);
    }

    public Evento guardar(Evento evento) {
        if (evento.getMascota() == null || evento.getMascota().getIdMascota() == null) {
            throw new IllegalArgumentException("No se puede registrar un evento sin asociarlo a una mascota.");
        }

        Integer idMascota = evento.getMascota().getIdMascota();
        
        // Validación de existencia de la mascota
        Mascota mascotaExistente = mascotaRepository.findById(idMascota)
                .orElseThrow(() -> new IllegalArgumentException("La mascota con ID " + idMascota + " no existe."));

        evento.setMascota(mascotaExistente);
        return eventoRepository.save(evento);
    }

    public void eliminar(Integer id) {
        eventoRepository.deleteById(id);
    }
}