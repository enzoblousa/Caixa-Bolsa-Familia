<img width="1141" height="937" alt="image" src="https://github.com/user-attachments/assets/fcebdc07-8cf6-46db-b7a9-b4aaf8a0618c" />

# Sistema de Gestão de Benefícios(Bolsa Família) - Caixa Econômica Federal

## 📌 Descrição
Sistema **backend** para gestão de beneficiários de programas sociais, desenvolvido em **Java com Spring Boot**.  
Esta API permite o **cadastro, consulta, atualização e gerenciamento** de beneficiários com controle de status (**ativo/inativo**).

---

## 🚀 Tecnologias Utilizadas
- Java 17  
- Spring Boot 3.2.4  
- Spring Data JPA  
- H2 Database (desenvolvimento)  
- Maven  
- Lombok  
- Validation API  

---

## ⚙️ Funcionalidades
- Cadastro de beneficiários com validação de **NIS único**  
- Listagem de todos os beneficiários  
- Filtros por **status** (ativos/inativos)  
- Ativação e inativação de beneficiários  
- Exclusão de beneficiários  
- API RESTful completa  
- Banco H2 com console para desenvolvimento  

---

## 🔧 Pré-requisitos
- Java **17** ou superior  
- Maven (opcional, o projeto inclui **Maven Wrapper**)  
- Git  

---

## ▶️ Como Executar
1. Clone o repositório:
   ```bash
   git clone <url-do-repositorio>
   cd beneficios-api

2. ## Execute a aplicação:
Usando Maven Wrapper: ./mvnw clean spring-boot:run


3. ## Acesse a aplicação:
   http://localhost:8080
## 🗄️ Banco de Dados H2

## Durante o desenvolvimento, a aplicação utiliza o H2 Database em memória.

## Console H2: http://localhost:8080/h2-console

## Configurações de conexão:

JDBC URL: jdbc:h2:mem:testdb

Username: sa
Password: (em branco)

## Este projeto é para fins educacionais e de demonstração.

## 📬 Contato
## https://www.linkedin.com/in/enzo-sp%C3%ADndola-1b747431a/
