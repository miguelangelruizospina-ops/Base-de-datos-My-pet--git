package com.example.My_Pet.controller;

import com.example.My_Pet.model.Usuario;
import com.example.My_Pet.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    // Registrar usuario
    @PostMapping("/registro")
    public Usuario registrar(@RequestBody Usuario usuario) {
        return usuarioService.registrarUsuario(usuario);
    }

    // Listar todos los usuarios
    @GetMapping("/listar")
    public List<Usuario> listar() {
        return usuarioService.obtenerTodosLosUsuarios();
    }

    // Buscar usuario por ID
    @GetMapping("/{id}")
    public Usuario obtenerPorId(@PathVariable int id) {
        return usuarioService.obtenerUsuarioPorId(id);
    }

    // Actualizar usuario
    @PutMapping("/actualizar/{id}")
    public Usuario actualizar(
            @PathVariable int id,
            @RequestBody Usuario usuario) {

        return usuarioService.actualizarUsuario(id, usuario);
    }

    // Eliminar usuario
    @DeleteMapping("/eliminar/{id}")
    public String eliminar(@PathVariable int id) {

        usuarioService.eliminarUsuario(id);

        return "Usuario eliminado correctamente";
    }
}