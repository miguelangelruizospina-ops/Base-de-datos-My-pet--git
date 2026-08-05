package com.example.My_Pet.service;

import com.example.My_Pet.model.PerfilUsuario;
import com.example.My_Pet.repository.PerfilUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PerfilUsuarioService {

    @Autowired
    private PerfilUsuarioRepository perfilUsuarioRepository;

    // Método para listar todos los perfiles de usuario
    public List<PerfilUsuario> obtenerTodosLosPerfiles() {
        return perfilUsuarioRepository.findAll();
    }

    // Método para buscar un perfil por el ID del usuario
    public Optional<PerfilUsuario> obtenerPerfilPorId(Integer idUsuario) {
        return perfilUsuarioRepository.findById(idUsuario);
    }

    // Método para guardar o actualizar un perfil de usuario
    public PerfilUsuario guardarPerfil(PerfilUsuario perfilUsuario) {
        return perfilUsuarioRepository.save(perfilUsuario);
    }

    // Método para eliminar un perfil de usuario
    public void eliminarPerfil(Integer idUsuario) {
        perfilUsuarioRepository.deleteById(idUsuario);
    }
}