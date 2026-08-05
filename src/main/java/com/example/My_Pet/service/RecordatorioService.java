package com.example.My_Pet.service;

import com.example.My_Pet.model.Recordatorio;
import com.example.My_Pet.model.Usuario;
import com.example.My_Pet.repository.RecordatorioRepository;
import com.example.My_Pet.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecordatorioService {

    @Autowired
    private RecordatorioRepository recordatorioRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Recordatorio> obtenerTodos() {
        return recordatorioRepository.findAll();
    }

    public List<Recordatorio> obtenerPorUsuario(Integer idUsuario) {
        return recordatorioRepository.findByUsuarioIdUsuario(idUsuario);
    }

    public Recordatorio guardar(Recordatorio recordatorio) {
        if (recordatorio.getUsuario() == null || recordatorio.getUsuario().getIdUsuario() <= 0) {
       throw new IllegalArgumentException("No se puede crear un recordatorio sin asociarlo a un usuario válido.");
   }

        Integer idUsuario = recordatorio.getUsuario().getIdUsuario();
        
        // Validación de coherencia del usuario dueño
        Usuario usuarioExistente = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new IllegalArgumentException("El usuario con ID " + idUsuario + " no existe."));

        recordatorio.setUsuario(usuarioExistente);
        
        // Estado por defecto si llega vacío (ej: "Pendiente")
        if (recordatorio.getEstado() == null || recordatorio.getEstado().isEmpty()) {
            recordatorio.setEstado("Pendiente");
        }

        return recordatorioRepository.save(recordatorio);
    }

    public void eliminar(Integer id) {
        recordatorioRepository.deleteById(id);
    }
}