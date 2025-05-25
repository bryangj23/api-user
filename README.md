# 📦 api-user
The api-user microservice is part of the system's distributed architecture and is responsible for managing users and roles within the application.

📚 Available Endpoints

🔐 Roles

- GET /roles

Retrieves a paginated list of roles with optional filtering support.
![image](https://github.com/user-attachments/assets/6fa6c876-ec02-4e51-8160-fea8ef1183ac)


- POST /roles

Creates a new role using the data provided in the request body.
![image](https://github.com/user-attachments/assets/7f173e49-9768-4836-a276-726cd93cd969)


- PUT /roles/{roleId}

Updates an existing role identified by its ID.
![image](https://github.com/user-attachments/assets/a3859be7-e830-491d-8e58-98b7423c66a3)


- DELETE /roles/{roleId}

Deletes a role from the system (irreversible operation).
![image](https://github.com/user-attachments/assets/86a7b94b-b883-4558-abaa-e0f120da5cc5)


👤 Users

- GET /users

Retrieves a paginated list of registered users with filtering support.
![image](https://github.com/user-attachments/assets/b6f610ea-0861-4fce-960a-4be13fdef2d6)


- GET /users/{userId}

Retrieves detailed information of a specific user by their ID.
![image](https://github.com/user-attachments/assets/90dc80d4-c392-4dc1-83eb-6c39f4059d62)

- POST /users

Creates a new user with input validations.
![image](https://github.com/user-attachments/assets/1af26cc5-5f91-4f8d-94e7-58a25788a17e)


- PUT /users/{userId}

Partially updates the data of an existing user.
![image](https://github.com/user-attachments/assets/48c19a2c-3a29-45d4-9511-16abec41c8b3)


- DELETE /users/{userId}

Deactivates a user (the user is not physically removed, just marked as inactive).
![image](https://github.com/user-attachments/assets/5af9810e-cb43-4905-959a-e9f02fdf3ce5)



🧩 Entity Relationship Overview
The api-user microservice includes two main domain entities: User and Role, which are designed following a normalized relational model to ensure consistency and integrity.

🔗 Entity Relationship
A User is associated with exactly one Role.

A Role can be assigned to many Users.

This is a classic many-to-one relationship from User to Role.


## 🧰 Technologies Used

- Java 17+
- Spring Boot
- Spring Web
- Spring JPA
- RSQL JPA specification
- PostgresSQL Driver
- Swagger UI (opcional)
- Maven

🌐 Internationalization (i18n)
Error messages are centralized in messages.properties files and translated according to the LocaleConfiguration configuration class.

## Project organization

The code is organized as follows:

1. `configuration` contains all the external library implementations to be configured inside spring-boot.
2. `controller` contains the communication interfaces with the client.
3. `entity` contains the persistence domains.
4. `dto` contains classes that separate in-memory objects from the database.
5. `mapper` contains mapping classes between objects and entities.
6. `repository` contains the classes or components that encapsulate the logic necessary to access the data sources.
7. `service` contains the interfaces and implementations that define the functionality provided by the service.
8. `utils` contains application utils classes.


## Environment variable

**Database environment variable**

- DB_PORT: Database port. Default value: 5432
- DB_NAME: Database name.
- DB_SCHEMA: Database schema.
- DB_USER: Database user.
- DB_PASS: Database password.


```json
{
  "DB_PORT": "5432",
  "DB_NAME": "postgres",
  "DB_SCHEMA": "api_webflux",
  "DB_USER": "",
  "DB_PASS": ""
}
```


## Building from Source

1. Install JDK 17- [JDK 17 Download](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
2. Install dependencies: mvn dependency:resolve
3. Run clean: mvn clean
4. Run compile: mvn compile


## Run Database Migrations

1. Install PostgreSQL 16 - [PostgreSQL 16 Download](https://www.enterprisedb.com/downloads/postgres-postgresql-downloads)
2. Install Liquibase - [Liquibase Download](https://docs.liquibase.com/start/install/home.html)
3. Configure Database host, Database port, Database name, Schema, User, Password in liquibase configuration `liquibase/liquibase.properties`
4. Running migrations:

```bash
liquibase --defaultsFile=liquibase/liquibase.properties --changeLogFile=liquibase/changelog.yaml update
```

## Run in Local Mode

1. Install dependencies: mvn dependency:resolve
2. Run clean: mvn clean
3. Run compile: mvn compile
4. Run server: mvn spring-boot:run -Dspring-boot.run.profiles=local

### Documentation

Document Reference: [Docs File](swagger/swagger.yaml)

📄 License
This project is free to use for educational and evaluation purposes.

🙋‍♂️ Contact
Developed by Brayan Guardo – [brayanguardodiaz0@gmail.com]
Repository: GitHub
