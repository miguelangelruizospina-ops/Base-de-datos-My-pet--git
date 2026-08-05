package com.example.My_Pet.service;

import com.example.My_Pet.model.Emergencia;
import com.example.My_Pet.model.Usuario;
import com.example.My_Pet.repository.EmergenciaRepository;
import com.example.My_Pet.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

// @Service encapsula la lógica de negocio y las reglas operacionales del sistema
@Service
public class EmergenciaService {

    // @Autowired realiza la inyección de dependencias para conectar las capas de datos
    @Autowired
    private EmergenciaRepository emergenciaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Recupera el listado completo de alertas de emergencias en la app
    public List<Emergencia> obtenerTodas() {
        return emergenciaRepository.findAll();
    }

    // Recupera el historial de emergencias reportadas por un usuario específico
    public List<Emergencia> obtenerPorUsuario(Integer idUsuario) {
        return emergenciaRepository.findByUsuarioIdUsuario(idUsuario);
    }

    // Almacena o edita una alerta aplicando validaciones de integridad de datos
    public Emergencia guardar(Emergencia emergencia) {
        
        // CORRECCIÓN DE PRIMITIVO: Verificamos si el objeto usuario no fue enviado o si su ID numérico es inválido (<= 0)
        if (emergencia.getUsuario() == null || emergencia.getUsuario().getIdUsuario() <= 0) {
            throw new IllegalArgumentException("La emergencia debe estar reportada por un usuario válido.");
        }

        Integer idUsuario = emergencia.getUsuario().getIdUsuario();
        
        // Valida si el usuario reportante existe realmente en los registros de MySQL
        Usuario usuarioExistente = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new IllegalArgumentException("El usuario con ID " + idUsuario + " no existe."));

        // Se asigna la entidad de usuario verificada al reporte
        emergencia.setUsuario(usuarioExistente);

        // Si es una alerta nueva, capturamos el tiempo exacto del sistema antes de impactar la BD
        if (emergencia.getIdEmergencia() == null) {
            emergencia.setFecha(LocalDateTime.now());
        }

        // Guarda el registro y nos devuelve el objeto persistido con su ID final
        return emergenciaRepository.save(emergencia);
    }

    // Borra una alerta médica o de emergencia mediante su ID principal
    public void eliminar(Integer id) {
        emergenciaRepository.deleteById(id);
    }
}