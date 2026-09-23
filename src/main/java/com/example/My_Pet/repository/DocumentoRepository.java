package com.example.My_Pet.repository;

import com.example.My_Pet.model.Documento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DocumentoRepository extends JpaRepository<Documento, Integer> {

    List<Documento> findByUsuario_IdUsuario(Integer idUsuario);

    List<Documento> findByMascota_IdMascota(Integer idMascota);
}