package com.viatik.service;

import com.viatik.dto.UsuarioRegistroDTO;
import com.viatik.entity.Usuario;
import com.viatik.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository,
                          PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Usuario registerNewUser(UsuarioRegistroDTO registroDto) {
        // Validación básica (puedes extenderla con verificación de duplicados, etc.)
        if (usuarioRepository.findByUsername(registroDto.getUsername()) != null) {
            throw new RuntimeException("El nombre de usuario ya está registrado.");
        }

        Usuario newUser = new Usuario();
        newUser.setUsername(registroDto.getUsername());
        newUser.setPasswordHash(passwordEncoder.encode(registroDto.getPassword())); // ✅ Encriptado real
        newUser.setNombreCompleto(registroDto.getNombreCompleto());
        newUser.setIdRol(registroDto.getIdRol());
        newUser.setIdEmpresa(registroDto.getIdEmpresa());

        return usuarioRepository.save(newUser);
    }
}