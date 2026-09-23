package com.example.My_Pet.controller;

import com.example.My_Pet.model.Notificacion;
import com.example.My_Pet.service.NotificacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notificaciones")
public class NotificacionController {

    @Autowired
    private NotificacionService notificacionService;


    // GET - Listar todas
    @GetMapping("/listar")
    public List<Notificacion> listarTodo() {

        return notificacionService.obtenerTodas();
    }


    // GET - Listar por usuario
    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<Notificacion>> listarPorUsuario(
            @PathVariable Integer idUsuario) {

        List<Notificacion> notificaciones =
                notificacionService.obtenerPorUsuario(idUsuario);

        return ResponseEntity.ok(notificaciones);
    }


    // DELETE - Eliminar
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarNotificacion(
            @PathVariable Integer id) {

        try {

            notificacionService.eliminar(id);

            return ResponseEntity.ok(
                    "Notificación eliminada correctamente."
            );

        } catch (IllegalArgumentException e) {

            return ResponseEntity.badRequest()
                    .body(e.getMessage());
        }
    }
}