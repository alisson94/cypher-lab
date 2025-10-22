package com.cypherlab.cypher_lab.services;

import com.cypherlab.cypher_lab.dto.RegisterDTO;
import com.cypherlab.cypher_lab.models.Usuario;
import com.cypherlab.cypher_lab.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void registrarUsuario(RegisterDTO registerDTO) {
        if (usuarioRepository.findByEmail(registerDTO.email()).isPresent()) {
            throw new RuntimeException("Email já cadastrado");
        }

        String senhaCriptografada = passwordEncoder.encode(registerDTO.senha());
        Usuario novoUsuario = new Usuario();
        novoUsuario.setEmail(registerDTO.email());
        novoUsuario.setSenha(senhaCriptografada);
        novoUsuario.setPontos(0);
        usuarioRepository.save(novoUsuario);
    }
}
