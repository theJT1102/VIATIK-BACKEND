package com.viatik.controller;

import com.viatik.entity.CategoriaGasto;
import com.viatik.entity.LimiteMovilidad;
import com.viatik.entity.Usuario;
import com.viatik.service.CatalogoService;
import com.viatik.repository.CategoriaGastoRepository;

import com.viatik.repository.CentroCostoRepository;
import com.viatik.repository.ArticuloRepository;
import com.viatik.repository.PerfilRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/catalogos")
public class CatalogoController {

    private final CatalogoService catalogoService;
    private final PerfilRepository perfilRepository;
    private final CategoriaGastoRepository categoriaGastoRepository;
    
    private final CentroCostoRepository centroCostoRepository;
    private final ArticuloRepository articuloRepository;

    public CatalogoController(
            CatalogoService catalogoService,
            PerfilRepository perfilRepository,
            CategoriaGastoRepository categoriaGastoRepository,
            
            CentroCostoRepository centroCostoRepository,
            ArticuloRepository articuloRepository
    ) {
        this.catalogoService = catalogoService;
        this.perfilRepository = perfilRepository;
        this.categoriaGastoRepository = categoriaGastoRepository;
        
        this.centroCostoRepository = centroCostoRepository;
        this.articuloRepository = articuloRepository;
    }

    // --- Perfiles (para el combobox de perfiles asignados al usuario) ---
    @GetMapping("/perfiles")
    public ResponseEntity<List<?>> getPerfiles() {
        // Perfil entity/repository puede variar; devolvemos lo que encuentre el repo
        List<?> perfiles = perfilRepository.findAll();
        return ResponseEntity.ok(perfiles);
    }

    // --- Categorías de gasto ---
    @GetMapping("/categorias-gasto")
    public ResponseEntity<List<CategoriaGasto>> getCategoriasGasto() {
        List<CategoriaGasto> categorias = categoriaGastoRepository.findAll();
        return ResponseEntity.ok(categorias);
    }

    // --- Categorías de movilidad ---
    

    // --- Centros de costo ---
    @GetMapping("/centros-costo")
    public ResponseEntity<List<?>> getCentrosCosto() {
        List<?> centros = centroCostoRepository.findAll();
        return ResponseEntity.ok(centros);
    }

    // --- Artículos (para autocompletar buscador) ---
    @GetMapping("/articulos")
    public ResponseEntity<List<?>> getArticulos(@RequestParam(value = "q", required = false) String q) {
        if (q == null || q.isEmpty()) {
            return ResponseEntity.ok(articuloRepository.findAll());
        }
        // Si tu repo tiene método de búsqueda por código/nombre, úsalo; ejemplo:
        List<?> encontrados = articuloRepository.findByNombreContainingIgnoreCaseOrCodigoContainingIgnoreCase(q, q);
        return ResponseEntity.ok(encontrados);
    }

    // --- Límites de movilidad (para validaciones de máximo diario) ---
    @GetMapping("/movilidad-limites")
    public ResponseEntity<List<LimiteMovilidad>> getLimitesMovilidad() {
        List<LimiteMovilidad> limites = catalogoService.findAllLimitesMovilidad();
        return ResponseEntity.ok(limites);
    }

    // --- Usuarios (útil para admin / seleccionar rendidor, ejemplo) ---
    @GetMapping("/usuarios")
    public ResponseEntity<List<Usuario>> getUsuarios() {
        List<Usuario> usuarios = catalogoService.findAllUsuarios();
        return ResponseEntity.ok(usuarios);
    }
}