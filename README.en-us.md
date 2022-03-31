For run the compose from project:
```ssh
docker-compose -d
```

For run the migration flyway:
```ssh
./gradlew flywayMigrate -i
```

Swagger link (Open API)
```
http://localhost:8080/swagger-ui/index.html)
```

For run the tests:
```ssh
./gradlew clean test
```

Path to test report:
```
- ./build/reports/tests/test/index.html
```