package com.example.My_Pet.controller;

import com.example.My_Pet.model.PerfilUsuario;
import com.example.My_Pet.service.PerfilUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/perfiles")
@CrossOrigin(origins = "*")
public class PerfilUsuarioController {

    @Autowired
    private PerfilUsuarioService perfilUsuarioService;

    // GET - LISTAR TODOS LOS PERFILES
    @GetMapping("/listar")
    public List<PerfilUsuario> listarPerfiles() {
        return perfilUsuarioService.obtenerTodosLosPerfiles();
    }

    // GET - BUSCAR PERFIL POR ID
    @GetMapping("/{id}")
    public PerfilUsuario obtenerPorId(@PathVariable int id) {
        return perfilUsuarioService.obtenerPerfilPorId(id);
    }

    // POST - CREAR PERFIL
    @PostMapping("/crear")
    public PerfilUsuario crearPerfil(
            @RequestBody PerfilUsuario perfilUsuario) {

        return perfilUsuarioService.guardarPerfil(perfilUsuario);
    }

    // PUT - ACTUALIZAR PERFIL
    @PutMapping("/actualizar/{id}")
    public PerfilUsuario actualizarPerfil(
            @PathVariable int id,
            @RequestBody PerfilUsuario perfilUsuario) {

        return perfilUsuarioService.actualizarPerfil(id, perfilUsuario);
    }

    // DELETE - ELIMINAR PERFIL
    @DeleteMapping("/eliminar/{id}")
    public String eliminarPerfil(@PathVariable int id) {

        perfilUsuarioService.eliminarPerfil(id);

        return "Perfil de usuario eliminado correctamente";
    }
}