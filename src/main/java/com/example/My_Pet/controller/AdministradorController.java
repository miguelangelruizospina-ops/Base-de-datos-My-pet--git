// Definición del paquete: organiza el proyecto y ubica esta clase en la capa de controladores
package com.example.My_Pet.controller;

// Importaciones de las clases necesarias para el modelo, la lógica de negocio y las herramientas de Spring Boot
import com.example.My_Pet.model.Administrador;
import com.example.My_Pet.service.AdministradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// @RestController: Indica que esta clase es un controlador API REST que maneja respuestas en formato JSON
@RestController
// @RequestMapping: Define la ruta base HTTP para acceder a todos los endpoints de este módulo
@RequestMapping("/api/administradores")
public class AdministradorController {

    // @Autowired: Inyección de dependencias para conectar y usar los métodos de la capa de servicio
    @Autowired
    private AdministradorService administradorService;

    // @GetMapping: Mapea peticiones de lectura. Ruta completa: http://localhost:8082/api/administradores/listar
    @GetMapping("/listar")
    public List<Administrador> listarTodo() {
        // Llama al servicio para obtener la lista completa desde la base de datos y la retorna
        return administradorService.obtenerTodos(); 
    }

    // @GetMapping con parámetro dinámico en la URL para buscar un administrador por su ID de usuario
    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<Administrador> obtenerPorUsuario(@PathVariable Integer idUsuario) {
        // Se ejecuta la búsqueda llamando a la lógica del servicio
        Administrador admin = administradorService.obtenerPorUsuario(idUsuario);
        
        // Validación de existencia: Si el objeto viene vacío, respondemos un código HTTP 404 (Not Found)
        if (admin == null) {
            return ResponseEntity.notFound().build();
        }
        // Si el registro existe, responde un código HTTP 200 (OK) enviando los datos correspondientes
        return ResponseEntity.ok(admin);
    }

    // @PostMapping: Se utiliza para recibir datos nuevos (JSON) en el cuerpo de la petición y crear un registro
    @PostMapping("/guardar")
    public ResponseEntity<?> crearAdministrador(@RequestBody Administrador administrador) {
        try {
            // Envía los datos mapeados al servicio para su procesamiento y persistencia
            Administrador nuevoAdmin = administradorService.guardar(administrador);
            // Si el proceso es exitoso, responde un HTTP 200 (OK) con el objeto guardado
            return ResponseEntity.ok(nuevoAdmin); 
        } catch (IllegalArgumentException e) {
            // Control de excepciones: Si fallan las validaciones del negocio, captura el error y responde un HTTP 400 (Bad Request) con el mensaje explicativo
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}