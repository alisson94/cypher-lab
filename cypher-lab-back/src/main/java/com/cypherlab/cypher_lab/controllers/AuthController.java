package com.cypherlab.cypher_lab.controllers; // Garanta que o pacote esteja correto!

import com.cypherlab.cypher_lab.dto.LoginDTO;
import com.cypherlab.cypher_lab.dto.RegisterDTO;
import com.cypherlab.cypher_lab.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth") // Este é o /auth da URL
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register") // Este é o /register da URL
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
            String response = authService.loginUsuario(data);
            return ResponseEntity.ok().body(response);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}