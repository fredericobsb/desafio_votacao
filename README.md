Sistema de votacao

O sistema foi desenvolvido com as seguintes tecnologias:

- Java 17
- Maven 3.8.4
- Springboot 3.0.0
- Docker 24.0.6
- Banco de dados Mysql 

Para executar o sistema, o usuário deverá ter o docker instalado, rodando na própria máquina, e executar os comandos abaixo no prompt de comando:

1) Para baixar o container do banco de dados mysql:
                 docker pull fredericoramos/votacao:02021232h_mysql

2) Para baixar o container da aplicacao desenvolvida:
                 docker pull fredericoramos/votacao:02021227h

3) Para subir a aplicação:
                 docker compose up

4) Acessar o Swagger da aplicação em:
          http://localhost:8080/swagger-ui/index.html#/

5) Acessar os endpoints da aplicação, versionada na versão 1, nos seguintes endereços:

* http://localhost:8080/api/v1/pautas
* http://localhost:8080/api/v1/associados
* http://localhost:8080/api/v1/votos
