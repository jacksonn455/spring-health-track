# Spring Boot: Doctors System API

RESTful API developed in Java 17 with Spring Boot 3, focused on managing doctors, their specialties, and addresses. The project was inspired by Spring Boot and best practices for REST APIs, including authentication, validations, and the use of JPA/Hibernate for data persistence.

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
spring.datasource.url=jdbc:mysql://localhost:3306/db_name
spring.datasource.username=root
spring.datasource.password=root
spring.jpa.hibernate.ddl-auto=update
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

## 👨‍💻 Author

<img src="https://avatars1.githubusercontent.com/u/46221221?s=460&u=0d161e390cdad66e925f3d52cece6c3e65a23eb2&v=4" width=115><br><sub>@jacksonn455</sub>

## 📚 References and Courses

This project was inspired by the courses:

* Spring Boot 3: develop a REST API in Java
  * Creation of CRUDs, Bean Validation, Spring Data JPA, Flyway, and pagination.
* Spring Boot 3: apply good practices and protect a REST API
  * Standardization of returns, error handling, Spring Security, and JWT.

