package com.example.My_Pet.controller;

import com.example.My_Pet.model.Evento;
import com.example.My_Pet.service.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/eventos")
public class EventoController {

    @Autowired
    private EventoService eventoService;

    // Listar todos los eventos
    @GetMapping("/listar")
    public List<Evento> listarTodo() {

        return eventoService.obtenerTodos();
    }

    // Listar eventos por mascota
    @GetMapping("/mascota/{idMascota}")
    public ResponseEntity<List<Evento>> listarPorMascota(
            @PathVariable Integer idMascota) {

        List<Evento> eventos = eventoService.obtenerPorMascota(idMascota);

        return ResponseEntity.ok(eventos);
    }

    // Crear un nuevo evento
    @PostMapping("/guardar")
    public ResponseEntity<?> crearEvento(@RequestBody Evento evento) {

        try {

            Evento nuevoEvento = eventoService.guardar(evento);

            return ResponseEntity.ok(nuevoEvento);

        } catch (IllegalArgumentException e) {

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Actualizar un evento
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> actualizarEvento(
            @PathVariable Integer id,
            @RequestBody Evento evento) {

        try {

            Evento eventoActualizado = eventoService.actualizar(id, evento);

            return ResponseEntity.ok(eventoActualizado);

        } catch (IllegalArgumentException e) {

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Eliminar un evento
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarEvento(@PathVariable Integer id) {

        try {

            eventoService.eliminar(id);

            return ResponseEntity.ok("Evento eliminado correctamente");

        } catch (IllegalArgumentException e) {

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}