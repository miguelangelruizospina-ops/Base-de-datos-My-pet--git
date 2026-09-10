package com.example.My_Pet.repository;

import com.example.My_Pet.model.Mascota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MascotaRepository extends JpaRepository<Mascota, Integer> {

    // Permite buscar todas las mascotas que pertenecen a un usuario
    List<Mascota> findByUsuarioIdUsuario(Integer idUsuario);
}