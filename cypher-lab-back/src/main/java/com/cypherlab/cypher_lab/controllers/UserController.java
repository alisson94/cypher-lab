package com.cypherlab.cypher_lab.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cypherlab.cypher_lab.dto.UserRankingDTO;
import com.cypherlab.cypher_lab.services.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/info")
    public ResponseEntity<?> getUserInfo() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        Map<String, Object> response = new HashMap<>();
        response.put("email", auth.getName());
        response.put("status", "Autenticado com sucesso");
        response.put("mensagem", "Estes dados vieram do servidor Java via JWT!");

        return ResponseEntity.ok(response);
    }

    @GetMapping("/ranking")
    public ResponseEntity<List<UserRankingDTO>> getRanking() {
        List<UserRankingDTO> ranking = userService.getRanking();
        return ResponseEntity.ok(ranking);
    }
}