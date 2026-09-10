package com.example.My_Pet.service;

import com.example.My_Pet.model.Documento;
import com.example.My_Pet.repository.DocumentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentoService {

    @Autowired
    private DocumentoRepository documentoRepository;

    // Obtener todos los documentos
    public List<Documento> obtenerTodos() {
        return documentoRepository.findAll();
    }

    // Obtener documentos de un usuario
    public List<Documento> obtenerPorUsuario(Integer idUsuario) {
        return documentoRepository.findByUsuario_IdUsuario(idUsuario);
    }

    // Obtener documentos de una mascota
    public List<Documento> obtenerPorMascota(Integer idMascota) {
        return documentoRepository.findByMascota_IdMascota(idMascota);
    }

    // Guardar documento
    public Documento guardar(Documento documento) {
        if (documento.getTipoDocumento() == null ||
            documento.getTipoDocumento().isBlank()) {

            throw new IllegalArgumentException(
                "El tipo de documento es obligatorio."
            );
        }

        if (documento.getArchivo() == null ||
            documento.getArchivo().isBlank()) {

            throw new IllegalArgumentException(
                "El archivo es obligatorio."
            );
        }

        if (documento.getUsuario() == null) {

             throw new IllegalArgumentException(
            "El usuario es obligatorio."
        );
        }

        return documentoRepository.save(documento);
    }

    // Eliminar documento
    public void eliminar(Integer id) {

        if (!documentoRepository.existsById(id)) {
            throw new RuntimeException("Documento no encontrado.");
        }

        documentoRepository.deleteById(id);
    }
}