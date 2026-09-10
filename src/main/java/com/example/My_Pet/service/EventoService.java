package com.example.My_Pet.service;

import com.example.My_Pet.model.Evento;
import com.example.My_Pet.model.Mascota;
import com.example.My_Pet.repository.EventoRepository;
import com.example.My_Pet.repository.MascotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EventoService {

    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private MascotaRepository mascotaRepository;

    // Lista todos los eventos
    public List<Evento> obtenerTodos() {
        return eventoRepository.findAll();
    }

    // Busca un evento por su ID
    public Evento obtenerPorId(Integer id) {
        return eventoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(
                        "El evento con ID " + id + " no existe."));
    }

    // Lista los eventos de una mascota
    public List<Evento> obtenerPorMascota(Integer idMascota) {
        return eventoRepository.findByMascotaIdMascota(idMascota);
    }

    // Guarda un nuevo evento
    public Evento guardar(Evento evento) {

        // Verifica que la mascota sea válida
        if (evento.getMascota() == null ||
                evento.getMascota().getIdMascota() <= 0) {

            throw new IllegalArgumentException(
                    "El evento debe estar asociado a una mascota válida.");
        }

        Integer idMascota = evento.getMascota().getIdMascota();

        // Verifica que la mascota exista
        Mascota mascotaExistente = mascotaRepository.findById(idMascota)
                .orElseThrow(() -> new IllegalArgumentException(
                        "La mascota con ID " + idMascota + " no existe."));

        evento.setMascota(mascotaExistente);

        // Coloca la fecha cuando se crea un evento nuevo
        if (evento.getIdEvento() == null) {
            evento.setFecha(LocalDateTime.now());
        }

        return eventoRepository.save(evento);
    }

    // Actualiza un evento existente
    public Evento actualizar(Integer id, Evento evento) {

        // Busca el evento que se quiere actualizar
        Evento eventoExistente = eventoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(
                        "El evento con ID " + id + " no existe."));

        // Verifica que la mascota sea válida
        if (evento.getMascota() == null ||
                evento.getMascota().getIdMascota() <= 0) {

            throw new IllegalArgumentException(
                    "El evento debe estar asociado a una mascota válida.");
        }

        Integer idMascota = evento.getMascota().getIdMascota();

        // Verifica que la mascota exista
        Mascota mascotaExistente = mascotaRepository.findById(idMascota)
                .orElseThrow(() -> new IllegalArgumentException(
                        "La mascota con ID " + idMascota + " no existe."));

        eventoExistente.setTipoEvento(evento.getTipoEvento());
        eventoExistente.setDescripcion(evento.getDescripcion());
        eventoExistente.setMascota(mascotaExistente);

        return eventoRepository.save(eventoExistente);
    }

    // Elimina un evento
    public void eliminar(Integer id) {

        // Verifica que el evento exista antes de eliminarlo
        if (!eventoRepository.existsById(id)) {
            throw new IllegalArgumentException(
                    "El evento con ID " + id + " no existe.");
        }

        eventoRepository.deleteById(id);
    }
}