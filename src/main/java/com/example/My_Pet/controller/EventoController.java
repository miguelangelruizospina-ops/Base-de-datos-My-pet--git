// Definición del paquete: organiza el proyecto y ubica esta clase en la capa de controladores (Endpoints de Eventos)
package com.example.My_Pet.controller;

// Importaciones de la entidad del modelo, el componente de servicio y las anotaciones de Spring Web
import com.example.My_Pet.model.Evento;
import com.example.My_Pet.service.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// @RestController: Define que esta clase es un controlador API REST, lo que permite retornar las respuestas directamente en formato JSON
@RestController
// @RequestMapping: Establece el prefijo o ruta base de la URL para acceder a todos los endpoints de este controlador
@RequestMapping("/api/eventos")
public class EventoController {

    // @Autowired: Inyección de dependencias para conectar y utilizar los métodos de la capa de lógica de negocio (Servicio)
    @Autowired
    private EventoService eventoService;

    // @GetMapping: Mapea peticiones de lectura HTTP GET. Ruta completa de prueba: http://localhost:8082/api/eventos/listar
    @GetMapping("/listar")
    public List<Evento> listarTodo() {
        // Invoca al servicio para extraer la lista completa de todos los eventos registrados en el sistema
        return eventoService.obtenerTodos();
    }

    // @GetMapping con variable de ruta: Permite capturar dinámicamente el ID de una mascota desde la URL para filtrar su historial
    // URL de consulta: http://localhost:8082/api/eventos/mascota/{idMascota}
    @GetMapping("/mascota/{idMascota}")
    public ResponseEntity<List<Evento>> listarPorMascota(@PathVariable Integer idMascota) {
        // Delega la consulta al método especializado del servicio enviando el ID capturado
        List<Evento> eventos = eventoService.obtenerPorMascota(idMascota);
        // Retorna un estado HTTP 200 (OK) transfiriendo la lista de eventos encontrados en formato JSON (puede ir vacía si no hay registros)
        return ResponseEntity.ok(eventos);
    }

    // @PostMapping: Recibe un objeto serializado en el cuerpo de la solicitud (JSON) para registrar una nueva actividad o evento
    @PostMapping("/guardar")
    public ResponseEntity<?> crearEvento(@RequestBody Evento evento) {
        try {
            // Envía la entidad mapeada a la capa de servicio para procesar las validaciones de negocio e insertarlo en la base de datos
            Evento nuevoEvento = eventoService.guardar(evento);
            // Si el proceso es exitoso, responde un código HTTP 200 (OK) junto con el objeto persistido y su ID autogenerado
            return ResponseEntity.ok(nuevoEvento);
        } catch (IllegalArgumentException e) {
            // Manejo de excepciones: Atrapa los fallos lógicos del Service y devuelve un HTTP 400 (Bad Request) con el motivo exacto del error
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}