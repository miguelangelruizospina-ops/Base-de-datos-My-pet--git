// Definición del paquete: organiza el proyecto y ubica esta clase en la capa de controladores (Endpoints de Historial Médico)
package com.example.My_Pet.controller;

// Importaciones de la entidad del modelo, el componente de servicio y las clases de manejo de respuestas web de Spring
import com.example.My_Pet.model.HistorialMedico;
import com.example.My_Pet.service.HistorialMedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// @RestController: Define que esta clase es un controlador API REST, lo que permite retornar las respuestas directamente en formato JSON
@RestController
// @RequestMapping: Establece el prefijo o ruta base de la URL para acceder a todos los endpoints de este controlador
@RequestMapping("/api/historiales-medicos")
public class HistorialMedicoController {

    // @Autowired: Inyección de dependencias para conectar y usar los métodos de la capa de lógica de negocio (Servicio)
    @Autowired
    private HistorialMedicoService historialMedicoService;

    // @GetMapping: Mapea solicitudes de lectura HTTP GET. Ruta de consulta global: http://localhost:8082/api/historiales-medicos/listar
    @GetMapping("/listar")
    public List<HistorialMedico> listarTodo() {
        // Invoca al servicio para recuperar la lista completa de todos los historiales médicos registrados en el sistema
        return historialMedicoService.obtenerTodos();
    }

    // @GetMapping con variable de ruta: Permite capturar dinámicamente el ID de una mascota desde la URL para filtrar su registro clínico
    // URL de consulta en entorno local: http://localhost:8082/api/historiales-medicos/mascota/{idMascota}
    @GetMapping("/mascota/{idMascota}")
    public ResponseEntity<List<HistorialMedico>> listarPorMascota(@PathVariable Integer idMascota) {
        // Delega la consulta al método especializado del servicio enviando el ID recibido
        List<HistorialMedico> historial = historialMedicoService.obtenerPorMascota(idMascota);
        // Retorna un estado HTTP 200 (OK) transfiriendo la colección de datos encontrada en formato JSON
        return ResponseEntity.ok(historial);
    }

    // @PostMapping: Recibe un objeto serializado en el cuerpo de la solicitud (JSON) para registrar un nuevo antecedente clínico
    @PostMapping("/guardar")
    public ResponseEntity<?> crearHistorial(@RequestBody HistorialMedico historial) {
        try {
            // Envía la entidad a la capa de servicio para procesar las validaciones de negocio y ejecutar la persistencia en MySQL
            HistorialMedico nuevoHistorial = historialMedicoService.guardar(historial);
            // Si el proceso es exitoso, responde un código HTTP 200 (OK) junto con el objeto persistido y su ID autogenerado
            return ResponseEntity.ok(nuevoHistorial);
        } catch (IllegalArgumentException e) {
            // Manejo de excepciones: Atrapa los fallos lógicos del Service y devuelve un HTTP 400 (Bad Request) con el motivo exacto del error
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}