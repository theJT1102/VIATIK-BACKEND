package com.viatik.security;

import com.viatik.entity.Usuario;
import com.viatik.repository.RolRepository;
import com.viatik.repository.UsuarioRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;
    // Necesitas el RolRepository para obtener el nombre del rol (ADMINISTRADOR, etc.)
    private final RolRepository rolRepository; 
    
    public CustomUserDetailsService(UsuarioRepository usuarioRepository, RolRepository rolRepository) {
        this.usuarioRepository = usuarioRepository;
        this.rolRepository = rolRepository;
    }

    /**
     * Carga el usuario desde la BD y construye el objeto UserDetails requerido por Spring Security.
     * @param username El nombre de usuario (ej: 'juan.perez')
     * @return UserDetails objeto que contiene el nombre de usuario, hash de contraseña y rol/autoridades.
     * @throws UsernameNotFoundException si el usuario no existe.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 1. Buscar el usuario por username en tu BD
        Usuario usuario = usuarioRepository.findByUsername(username);

        if (usuario == null) {
            throw new UsernameNotFoundException("Usuario no encontrado con username: " + username);
        }

        // 2. Obtener el nombre del Rol usando la FK (id_rol)
        String nombreRol = rolRepository.findById(usuario.getIdRol())
                                        .map(rol -> rol.getNombreRol())
                                        .orElseThrow(() -> new RuntimeException("Rol asociado no encontrado"));

        // 3. Convertir el Rol a la estructura de Authority requerida por Spring Security
        // Se añade el prefijo "ROLE_" que es estándar en Spring Security
        List<GrantedAuthority> authorities = Collections.singletonList(
            new SimpleGrantedAuthority("ROLE_" + nombreRol.toUpperCase())
        );

        // 4. Crear y devolver el objeto UserDetails (Clase User de Spring Security)
        return new User(
            usuario.getUsername(),
            usuario.getPasswordHash(),
            authorities
        );
    }
}