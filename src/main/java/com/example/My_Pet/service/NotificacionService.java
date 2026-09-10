package com.example.My_Pet.service;

import com.example.My_Pet.model.Notificacion;
import com.example.My_Pet.repository.NotificacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificacionService {

    @Autowired
    private NotificacionRepository notificacionRepository;


    // Obtener todas las notificaciones
    public List<Notificacion> obtenerTodas() {

        return notificacionRepository.findAll();
    }


    // Obtener las notificaciones de un usuario
    public List<Notificacion> obtenerPorUsuario(Integer idUsuario) {

        return notificacionRepository.findByUsuarioIdUsuario(idUsuario);
    }


    // Eliminar una notificación
    public void eliminar(Integer id) {

        // Verificar que la notificación exista
        if (!notificacionRepository.existsById(id)) {

            throw new IllegalArgumentException(
                    "La notificación con ID " + id + " no existe."
            );
        }

        // Eliminar la notificación
        notificacionRepository.deleteById(id);
    }
}