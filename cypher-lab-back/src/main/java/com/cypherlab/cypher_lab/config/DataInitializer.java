package com.cypherlab.cypher_lab.config;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.cypherlab.cypher_lab.models.Challenge;
import com.cypherlab.cypher_lab.models.ChallengeModule;
import com.cypherlab.cypher_lab.models.Usuario;
import com.cypherlab.cypher_lab.repository.ChallengeModuleRepository;
import com.cypherlab.cypher_lab.repository.ChallengeRepository;
import com.cypherlab.cypher_lab.repository.UsuarioRepository;
import com.cypherlab.cypher_lab.services.ChallengeService;

@Component
public class DataInitializer implements CommandLineRunner {

    private final ChallengeRepository challengeRepository;
    private final ChallengeModuleRepository challengeModuleRepository;
    private final ChallengeService challengeService;
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(ChallengeRepository challengeRepository, 
                          ChallengeModuleRepository challengeModuleRepository,
                          ChallengeService challengeService,
                          UsuarioRepository usuarioRepository,
                          PasswordEncoder passwordEncoder) {
        this.challengeRepository = challengeRepository;
        this.challengeModuleRepository = challengeModuleRepository;
        this.challengeService = challengeService;
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        
        // ==========================================
        // CRIAR USUÁRIOS COM DIFERENTES PONTUAÇÕES
        // ==========================================
        
        if (usuarioRepository.count() == 0) {
            // Admin
            Usuario admin = new Usuario();
            admin.setEmail("admin@admin.com");
            admin.setSenha(passwordEncoder.encode("admin"));
            admin.setUsername("admin");
            admin.setRole("ADMIN");
            admin.setPontos(0);
            
            // Usuários com diferentes perfis e pontuações
            Usuario user1 = new Usuario();
            user1.setEmail("alice@example.com");
            user1.setSenha(passwordEncoder.encode("senha123"));
            user1.setUsername("AliceHacker");
            user1.setRole("USER");
            user1.setPontos(350);
            
            Usuario user2 = new Usuario();
            user2.setEmail("bob@example.com");
            user2.setSenha(passwordEncoder.encode("senha123"));
            user2.setUsername("BobCrypto");
            user2.setRole("USER");
            user2.setPontos(280);
            
            Usuario user3 = new Usuario();
            user3.setEmail("carol@example.com");
            user3.setSenha(passwordEncoder.encode("senha123"));
            user3.setUsername("CarolSec");
            user3.setRole("USER");
            user3.setPontos(195);
            
            Usuario user4 = new Usuario();
            user4.setEmail("dave@example.com");
            user4.setSenha(passwordEncoder.encode("senha123"));
            user4.setUsername("DaveLinux");
            user4.setRole("USER");
            user4.setPontos(150);
            
            Usuario user5 = new Usuario();
            user5.setEmail("eve@example.com");
            user5.setSenha(passwordEncoder.encode("senha123"));
            user5.setUsername("EveNewbie");
            user5.setRole("USER");
            user5.setPontos(75);
            
            Usuario user6 = new Usuario();
            user6.setEmail("frank@example.com");
            user6.setSenha(passwordEncoder.encode("senha123"));
            user6.setUsername("FrankMaster");
            user6.setRole("USER");
            user6.setPontos(420);
            
            Usuario user7 = new Usuario();
            user7.setEmail("grace@example.com");
            user7.setSenha(passwordEncoder.encode("senha123"));
            user7.setUsername("GraceWeb");
            user7.setRole("USER");
            user7.setPontos(310);
            
            usuarioRepository.saveAll(Arrays.asList(admin, user1, user2, user3, user4, user5, user6, user7));
        }
        
        if(challengeRepository.count() > 0) {
            return;
        }

        // 1. Criar e salvar os módulos primeiro
        ChallengeModule moduloCriptografiaClassica = new ChallengeModule("Criptografia Clássica");
        moduloCriptografiaClassica.setIcon(1);
        moduloCriptografiaClassica = challengeModuleRepository.save(moduloCriptografiaClassica);

        // 2. Criar os desafios associando ao módulo salvo
        Challenge desafio1 = new Challenge(
            "Cifra de César",
            "Descriptografe a seguinte mensagem cifrada com César (deslocamento 3): <code>SBWKRQ</code>.<br><br><b>Instruções:</b> Resolva manualmente. Analise a cifra de César, identifique o deslocamento e recupere a mensagem original letra por letra.",
            "Fácil",
            moduloCriptografiaClassica,
            challengeService.hashSubmission("PYTHON"),
            10
        );

        Challenge desafio2 = new Challenge(
            "Cifra de Vigenère",
            "Descriptografe a mensagem <code>MTCQWSMOCMCBTEEET</code> utilizando a cifra de Vigenère com a chave <b>'MACACO'</b>.<br><br><b>Dica:</b> A chave se repete até cobrir todo o texto.",
            "Médio",
            moduloCriptografiaClassica,
            challengeService.hashSubmission("ATAQUEAOAMANHECER"),
            20
        );
        
        Challenge desafio3 = new Challenge(
            "ROT13",
            "O ROT13 é um caso especial da Cifra de César onde o deslocamento é sempre 13. Decifre: <code>PBQRVFYVSR</code>.",
            "Fácil",
            moduloCriptografiaClassica,
            challengeService.hashSubmission("CODEISLIFE"),
            10
        );

        ChallengeModule moduloEncoding = new ChallengeModule("Codificação e Hashing");
        moduloEncoding.setIcon(2);
        moduloEncoding = challengeModuleRepository.save(moduloEncoding);

        Challenge desafio4 = new Challenge(
            "Decodificando Base64",
            "Base64 não é criptografia, é codificação! Converta a seguinte string de volta para texto legível: <code>Q3lwaGVyTGFi</code>",
            "Fácil",
            moduloEncoding,
            challengeService.hashSubmission("CYPHERLAB"),
            10
        );

        Challenge desafio5 = new Challenge(
            "Identificando Hash MD5",
            "Você encontrou um hash num banco de dados vazado: <code>e10adc3949ba59abbe56e057f20f883e</code>. É um hash muito comum de uma senha numérica simples. Qual é a senha original?",
            "Médio",
            moduloEncoding,
            challengeService.hashSubmission("123456"),
            25
        );

        ChallengeModule moduloWebSec = new ChallengeModule("Segurança Web (OWASP)");
        moduloWebSec.setIcon(3);
        moduloWebSec = challengeModuleRepository.save(moduloWebSec);

        Challenge desafio6 = new Challenge(
            "Inspeção de Elemento",
            "Desenvolvedores às vezes deixam comentários no código HTML que não deveriam estar lá. <!-- A flag está escondida aqui: WEB_MASTER_DEV --> <br><br>Qual é a flag?",
            "Fácil",
            moduloWebSec,
            challengeService.hashSubmission("WEB_MASTER_DEV"),
            10
        );

        Challenge desafio7 = new Challenge(
            "SQL Injection Básico",
            "Em um formulário de login vulnerável, qual a string clássica usada para retornar 'Verdadeiro' e bypassar a senha?<br> Exemplo: <code>' OR 1=1 --</code>. <br>Envie apenas a parte condicional numérica: <code>1=1</code>",
            "Médio",
            moduloWebSec,
            challengeService.hashSubmission("1=1"),
            30
        );

        ChallengeModule moduloLinux = new ChallengeModule("Linux e Forense");
        moduloLinux.setIcon(4);
        moduloLinux = challengeModuleRepository.save(moduloLinux);

        Challenge desafio8 = new Challenge(
            "Permissões Linux",
            "Você precisa dar permissão total (leitura, escrita e execução) para todos os usuários em um arquivo. Qual o código numérico do comando <code>chmod</code> para isso?",
            "Fácil",
            moduloLinux,
            challengeService.hashSubmission("777"),
            15
        );

        Challenge desafio9 = new Challenge(
            "Comando Grep",
            "Qual comando Linux é utilizado para buscar uma string específica dentro de um arquivo de texto?",
            "Fácil",
            moduloLinux,
            challengeService.hashSubmission("GREP"),
            10
        );

        // 3. Salvar todos os desafios
        challengeRepository.saveAll(Arrays.asList(desafio1, desafio2, desafio3, desafio4, desafio5, desafio6, desafio7, desafio8, desafio9));
    }
}
