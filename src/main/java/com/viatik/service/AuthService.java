package com.viatik.service;

import com.viatik.dto.AuthTokenDTO;
import com.viatik.dto.LoginDTO;
import com.viatik.entity.Usuario;
import com.viatik.repository.UsuarioRepository;
import com.viatik.repository.RolRepository;
import com.viatik.security.JwtService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UsuarioRepository usuarioRepository,
                       RolRepository rolRepository,
                       JwtService jwtService,
                       PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.rolRepository = rolRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
    }

    public AuthTokenDTO authenticate(LoginDTO loginDto) {
        Usuario user = usuarioRepository.findByUsername(loginDto.getUsername());
        if (user == null || !passwordEncoder.matches(loginDto.getPassword(), user.getPasswordHash())) {
            throw new RuntimeException("Credenciales inválidas.");
        }

        // Recuperar rol por idRol
        String rolNombre;
        if (user.getIdRol() == null) {
            throw new RuntimeException("Usuario sin rol asignado. Contacta al administrador.");
        } else {
            rolNombre = rolRepository.findById(user.getIdRol())
                    .map(r -> r.getNombreRol())
                    .orElseThrow(() -> new RuntimeException("Rol no encontrado para idRol=" + user.getIdRol()));
        }

        // Normalizar formato (sin prefijos innecesarios)
        rolNombre = rolNombre.toUpperCase();

        Map<String, Object> claims = new HashMap<>();
        claims.put("rol", rolNombre);
        claims.put("idUsuario", user.getIdUsuario());

        String token = jwtService.generateToken(claims, user.getUsername());

        AuthTokenDTO dto = new AuthTokenDTO();
        dto.setToken(token);
        dto.setRolPrincipal(rolNombre);
        dto.setIdUsuario(user.getIdUsuario());
        dto.setNombreCompleto(user.getNombreCompleto());

        return dto;
    }
}