# Location Management User Authentication Microservice

This microservice handles user authentication for the Location Management system, providing registration and login functionalities.

## Features

- **User Registration**: Validates and registers new users.
- **User Login**: Authenticates users using email and password.
- **Error Handling**: Provides detailed error messages for invalid operations.
- **Swagger**: Includes Swagger for API documentation.

## Technologies Used

- **Spring Boot**: Framework for building the microservice.
- **PostgreSQL**: Database for storing user information.
- **Logback**: Logging framework for capturing application logs.
- **Swagger**: API documentation tool for easy API exploration.

## Prerequisites

- Java 11 or higher
- PostgreSQL database
- Maven

## Installation

### Backend Setup

1. **Clone the Repository**
    ```bash
    git clone https://github.com/YashM1234/location-management-user-auth
    cd location-management-user-auth
    ```

2. **Set Up PostgreSQL**
    - Create a PostgreSQL database named `location`.
    - Update `application.properties` with your PostgreSQL credentials:
      ```properties
      server.port=80
      spring.datasource.url=jdbc:postgresql://localhost:5432/location
      spring.datasource.username=your_username
      spring.datasource.password=your_password
      ```

3. **Build and Run the Backend Application**
    ```bash
    mvn clean install
    mvn spring-boot:run
    ```
4. **Explore the API**
    - Once running, access the Swagger UI to explore and test the API endpoints:
      [http://localhost:80/swagger-ui.html](http://localhost:80/swagger-ui.html)
      
## Note

- Service run on port 80.
- You also have to clone two repository.
- git clone https://github.com/YashM1234/location
- git clone https://github.com/YashM1234/offering-api

