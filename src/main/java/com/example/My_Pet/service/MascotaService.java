package com.example.My_Pet.service;

import com.example.My_Pet.model.Mascota;
import com.example.My_Pet.repository.MascotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MascotaService {

    @Autowired
    private MascotaRepository mascotaRepository;

    // Método para listar todas las mascotas
    public List<Mascota> obtenerTodasLasMascotas() {
        return mascotaRepository.findAll();
    }

    // Método para buscar una mascota por su ID
    public Optional<Mascota> obtenerMascotaPorId(Integer idMascota) {
        return mascotaRepository.findById(idMascota);
    }

    // Método para guardar una mascota
    public Mascota guardarMascota(Mascota mascota) {
        return mascotaRepository.save(mascota);
    }

    // Método para eliminar una mascota
    public void eliminarMascota(Integer idMascota) {
        mascotaRepository.deleteById(idMascota);
    }
}