package com.example.My_Pet.controller;

import com.example.My_Pet.model.Mascota;
import com.example.My_Pet.service.MascotaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mascotas")
@CrossOrigin(origins = "*")
public class MascotaController {

    @Autowired
    private MascotaService mascotaService;

    // GET - LISTAR TODAS LAS MASCOTAS
    @GetMapping("/listar")
    public List<Mascota> listarMascotas() {

        return mascotaService.obtenerTodasLasMascotas();
    }

    // GET - BUSCAR UNA MASCOTA POR ID
    @GetMapping("/{id}")
    public Mascota obtenerMascotaPorId(
            @PathVariable Integer id) {

        return mascotaService.obtenerMascotaPorId(id);
    }

    // POST - CREAR MASCOTA
    @PostMapping("/crear")
    public Mascota crearMascota(
            @RequestBody Mascota mascota) {

        return mascotaService.guardarMascota(mascota);
    }

    // PUT - ACTUALIZAR MASCOTA
    @PutMapping("/actualizar/{id}")
    public Mascota actualizarMascota(
            @PathVariable Integer id,
            @RequestBody Mascota mascota) {

        return mascotaService.actualizarMascota(id, mascota);
    }

    // DELETE - ELIMINAR MASCOTA
    @DeleteMapping("/eliminar/{id}")
    public String eliminarMascota(
            @PathVariable Integer id) {

        mascotaService.eliminarMascota(id);

        return "Mascota eliminada correctamente";
    }
}