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
* **Framework:** Spring Boot 3.2.0
* **Segurança:** Spring Security, JWT (JSON Web Token)
* **Persistência:** Spring Data JPA / Hibernate
* **Banco de Dados:** PostgreSQL
* **Gerenciador de Build:** Gradle

## 🏗️ Arquitetura e Estrutura

O código está organizado em pacotes que refletem responsabilidades distintas:

* `config`: Configurações de segurança (CORS, Filtros JWT) e inicialização de dados.
* `controllers`: Camada REST que recebe requisições HTTP e valida DTOs.
* `services`: Regras de negócio, lógica de pontuação e validação de hashes.
* `repository`: Interfaces de comunicação com o banco de dados.
* `models`: Entidades JPA (Mapeamento das tabelas `users`, `challenges`, etc).
* `dto`: Objetos de transferência de dados (Data Transfer Objects).

## ⚙️ Configuração e Execução

### Pré-requisitos
* JDK 17 instalado.
* PostgreSQL rodando.

### 1. Configuração do Banco de Dados
Crie um banco de dados vazio no seu PostgreSQL:

```sql
CREATE DATABASE cypherlab_db;
```




