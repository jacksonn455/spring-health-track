# Spring Boot: Health Track API

A RESTful API developed in Java 17 with Spring Boot 3 for managing doctors, patients, and appointments. This project applies best practices for REST APIs, including JWT authentication, role-based authorization, data validation, and robust data persistence with JPA/Hibernate.

## Features (Implemented Objectives)

This API was built with a focus on clean architecture and modern software development practices, implementing the following key objectives:

*   **Comprehensive Domain Management:**
    *   Full CRUD operations (Create, Read, Update, Delete) for **Doctors**, **Patients**, and **Appointments**.
    *   Use of DTOs (Data Transfer Objects) to ensure a clean and secure interface between the client and the server.
    *   Advanced input validation for all entities using Bean Validation.

*   **Architecture & Best Practices:**
    *   Isolation of business logic from the framework, following clean architecture principles.
    *   Application of **SOLID** principles for cleaner, more maintainable, and extensible code.
    *   API responses with **pagination** for optimized performance on large datasets.
    *   Centralized and configurable **logging** using **SLF4J** for monitoring and debugging.
    *   Asynchronous processing and communication with **Apache Kafka** for message queues.

*   **Authentication & Security:**
    *   A dedicated login endpoint (`/login`) for user authentication.
    *   **JWT (Json Web Token)** generation and validation for secure, stateless access control.
    *   Integration with **Spring Security** to protect endpoints and manage user roles.

*   **Data Persistence:**
    *   Data storage using **Spring Data JPA** and Hibernate.
    *   Support for both **MySQL** and **PostgreSQL** databases.
    *   Automated database schema migration management with **Flyway**.

*   **Cache Management (Redis):**
    *   Integration with **Redis** to cache frequently accessed data and reduce database load.
    *   Cached data (like **Doctors** and **Patients**) has a **TTL of 30 seconds**, after which fresh data is fetched from the database.

*   **Application Monitoring (New Relic):**
    *   Monitors application performance, request throughput, response times, and errors.
    *   Provides alerts and dashboards to track database queries and overall system health.

*   **Documentation & Testing:**
    *   API documentation designed to follow the **OpenAPI** standard (ready for Swagger UI integration).
    *   A structured project setup that is ready for **automated tests** (unit, integration) within the Spring ecosystem.

*   **Build & Deployment:**
    *   Configuration to **build** the application into a runnable JAR for production.
    *   Use of **environment variables** for sensitive data (e.g., JWT secrets, database credentials), preparing the application for containerization and deployment.

## Technologies Used

*   **Core:** Java 17, Spring Boot 3, Maven
*   **Persistence:** Spring Data JPA, Hibernate, Flyway
*   **Database:** MySQL, PostgreSQL
*   **Security:** Spring Security, JWT (Json Web Token)
*   **Validation:** Bean Validation (Jakarta Validation)
*   **Utilities:** Lombok
*   **Logging:** SLF4J
*   **Messaging/Queue:** Apache Kafka
*   **Caching:** Redis
*   **Monitoring:** New Relic

## How to Run the Project

1.  **Clone the repository:**
    ```bash
    git clone https://github.com/jacksonn455/springhealthtrack.git
    ```

2.  **Enter the project folder:**
    ```bash
    cd springhealthtrack
    ```

3**Run the application:**
    ```bash
    mvn spring-boot:run
    ```

The API will be available at `http://localhost:8080`.

## Main Endpoints

| Method | Endpoint         | Description                           |
|--------|------------------|---------------------------------------|
| `POST` | `/login`         | Authenticates a user and returns a JWT. |
|        |                  |                                       |
| `POST` | `/doctors`       | Creates a new doctor.                 |
| `GET`  | `/doctors`       | Lists all doctors with pagination.    |
| `GET`  | `/doctors/{id}`  | Finds a doctor by their ID.           |
| `PUT`  | `/doctors/{id}`  | Updates a doctor's information.       |
| `DELETE` | `/doctors/{id}` | Deactivates a doctor (logical deletion). |
|        |                  |                                       |
| `POST` | `/patients`      | Creates a new patient.                |
| `GET`  | `/patients`      | Lists all patients with pagination.   |
| `GET`  | `/patients/{id}` | Finds a patient by their ID.          |
| `PUT`  | `/patients/{id}` | Updates a patient's information.      |
| `DELETE` | `/patients/{id}` | Deactivates a patient (logical deletion). |
|        |                  |                                       |
| `POST` | `/appointments`  | Schedules a new appointment.          |
| `GET`  | `/appointments`  | Lists all appointments.               |
| `DELETE` | `/appointments/{id}` | Cancels an appointment.          |
|        |                  |                                       |
| `GET`  | `/v3/api-docs`   | Swagger API documentation.            |
| `GET`  | `/v3/api-docs/**` | Additional Swagger API documentation paths. |
| `GET`  | `/swagger-ui.html` | Swagger UI interface.              |
| `GET`  | `/swagger-ui/**` | Additional Swagger UI resources.      |
| `GET`  | `/swagger-resources/**` | Swagger resources.              |
| `GET`  | `/webjars/**`    | WebJars resources for Swagger UI.     |

## Project Structure

    src
    ├── main
    │   ├── java
    │   │   └── com.springhealthtrack.api
    │   │       ├── controllers
    │   │       ├── core       
    │   │       ├── documentations
    │   │       ├── domains
    │   │       ├── dtos
    │   │       ├── enums
    │   │       ├── infraestructure
    │   │       ├── repositories
    │   │       ├── services
    │   │       └── SpringHealthTrackApiApplication
    ├── resources
    │   ├── db.migration
    │   ├── static
    │   ├── templates
    │   └── application.properties
    └── test

## Author

<img src="https://avatars1.githubusercontent.com/u/46221221?s=460&u=0d161e390cdad66e925f3d52cece6c3e65a23eb2&v=4" width=115>  

<sub>@jacksonn455</sub>

## ⚙️ Automatic Restart Configuration

To enable automatic project restart during development, follow these steps:

*   **Save and Build:** Ensure your IDE is configured to automatically build the project on save (e.g., `Ctrl + S` / `Build`, `Execution` / `Compiler` - `Build project automatically`).
*   **Advanced Settings:** In your IDE's advanced settings, allow `automake` to start the project automatically.
