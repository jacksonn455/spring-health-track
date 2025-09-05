CREATE TABLE patients (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    phone VARCHAR(20) NOT NULL,
    document VARCHAR(20) NOT NULL UNIQUE,
    street VARCHAR(100),
    city VARCHAR(100),
    state VARCHAR(100),
    zip_code VARCHAR(20),
    complement VARCHAR(100),
    number VARCHAR(20),
    active BOOLEAN NOT NULL
);