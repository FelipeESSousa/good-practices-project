Para rodar o docker do projeto:
```ssh
docker-compose -d
```

Utilize o comando abaixo para executar o flyway db migration:
```ssh
./gradlew flywayMigrate -i
```

Swagger link (Open API)
```
http://localhost:8080/swagger-ui/index.html)
```

Para rodar os testes:
```ssh
./gradlew clean test
```

Endereço para o relátorio de testes:
```
- ./build/reports/tests/test/index.html
```