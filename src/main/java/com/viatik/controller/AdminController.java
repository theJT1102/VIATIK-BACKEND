package com.viatik.controller;

import com.viatik.dto.PerfilCreacionDTO;
import com.viatik.dto.PerfilRespuestaDTO;
import com.viatik.entity.LimiteMovilidad;
import com.viatik.entity.Usuario;
import com.viatik.service.PerfilService;
import com.viatik.service.CatalogoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
// Nota: Todo este controller debería estar protegido para el rol ADMINISTRADOR
public class AdminController {

    private final PerfilService perfilService;
    private final CatalogoService catalogoService; // Para gestionar límites y catálogos

    public AdminController(PerfilService perfilService, CatalogoService catalogoService) {
        this.perfilService = perfilService;
        this.catalogoService = catalogoService;
    }

    // --- Gestión de Perfiles ---

    @PostMapping("/perfiles")
    public ResponseEntity<PerfilRespuestaDTO> crearPerfil(@RequestBody PerfilCreacionDTO dto) {
        PerfilRespuestaDTO nuevoPerfil = perfilService.crearPerfil(dto);
        return new ResponseEntity<>(nuevoPerfil, HttpStatus.CREATED);
    }

    @PutMapping("/perfiles/{id}")
    public ResponseEntity<PerfilRespuestaDTO> actualizarPerfil(@PathVariable Integer id, @RequestBody PerfilCreacionDTO dto) {
        PerfilRespuestaDTO perfilActualizado = perfilService.actualizarPerfil(id, dto);
        return ResponseEntity.ok(perfilActualizado);
    }
    
    // --- Gestión de Límites de Movilidad ---

    @GetMapping("/limites/movilidad")
    public ResponseEntity<List<LimiteMovilidad>> getLimitesMovilidad() {
        return ResponseEntity.ok(catalogoService.findAllLimitesMovilidad());
    }

    @PutMapping("/limites/movilidad/{idCategoria}")
    public ResponseEntity<LimiteMovilidad> updateLimiteMovilidad(@PathVariable Integer idCategoria, @RequestBody LimiteMovilidad limite) {
        // Se asume que LimiteMovilidad tiene los campos limiteSoles y limiteDolares
        LimiteMovilidad actualizado = catalogoService.updateLimiteMovilidad(idCategoria, limite);
        return ResponseEntity.ok(actualizado);
    }
    
    // --- Gestión de Usuarios (Adicional, para ver lista) ---
    @GetMapping("/usuarios")
    public ResponseEntity<List<Usuario>> getAllUsuarios() {
        return ResponseEntity.ok(catalogoService.findAllUsuarios());
    }
}