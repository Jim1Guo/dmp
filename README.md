# Getting Started
This is a proof of concept API in Java that functions as a simple credit card fraud detection service.
We build a service with endpoint named "/analyzeTransaction" that takes in a json object of 1 transaction with properties for card number and transaction amount. Then applies some business logic before returning a decision to approve or decline the transaction.

# Technical view
## Assumption
1. API Security is out of scope.
2. Resource are limited with local dev environment.

## Framework in use
Spring Boot: Restful API
Spring Validation: Input validation
Spring Boot Webflux: api call to external microservice
EhCache: cache for performance
Rule Engine: Simple rule implementation built by hand
Lombok: simple clean code
Open API / Swagger: API documentation
Spring Boot Actuator: API monitoring
Log4j Slf4j: Logging
Mockito: unit / integration testing

# Guides
## Build & Deployment
### Run the API
mvn spring-boot:run -Dspring-boot.run.profiles=local
### Docker build image
mvn spring-boot:build-image
### Run docker image with active Spring profile
docker run -p 8080:8080 -e "SPRING_PROFILES_ACTIVE=local" 124e71a24cde

## API documenation & monitoring
### Java Doc
/doc/com/icebirdtech/dmp/package-summary.html
### Surefire test report
surefire-report.html
### API Doc
http://localhost:8080/swagger-ui/index.html
### API monitoring
http://localhost:8081/manage/
	for eg: http://localhost:8081/manage/metrics/jvm.memory.max

