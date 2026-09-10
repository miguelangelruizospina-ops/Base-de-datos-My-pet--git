package com.example.My_Pet.controller;

import com.example.My_Pet.model.PublicacionForo;
import com.example.My_Pet.service.PublicacionForoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/publicaciones-foro")
public class PublicacionForoController {

    @Autowired
    private PublicacionForoService forumService;

    // Listar todas las publicaciones
    @GetMapping("/listar")
    public List<PublicacionForo> listarTodo() {

        return forumService.obtenerTodas();
    }

    // Listar publicaciones de un usuario
    @GetMapping("/usuario/{idUsuario}")
    public List<PublicacionForo> listarPorUsuario(
            @PathVariable Integer idUsuario) {

        return forumService.obtenerPorUsuario(idUsuario);
    }

    // Crear una publicación
    @PostMapping("/guardar")
    public ResponseEntity<?> crearPublicacion(
            @RequestBody PublicacionForo publicacion) {

        try {

            PublicacionForo nuevaPublicacion =
                    forumService.guardar(publicacion);

            return ResponseEntity.ok(nuevaPublicacion);

        } catch (IllegalArgumentException e) {

            return ResponseEntity.badRequest()
                    .body(e.getMessage());
        }
    }

    // Actualizar una publicación
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarPublicacion(
            @PathVariable Integer id,
            @RequestBody PublicacionForo publicacion) {

        try {

            PublicacionForo actualizada =
                    forumService.actualizar(id, publicacion);

            return ResponseEntity.ok(actualizada);

        } catch (IllegalArgumentException e) {

            return ResponseEntity.badRequest()
                    .body(e.getMessage());
        }
    }

    // Eliminar una publicación
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarPublicacion(
            @PathVariable Integer id) {

        try {

            forumService.eliminar(id);

            return ResponseEntity.ok(
                    "Publicación eliminada correctamente."
            );

        } catch (IllegalArgumentException e) {

            return ResponseEntity.badRequest()
                    .body(e.getMessage());
        }
    }
}