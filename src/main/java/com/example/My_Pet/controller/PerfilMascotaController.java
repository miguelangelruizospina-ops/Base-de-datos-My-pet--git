// Definición del paquete: organiza el proyecto y ubica esta clase en la capa de controladores (Endpoints de Perfiles de Mascotas)
package com.example.My_Pet.controller;

// Importaciones de la entidad del modelo, el componente de servicio y las utilidades de respuestas HTTP de Spring
import com.example.My_Pet.model.PerfilMascota;
import com.example.My_Pet.service.PerfilMascotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// @RestController: Define que esta clase es un controlador API REST, lo que permite retornar las respuestas directamente en formato JSON
@RestController
// @RequestMapping: Establece el prefijo o ruta base de la URL para acceder a todos los endpoints de este controlador
@RequestMapping("/api/perfiles-mascotas")
public class PerfilMascotaController {

    // @Autowired: Inyección automática de dependencias para enlazar la lógica de negocio de la capa de servicio
    @Autowired
    private PerfilMascotaService perfilMascotaService;

    // @GetMapping: Mapea solicitudes de lectura HTTP GET. URL de prueba: http://localhost:8082/api/perfiles-mascotas/listar
    @GetMapping("/listar")
    public List<PerfilMascota> listarPerfiles() {
        // Invoca al servicio para extraer la lista completa de todos los perfiles de mascotas registrados
        return perfilMascotaService.obtenerTodos();
    }

    // @GetMapping con variable de ruta: Captura el ID de la mascota desde la URL para buscar su perfil específico
    // URL de consulta en entorno local: http://localhost:8082/api/perfiles-mascotas/mascota/{idMascota}
    @GetMapping("/mascota/{idMascota}")
    public ResponseEntity<PerfilMascota> obtenerPorMascota(@PathVariable Integer idMascota) {
        // El servicio retorna un Optional. Si el perfil existe, .map() lo empaqueta en un HTTP 200 (OK). Si no existe, .orElse() retorna un HTTP 404 (Not Found)
        return perfilMascotaService.obtenerPorMascota(idMascota)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // @PostMapping: Recibe un objeto serializado en el cuerpo de la solicitud (JSON) para registrar o actualizar las especificaciones del perfil
    @PostMapping("/guardar")
    public ResponseEntity<?> crearPerfil(@RequestBody PerfilMascota perfil) {
        try {
            // Envía la entidad mapeada a la capa de servicio para procesar las validaciones e insertarlo en la base de datos
            PerfilMascota nuevoPerfil = perfilMascotaService.guardar(perfil);
            // Si el proceso es exitoso, responde un código HTTP 200 (OK) junto con el objeto persistido y sus datos actualizados
            return ResponseEntity.ok(nuevoPerfil);
        } catch (IllegalArgumentException e) {
            // Manejo de excepciones: Atrapa los fallos lógicos del Service y devuelve un HTTP 400 (Bad Request) con el motivo exacto del error
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}