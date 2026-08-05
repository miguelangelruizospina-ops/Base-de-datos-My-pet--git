// Definición del paquete: organiza el proyecto y le dice a Java que este archivo es un Controlador (el que atiende las peticiones web)
package com.example.My_Pet.controller;

// Importaciones: Traemos las herramientas de Spring y los otros archivos del proyecto (Modelo y Servicio) que necesitamos usar aquí
import com.example.My_Pet.model.PerfilUsuario;
import com.example.My_Pet.service.PerfilUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// @RestController: Hace que esta clase funcione como una API. Todo lo que devuelva se transformará automáticamente en texto plano (JSON) para el Frontend
@RestController
// @RequestMapping: Define la dirección web base para este módulo. En el navegador todas las consultas empezarán por: /api/perfiles
@RequestMapping("/api/perfiles")
public class PerfilUsuarioController {

    // @Autowired: Conexión automática. Trae toda la lógica y las consultas que ya programamos en la capa de Servicio sin tener que crear un objeto desde cero
    @Autowired
    private PerfilUsuarioService perfilUsuarioService;

    // @GetMapping: Le dice al servidor que este método se activa cuando alguien entra a la URL a "pedir" o "leer" información
    // URL exacta para probar en el navegador: http://localhost:8082/api/perfiles/listar
    @GetMapping("/listar")
    public List<PerfilUsuario> listarPerfiles() {
        // Va a la capa de servicio, le pide la lista completa de perfiles guardados en MySQL y se la manda de inmediato al navegador
        return perfilUsuarioService.obtenerTodosLosPerfiles();
    }
}