package com.example.My_Pet.controller;

import com.example.My_Pet.model.Documento;
import com.example.My_Pet.service.DocumentoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Controlador encargado de gestionar los documentos de My Pet
@RestController
@RequestMapping("/api/documentos")
@CrossOrigin(origins = "*")
public class DocumentoController {

    @Autowired
    private DocumentoService documentoService;


    // =========================================================
    // GET - LISTAR TODOS LOS DOCUMENTOS
    // =========================================================

    @GetMapping("/listar")
    public List<Documento> listarTodo() {

        return documentoService.obtenerTodos();
    }


    // =========================================================
    // GET - BUSCAR DOCUMENTOS POR USUARIO
    // =========================================================

    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<Documento>> listarPorUsuario(
            @PathVariable Integer idUsuario) {

        List<Documento> documentos =
                documentoService.obtenerPorUsuario(idUsuario);

        return ResponseEntity.ok(documentos);
    }


    // =========================================================
    // GET - BUSCAR DOCUMENTOS POR MASCOTA
    // =========================================================

    @GetMapping("/mascota/{idMascota}")
    public ResponseEntity<List<Documento>> listarPorMascota(
            @PathVariable Integer idMascota) {

        List<Documento> documentos =
                documentoService.obtenerPorMascota(idMascota);

        return ResponseEntity.ok(documentos);
    }


    // =========================================================
    // POST - REGISTRAR / SUBIR DOCUMENTO
    // =========================================================

    @PostMapping("/guardar")
    public ResponseEntity<?> crearDocumento(
            @RequestBody Documento documento) {

        try {

            Documento nuevoDocumento =
                    documentoService.guardar(documento);

            return ResponseEntity.ok(nuevoDocumento);

        } catch (IllegalArgumentException e) {

            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }


    // =========================================================
    // DELETE - ELIMINAR DOCUMENTO
    // =========================================================

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarDocumento(
            @PathVariable Integer id) {

        try {

            documentoService.eliminar(id);

            return ResponseEntity.ok(
                    "Documento eliminado correctamente"
            );

        } catch (RuntimeException e) {

            return ResponseEntity
                    .notFound()
                    .build();
        }
    }
}