# ToDoList Java - Instruções de Execução e Testes

Este é um aplicativo de Lista de Tarefas (ToDoList) desenvolvido em Java com o framework Spring Boot. Abaixo estão as instruções para executar e testar o aplicativo.

## Requisitos Prévios

- [Java Development Kit (JDK)](https://www.oracle.com/java/technologies/javase-downloads.html) - Versão 11 ou superior.
- [Maven](https://maven.apache.org/download.cgi) - Ferramenta de gerenciamento de construção.

## Configuração do Banco de Dados (Opcional)

O aplicativo utiliza um banco de dados PostgreSQL 9.5 para facilitar a execução.

## Instruções de Execução

1. **Clonar o Repositório:**
   ```bash
   git clone https://seu-repositorio-git.com/todo-list-java.git
   cd todo-list-java

2. **Executar o Aplicativo:**
   ```bash
   mvn spring-boot:run

3. **Acesso à API:**
	O aplicativo estará disponível em http://localhost:8080/api/tasks
	
# Acessando a API via Postman

## Uso do Postman

Para facilitar o teste da API, disponibilizamos uma coleção do Postman com os principais endpoints. Siga as instruções abaixo para importar a coleção:

1. Baixe a [Coleção do Postman](https://drive.google.com/file/d/14MAZ-_y9Cvo2pmKxMrIS3SmUsK1_5l8R/view?usp=drive_link).
2. Abra o Postman e clique em "Importar" na barra lateral esquerda.
3. Selecione "Importar do Link" e cole o link da coleção.
4. A coleção será importada e estará disponível no Postman.

Lembre-se de ajustar as variáveis de ambiente, se necessário, para corresponder ao seu ambiente local.


## 1. Salvar uma Nova Tarefa (POST)

- **Endpoint:** `http://localhost:8080/api/tasks/salvarTask`
- **Método:** `POST`
- **Body (JSON):**
  ```json
  {
    "descricao": "Descrição da Tarefa"
  }

- **Observação:** Certifique-se de incluir o cabeçalho Content-Type com o valor application/json.

## 2. Deletar uma Tarefa por ID (DELETE)

- **Endpoint:** `http://localhost:8080/api/tasks/deletarTaskPorId/{id}`
- **Método:** `DELETE`
- **Parâmetros de Path:**
  - `{id}`: ID da tarefa que deseja deletar.

## 3. Buscar uma Tarefa por ID (GET)

- **Endpoint:** `http://localhost:8080/api/tasks/buscarTaskPorId/{id}`
- **Método:** `GET`
- **Parâmetros de Path:**
  - `{id}`: ID da tarefa que deseja buscar.

## 4. Buscar Tarefas por Descrição (GET)

- **Endpoint:** `http://localhost:8080/api/tasks/buscarTaskPorDesc/{desc}`
- **Método:** `GET`
- **Parâmetros de Path:**
  - `{desc}`: Descrição da tarefa que deseja buscar.

## 5. Atualizar Status da Tarefa (PUT)

- **Endpoint:** `http://localhost:8080/api/tasks/atualizarTaskStatus/{id}/isCompletado`
- **Método:** `PUT`
- **Parâmetros de Path:**
  - `{id}`: ID da tarefa que deseja atualizar.
- **Parâmetros de Query:**
  - `isCompletado` (opcional, padrão: `value`): `true` para tarefa completada, `false` para tarefa não completada.

# Testes Automatizados

## Teste de Integração com TaskController

Para garantir o correto funcionamento do `TaskController` e a integração adequada com o banco de dados, são fornecidos testes automatizados utilizando a biblioteca JUnit 5 e a biblioteca Awaitility para testes assíncronos. Abaixo, detalhamos um exemplo de teste incluído no arquivo `TodolistApplicationTests.java`:

### [TodolistApplicationTests.java](src/test/java/com/kanechan/todolist/TodolistApplicationTests.java)

Este teste verifica a funcionalidade de salvar uma tarefa (`testSalvarTask`) utilizando o método `salvarTask` do `TaskController`. Abaixo estão os passos chave do teste:

1. **Criação da Tarefa:**
   - Uma nova instância da classe `Task` é criada com uma descrição de teste.

2. **Chamada do Método no Controlador:**
   - O método `salvarTask` do `TaskController` é chamado com a tarefa criada.

3. **Verificação da Resposta:**
   - É verificado se a resposta foi bem-sucedida (código HTTP 200).

4. **Obtenção da Tarefa Salva:**
   - A tarefa salva é obtida do corpo da resposta.

5. **Espera Assíncrona:**
   - Utilizando o Awaitility, o teste aguarda até que a tarefa seja persistida no banco de dados (por um máximo de 5 segundos).

6. **Verificação no Banco de Dados:**
   - É verificado se a tarefa está presente no banco de dados.


`bash`
mvn test




## Licença

Este projeto está licenciado sob a [Licença MIT](LICENSE.md).

---

**Autor:** Kane Chan

**Projeto criado em:** 01/2024
