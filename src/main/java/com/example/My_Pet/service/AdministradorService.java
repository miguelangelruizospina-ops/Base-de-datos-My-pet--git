package com.example.My_Pet.service;

import com.example.My_Pet.model.Administrador;
import com.example.My_Pet.model.Usuario;
import com.example.My_Pet.repository.AdministradorRepository;
import com.example.My_Pet.repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdministradorService {

    @Autowired
    private AdministradorRepository administradorRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;


    // LISTAR TODOS LOS ADMINISTRADORES
    public List<Administrador> obtenerTodos() {

        return administradorRepository.findAll();
    }


    // BUSCAR ADMINISTRADOR POR ID
    public Administrador obtenerPorId(Integer id) {

        return administradorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Administrador no encontrado con ID: " + id
                ));
    }


    // BUSCAR ADMINISTRADOR POR USUARIO
    public Administrador obtenerPorUsuario(Integer idUsuario) {

        return administradorRepository
                .findByUsuarioIdUsuario(idUsuario)
                .orElseThrow(() -> new RuntimeException(
                        "No existe un administrador asociado al usuario con ID: "
                                + idUsuario
                ));
    }


    // CREAR ADMINISTRADOR
    public Administrador guardar(Administrador administrador) {

        // Verificamos que exista un usuario asociado
        if (administrador.getUsuario() == null ||
                administrador.getUsuario().getIdUsuario() <= 0) {

            throw new IllegalArgumentException(
                    "El administrador debe estar ligado a un usuario válido."
            );
        }

        // Obtenemos el ID del usuario
        Integer idUsuario =
                administrador.getUsuario().getIdUsuario();

        // Buscamos el usuario en la base de datos
        Usuario usuarioExistente =
                usuarioRepository.findById(idUsuario)
                        .orElseThrow(() ->
                                new IllegalArgumentException(
                                        "El usuario con ID " +
                                        idUsuario +
                                        " no existe."
                                )
                        );

        // Verificamos que el usuario no tenga otro administrador
        if (administradorRepository
                .findByUsuarioIdUsuario(idUsuario)
                .isPresent()) {

            throw new IllegalArgumentException(
                    "El usuario con ID " + idUsuario +
                    " ya está registrado como administrador."
            );
        }

        // Asociamos el usuario existente
        administrador.setUsuario(usuarioExistente);

        // Guardamos el administrador
        return administradorRepository.save(administrador);
    }


    // ACTUALIZAR ADMINISTRADOR
    public Administrador actualizar(
            Integer id,
            Administrador datosAdministrador) {

        // Buscamos el administrador que vamos a actualizar
        Administrador administradorExistente =
                obtenerPorId(id);

        // Verificamos que los permisos hayan sido enviados
        if (datosAdministrador.getPermisos() == null ||
                datosAdministrador.getPermisos().trim().isEmpty()) {

            throw new IllegalArgumentException(
                    "Los permisos son obligatorios."
            );
        }

        // Actualizamos los permisos
        administradorExistente.setPermisos(
                datosAdministrador.getPermisos()
        );

        // Guardamos los cambios
        return administradorRepository.save(
                administradorExistente
        );
    }


    // ELIMINAR ADMINISTRADOR
    public void eliminar(Integer id) {

        // Buscamos el administrador que vamos a eliminar
        Administrador administradorExistente =
                obtenerPorId(id);

        // Eliminamos el administrador
        administradorRepository.delete(
                administradorExistente
        );
    }
}