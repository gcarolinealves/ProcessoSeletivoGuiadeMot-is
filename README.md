# Relatório de Testes Automatizados

Este repositório contém três conjuntos de testes automatizados.

1. **Testes de UI** para o formulário de cadastro de usuário, utilizando o **Selenium**.
2. **Testes de API** para validar os comportamentos esperados de uma API Mock, utlizando **Postman**.
3. **Testes de Carga** para simular múltiplos usuários acessando a API Mock simultaneamente, utilizando **JMeter**.

## **Testes de UI - Cadastro de Usuário**

### Requisitos do Teste

- **Campos obrigatórios**: O formulário não deve permitir o envio sem preencher todos os campos obrigatórios.
- **Senha forte**: A senha deve ter um mínimo de 8 caracteres, 1 letra maiúscula e 1 número.
- **Confirmação de e-mail**: O e-mail digitado no campo "Confirmação de E-mail" deve ser igual ao e-mail principal.

### Cenários de Testes

1. **Cadastro com dados válidos**: Verificar se a mensagem de sucesso é exibida ao preencher todos os campos corretamente.  
   - **Status**: Pass ✅

2. **Campos obrigatórios vazios**: Verificar se o sistema exibe erro ao deixar o campo "Nome" vazio.  
   - **Status**: Pass ✅

3. **Senha fraca**: Verificar se o sistema exibe erro quando a senha não atende aos requisitos mínimos.  
   - **Status**: Pass ✅

4. **Confirmação de e-mail diferente**: Verificar se o sistema exibe erro quando os campos "E-mail" e "Confirmação de E-mail" não correspondem.  
   - **Status**: Pass ✅

## **Testes de API**

### Cenários de Testes

1. Fazer uma requisição **GET** e validar se a resposta contém os campos: `id`, `name`, `username`, `email`, `address`, `phone`, `website`, e `company`.
   - **Status**: Pass ✅

2. Enviar uma requisição **POST** com todos os campos obrigatórios e validar se a resposta retorna o status 201.
   - **Status**: Pass ✅

3. Enviar uma requisição **POST** com um campo faltando e verificar se a API retorna erro 400.
   - **Status**: Pass ✅

4. Simular uma falha no servidor e verificar se a resposta retorna status 500.
   - **Status**: Pass ✅

## **Teste de Performance (Teste de Carga)**

### Cenários de Testes

1. Configurar um teste de carga para 100 usuários simultâneos acessando a API mock.  

   - **Status**: Pass ✅

### Resultados

- **Número de amostras**: 100
- **Tempo médio de resposta**: 65 ms
- **Tempo mínimo**: 53 ms
- **Tempo máximo**: 137 ms
- **Desvio padrão**: 10.91 ms
- **Código de resposta**: 200 (OK)
- **Erros**: 0%
- **Throughput**: 95.3 requisições/seg
- **Dados recebidos**: 645.48 KB
- **Dados enviados**: 12.66 KB
- **Tamanho médio da resposta**: 6933.6 bytes

Aplicação Web Testada: https://www.guiademoteis.com.br/usuario/cadastro<br>
API Mock Testada: https://jsonplaceholder.typicode.com/users
