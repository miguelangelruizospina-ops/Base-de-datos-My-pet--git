// Definición del paquete: organiza el proyecto y ubica esta clase en la capa de controladores (Endpoints API)
package com.example.My_Pet.controller;

// Importaciones de los modelos, servicios necesarios y anotaciones del framework de Spring Web
import com.example.My_Pet.model.Notificacion;
import com.example.My_Pet.service.NotificacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// @RestController: Indica que esta clase es un controlador de Spring MVC donde cada método devuelve directamente el objeto serializado en JSON
@RestController
// @RequestMapping: Establece el prefijo o ruta base de la URL para mapear todas las peticiones HTTP de este controlador
@RequestMapping("/api/notificaciones")
public class NotificacionController {

    // Inyección de dependencias para vincular la capa de lógica de negocio del servicio
    @Autowired
    private NotificacionService notificacionService;

    // @GetMapping: Mapea solicitudes HTTP GET. Dirección de acceso: http://localhost:8082/api/notificaciones/listar
    @GetMapping("/listar")
    public List<Notificacion> listarTodo() {
        // Delega la petición al servicio para obtener todas las notificaciones registradas
        return notificacionService.obtenerTodas();
    }

    // @GetMapping con variable de ruta: Permite filtrar dinámicamente y listar las notificaciones mapeadas a un usuario específico
    // Ruta base de consulta: http://localhost:8082/api/notificaciones/usuario/{idUsuario}
    @GetMapping("/usuario/{idUsuario}")
    public List<Notificacion> listarPorUsuario(@PathVariable Integer idUsuario) {
        // Invoca el método especializado del servicio enviando el parámetro capturado en la URL
        return notificacionService.obtenerPorUsuario(idUsuario);
    }

    // @PostMapping: Mapea solicitudes HTTP POST para la inserción de datos en el sistema
    @PostMapping("/guardar")
    public ResponseEntity<?> crearNotificacion(@RequestBody Notificacion notificacion) {
        try {
            // Recibe el JSON deserializado en el objeto entidad y lo envía a la capa de negocio para su procesamiento
            Notificacion nuevaNotificacion = notificacionService.guardar(notificacion);
            // Si el flujo es exitoso y supera las validaciones, responde un HTTP 200 (OK) enviando el nuevo registro persistido
            return ResponseEntity.ok(nuevaNotificacion); 
        } catch (IllegalArgumentException e) {
            // Manejo de excepciones: Captura los errores disparados por el Service y devuelve un HTTP 400 (Bad Request) con el texto del error
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}