// Definición del paquete: organiza el proyecto y ubica esta clase en la capa de controladores
package com.example.My_Pet.controller;

// Importaciones de las clases necesarias para el modelo, la lógica de negocio y las herramientas de Spring Boot
import com.example.My_Pet.model.Documento;
import com.example.My_Pet.service.DocumentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// @RestController: Indica que esta clase es un controlador API REST que maneja respuestas en formato JSON
@RestController
// @RequestMapping: Define la ruta base HTTP para acceder a todos los endpoints de este módulo
@RequestMapping("/api/documentos")
public class DocumentoController {

    // @Autowired: Inyección de dependencias para conectar y usar los métodos de la capa de servicio
    @Autowired
    private DocumentoService documentoService;

    // @GetMapping: Mapea peticiones de lectura. Ruta completa: http://localhost:8082/api/documentos/listar
    @GetMapping("/listar")
    public List<Documento> listarTodo() {
        // Llama al servicio para obtener la lista completa de documentos desde la base de datos
        return documentoService.obtenerTodos();
    }

    // @GetMapping con parámetro dinámico en la URL para listar los documentos asociados a un usuario específico
    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<Documento>> listarPorUsuario(@PathVariable Integer idUsuario) {
        // Se ejecuta la búsqueda llamando a la lógica del servicio para traer la lista filtrada
        List<Documento> documentos = documentoService.obtenerPorUsuario(idUsuario);
        // Retorna un código HTTP 200 (OK) enviando la lista de documentos encontrados (puede ir vacía si no tiene)
        return ResponseEntity.ok(documentos);
    }

    // @GetMapping con parámetro dinámico en la URL para listar los documentos vinculados a una mascota
    @GetMapping("/mascota/{idMascota}")
    public ResponseEntity<List<Documento>> listarPorMascota(@PathVariable Integer idMascota) {
        // Se consulta al servicio la lista de registros que coincidan con el ID de la mascota
        List<Documento> documentos = documentoService.obtenerPorMascota(idMascota);
        // Responde un código HTTP 200 (OK) entregando la colección de datos en formato JSON
        return ResponseEntity.ok(documentos);
    }

    // @PostMapping: Recibe un objeto (JSON) en el cuerpo de la petición para registrar un nuevo documento
    @PostMapping("/guardar")
    public ResponseEntity<?> crearDocumento(@RequestBody Documento documento) {
        try {
            // Envía los datos mapeados a la capa de negocio para su validación e inserción en la base de datos
            Documento nuevoDocumento = documentoService.guardar(documento);
            // Si el proceso es exitoso, responde un HTTP 200 (OK) con el objeto ya guardado y su ID generado
            return ResponseEntity.ok(nuevoDocumento);
        } catch (IllegalArgumentException e) {
            // Control de excepciones: Captura los errores de validación del servicio y responde un HTTP 400 (Bad Request) con el motivo del fallo
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}