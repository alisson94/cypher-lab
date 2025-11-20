package com.cypherlab.cypher_lab.services;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cypherlab.cypher_lab.dto.UserRankingDTO;
import com.cypherlab.cypher_lab.models.Usuario;
import com.cypherlab.cypher_lab.repository.UsuarioRepository;

@Service
public class UserService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<UserRankingDTO> getRanking() {
        List<Usuario> usuarios = usuarioRepository.findAllByOrderByPontosDesc();
        
        return IntStream.range(0, usuarios.size())
            .mapToObj(i -> new UserRankingDTO(
                usuarios.get(i).getId(),
                usuarios.get(i).getEmail(),
                usuarios.get(i).getPontos(),
                i + 1
            ))
            .collect(Collectors.toList());
    }
}
