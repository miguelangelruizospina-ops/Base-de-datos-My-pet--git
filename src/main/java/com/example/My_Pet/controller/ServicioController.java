package com.example.My_Pet.controller;

import com.example.My_Pet.model.Servicio;
import com.example.My_Pet.service.ServicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/servicios")
public class ServicioController {

    @Autowired
    private ServicioService servicioService;


    // GET - Listar todos los servicios
    @GetMapping("/listar")
    public List<Servicio> listarTodo() {

        return servicioService.obtenerTodos();
    }


    // GET - Listar servicios por tipo
    @GetMapping("/tipo/{tipo}")
    public List<Servicio> listarPorTipo(
            @PathVariable String tipo) {

        return servicioService.obtenerPorTipo(tipo);
    }


    // GET - Buscar un servicio por ID
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(
            @PathVariable Integer id) {

        try {

            Servicio servicio =
                    servicioService.obtenerPorId(id);

            return ResponseEntity.ok(servicio);

        } catch (IllegalArgumentException e) {

            return ResponseEntity.badRequest()
                    .body(e.getMessage());
        }
    }


    // POST - Registrar un nuevo servicio
    @PostMapping("/guardar")
    public ResponseEntity<?> crearServicio(
            @RequestBody Servicio servicio) {

        try {

            Servicio nuevoServicio =
                    servicioService.guardar(servicio);

            return ResponseEntity.ok(nuevoServicio);

        } catch (IllegalArgumentException e) {

            return ResponseEntity.badRequest()
                    .body(e.getMessage());
        }
    }


    // PUT - Actualizar un servicio
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarServicio(
            @PathVariable Integer id,
            @RequestBody Servicio servicio) {

        try {

            Servicio servicioActualizado =
                    servicioService.actualizar(
                            id,
                            servicio
                    );

            return ResponseEntity.ok(
                    servicioActualizado
            );

        } catch (IllegalArgumentException e) {

            return ResponseEntity.badRequest()
                    .body(e.getMessage());
        }
    }


    // DELETE - Eliminar un servicio
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarServicio(
            @PathVariable Integer id) {

        try {

            servicioService.eliminar(id);

            return ResponseEntity.ok(
                    "Servicio eliminado correctamente."
            );

        } catch (IllegalArgumentException e) {

            return ResponseEntity.badRequest()
                    .body(e.getMessage());
        }
    }
}