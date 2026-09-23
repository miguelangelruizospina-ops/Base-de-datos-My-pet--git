package com.example.My_Pet.repository;

import com.example.My_Pet.model.HistorialMedico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistorialMedicoRepository extends JpaRepository<HistorialMedico, Integer> {
    // Busca todos los registros médicos pertenecientes a una mascota
    List<HistorialMedico> findByMascotaIdMascota(Integer idMascota);
}