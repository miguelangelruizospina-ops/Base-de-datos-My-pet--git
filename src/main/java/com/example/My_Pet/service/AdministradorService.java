package com.example.My_Pet.service;

import com.example.My_Pet.model.Administrador;
import com.example.My_Pet.model.Usuario;
import com.example.My_Pet.repository.AdministradorRepository;
import com.example.My_Pet.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// @Service define que aquí va la lógica operativa de la aplicación
@Service
public class AdministradorService {

    // @Autowired realiza la "Inyección de Dependencias", trayendo las herramientas de consulta
    @Autowired
    private AdministradorRepository administradorRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Retorna la lista completa de administradores registrados
    public List<Administrador> obtenerTodos() {
        return administradorRepository.findAll();
    }

    // Busca un administrador por su ID de usuario; si no existe, devuelve null de forma controlada
    public Administrador obtenerPorUsuario(Integer idUsuario) {
        return administradorRepository.findByUsuarioIdUsuario(idUsuario).orElse(null);
    }

    // Registra o actualiza un administrador aplicando reglas de validación
    public Administrador guardar(Administrador administrador) {
        
        // CORRECCIÓN DE PRIMITIVO: Validamos si el objeto usuario es nulo o si su ID es inválido (menor o igual a cero)
        if (administrador.getUsuario() == null || administrador.getUsuario().getIdUsuario() <= 0) {
            throw new IllegalArgumentException("El administrador debe estar ligado a un usuario válido.");
        }

        Integer idUsuario = administrador.getUsuario().getIdUsuario();
        
        // Verifica en la tabla de usuarios si el ID enviado realmente existe en el sistema
        Usuario usuarioExistente = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new IllegalArgumentException("El usuario con ID " + idUsuario + " no existe."));

        // Al verificar que todo está en orden, se asocia el usuario real y se guarda en la BD
        administrador.setUsuario(usuarioExistente);
        return administradorRepository.save(administrador);
    }

    // Elimina el rango de administrador por su ID principal
    public void eliminar(Integer id) {
        administradorRepository.deleteById(id);
    }
}