# Spring Boot: Doctors System API

To create a RESTful API in Java 17 with Spring Boot 3 for managing doctors, patients, and appointments, inspired by Spring Boot best practices, I'll outline a structured approach. The API will include authentication, validations, and JPA/Hibernate for data persistence. Below is a comprehensive guide to set up the project, including key components, code examples, and best practices.

## 📌 Features

* Doctor CRUD (Create, Read, Update, Delete)
* Data validations with Bean Validation
* Result pagination
* Data storage with Spring Data JPA and MySQL/PostgreSQL
* Use of DTOs for data transfer
* Conversion between DTOs and entities
* Ready structure for authentication and access control (JWT/Spring Security)

## 🛠 Technologies Used

* Java 17
* Spring Boot 3
* Spring Data JPA / Hibernate
* Bean Validation (Jakarta Validation)
* Lombok
* MySQL / PostgreSQL
* Maven
* Flyway (for migrations)
* Spring Security (for authentication and access control)
* JWT (Json Web Token)

## 🚀 How to Run the Project

Clone the repository:

```bash
git clone https://github.com/jacksonn455/springhealthtrack.git
```

Enter the project folder:

```bash
cd springhealthtrack
```

Configure your database in `application.properties` or `application.yml`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/spring_health_track
spring.datasource.username=root
spring.datasource.password=admin
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
server.error.include-stacktrace=never
api.security.token.secret=${jwt_secret:12345678} 
```

Run the application:

```bash
mvn spring-boot:run
```

The API will be available at:

`http://localhost:8080`

## 🔗 Main Endpoints

* `POST /doctors` → Create a doctor
* `GET /doctors` → List doctors (with pagination)
* `GET /doctors/{id}` → Find doctor by ID
* `PUT /doctors/{id}` → Update doctor
* `DELETE /doctors/{id}` → Remove doctor
* `POST /login` → Authenticate and receive a JWT token

## 👨‍💻 Author

<img src="https://avatars1.githubusercontent.com/u/46221221?s=460&u=0d161e390cdad66e925f3d52cece6c3e65a23eb2&v=4" width=115><br><sub>@jacksonn455</sub>

### Objectives

* Isolate business rule code in an application
* Implement SOLID principles
* Document an API following the OpenAPI standard
* Create automated tests in a Spring Boot application
* Perform a build of a Spring Boot application
* Use environment variables and prepare an application for deployment

## ⚙️ Automatic Restart Configuration

To enable automatic project restart during development, follow these steps:

*   **Save and Build:** Ensure your IDE is configured to automatically build the project on save (e.g., `Ctrl + S` / `Build`, `Execution` / `Compiler` - `Build project automatically`).
*   **Advanced Settings:** In your IDE's advanced settings, allow `automake` to start the project automatically.
