package com.example.My_Pet.controller;

import com.example.My_Pet.model.Recordatorio;
import com.example.My_Pet.service.RecordatorioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recordatorios")
public class RecordatorioController {

    @Autowired
    private RecordatorioService recordatorioService;

    // GET - Listar todos
    @GetMapping("/listar")
    public List<Recordatorio> listarTodo() {

        return recordatorioService.obtenerTodos();
    }

    // GET - Listar por usuario
    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<Recordatorio>> listarPorUsuario(
            @PathVariable Integer idUsuario) {

        List<Recordatorio> recordatorios =
                recordatorioService.obtenerPorUsuario(idUsuario);

        return ResponseEntity.ok(recordatorios);
    }

    // POST - Crear
    @PostMapping("/guardar")
    public ResponseEntity<?> crearRecordatorio(
            @RequestBody Recordatorio recordatorio) {

        try {

            Recordatorio nuevoRecordatorio =
                    recordatorioService.guardar(recordatorio);

            return ResponseEntity.ok(nuevoRecordatorio);

        } catch (IllegalArgumentException e) {

            return ResponseEntity.badRequest()
                    .body(e.getMessage());
        }
    }

    // PUT - Actualizar
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarRecordatorio(
            @PathVariable Integer id,
            @RequestBody Recordatorio recordatorio) {

        try {

            Recordatorio recordatorioActualizado =
                    recordatorioService.actualizar(id, recordatorio);

            return ResponseEntity.ok(recordatorioActualizado);

        } catch (IllegalArgumentException e) {

            return ResponseEntity.badRequest()
                    .body(e.getMessage());
        }
    }

    // DELETE - Eliminar
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarRecordatorio(
            @PathVariable Integer id) {

        try {

            recordatorioService.eliminar(id);

            return ResponseEntity.ok(
                    "Recordatorio eliminado correctamente."
            );

        } catch (IllegalArgumentException e) {

            return ResponseEntity.badRequest()
                    .body(e.getMessage());
        }
    }
}