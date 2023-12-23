Spring Boot header-based Principal Security
=

This is an example of how a Spring Boot application can be secured using annotations where the Principal is based on an HTTP header, e.g. when authentication is performed by an API Gateway or a Load Balancer.

Setup
-

Checkout the code and run

```
mvn clean install spring-boot:repackage
```

Run
-

```
java -jar target/api.jar
```

For examples see `./src/test/java/org/example/boundary/*` for the various Controller tests with and without the correct header present, e.g. `curl http://localhost:8080/api/public` for the successful `public` access, or `curl -H "id: user" -H "roles: ADMIN" http://localhost:8080/api/admin
` for the successful `admin` access - or, `curl -H "id: user" -H "roles: USER" http://localhost:8080/api/admin` for a failed `admin` access due to a mismatch in roles.
