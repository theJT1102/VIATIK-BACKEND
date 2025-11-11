package com.viatik.controller;

import com.viatik.dto.AuthTokenDTO;
import com.viatik.dto.LoginDTO;
import com.viatik.dto.UsuarioRegistroDTO;
import com.viatik.service.AuthService;
import com.viatik.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;
    private final UsuarioService usuarioService;

    public AuthController(AuthService authService, UsuarioService usuarioService) {
        this.authService = authService;
        this.usuarioService = usuarioService;
    }

    /**
     * 🔐 Login: genera JWT si las credenciales son válidas
     */
    @PostMapping("/login")
    public ResponseEntity<AuthTokenDTO> login(@RequestBody LoginDTO loginDto) {
        AuthTokenDTO tokenDto = authService.authenticate(loginDto);
        return ResponseEntity.ok(tokenDto);
    }

    /**
     * 🛡️ Registro de usuarios (solo ADMINISTRADOR puede hacerlo)
     */
    @PostMapping("/register")    
    public ResponseEntity<String> register(@RequestBody UsuarioRegistroDTO registroDto) {
        usuarioService.registerNewUser(registroDto);
        return new ResponseEntity<>("Usuario registrado exitosamente por Admin.", HttpStatus.CREATED);
    }
}