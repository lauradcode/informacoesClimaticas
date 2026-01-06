# Sistema de Informações Climáticas em Tempo Real

Projeto em Java desenvolvido para consumo de API REST, com integração a serviço externo de dados climáticos, utilizando requisições HTTP e processamento de respostas em JSON, seguindo boas práticas de organização e legibilidade de código.

O sistema consulta dados climáticos em tempo real a partir do nome de uma cidade, utilizando a WeatherAPI, e exibe informações organizadas no terminal.


## Tecnologias Utilizadas

* **Java 11+**
* **API REST (WeatherAPI)**
* **org.json** (manipulação de JSON)
* **HttpClient / HttpRequest / HttpResponse**
* **Scanner** (entrada de dados via terminal)


## Funcionalidades

* Entrada de nome da cidade pelo usuário
* Requisição HTTP para API externa
* Recebimento de dados climáticos em formato JSON
* Extração e exibição de:

  - Cidade e país
  - Data e hora da última atualização
  - Temperatura atual
  - Sensação térmica
  - Umidade
  - Velocidade do vento
  - Pressão atmosférica
  - Condição do tempo


## Configuração do Ambiente

### Pré-requisitos

* Java 11 ou superior
* Biblioteca **org.json** adicionada ao projeto
* Conta ativa na WeatherAPI

### Chave da API

A chave da API deve ser armazenada em um arquivo externo:

```
api-key.txt
```


## Compilação e Execução (Windows)

Caso a biblioteca `org.json` esteja adicionada ao classpath do projeto:

```bash
javac -cp ".;lib/json.jar" ProjetoSistemaDeInformacoesClimaticasEmTempoReal.java
java -cp ".;lib/json.jar" ProjetoSistemaDeInformacoesClimaticasEmTempoReal
```

## Contexto do Projeto

Projeto desenvolvido como parte dos estudos em Java, com foco na aplicação prática de conceitos como consumo de APIs REST, requisições HTTP e manipulação de dados em JSON.
