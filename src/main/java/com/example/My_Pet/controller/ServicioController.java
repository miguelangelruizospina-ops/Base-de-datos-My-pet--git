// Definición del paquete: organiza el proyecto y le dice a Java que este archivo maneja las peticiones web de los servicios ofrecidos
package com.example.My_Pet.controller;

// Importaciones: Traemos las herramientas de Spring y los archivos de Servicios (Modelo y Servicio) que usaremos aquí
import com.example.My_Pet.model.Servicio;
import com.example.My_Pet.service.ServicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// @RestController: Convierte esta clase en una API. Todo lo que devuelva se transforma en texto plano (JSON) para que el Frontend lo cargue fácil
@RestController
// @RequestMapping: Configura la dirección web base para este módulo. En el navegador todas las rutas empezarán por: /api/servicios
@RequestMapping("/api/servicios")
public class ServicioController {

    // @Autowired: Conexión automática. Nos conecta con la capa de Servicio para usar la lógica de los servicios sin crear objetos desde cero
    @Autowired
    private ServicioService servicioService;

    // @GetMapping: Se activa cuando el Frontend entra a la URL a "pedir" o "leer" la lista de todos los servicios disponibles
    // URL para probar en el navegador: http://localhost:8082/api/servicios/listar
    @GetMapping("/listar")
    public List<Servicio> listarTodo() {
        // Va a la capa de servicio, trae absolutamente todos los servicios de la base de datos y se los muestra al usuario
        return servicioService.obtenerTodos(); 
    }

    // @GetMapping con {tipo}: Sirve para filtrar. Se activa cuando necesitas buscar servicios de una sola categoría (ej: Solo Veterinarias)
    // URL para probar en el navegador: http://localhost:8082/api/servicios/tipo/Veterinaria
    @GetMapping("/tipo/{tipo}")
    public List<Servicio> listarPorTipo(@PathVariable String tipo) {
        // @PathVariable agarra la palabra que escribiste al final de la URL (el tipo) y el servicio busca solo las coincidencias en MySQL
        return servicioService.obtenerPorTipo(tipo); 
    }

    // @PostMapping: Se activa cuando un administrador o aliado quiere registrar un nuevo servicio en la plataforma de Mi Pet
    @PostMapping("/guardar")
    public ResponseEntity<?> crearServicio(@RequestBody Servicio servicio) {
        try {
            // @RequestBody agarra los datos del nuevo servicio que viajan por la web, los vuelve un objeto Java y el servicio los guarda en MySQL
            Servicio nuevoServicio = servicioService.guardar(servicio);
            // Si todo sale bien, el servidor responde con un código "HTTP 200 OK" y devuelve el servicio creado con su ID generado
            return ResponseEntity.ok(nuevoServicio); 
        } catch (IllegalArgumentException e) {
            // Si falta un dato obligatorio (como el nombre del negocio), el "catch" frena el proceso y responde un "HTTP 400" explicando el error
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}