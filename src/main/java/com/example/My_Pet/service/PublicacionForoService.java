package com.example.My_Pet.service;

import com.example.My_Pet.model.PublicacionForo;
import com.example.My_Pet.model.Usuario;
import com.example.My_Pet.repository.PublicacionForoRepository;
import com.example.My_Pet.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

// @Service le dice al contenedor de Spring que aquí reside la lógica transaccional y operativa
@Service
public class PublicacionForoService {

    @Autowired
    private PublicacionForoRepository forumRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Obtiene la lista completa de todas las publicaciones del foro
    public List<PublicacionForo> obtenerTodas() {
        return forumRepository.findAll();
    }

    // Obtiene todas las publicaciones de un único autor
    public List<PublicacionForo> obtenerPorUsuario(Integer idUsuario) {
        return forumRepository.findByUsuarioIdUsuario(idUsuario);
    }

    // Registra una nueva publicación controlando la asignación del tiempo y validación del usuario
    public PublicacionForo guardar(PublicacionForo publicacion) {
        
        // CORRECCIÓN DE PRIMITIVO: Evaluamos si el usuario es nulo o si su ID de tipo int es inválido (<= 0)
        if (publicacion.getUsuario() == null || publicacion.getUsuario().getIdUsuario() <= 0) {
            throw new IllegalArgumentException("La publicación debe estar asociada a un usuario válido.");
        }

        Integer idUsuario = publicacion.getUsuario().getIdUsuario();
        
        // Consulta en la base de datos si el usuario que intenta publicar realmente existe
        Usuario usuarioExistente = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new IllegalArgumentException("El usuario con ID " + idUsuario + " no existe."));

        // Vincula el usuario verificado a la publicación
        publicacion.setUsuario(usuarioExistente);

        // Si es una publicación nueva (id es nulo), le asignamos la fecha y hora actual antes de guardar
        if (publicacion.getIdPublicacion() == null) {
            publicacion.setFecha(LocalDateTime.now());
        }

        // Almacena el registro en MySQL y lo devuelve con su clave autogenerada
        return forumRepository.save(publicacion);
    }

    // Remueve una publicación utilizando su ID principal
    public void eliminar(Integer id) {
        forumRepository.deleteById(id);
    }
}