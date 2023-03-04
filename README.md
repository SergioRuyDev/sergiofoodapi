# Sergio Food Api
Open Api for customers and restaurants.

> Status of Project: Under development :wrench: :hammer:

## Tools and technologies used:
  1. Java 11

  2. Spring boot

  3. Spring Data JPA

  4. Hibernate and MySQL

  5. FlywayDB

  6. Projections and Reports in PDF

  7. Upload/Download of files
  
  8. Criteria JPQL Consults.

  9. E-mail Template with Apache FreeMarker

  10. Documentation with OpenApi(Swagger)

  11. Spring Security

  12. OAuth2

  13. JWT
  
  14. Dockerized Api
  
  15. Deployed in Amazon AWS

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
docker run -p 3306M3306 --name sergiofood -e
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

Look at the diagram of classes (in portuguese, but in english soon) below:

<p align="center">
  <img src="https://snipboard.io/vuWC7R.jpg"/>
</p>
