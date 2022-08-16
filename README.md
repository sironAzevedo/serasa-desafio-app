### Documentation

O projet contém as seguintes tecnologia:
* Java 11
* Maven
* H2
* [Flyway Migration](https://docs.spring.io/spring-boot/docs/2.7.2/reference/htmlsingle/#howto.data-initialization.migration-tool.flyway)
* Lombok
* Junit
* Mockito

Executando a aplicação via maven com o comando:
```
mvn spring-boot:run
```

URL swagger local:
```
http://localhost:8080/swagger-ui/index.html#/
```

URL Gerenciador H2 local:
```
http://localhost:8080/h2-console

Query:
SELECT * FROM TBL_AFINIDADE;
SELECT * FROM  TBL_ESTADO;
SELECT * FROM  TBL_PESSOA;
SELECT * FROM  TBL_SCORE;
```

OBS.:
```
A aplicação no momento da execução ja carrega dados para executar testes
```