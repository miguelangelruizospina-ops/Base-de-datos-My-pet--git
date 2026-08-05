package com.example.My_Pet.service;

import com.example.My_Pet.model.Documento;
import com.example.My_Pet.model.Mascota;
import com.example.My_Pet.model.Usuario;
import com.example.My_Pet.repository.DocumentoRepository;
import com.example.My_Pet.repository.MascotaRepository;
import com.example.My_Pet.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentoService {

    @Autowired
    private DocumentoRepository documentoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private MascotaRepository mascotaRepository;

    public List<Documento> obtenerTodos() {
        return documentoRepository.findAll();
    }

    public List<Documento> obtenerPorUsuario(Integer idUsuario) {
        return documentoRepository.findByUsuarioIdUsuario(idUsuario);
    }

    public List<Documento> obtenerPorMascota(Integer idMascota) {
        return documentoRepository.findByMascotaIdMascota(idMascota);
    }

    public Documento guardar(Documento documento) {
        // Validar Usuario Obligatorio
        if (documento.getUsuario() == null || documento.getUsuario().getIdUsuario() <= 0) {
            throw new IllegalArgumentException("El documento debe estar asociado a un usuario válido.");
        }

        Integer idUsuario = documento.getUsuario().getIdUsuario();
        Usuario usuarioExistente = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new IllegalArgumentException("El usuario con ID " + idUsuario + " no existe."));
        documento.setUsuario(usuarioExistente);

        // Validar Mascota Opcional
        if (documento.getMascota() != null && documento.getMascota().getIdMascota() > 0) {
            Integer idMascota = documento.getMascota().getIdMascota();
            Mascota mascotaExistente = mascotaRepository.findById(idMascota)
                    .orElseThrow(() -> new IllegalArgumentException("La mascota con ID " + idMascota + " no existe."));
            documento.setMascota(mascotaExistente);
        } else {
            documento.setMascota(null); // Nos aseguramos de que quede nulo si no se envía mascota
        }

        return documentoRepository.save(documento);
    }

    public void eliminar(Integer id) {
        documentoRepository.deleteById(id);
    }
}