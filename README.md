[![Build Status](https://travis-ci.org/thiagofarbo/api-funcionarios.svg?branch=master)](https://travis-ci.org/thiagofarbo/api-funcionarios)

## Funcionario API
Projeto com o objetivo de criar interfaces para aplicações de front-end.

Esta API foi concebida para atenter as plataformas web e mobile e agregar as funcionalidades requisitadas pelos APPs.

## Requerimentos

## Tecnologias

* Java
* Spring 
* Spring Boot
* Maven
* Restfull (hateoas)
* Swagger
* JUnit

Para executar a aplicação é necessário:

- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven 3](https://maven.apache.org)

# Swagger
Para acessar a documentação Swagger da api, acesse a url http://localhost:8095/swagger-ui.html	

# Base de dados.
Para acessar a base de dados, basta acessar a URL http://localhost:8080/h2/

# Imagem docker.
Para criar a imagem docker, basta acessar a raiz do projeto e executar o comamndo abaixo.

**docker build -f Dockerfile -t api-funcionarios-0.0.1.jar . **

# Rodar a aplicação no docker
Para rodar a imagem no docker, basta executar o comando a baixo.
**docker run -p 8085:8085 api-funcionarios-0.0.1.jar**

# URL's REST API


** @POST Para salvar um funcionário, penas realize uma request para a url. http://localhost:8095/api/funcionarios 

** @GET Para consultar um funcionário, apenas realize uma request para a url a urln http://localhost:8095/api/funcionarios/1. passando o id do funcionário 

** @GET Para listar os funcionário, apenas realize uma request para a url. http://localhost:8095/api/funcionarios

** @PUT Para editar um funcionário, apenas realize uma request para a url http://localhost:8095/api/funcionarios passando o json abaixo. 

{
	"nome": "Jonh",
	"cargo": "Estagiário",
	"salario": "3.000",
	"dataAdmissao": "20-10-2019",
	"dataDemissao":"20-10-2020",
	"status": "ATIVO"
}

** @PATCH Para atualizar um funcionário parcialmente, apenas realize uma request para a url http://localhost:8095/api/funcionarios passando o json abaixo.

{
	"nome": "Jonh",
	"cargo": "Gerente",
	"salario": "5.000",

}


** @DELETE Para deletar um funcionário, apenas realize uma request para a url http://localhost:8095/api/funcionarios passando o id do funcionário
