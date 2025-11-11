package com.viatik.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.viatik.entity.LimiteMovilidad;
import com.viatik.entity.Usuario;
import com.viatik.repository.LimiteMovilidadRepository;
import com.viatik.repository.UsuarioRepository;

@Service
public class CatalogoService {

    private final LimiteMovilidadRepository limiteRepository;
    private final UsuarioRepository usuarioRepository;
    // ... otros Repositories de Catálogo

    public CatalogoService(LimiteMovilidadRepository limiteRepository, UsuarioRepository usuarioRepository) {
        this.limiteRepository = limiteRepository;
        this.usuarioRepository = usuarioRepository;
    }

    // --- Métodos de Límites (ADMIN) ---

    public List<LimiteMovilidad> findAllLimitesMovilidad() {
        return limiteRepository.findAll();
    }

    public LimiteMovilidad updateLimiteMovilidad(Integer idCategoria, LimiteMovilidad limite) {
        // Lógica de actualización y validación de campos
        LimiteMovilidad existing = limiteRepository.findByIdCategoria(idCategoria);
        if (existing == null) {
            throw new RuntimeException("Límite de movilidad no encontrado.");
        }
        existing.setLimiteSoles(limite.getLimiteSoles());
        existing.setLimiteDolares(limite.getLimiteDolares());
        return limiteRepository.save(existing);
    }
    
    // --- Métodos de Usuarios (ADMIN) ---
    public List<Usuario> findAllUsuarios() {
        return usuarioRepository.findAll();
    }
}