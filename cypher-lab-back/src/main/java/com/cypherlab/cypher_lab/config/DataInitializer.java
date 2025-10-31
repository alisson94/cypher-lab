package com.cypherlab.cypher_lab.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import com.cypherlab.cypher_lab.models.Challenge;
import com.cypherlab.cypher_lab.repository.ChallengeRepository;

import java.util.Arrays;

@Component
public class DataInitializer implements CommandLineRunner {


    private final ChallengeRepository challengeRepository;

    public DataInitializer(ChallengeRepository challengeRepository) {
        this.challengeRepository = challengeRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        
        if(challengeRepository.count() > 0) {
            return;
        }
        
        Challenge desafio1 = new Challenge(
            "Cifra de César",
            "Descriptografe a seguinte mensagem cifrada com César (deslocamento 3): 'SBWKRQ'.<br><br>Instruções: Resolva manualmente. Analise a cifra de César, identifique o deslocamento e recupere a mensagem original letra por letra.",
            "Fácil",
            "Criptografia Clássica",
            "PYTHON",
            10
        );

        Challenge desafio2 = new Challenge(
            "Cifra de Vigenère",
            "Descriptografe a mensagem 'MTCQWSMOCMCBTEEET' utilizando a cifra de Vigenère com a chave 'MACACO'.<br><br>Instruções: Resolva de forma computacional ou manual. Aplique o algoritmo da cifra de Vigenère, utilizando a chave fornecida para decifrar cada letra da mensagem.",
            "Médio",
            "Criptografia Clássica",
            "ATAQUEAOAMANHECER",
            20
        );

        challengeRepository.saveAll(Arrays.asList(desafio1, desafio2));
    }
}
