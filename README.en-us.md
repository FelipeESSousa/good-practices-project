For run the docker-compose of project, the indicated are the docker-run.sh because it has aditional configuration:
```ssh
./docker-run.sh 
or
docker-compose -d
```

For run the migration flyway:
```ssh
./gradlew flywayMigrate -i
./gradlew flywayMigrate -i -Dflyway.user=pguser -Pflyway.password=pguser
./gradlew flywayMigrate -i -Dflyway.user=pguser -Pflyway.password=pguser -Pflyway.url=jdbc:postgresql://localhost:5432/pgdb
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

### SonarQube

The application url are:

```url
http://localhost:9000
```
On your first access, you are going to be redirected to the login page, the credêntials are:
```
login: admin
password: admin
```
After login, you have to change the password, so use the word **sonar** as password for our example works.

#### Using sonarqube on your project 
For run sonar in your code, you need to run first your tests, so that will be used on sonarqube:
```ssh
./gradlew clean test
```
After that you can run the command:
```ssh
./gradlew sonarqube
```
And access your application report.
```
http://localhost:9000/dashboard?id=Good-Practices-API
```

### New Relic

Create the file  **`newrelic.yml`** and add this on your project file root. Obs: This file can be found into your account or in this link:
https://docs.newrelic.com/docs/apm/agents/java-agent/configuration/java-agent-config-file-template/

You need to change two params inside this file:
**app_name** e **license_key**

The license_key you going to find in this link:
https://one.newrelic.com/admin-portal/api-keys/home

Use the key TYPE: **INGEST - LICENSE** for license_key

For the field app_name, you can choose any name. I'm using `Good Practices API`

Latter that in your project configurations do that configurations

`-javaagent:newrelic.jar`
`-Xmx512m`

![](./imgs/edit_run_config.png)
![](./imgs/arguments.png)