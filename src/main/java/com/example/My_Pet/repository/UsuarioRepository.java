package com.example.My_Pet.repository;

import com.example.My_Pet.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    // Buscar usuario por correo
    Optional<Usuario> findByCorreo(String correo);
}