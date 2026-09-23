package com.example.My_Pet.controller;

import com.example.My_Pet.model.HistorialMedico;
import com.example.My_Pet.service.HistorialMedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/historiales-medicos")
@CrossOrigin(origins = "*")
public class HistorialMedicoController {

    // Conectamos el Controller con el Service
    @Autowired
    private HistorialMedicoService historialMedicoService;


    // GET - LISTAR TODOS LOS HISTORIALES
    @GetMapping("/listar")
    public List<HistorialMedico> listarTodo() {

        // Obtenemos todos los historiales
        return historialMedicoService.obtenerTodos();
    }


    // GET - BUSCAR HISTORIALES POR MASCOTA
    @GetMapping("/mascota/{idMascota}")
    public ResponseEntity<List<HistorialMedico>> listarPorMascota(
            @PathVariable Integer idMascota) {

        // Buscamos los historiales de la mascota
        List<HistorialMedico> historial =
                historialMedicoService.obtenerPorMascota(idMascota);

        return ResponseEntity.ok(historial);
    }


    // POST - CREAR HISTORIAL MÉDICO
    @PostMapping("/guardar")
    public ResponseEntity<?> crearHistorial(
            @RequestBody HistorialMedico historial) {

        try {

            // Guardamos el nuevo historial
            HistorialMedico nuevoHistorial =
                    historialMedicoService.guardar(historial);

            return ResponseEntity.ok(nuevoHistorial);

        } catch (IllegalArgumentException e) {

            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }


    // PUT - ACTUALIZAR HISTORIAL MÉDICO
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> actualizarHistorial(
            @PathVariable Integer id,
            @RequestBody HistorialMedico datos) {

        try {

            // Actualizamos el historial
            HistorialMedico historialActualizado =
                    historialMedicoService.actualizar(id, datos);

            return ResponseEntity.ok(historialActualizado);

        } catch (IllegalArgumentException e) {

            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());

        } catch (RuntimeException e) {

            return ResponseEntity
                    .notFound()
                    .build();
        }
    }


    // DELETE - ELIMINAR HISTORIAL MÉDICO
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarHistorial(
            @PathVariable Integer id) {

        try {

            // Eliminamos el historial
            historialMedicoService.eliminar(id);

            return ResponseEntity.ok(
                    "Historial médico eliminado correctamente"
            );

        } catch (IllegalArgumentException e) {

            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());

        } catch (RuntimeException e) {

            return ResponseEntity
                    .notFound()
                    .build();
        }
    }
}