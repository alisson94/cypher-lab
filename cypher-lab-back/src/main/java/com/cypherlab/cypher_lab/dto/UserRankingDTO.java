package com.cypherlab.cypher_lab.dto;

public class UserRankingDTO {
    private String email;
    private Integer pontos;
    private Integer posicao;

    public UserRankingDTO(String email, Integer pontos, Integer posicao) {
        this.email = email;
        this.pontos = pontos;
        this.posicao = posicao;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getPontos() {
        return pontos;
    }

    public void setPontos(Integer pontos) {
        this.pontos = pontos;
    }

    public Integer getPosicao() {
        return posicao;
    }

    public void setPosicao(Integer posicao) {
        this.posicao = posicao;
    }
}
