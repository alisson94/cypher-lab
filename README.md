# CypherLab API - Back-end de Segurança e Gamificação

![Java](https://img.shields.io/badge/Java-17%2B-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white)
![Gradle](https://img.shields.io/badge/Gradle-02303A?style=for-the-badge&logo=gradle&logoColor=white)

## 🎯 Sobre o Projeto

O **CypherLab API** é o motor back-end de uma plataforma educacional gamificada focada no ensino de Cibersegurança. Desenvolvido em Java com Spring Boot, este sistema gerencia a autenticação de usuários, a lógica de progressão (gamificação) e, crucialmente, a **validação segura** dos desafios práticos (flags).

O projeto segue uma **Arquitetura em Camadas** (*Layered Architecture*) para garantir desacoplamento, segurança e escalabilidade.

## 🛠️ Tecnologias Utilizadas

* **Linguagem:** Java 17+
* **Framework:** Spring Boot 3.5.6
* **Segurança:** Spring Security, JWT (JSON Web Token)
* **Persistência:** Spring Data JPA / Hibernate
* **Banco de Dados:** PostgreSQL
* **Gerenciador de Build:** Gradle

## 🏗️ Arquitetura e Estrutura

O código está organizado em pacotes que refletem responsabilidades distintas:

* `config`: Configurações de segurança (CORS, Filtros JWT) e inicialização de dados.
* `controllers`: Camada REST que recebe requisições HTTP e valida DTOs.
* `services`: Regras de negócio, lógica de pontuação e validação de hashes.
* `repository`: Interfaces de comunicação com o banco de dados (Spring Data JPA).
* `models`: Entidades JPA (Mapeamento das tabelas `users`, `challenges`, etc).
* `dto`: Objetos de transferência de dados (Data Transfer Objects).

## ⚙️ Configuração e Execução

### Pré-requisitos
* JDK 17+ instalado.
* PostgreSQL rodando localmente ou via Docker.
* Gradle (já incluído via wrapper).

### 1. Configuração do Banco de Dados
Crie um banco de dados vazio no seu PostgreSQL:

```sql
CREATE DATABASE cyberlabdb;
```

### 2. Variáveis de Ambiente
Configure as credenciais no arquivo `src/main/resources/application.properties`:

```properties
# Configuração do PostgreSQL
spring.datasource.url=jdbc:postgresql://localhost:5432/cyberlabdb
spring.datasource.username=seu_usuario_postgres
spring.datasource.password=sua_senha_postgres

# JPA / Hibernate
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
```

### 3. Executando o Projeto

No terminal, na raiz do projeto (`cypher-lab-back`), execute:

```bash
# Linux / Mac
./gradlew bootRun

# Windows
gradlew.bat bootRun
```

A API estará disponível em:

```
http://localhost:8080/api
```

---

## 🔒 Segurança e Validação de Desafios

A plataforma segue o princípio de **Security by Design**, implementando diversas proteções:

### 🔐 Proteção de Senhas
* Todas as senhas são criptografadas com **BCrypt**.
* Valores nunca são armazenados em texto puro.

### 🛡️ Integridade das Respostas (Flags)
As respostas corretas **não ficam salvas em texto plano**. O processo é:

1. O sistema normaliza a resposta (`trim()` + `toUpperCase()`).
2. Gera um hash **SHA-256**.
3. Compara o resultado com o `solution_hash` salvo no banco.

**Isso impede:**
* Engenharia reversa.
* Acesso indevido às respostas.
* Vazamento de flags em caso de dump do banco.

---

## 🔑 Principais Endpoints

### 🔐 Autenticação
| Método | Endpoint | Descrição |
|--------|----------|-----------|
| `POST` | `/api/auth/register` | Criar nova conta |
| `POST` | `/api/auth/login` | Login (retorna Bearer Token JWT) |

### 👤 Usuário (Requer Token JWT)
| Método | Endpoint | Descrição |
|--------|----------|-----------|
| `GET` | `/api/user/{userId}/progress` | Progresso nos desafios resolvidos |
| `GET` | `/api/user/{userId}/challenges/all` | Todos os desafios com status de progresso |
| `POST` | `/api/user/{userId}/challenges/{challengeId}/submit` | Enviar resposta do desafio |
| `GET` | `/api/user/{userId}/stats` | Estatísticas gerais do usuário |
| `GET` | `/api/user/{userId}/rank` | Posição no ranking global |

### 📚 Desafios (Público ou protegido)
| Método | Endpoint | Descrição |
|--------|----------|-----------|
| `GET` | `/api/challenges` | Listar todos os desafios |
| `GET` | `/api/challenges/{id}` | Detalhes de um desafio específico |
| `POST` | `/api/challenges/{id}/submit` | Validar resposta (sem contexto de usuário) |

### 📦 Módulos
| Método | Endpoint | Descrição |
|--------|----------|-----------|
| `GET` | `/api/modules` | Listar todos os módulos de aprendizado |
| `GET` | `/api/user/{userId}/modules/progress` | Progresso do usuário em todos os módulos |

---

## 🤝 Contribuição

Este é um projeto **Open Source** desenvolvido no contexto acadêmico.  
Sinta-se livre para abrir **Issues** ou enviar **Pull Requests**.

### Fluxo recomendado:

1. Faça um **Fork** do projeto.
2. Crie uma nova **Branch**:
   ```bash
   git checkout -b feat/nova-feature
   ```
3. Realize seus **commits** seguindo [Conventional Commits](https://www.conventionalcommits.org/):
   ```bash
   git commit -m "feat: adiciona nova feature"
   ```
4. Envie para seu repositório:
   ```bash
   git push origin feat/nova-feature
   ```
5. Abra um **Pull Request**.

---

## 🧑‍💻 Desenvolvedores

- Luiz Fernando Lessa Mineiro Albuquerque – [[@LFMineiro](https://github.com/LFMineiro)]
- Álisson Nunes Santana - [[@alisson94](https://github.com/alisson94)]
- Nivaldo Pereira da Silva Junior - [[@NivaJr](https://github.com/NivaJr)]
---

## 📧 Contato

Para dúvidas ou sugestões, abra uma **Issue** no repositório ou entre em contato através do GitHub.




