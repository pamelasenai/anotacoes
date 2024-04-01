![logo](images/logo.png)

# Projeto Anotações
#### _Exercícios da Semana 11 - (25/03/2024 a 29/03/2024)_

## ✏️ Descrição
Este projeto Java baseado em Spring é uma aplicação de gerenciamento de notas desenvolvida utilizando as tecnologias:
- Spring-Security, 
- JPA, 
- JWT, 
- OAuth2, 
- REST, 
- H2-Database.

## 📌 Entidades do Projeto
### Caderno
- id
- nome
- id_usuario

### Notas
- id
- titulo
- conteúdo
- id_caderno
- id_usuario

## Usuário
- id
- nome
- senha

---
## 🎯 Endpoints
### CREATE
- POST ```/cadernos```
- POST ```/notas```
- POST ```/usuarios```

### READ
- GET ```/cadernos```
- GET ```/notas```
- GET ```/usuarios``` 

### UPDATE
- PUT ```/cadernos```
- PUT ```/notas```
- PUT ```/usuarios```

### DELETE
- DELETE ```/cadernos```
- DELETE ```/notas```
- DELETE ```/usuarios```

--- 
## 📋 Todo List
- [x] [Exercício 1 - Setup do Projeto](#-m1s11-ex-1---setup-do-projeto-anotações)
- [x] [Ex 2 - CRUD Caderno](#-m1s11-ex-2---crud-caderno)
- [x] [Ex 3 - CRUD Notas](#-m1s11-ex-3---crud-notas)
- [x] [Ex 4 - Configuração Inicial de Segurança](#-m1s11-ex-4---configuração-inicial-de-segurança)
- [x] [Ex 5 - JWT na Segurança](#-m1s11-ex-5---jwt-na-segurança)
- [x] [Ex 6 - Endpoint de Cadastro Usuário](#-m1s11-ex-6---endpoint-de-cadastro-usuário)
- [x] [Ex 7 - Endpoint de Login](#-m1s11-ex-7---endpoint-de-login)
- [x] [Ex 8 - Validação de Dono](#-m1s11-ex-8---validação-de-dono)

## 📂 Descrição dos exercícios
### 📖 [M1S11] Ex 1 - Setup do Projeto Anotações
O projeto será um CRUD e terá 3 (três) entidades:
- Caderno: id, nome, id_usuario;
- Nota: id, title, content, id_caderno, id_usuario;
- Usuario: essa será demonstrada em aula.

### 📖 [M1S11] Ex 2 - CRUD Caderno
Crie o CRUD para Caderno:
- Busca de todos os Cadernos;
- Busca de Caderno por id;
- Update de Caderno;
- Delete de Caderno.

### 📖 [M1S11] Ex 3 - CRUD Notas
Crie o CRUD para Nota:
- Busca de todos os Notas;
- Busca de Nota por id;
- Update de Nota;
- Delete de Nota.

### 📖 [M1S11] Ex 4 - Configuração Inicial de Segurança
Crie as classes de configuração de segurança. <br/>
Essa classe deve permitir o acesso irrestrito a uma rota /test, que retorna um texto "TESTE".

### 📖 [M1S11] Ex 5 - JWT na Segurança
Alterar as configurações de segurança para usar JWT, onde teremos a geração e a validação de JWT.

### 📖 [M1S11] Ex 6 - Endpoint de Cadastro Usuário
Crie um endpoint de Cadastrar usuário, siga o exemplo de aula!

### 📖 [M1S11] Ex 7 - Endpoint de Login
Crie um endpoint de Login de usuário.
- Esse deve retornar um JWT.

### 📖 [M1S11] Ex 8 - Validação de Dono
Adicione uma validação, se o user que está acessando os enpoints CRUD é dono de um Caderno ou de uma Nota. <br/>
Sendo assim, apenas pode consultar os próprios dados.