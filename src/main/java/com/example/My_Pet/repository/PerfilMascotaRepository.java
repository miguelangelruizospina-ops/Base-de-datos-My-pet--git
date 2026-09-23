package com.example.My_Pet.repository;

import com.example.My_Pet.model.PerfilMascota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PerfilMascotaRepository extends JpaRepository<PerfilMascota, Integer> {

    // Permite buscar el perfil usando el ID de la mascota
    Optional<PerfilMascota> findByMascotaIdMascota(Integer idMascota);
}