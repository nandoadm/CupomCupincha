# 🚀 Projeto Spring Boot com Docker e PostgreSQL

Este projeto é uma aplicação web desenvolvida com Java 17, Spring Boot e Maven, utilizando Docker para facilitar o ambiente de desenvolvimento e PostgreSQL como banco de dados.

---

## 📋 Pré-requisitos

Antes de iniciar, é necessário ter instalado:

- ✅ Java 17+
- ✅ Maven 3.8+
- ✅ Docker e Docker Compose
- ✅ Git

---
### 📋 Fique Atento!

Antes de executar os comandos, crie um arquivo .env e altere as variaveis de ambiente(use suas informaçoes):

---

## ✅ Resultado

# Banco de dados

POSTGRES_DB=loginform

POSTGRES_USER=SeuUsuario

POSTGRES_PASSWORD=SuaSenha

# Spring Boot datasource
SPRING_DATASOURCE_URL=jdbc:postgresql://db:5432/loginform

SPRING_DATASOURCE_USERNAME=SeuUsuario

SPRING_DATASOURCE_PASSWORD=SuaSenha

SPRING_JPA_HIBERNATE_DDL_AUTO=update

---

Quer que eu atualize o README completo com isso embutido?



## 📦 Passo a passo para rodar o projeto

### 1. Clonar o repositório

```bash
git clone https://github.com/nandoadm/CupomCupincha.git
cd projeto
```

### 2. Gerar o .jar da aplicação

```bash
mvn clean package -DskipTests
```

### 3. Subir os containers com Docker Compose

```bash
docker-compose up --build
```

### 4. Acessar a aplicação no navegador
```bash
http://localhost:8080
```


