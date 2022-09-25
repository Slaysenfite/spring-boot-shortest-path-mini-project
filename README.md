# Shortest Path Application
This repository consists of two applications within a single project.
The first application, whose runnable main method can be found in the shortest-path-service-assembler module, provides a REST service that exposes basic CRUD behaviour and a SOAP service that handles requests for finding the shortest path between two given planet names.
The second application provides a frontend user interface from consuming the soap web service. It's runnable main class is located in the module shortest-path-ui.
Alternatively, there is an angular frontend that consumes a rest services for the shortest path algorithm.
## Running the application 
1. cd into project root directory
2. mvn clean install
3. cd shortest-path-service-assembler
4. mvn spring-boot:run or run ShortestPathServiceAssembler.java from dashboard

### Using the SOAP consuming front end
6. cd into shortest-path-ui
7. mvn spring-boot:run or run ShortestPathClientUI.java from dashboard

### Using the REST consuming front end
6. cd into ng-frontend
7. ng serve or run Angular cli server from dashboard

### Note
The source files generated from the xsd schema need to be present in the target folder before the web service application will run.
## Important Ports and Endpoint
- By default, the web service application runs on port 8080 
- Soap consuming front end UI runs on port 8082
- Rest consuming front end runs on port 4200
- Swagger documentation for the REST service can be found at /swagger-ui.html on 8080
- The wsdl for the SOAP service can be found at /ws/shortestPath.wsdl on 8080
## Assumptions
- The graph is uni-directional since it was never indicated otherwise
- It was never explicitly mentioned that the frontend ought to consume the SOAP service, I decided to add a project module to consume the soap services anyway, which results in having a separate and independently runnable module for the frontend UI.
## Challenges
- Initially, I built this application within one module, and later decided to split the module into logical sub-modules (ie separating soap, rest, and common classes). I was unable to correctly configure the contexts for tests that required autowired beans, unfortunately.
