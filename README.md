# Sergio Food Api
Open Api for costumers and restaurants.

> Status of Project: Under development :wrench: :hammer:

## Technical and Functionalities Description

Restful API built with Java 11, Spring Boot, Spring Data JPA, Hibernate and MySQL.  
Modern Api with many advanced resources, like DDD concepts, migrations with FlywayDB,
projections, reports, upload/download of documents, template emails with Apache FreeMarker,
documentation with OpenApi(swagger), Spring Security with OAuth2 and JWT.  
The application is dockerized and deployed on Amazon AWS.


## Steps and Requirements to build and run the Project

**1. Download and Install JDK 11**
  ```bash
https://www.oracle.com/java/technologies/javase-jdk11-downloads.html
 ```
**2. Install Docker and Run the MySQL Database**
 ```bash
Download and Install Docker:  
https://docs.docker.com/get-docker/

Command to run the Database: 
docker run -p 3306M3306 --name learnBjjDb -e
MYSQL_ROOT_PASSWORD=Julia917 -e MYSQL_DATABASE=learnBjj -d
mysql:latest
  ```  
**3. Clone the application**
```bash
git clone https://github.com/SergioRuyDev/sergiofoodapi.git
```
**4. Run the app using Maven**
  ```bash
  mvn spring-boot:run or sergiofoodapi/src/main/java/com/sergioruy/sergiofoodapi/SergiofoodapiApplication.java
  ```
The application will start running at <http://localhost:8080>

Look at the diagram of classes (in portuguese) below:

<p align"center">
  <img src="https://snipboard.io/vuWC7R.jpg"/>
</p>
