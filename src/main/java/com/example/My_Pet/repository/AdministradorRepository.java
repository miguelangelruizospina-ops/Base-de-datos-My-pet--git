package com.example.My_Pet.repository;

import com.example.My_Pet.model.Administrador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// @Repository le dice a Spring que gestione este archivo como el acceso a datos
// JpaRepository<Administrador, Integer> le hereda todos los métodos básicos (findAll, save, deleteById)
@Repository
public interface AdministradorRepository extends JpaRepository<Administrador, Integer> {
    
    // Spring Boot lee el nombre del método y genera el SQL automáticamente:
    // SELECT * FROM administrador WHERE id_usuario = ?
    // El Optional sirve para manejar de forma segura si encuentra o no al administrador sin romper el sistema
    Optional<Administrador> findByUsuarioIdUsuario(Integer idUsuario);
}