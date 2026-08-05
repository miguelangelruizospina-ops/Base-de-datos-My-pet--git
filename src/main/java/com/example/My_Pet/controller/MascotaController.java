// Definición del paquete: organiza el proyecto y ubica esta clase en la capa de controladores (Endpoints de Mascotas)
package com.example.My_Pet.controller;

// Importaciones del modelo de la mascota, la capa de servicio y las anotaciones para habilitar la API Web
import com.example.My_Pet.model.Mascota;
import com.example.My_Pet.service.MascotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// @RestController: Desarrolla este componente como un controlador de servicios REST, serializando las respuestas directamente en formato JSON
@RestController
// @RequestMapping: Configura el prefijo de la ruta base URL para unificar todos los accesos de este controlador
@RequestMapping("/api/mascotas")
public class MascotaController {

    // @Autowired: Inyección automática de dependencias para enlazar la lógica de negocio de la capa de servicio
    @Autowired
    private MascotaService mascotaService;

    // @GetMapping: Mapea las solicitudes HTTP GET orientadas a la lectura de datos. URL de prueba: http://localhost:8082/api/mascotas/listar
    @GetMapping("/listar")
    public List<Mascota> listarMascotas() {
        // Invoca los métodos de la capa de servicio para recuperar la lista completa de todas las mascotas almacenadas en el sistema
        return mascotaService.obtenerTodasLasMascotas();
    }
}