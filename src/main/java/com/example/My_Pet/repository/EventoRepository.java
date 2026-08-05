package com.example.My_Pet.repository;

import com.example.My_Pet.model.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Integer> {
    // Recupera todos los eventos de una mascota específica
    List<Evento> findByMascotaIdMascota(Integer idMascota);
}