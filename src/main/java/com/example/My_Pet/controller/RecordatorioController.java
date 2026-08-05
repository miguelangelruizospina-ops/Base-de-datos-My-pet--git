// Definición del paquete: organiza el proyecto y le dice a Java que este archivo maneja las peticiones web de los recordatorios
package com.example.My_Pet.controller;

// Importaciones: Traemos las herramientas de Spring y los archivos de Recordatorios (Modelo y Servicio) que usaremos aquí
import com.example.My_Pet.model.Recordatorio;
import com.example.My_Pet.service.RecordatorioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// @RestController: Convierte esta clase en una API. Todo lo que devuelva se transforma en texto plano (JSON) para que el Frontend lo entienda fácil
@RestController
// @RequestMapping: Configura la dirección web base para este módulo. En el navegador todas las rutas empezarán por: /api/recordatorios
@RequestMapping("/api/recordatorios")
public class RecordatorioController {

    // @Autowired: Conexión automática. Nos conecta con la capa de Servicio para usar las consultas de alarmas sin crear objetos desde cero
    @Autowired
    private RecordatorioService recordatorioService;

    // @GetMapping: Se activa cuando el Frontend entra a la URL a "pedir" o "leer" la lista de todos los recordatorios del sistema
    // URL para probar en el navegador: http://localhost:8082/api/recordatorios/listar
    @GetMapping("/listar")
    public List<Recordatorio> listarTodo() {
        // Va a la capa de servicio, trae todos los recordatorios guardados en la base de datos y se los muestra al usuario
        return recordatorioService.obtenerTodos();
    }

    // @GetMapping con {idUsuario}: Busca en la web las alarmas o recordatorios que le pertenecen únicamente a un usuario específico
    // URL para probar en el navegador: http://localhost:8082/api/recordatorios/usuario/1
    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<Recordatorio>> listarPorUsuario(@PathVariable Integer idUsuario) {
        // Captura el ID de la URL, le pide al servicio los recordatorios de ese dueño y responde con un código "HTTP 200 OK" junto con la lista
        List<Recordatorio> recordatorios = recordatorioService.obtenerPorUsuario(idUsuario);
        return ResponseEntity.ok(recordatorios);
    }

    // @PostMapping: Se activa cuando el usuario programa una nueva alarma (como "Darle la pastilla a la mascota") y hunde el botón "Guardar"
    @PostMapping("/guardar")
    public ResponseEntity<?> crearRecordatorio(@RequestBody Recordatorio recordatorio) {
        try {
            // @RequestBody agarra los datos de la alarma que viajan por la web, los vuelve un objeto Java y el servicio los guarda en MySQL
            Recordatorio nuevoRecordatorio = recordatorioService.guardar(recordatorio);
            // Si todo sale bien, el servidor responde con un código "HTTP 200 OK" y devuelve el recordatorio creado con su nueva ID
            return ResponseEntity.ok(nuevoRecordatorio);
        } catch (IllegalArgumentException e) {
            // Si el usuario pone una fecha inválida o pasada, el "catch" frena el proceso y responde un código "HTTP 400" explicando el error
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}