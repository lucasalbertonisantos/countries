# Countries

<img src="/images/world.png" alt="World" width="200" height="200" />

## About the application
The application was done using Spring Boot and h2 database (memory database).
To load some data on startup, there is a class called DataLoader that is only used when you are running on DEV context.
If you want to call the endpoints by the postman, you only need to import the Visual-Nuts.postman_collection.json file into your postman. It is located "src/main/resources" folder

## Quick Run
1 - Right-click the Application.java class

2 - Debug as -> java application

## Run the tests
To run the all tests just Right-click on the project -> Run As -> JUnit Tests

### Cucumber
If you want to run only the cucumber tests: just Right-Click on RunCucumberTests.java class -> Run As -> JUnit Tests
The .feature file is located on the "src/test/resources/pt/visualnuts/lucasalbertoni/exercises" folder.

### API documentation
There is an API documentation with Swagger (Spring OpenAPI 3).
To access the documentation you just have to run the application and check on the screen page "/swagger-ui/index.html". If you want to see the documentation by API you can access on "/v3/api-docs/". You can also download a yaml file using the "/v3/api-docs.yaml" endpoint.

### Health Check
To check if the application is running correctly you just have to access "/actuator/health" endpoint.

### H2
To access the H2 console you just have to access the "/h2-console" screen page.

In the parameters you have to put:
- Driver Class: org.h2.Driver
- JDBC URL: jdbc:h2:mem:countries
- User Name: h2
- Password: pass

Then click on the "Connect" button