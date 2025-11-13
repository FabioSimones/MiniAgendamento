# Mini Sistema de Agendamento com Spring Boot + PostgreSQL + Flyway

Este projeto demonstra a criação de um **sistema simples de agendamento** utilizando:
- **Spring Boot** para desenvolvimento backend em Java
- **PostgreSQL** como banco de dados relacional
- **Flyway** para versionamento e migração de esquema de banco de dados

## 🎯 Objetivos do Projeto
- Implementar uma API REST para gerenciar agendamentos
- Utilizar boas práticas de arquitetura com Spring Boot
- Garantir consistência do banco de dados através de migrações automáticas
- Demonstrar integração entre camadas (Controller, Service, Repository)

## 🛠️ Tecnologias Utilizadas
- **Java 17+**
- **Spring Boot**
- **Spring Data JPA**
- **PostgreSQL**
- **Docker**
- **Flyway**
- **Maven**

## 📂 Estrutura do Projeto
- `controller/` → Endpoints REST para agendamentos
- `service/` → Regras de negócio
- `repository/` → Integração com banco de dados
- `model/` → Entidades JPA
- `resources/db/migration/` → Scripts de migração Flyway

## 🚀 Como Executar
1. Clone este repositório:
```bash
   git clone https://github.com/seu-usuario/seu-repo.git
```
2. Configure o banco de dados PostgreSQL e ajuste o application.properties.
3. Execute as migrações com Flyway (automático ao iniciar).
4. Inicie a aplicação.
5. Acesse os endpoints em ``` http://localhost:8080 ```

## 📌 Funcionalidades
- **Criar novos agendamentos**
- **Listar agendamentos existentes**
- **Atualizar informações de um agendamento**
- **Excluir agendamentos**
