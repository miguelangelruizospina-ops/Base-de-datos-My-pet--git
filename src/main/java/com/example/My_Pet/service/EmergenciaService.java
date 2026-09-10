package com.example.My_Pet.service;

import com.example.My_Pet.model.Emergencia;
import com.example.My_Pet.model.Usuario;
import com.example.My_Pet.repository.EmergenciaRepository;
import com.example.My_Pet.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmergenciaService {

    // Repositorio de emergencias
    @Autowired
    private EmergenciaRepository emergenciaRepository;

    // Repositorio de usuarios
    @Autowired
    private UsuarioRepository usuarioRepository;

    // Lista todas las emergencias
    public List<Emergencia> obtenerTodas() {
        return emergenciaRepository.findAll();
    }

    // Lista las emergencias de un usuario
    public List<Emergencia> obtenerPorUsuario(Integer idUsuario) {
        return emergenciaRepository.findByUsuarioIdUsuario(idUsuario);
    }

    // Guarda o actualiza una emergencia
    public Emergencia guardar(Emergencia emergencia) {

        // Verifica que exista un usuario válido
        if (emergencia.getUsuario() == null ||
            emergencia.getUsuario().getIdUsuario() <= 0) {

            throw new IllegalArgumentException(
                "La emergencia debe estar reportada por un usuario válido."
            );
        }

        Integer idUsuario = emergencia.getUsuario().getIdUsuario();

        // Verifica que el usuario exista
        Usuario usuarioExistente = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new IllegalArgumentException(
                    "El usuario con ID " + idUsuario + " no existe."
                ));

        // Asigna el usuario encontrado
        emergencia.setUsuario(usuarioExistente);

       
        if (emergencia.getIdEmergencia() == null) {
            emergencia.setFecha(LocalDateTime.now());
        }

        // Guarda la emergencia
        return emergenciaRepository.save(emergencia);
    }

    // Elimina una emergencia por su ID
    public void eliminar(Integer id) {

        // Verifica que la emergencia exista
        if (!emergenciaRepository.existsById(id)) {
            throw new IllegalArgumentException(
                "La emergencia con ID " + id + " no existe."
            );
        }

        
        emergenciaRepository.deleteById(id);
    }
}