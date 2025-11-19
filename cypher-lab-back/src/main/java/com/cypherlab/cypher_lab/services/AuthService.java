package com.cypherlab.cypher_lab.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cypherlab.cypher_lab.dto.LoginDTO;
import com.cypherlab.cypher_lab.dto.RegisterDTO;
import com.cypherlab.cypher_lab.models.Usuario;
import com.cypherlab.cypher_lab.repository.UsuarioRepository;

@Service
public class AuthService {
    @Autowired
    private JwtService jwtService;

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
    public String loginUsuario(LoginDTO loginDTO) {
        var usuario = usuarioRepository.findByEmail(loginDTO.email())
            .orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));

        if (passwordEncoder.matches(loginDTO.senha(), usuario.getSenha())) {
            return "Login realizado com sucesso!";
        }

        throw new RuntimeException("Senha inválida!");
    }
}
