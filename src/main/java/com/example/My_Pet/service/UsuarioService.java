package com.example.My_Pet.service;

import com.example.My_Pet.model.Usuario;
import com.example.My_Pet.repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

// Esta clase contiene la lógica relacionada con los usuarios
@Service
public class UsuarioService {

    // Conecto el servicio con el repositorio de usuarios
    @Autowired
    private UsuarioRepository usuarioRepository;

<<<<<<< HEAD
=======

>>>>>>> origin/master
    // POST: registrar un nuevo usuario
    public Usuario registrarUsuario(Usuario usuario) {

        // Guardo automáticamente la fecha y hora en que se registra
        usuario.setFechaCreacion(LocalDateTime.now());

        // Guardo el usuario en la base de datos
        return usuarioRepository.save(usuario);
    }

<<<<<<< HEAD
=======

>>>>>>> origin/master
    // GET: obtener todos los usuarios registrados
    public List<Usuario> obtenerTodosLosUsuarios() {

        // Consulto todos los usuarios de la base de datos
        return usuarioRepository.findAll();
    }

<<<<<<< HEAD
=======

>>>>>>> origin/master
    // GET: obtener un usuario por su ID
    public Usuario obtenerUsuarioPorId(int id) {

        // Busco el usuario utilizando su ID
        Optional<Usuario> usuario = usuarioRepository.findById(id);

        // Si el usuario existe, lo devuelvo
        if (usuario.isPresent()) {
            return usuario.get();
        }

        // Si no existe, muestro un mensaje indicando el problema
        throw new RuntimeException("Usuario no encontrado con ID: " + id);
    }

<<<<<<< HEAD
=======

>>>>>>> origin/master
    // PUT: actualizar únicamente el correo y la contraseña
    public Usuario actualizarUsuario(int id, Usuario datosUsuario) {

        // Busco primero el usuario que ya existe
        Usuario usuarioExistente = obtenerUsuarioPorId(id);

<<<<<<< HEAD
        // Actualizo los datos permitidos
        usuarioExistente.setCorreo(datosUsuario.getCorreo());
        usuarioExistente.setContrasena(datosUsuario.getContrasena());

        // Guardo los cambios
        return usuarioRepository.save(usuarioExistente);
    }

=======
        // Actualizo solamente los datos que permitimos modificar
        usuarioExistente.setCorreo(datosUsuario.getCorreo());
        usuarioExistente.setContrasena(datosUsuario.getContrasena());

        // Guardo los cambios sin modificar los demás datos
        return usuarioRepository.save(usuarioExistente);
    }


>>>>>>> origin/master
    // DELETE: eliminar un usuario por su ID
    public void eliminarUsuario(int id) {

        // Busco primero el usuario para comprobar que existe
        Usuario usuarioExistente = obtenerUsuarioPorId(id);

<<<<<<< HEAD
        // Elimino el usuario
        usuarioRepository.delete(usuarioExistente);
    }

    // POST: validar el inicio de sesión
    public Usuario login(String correo, String contrasena) {

        // Busco el usuario por su correo
        Optional<Usuario> usuario = usuarioRepository.findByCorreo(correo);

        // Compruebo que el usuario exista y que la contraseña coincida
        if (usuario.isPresent()
                && usuario.get().getContrasena().equals(contrasena)) {

            // Si los datos son correctos, devuelvo el usuario
            return usuario.get();
        }

        // Si los datos no coinciden, muestro un mensaje de error
        throw new RuntimeException("Correo o contraseña incorrectos");
    }
=======
        // Elimino el usuario.
        // MySQL se encarga de eliminar automáticamente las mascotas
        // relacionadas gracias a ON DELETE CASCADE.
        usuarioRepository.delete(usuarioExistente);
    }
>>>>>>> origin/master
}