package com.example.My_Pet.service;

import com.example.My_Pet.model.Usuario;
import com.example.My_Pet.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Lógica para guardar un nuevo usuario
    public Usuario registrarUsuario(Usuario usuario) {
        // Le asignamos la fecha de creación automáticamente antes de guardar
        usuario.setFechaCreacion(LocalDateTime.now());
        return usuarioRepository.save(usuario);
    }

    // Lógica para listar todos los usuarios registrados
    public List<Usuario> obtenerTodosLosUsuarios() {
        return usuarioRepository.findAll();
    }
}