package com.example.My_Pet.repository;

import com.example.My_Pet.model.Mascota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MascotaRepository extends JpaRepository<Mascota, Integer> {
    // Listo para conectarse con la tabla mascota
}