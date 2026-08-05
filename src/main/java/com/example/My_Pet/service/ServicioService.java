package com.example.My_Pet.service;

import com.example.My_Pet.model.Servicio;
import com.example.My_Pet.model.Usuario;
import com.example.My_Pet.repository.ServicioRepository;
import com.example.My_Pet.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// @Service le dice al contenedor de Spring que aquí reside la lógica transaccional y operativa
@Service
public class ServicioService {

    // @Autowired inyecta automáticamente los repositorios para poder usarlos en las funciones
    @Autowired
    private ServicioRepository servicioRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Obtiene la lista completa de todos los servicios registrados en la plataforma
    public List<Servicio> obtenerTodos() {
        return servicioRepository.findAll();
    }

    // Obtiene una lista filtrada de servicios según la categoría enviada (ej: "Veterinaria")
    public List<Servicio> obtenerPorTipo(String tipo) {
        return servicioRepository.findByTipo(tipo);
    }

    // Registra un nuevo servicio o actualiza uno existente bajo reglas estrictas
    public Servicio guardar(Servicio servicio) {
        
        // CORRECCIÓN DE PRIMITIVO: Evaluamos si el usuario es nulo o si su ID de tipo int es inválido (<= 0)
        if (servicio.getUsuario() == null || servicio.getUsuario().getIdUsuario() <= 0) {
            throw new IllegalArgumentException("El servicio debe estar asociado a un usuario administrador o proveedor válido.");
        }

        Integer idUsuario = servicio.getUsuario().getIdUsuario();
        
        // Consulta en la base de datos si el ID de usuario suministrado realmente existe
        Usuario usuarioExistente = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new IllegalArgumentException("El usuario con ID " + idUsuario + " no existe."));

        // Al verificar que el usuario sí existe, se vincula formalmente al servicio antes de persistirlo
        servicio.setUsuario(usuarioExistente);
        
        // Guarda el registro en la base de datos y retorna el objeto con su ID auto-generado
        return servicioRepository.save(servicio);
    }

    // Remueve un servicio del sistema utilizando su ID principal
    public void eliminar(Integer id) {
        servicioRepository.deleteById(id);
    }
}