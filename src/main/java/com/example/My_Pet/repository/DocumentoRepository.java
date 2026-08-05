package com.example.My_Pet.repository;

import com.example.My_Pet.model.Documento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentoRepository extends JpaRepository<Documento, Integer> {
    // Buscar documentos por dueño
    List<Documento> findByUsuarioIdUsuario(Integer idUsuario);
    
    // Buscar documentos por mascota
    List<Documento> findByMascotaIdMascota(Integer idMascota);
}