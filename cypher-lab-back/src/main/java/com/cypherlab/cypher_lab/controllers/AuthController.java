package com.cypherlab.cypher_lab.controllers; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cypherlab.cypher_lab.dto.AuthResponse;
import com.cypherlab.cypher_lab.dto.LoginDTO;
import com.cypherlab.cypher_lab.dto.RegisterDTO;
import com.cypherlab.cypher_lab.models.Usuario;
import com.cypherlab.cypher_lab.repository.UsuarioRepository;
import com.cypherlab.cypher_lab.services.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterDTO data) {
        try {
            authService.registrarUsuario(data);
            return ResponseEntity.ok().body("Usuário registrado com sucesso!");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO data) {
        try {
            // Tenta fazer o login e obter o token
            String token = authService.loginUsuario(data);
            
            // Busca dados adicionais do usuário para retornar junto
            Usuario usuario = usuarioRepository.findByEmail(data.email()).orElseThrow();
            
            // Retorna o objeto com token e dados
            AuthResponse response = new AuthResponse(token, usuario.getEmail(),usuario.getUsername(), usuario.getId(), usuario.getRole());
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}