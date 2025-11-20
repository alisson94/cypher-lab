package com.cypherlab.cypher_lab.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cypherlab.cypher_lab.models.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);
    
    Optional<Usuario> findByUsername(String username);
    
    Long countByPontosGreaterThan(Integer pontos);
    
    List<Usuario> findByRoleNotOrderByPontosDesc(String role);
}
