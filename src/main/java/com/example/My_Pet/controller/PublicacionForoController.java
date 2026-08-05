// Definición del paquete: organiza el proyecto y le dice a Java que este archivo maneja las peticiones web del foro
package com.example.My_Pet.controller;

// Importaciones: Traemos las herramientas de Spring y los archivos del Foro (Modelo y Servicio) que usaremos aquí
import com.example.My_Pet.model.PublicacionForo;
import com.example.My_Pet.service.PublicacionForoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// @RestController: Convierte esta clase en una API. Todo lo que devuelva se convierte en texto plano (JSON) que el Frontend entiende perfectamente
@RestController
// @RequestMapping: Configura la dirección web base para este módulo. En el navegador todas las rutas empezarán por: /api/publicaciones-foro
@RequestMapping("/api/publicaciones-foro")
public class PublicacionForoController {

    // @Autowired: Conexión automática. Nos conecta con la capa de Servicio para usar las consultas del foro sin tener que crear objetos nuevos
    @Autowired
    private PublicacionForoService forumService;

    // @GetMapping: Se activa cuando el Frontend entra a la URL a "pedir" o "leer" la lista de todos los posts del foro
    // URL para probar en el navegador: http://localhost:8082/api/publicaciones-foro/listar
    @GetMapping("/listar")
    public List<PublicacionForo> listarTodo() {
        // Va a la capa de servicio, trae todas las publicaciones guardadas en la base de datos y se las muestra al usuario
        return forumService.obtenerTodas();
    }

    // @GetMapping con {idUsuario}: Busca en la web las publicaciones que le pertenecen únicamente a un usuario específico
    // URL para probar en el navegador: http://localhost:8082/api/publicaciones-foro/usuario/1
    @GetMapping("/usuario/{idUsuario}")
    public List<PublicacionForo> listarPorUsuario(@PathVariable Integer idUsuario) {
        // Captura el ID que pusiste en la URL y le pide al servicio que solo traiga los posts de esa persona
        return forumService.obtenerPorUsuario(idUsuario);
    }

    // @PostMapping: Se activa cuando el usuario escribe una nueva publicación en el foro y hunde el botón de "Publicar" o "Guardar"
    @PostMapping("/guardar")
    public ResponseEntity<?> crearPublicacion(@RequestBody PublicacionForo publicacion) {
        try {
            // @RequestBody agarra el texto del post que viaja por la web, lo convierte en un objeto Java y el servicio lo guarda en MySQL
            PublicacionForo nuevaPublicacion = forumService.guardar(publicacion);
            // Si todo sale bien, el servidor responde con un código "HTTP 200 OK" y devuelve la publicación creada con su ID
            return ResponseEntity.ok(nuevaPublicacion); 
        } catch (IllegalArgumentException e) {
            // Si falta algún dato obligatorio (como el título), el "catch" frena el error y responde un código "HTTP 400" explicando qué faltó
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}