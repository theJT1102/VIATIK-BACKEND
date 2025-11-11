package com.viatik.service;

import com.viatik.dto.PerfilCreacionDTO;
import com.viatik.dto.PerfilRespuestaDTO;
import com.viatik.entity.Perfil;
import com.viatik.repository.PerfilRepository;
import org.springframework.stereotype.Service;

@Service
public class PerfilService {

    private final PerfilRepository perfilRepository;
    private final JsonService jsonService;
    // ... Repositorios de Empresas, Usuarios, etc. para validación y mapeo

    public PerfilService(PerfilRepository perfilRepository, JsonService jsonService) {
        this.perfilRepository = perfilRepository;
        this.jsonService = jsonService;
    }

    public PerfilRespuestaDTO crearPerfil(PerfilCreacionDTO dto) {
        // **VALIDACIONES** (ej: límites de usuarios, existencia de IDs de recursos)
        
        Perfil nuevoPerfil = new Perfil();
        nuevoPerfil.setNombre(dto.getNombre());
        nuevoPerfil.setDescripcion(dto.getDescripcion());
        nuevoPerfil.setIdEmpresa(dto.getIdEmpresa());

        // Mapear DTOs a JsonNode para guardar
        nuevoPerfil.setUsuariosAsignados(jsonService.listToJsonNode(dto.getUsuarios()));
        nuevoPerfil.setAlmacenesAsignados(jsonService.listToJsonNode(dto.getAlmacenes()));
        nuevoPerfil.setCategoriasAsignadas(jsonService.listToJsonNode(dto.getCategorias()));
        nuevoPerfil.setProyectosAsignados(jsonService.listToJsonNode(dto.getProyectos()));

        Perfil saved = perfilRepository.save(nuevoPerfil);

        // Retornar un DTO de respuesta que puede incluir nombres en lugar de solo IDs
        return mapToRespuestaDTO(saved);
    }
    
    public PerfilRespuestaDTO actualizarPerfil(Integer id, PerfilCreacionDTO dto) {
        Perfil existing = perfilRepository.findById(id)
                                          .orElseThrow(() -> new RuntimeException("Perfil no encontrado"));
        
        // ... Lógica similar a crear, pero aplicando actualizaciones
        
        Perfil updated = perfilRepository.save(existing);
        return mapToRespuestaDTO(updated);
    }

    private PerfilRespuestaDTO mapToRespuestaDTO(Perfil perfil) {
        // Lógica de mapeo: convertir JsonNode de vuelta a listas DTO o buscar nombres
        // Por ejemplo, buscar el nombre de la empresa usando perfil.getIdEmpresa()
        PerfilRespuestaDTO dto = new PerfilRespuestaDTO();
        dto.setIdPerfil(perfil.getIdPerfil());
        dto.setNombre(perfil.getNombre());
        // ... conversión de JsonNode a List<Integer> y List<UsuarioAsignacionDTO>
        return dto;
    }
}