package com.example.My_Pet.service;

import com.example.My_Pet.model.PerfilMascota;
import com.example.My_Pet.model.Mascota;
import com.example.My_Pet.repository.PerfilMascotaRepository;
import com.example.My_Pet.repository.MascotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PerfilMascotaService {

    @Autowired
    private PerfilMascotaRepository perfilMascotaRepository;

    @Autowired
    private MascotaRepository mascotaRepository;

    public List<PerfilMascota> obtenerTodos() {
        return perfilMascotaRepository.findAll();
    }

    public Optional<PerfilMascota> obtenerPorMascota(Integer idMascota) {
        return perfilMascotaRepository.findByMascotaIdMascota(idMascota);
    }

    // Restricción de negocio integrada con tus modelos anteriores
    public PerfilMascota guardar(PerfilMascota perfil) {
        if (perfil.getMascota() == null || perfil.getMascota().getIdMascota() == null) {
            throw new IllegalArgumentException("No se puede crear un perfil sin asociarlo a una mascota.");
        }

        Integer idMascota = perfil.getMascota().getIdMascota();
        
        // Coherencia: Verificar en tu base de datos si esa mascota real existe
        Mascota mascotaExistente = mascotaRepository.findById(idMascota)
                .orElseThrow(() -> new IllegalArgumentException("La mascota con ID " + idMascota + " no existe."));

        perfil.setMascota(mascotaExistente);
        return perfilMascotaRepository.save(perfil);
    }

    public void eliminar(Integer id) {
        perfilMascotaRepository.deleteById(id);
    }
}