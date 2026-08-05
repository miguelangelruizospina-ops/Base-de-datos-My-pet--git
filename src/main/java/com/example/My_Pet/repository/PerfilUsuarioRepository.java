package com.example.My_Pet.repository;

import com.example.My_Pet.model.PerfilUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerfilUsuarioRepository extends JpaRepository<PerfilUsuario, Integer> {
    // Aquí ya tenemos listos los métodos para guardar, listar y borrar perfiles
}