package com.example.My_Pet.service;

import com.example.My_Pet.model.PerfilUsuario;
import com.example.My_Pet.model.Usuario;
import com.example.My_Pet.repository.PerfilUsuarioRepository;
import com.example.My_Pet.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PerfilUsuarioService {

    @Autowired
    private PerfilUsuarioRepository perfilUsuarioRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    // CREAR PERFIL
    @Transactional
    public PerfilUsuario guardarPerfil(PerfilUsuario perfilUsuario) {

        if (perfilUsuario.getUsuario() == null) {
            throw new RuntimeException("El usuario es obligatorio");
        }

        int idUsuario = perfilUsuario.getUsuario().getIdUsuario();

        // Buscar el usuario existente
        Usuario usuarioBD = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException(
                        "El usuario con ID " + idUsuario + " no existe"
                ));

        // Verificar que el usuario no tenga ya un perfil
        if (perfilUsuarioRepository.existsById(idUsuario)) {
            throw new RuntimeException(
                    "El usuario con ID " + idUsuario + " ya tiene un perfil"
            );
        }

        // Asociar el usuario existente
        perfilUsuario.setUsuario(usuarioBD);

        // @MapsId utilizará el mismo ID del usuario
        perfilUsuario.setIdUsuario(idUsuario);

        return perfilUsuarioRepository.save(perfilUsuario);
    }

    // LISTAR TODOS LOS PERFILES
    public List<PerfilUsuario> obtenerTodosLosPerfiles() {
        return perfilUsuarioRepository.findAll();
    }

    // BUSCAR PERFIL POR ID
    public PerfilUsuario obtenerPerfilPorId(int id) {

        return perfilUsuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Perfil de usuario no encontrado con ID: " + id
                ));
    }

    // ACTUALIZAR PERFIL
    @Transactional
    public PerfilUsuario actualizarPerfil(
            int id,
            PerfilUsuario datosPerfil) {

        PerfilUsuario perfilExistente = obtenerPerfilPorId(id);

        perfilExistente.setNombreUsuario(datosPerfil.getNombreUsuario());
        perfilExistente.setFotoPerfil(datosPerfil.getFotoPerfil());
        perfilExistente.setBiografia(datosPerfil.getBiografia());
        perfilExistente.setTelefono(datosPerfil.getTelefono());
        perfilExistente.setCiudad(datosPerfil.getCiudad());
        perfilExistente.setGenero(datosPerfil.getGenero());
        perfilExistente.setFechaNacimiento(datosPerfil.getFechaNacimiento());

        return perfilUsuarioRepository.save(perfilExistente);
    }

    // ELIMINAR PERFIL
    @Transactional
    public void eliminarPerfil(int id) {

        PerfilUsuario perfilExistente = obtenerPerfilPorId(id);

        perfilUsuarioRepository.delete(perfilExistente);
    }
}