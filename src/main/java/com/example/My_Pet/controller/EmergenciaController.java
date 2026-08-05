// Definición del paquete: organiza el proyecto y ubica esta clase en la capa de controladores (Endpoints de Emergencias)
package com.example.My_Pet.controller;

// Importaciones de la entidad del modelo, el componente de servicio y las clases de manejo de respuestas web
import com.example.My_Pet.model.Emergencia;
import com.example.My_Pet.service.EmergenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// Utilidad para el manejo de colecciones de datos en Java
import java.util.List;

// @RestController: Indica que esta clase es un controlador REST que serializa automáticamente los objetos de retorno en formato JSON
@RestController
// @RequestMapping: Define la ruta base URL unificada para el mapeo de todas las peticiones HTTP orientadas a este módulo
@RequestMapping("/api/emergencias")
public class EmergenciaController {

    // @Autowired: Inyección de dependencias para vincular la lógica operacional de la capa de servicio
    @Autowired
    private EmergenciaService emergenciaService;

    // @GetMapping: Mapea solicitudes de lectura HTTP GET. Ruta de acceso global: http://localhost:8082/api/emergencias/listar
    @GetMapping("/listar")
    public List<Emergencia> listarTodo() {
        // Invoca el método del servicio para recuperar el historial completo de emergencias registradas
        return emergenciaService.obtenerTodas();
    }

    // @GetMapping con parámetro dinámico: Permite filtrar e indexar el listado de emergencias asociadas a un usuario en específico
    // URL de consulta en entorno local: http://localhost:8082/api/emergencias/usuario/{idUsuario}
    @GetMapping("/usuario/{idUsuario}")
    public List<Emergencia> listarPorUsuario(@PathVariable Integer idUsuario) {
        // Filtra la búsqueda capturando la variable de ruta e invocando la lógica de negocio del servicio
        return emergenciaService.obtenerPorUsuario(idUsuario);
    }

    // @PostMapping: Mapea solicitudes de inserción HTTP POST procesando datos estructurados adjuntos en el body
    @PostMapping("/guardar")
    public ResponseEntity<?> crearEmergencia(@RequestBody Emergencia emergencia) {
        try {
            // Envía la entidad deserializada al servicio para procesar las reglas de negocio y ejecutar la persistencia en MySQL
            Emergencia nuevaEmergencia = emergenciaService.guardar(emergencia);
            // Retorna un código de estado HTTP 200 (OK) enviando la instancia del registro persistido
            return ResponseEntity.ok(nuevaEmergencia); 
        } catch (IllegalArgumentException e) {
            // Manejo de excepciones: Atrapa las violaciones lógicas de la capa de servicio y responde un HTTP 400 (Bad Request) con el diagnóstico
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}