// Definición del paquete: organiza el proyecto y le dice a Java que este archivo maneja las cuentas de los usuarios (registro e ingresos)
package com.example.My_Pet.controller;

// Importaciones: Traemos las herramientas de Spring y los archivos de Usuarios (Modelo y Servicio) necesarios para este controlador
import com.example.My_Pet.model.Usuario;
import com.example.My_Pet.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// @RestController: Convierte esta clase en una API. Todo lo que devuelva se transforma en texto plano (JSON) para que el Frontend lo lea sin enredarse
@RestController
// @RequestMapping: Configura la dirección web base para este módulo. En el navegador todas las rutas empezarán por: /api/usuarios
@RequestMapping("/api/usuarios")
// @CrossOrigin: Permiso especial. Evita que el navegador bloquee la conexión cuando tu aplicación móvil o tu interfaz HTML intenten comunicarse con el servidor
@CrossOrigin(origins = "*") 
public class UsuarioController {

    // @Autowired: Conexión automática. Nos conecta con la capa de Servicio para validar y procesar los usuarios sin crear objetos desde cero
    @Autowired
    private UsuarioService usuarioService;

    // @PostMapping: Se activa cuando una persona nueva llega a la aplicación, llena el formulario de registro y hunde el botón "Crear cuenta"
    // URL exacta para enviar los datos: http://localhost:8082/api/usuarios/registro
    @PostMapping("/registro")
    public Usuario registrar(@RequestBody Usuario usuario) {
        // @RequestBody agarra el correo, la contraseña y los datos que el usuario escribió en el HTML, los vuelve un objeto Java y el servicio los guarda en MySQL
        return usuarioService.registrarUsuario(usuario);
    }

    // @GetMapping: Se activa cuando entramos a la URL a "pedir" o "leer" la lista de todas las personas registradas en el sistema
    // URL exacta para probar en el navegador: http://localhost:8082/api/usuarios/listar
    @GetMapping("/listar")
    public List<Usuario> listar() {
        // Va a la capa de servicio, pide la lista completa de usuarios de la base de datos y la devuelve (ideal para revisar que sí estén quedando guardados)
        return usuarioService.obtenerTodosLosUsuarios();
    }
}