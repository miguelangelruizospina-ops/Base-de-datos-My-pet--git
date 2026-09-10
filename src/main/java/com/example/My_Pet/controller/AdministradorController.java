package com.example.My_Pet.controller;

import com.example.My_Pet.model.Administrador;
import com.example.My_Pet.service.AdministradorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/administradores")
@CrossOrigin(origins = "*")
public class AdministradorController {

    // Conectamos el Controller con el Service
    @Autowired
    private AdministradorService administradorService;


    // GET - LISTAR TODOS LOS ADMINISTRADORES
    @GetMapping("/listar")
    public List<Administrador> listarAdministradores() {

        return administradorService.obtenerTodos();
    }


    // GET - BUSCAR ADMINISTRADOR POR ID
    @GetMapping("/{id}")
    public ResponseEntity<Administrador> obtenerPorId(
            @PathVariable Integer id) {

        try {

            return ResponseEntity.ok(
                    administradorService.obtenerPorId(id)
            );

        } catch (RuntimeException e) {

            return ResponseEntity.notFound().build();
        }
    }


    // GET - BUSCAR ADMINISTRADOR POR USUARIO
    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<Administrador> obtenerPorUsuario(
            @PathVariable Integer idUsuario) {

        try {

            return ResponseEntity.ok(
                    administradorService.obtenerPorUsuario(idUsuario)
            );

        } catch (RuntimeException e) {

            return ResponseEntity.notFound().build();
        }
    }


    // POST - CREAR ADMINISTRADOR
    @PostMapping("/crear")
    public ResponseEntity<?> crearAdministrador(
            @RequestBody Administrador administrador) {

        try {

            Administrador nuevoAdministrador =
                    administradorService.guardar(administrador);

            return ResponseEntity.ok(nuevoAdministrador);

        } catch (IllegalArgumentException e) {

            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }


    // PUT - ACTUALIZAR ADMINISTRADOR
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> actualizarAdministrador(
            @PathVariable Integer id,
            @RequestBody Administrador administrador) {

        try {

            Administrador administradorActualizado =
                    administradorService.actualizar(
                            id,
                            administrador
                    );

            return ResponseEntity.ok(administradorActualizado);

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


    // DELETE - ELIMINAR ADMINISTRADOR
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarAdministrador(
            @PathVariable Integer id) {

        try {

            administradorService.eliminar(id);

            return ResponseEntity.ok(
                    "Administrador eliminado correctamente"
            );

        } catch (RuntimeException e) {

            return ResponseEntity
                    .notFound()
                    .build();
        }
    }
}