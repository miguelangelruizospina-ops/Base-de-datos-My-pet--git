// Definición del paquete: ubica esta clase dentro de la capa de servicios (lógica de negocio)
package com.example.My_Pet.service;

// Importaciones de los modelos, repositorios necesarios y componentes de Spring Boot y Java
import com.example.My_Pet.model.Notificacion;
import com.example.My_Pet.model.Usuario;
import com.example.My_Pet.repository.NotificacionRepository;
import com.example.My_Pet.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

// @Service: Componente de Spring que encapsula la lógica de negocio principal y las reglas operacionales del sistema
@Service
public class NotificacionService {

    // Inyección de dependencias para interactuar con los métodos de persistencia de Notificaciones
    @Autowired
    private NotificacionRepository notificacionRepository;

    // Inyección de dependencias para consultar la existencia de Usuarios en el sistema
    @Autowired
    private UsuarioRepository usuarioRepository;

    // Método de lectura: Consume el repositorio para recuperar la lista completa de notificaciones
    public List<Notificacion> obtenerTodas() {
        return notificacionRepository.findAll();
    }

    // Método de lectura filtrada: Recupera la colección de notificaciones mapeadas para un usuario específico
    public List<Notificacion> obtenerPorUsuario(Integer idUsuario) {
        return notificacionRepository.findByUsuarioIdUsuario(idUsuario);
    }

    // Método de persistencia: Implementa la lógica de negocio y las validaciones de integridad estructural
    public Notificacion guardar(Notificacion notificacion) {
        
        // Regla de negocio: Validación preventiva para asegurar que la notificación contenga un objeto Usuario con un ID válido
        if (notificacion.getUsuario() == null || notificacion.getUsuario().getIdUsuario() <= 0) {
            throw new IllegalArgumentException("La notificación debe estar asignada a un usuario válido.");
        }

        // Extracción del identificador del usuario para realizar la validación de existencia
        Integer idUsuario = notificacion.getUsuario().getIdUsuario();
        
        // Validación de integridad referencial: Verifica si el usuario existe en la base de datos, lanzando una excepción si no es así
        Usuario usuarioExistente = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new IllegalArgumentException("El usuario con ID " + idUsuario + " no existe."));

        // Vinculación formal: Se asocia la instancia del usuario validado directamente a la entidad Notificación
        notificacion.setUsuario(usuarioExistente);

        // Lógica para registros nuevos: Si el ID es nulo, se determina que es una inserción (INSERT)
        if (notificacion.getIdNotificacion() == null) {
            // Setea por defecto la marca de tiempo actual del servidor
            notificacion.setFecha(LocalDateTime.now());
            // Si el cliente no especificó un estado inicial, se parametriza como "Pendiente" por defecto
            if (notificacion.getEstado() == null) {
                notificacion.setEstado("Pendiente");
            }
        }

        // Transacción de persistencia: Envía el objeto validado al repositorio para su inserción o actualización en la base de datos
        return notificacionRepository.save(notificacion);
    }

    // Método de eliminación: Realiza el borrado físico del registro en la base de datos mediante su llave primaria
    public void eliminar(Integer id) {
        notificacionRepository.deleteById(id);
    }
}