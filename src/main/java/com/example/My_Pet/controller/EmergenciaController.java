package com.example.My_Pet.controller;

import com.example.My_Pet.model.Emergencia;
import com.example.My_Pet.service.EmergenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Controlador de emergencias
@RestController
@RequestMapping("/api/emergencias")
public class EmergenciaController {

    // Conecta con el servicio
    @Autowired
    private EmergenciaService emergenciaService;

    // Lista todas las emergencias
    @GetMapping("/listar")
    public List<Emergencia> listarTodo() {
        return emergenciaService.obtenerTodas();
    }

    // Lista las emergencias de un usuario
    @GetMapping("/usuario/{idUsuario}")
    public List<Emergencia> listarPorUsuario(
            @PathVariable Integer idUsuario) {

        return emergenciaService.obtenerPorUsuario(idUsuario);
    }

    // Guarda una emergencia
    @PostMapping("/guardar")
    public ResponseEntity<?> crearEmergencia(
            @RequestBody Emergencia emergencia) {

        try {

            Emergencia nuevaEmergencia =
                    emergenciaService.guardar(emergencia);

            return ResponseEntity.ok(nuevaEmergencia);

        } catch (IllegalArgumentException e) {

            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }

    // Actualiza una emergencia
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> actualizarEmergencia(
            @PathVariable Integer id,
            @RequestBody Emergencia emergencia) {

        try {

            emergencia.setIdEmergencia(id);

            Emergencia emergenciaActualizada =
                    emergenciaService.guardar(emergencia);

            return ResponseEntity.ok(emergenciaActualizada);

        } catch (IllegalArgumentException e) {

            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }

    // Elimina una emergencia
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarEmergencia(
            @PathVariable Integer id) {

        try {

            emergenciaService.eliminar(id);

            return ResponseEntity.ok(
                    "Emergencia eliminada correctamente."
            );

        } catch (Exception e) {

            return ResponseEntity
                    .badRequest()
                    .body(
                            "No fue posible eliminar la emergencia."
                    );
        }
    }
}