package com.example.My_Pet.controller;

import com.example.My_Pet.model.PerfilMascota;
import com.example.My_Pet.service.PerfilMascotaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/perfiles-mascotas")
@CrossOrigin(origins = "*")
public class PerfilMascotaController {

    @Autowired
    private PerfilMascotaService perfilMascotaService;

    // GET - LISTAR TODOS LOS PERFILES
    @GetMapping("/listar")
    public List<PerfilMascota> listarPerfiles() {

        return perfilMascotaService.obtenerTodos();
    }

    // GET - BUSCAR PERFIL POR ID
    @GetMapping("/{id}")
    public ResponseEntity<PerfilMascota> obtenerPorId(
            @PathVariable Integer id) {

        try {
            return ResponseEntity.ok(
                    perfilMascotaService.obtenerPorId(id)
            );

        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // GET - BUSCAR PERFIL POR ID DE MASCOTA
    @GetMapping("/mascota/{idMascota}")
    public ResponseEntity<PerfilMascota> obtenerPorMascota(
            @PathVariable Integer idMascota) {

        try {
            return ResponseEntity.ok(
                    perfilMascotaService.obtenerPorMascota(idMascota)
            );

        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // POST - CREAR PERFIL
    @PostMapping("/guardar")
    public ResponseEntity<?> crearPerfil(
            @RequestBody PerfilMascota perfil) {

        try {

            PerfilMascota nuevoPerfil =
                    perfilMascotaService.guardar(perfil);

            return ResponseEntity.ok(nuevoPerfil);

        } catch (IllegalArgumentException e) {

            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }

    // PUT - ACTUALIZAR PERFIL
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> actualizarPerfil(
            @PathVariable Integer id,
            @RequestBody PerfilMascota perfil) {

        try {

            PerfilMascota perfilActualizado =
                    perfilMascotaService.actualizar(id, perfil);

            return ResponseEntity.ok(perfilActualizado);

        } catch (RuntimeException e) {

            return ResponseEntity
                    .notFound()
                    .build();
        }
    }

    // DELETE - ELIMINAR PERFIL
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarPerfil(
            @PathVariable Integer id) {

        try {

            perfilMascotaService.eliminar(id);

            return ResponseEntity.ok(
                    "Perfil de mascota eliminado correctamente"
            );

        } catch (RuntimeException e) {

            return ResponseEntity
                    .notFound()
                    .build();
        }
    }
}