# BankingPortal Spring Boot Application

This project represents a **BankingPortal** application developed using **Spring Boot**. It provides features such as account management, user roles, permissions, and secure transactions with comprehensive Spring Security and JPA integrations.

## Features

- Account creation and management
- User authentication and role-based authorization
- Secure banking operations (e.g., deposits, withdrawals, transfers)
- Transaction history tracking
- Support for multiple account types (Savings, Checking)
- Flexible account statuses (Active, Inactive, Closed)
- CSRF protection for secure user interactions

## Prerequisites

- Java 21 or later
- Maven
- PostgreSQL (configured via Docker or standalone)

## Quick Start

1. Clone the repository:
   ```bash
   git clone <repository-url>
   cd BankingPortal
   ```

2. Set environment variables:
   ```bash
   export SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/banking_db
   export SPRING_DATASOURCE_USERNAME=db_user
   export SPRING_DATASOURCE_PASSWORD=db_password
   ```

3. Run the application:
   ```bash
   mvn spring-boot:run
   ```

4. Visit the application:
   ```
   http://localhost:8080
   ```

## Default User Credentials

For testing purposes, the application comes with a default admin user:
- Username: `admin`
- Password: `admin123`

## Database Setup

This application uses PostgreSQL for persistence. If you have Docker installed, you can quickly spin up a PostgreSQL instance:

```yaml
version: '3.8'
services:
  db:
    image: postgres:15
    environment:
      POSTGRES_USER: db_user
      POSTGRES_PASSWORD: db_password
      POSTGRES_DB: banking_db
    ports:
      - "5432:5432"
```

Start the database:
```bash
docker-compose up -d
```

## Configuration

Update `application.yml` with your database configuration:

```yaml
spring:
  datasource:
    url: ${SPRING_DATASOURCE_URL}
    username: ${SPRING_DATASOURCE_USERNAME}
    password: ${SPRING_DATASOURCE_PASSWORD}
  jpa:
    hibernate:
      ddl-auto: update
    properties:
      hibernate.dialect: org.hibernate.dialect.PostgreSQLDialect
  security:
    csrf:
      enabled: true
```

## Key Dependencies

```xml
<dependencies>
    <!-- Spring Boot Starters -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-security</artifactId>
    </dependency>

    <!-- PostgreSQL Driver -->
    <dependency>
        <groupId>org.postgresql</groupId>
        <artifactId>postgresql</artifactId>
    </dependency>

    <!-- Lombok -->
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <optional>true</optional>
    </dependency>
</dependencies>
```

## Troubleshooting

### Common Issues

1. **Database Connection Issues**:
    - Verify the database is running and accessible.
    - Check the environment variables `SPRING_DATASOURCE_URL`, `SPRING_DATASOURCE_USERNAME`, and `SPRING_DATASOURCE_PASSWORD`.

2. **H2 Error Logs**:
    - Ensure the application is not connecting to H2 by removing H2-related configurations or dependencies if PostgreSQL is being used.

3. **Login Issues**:
    - Ensure the default admin user is created on application start by verifying in `data.sql`.

### CSRF Issues

If CSRF token-related issues arise, clear your browser cookies or check that your frontend is including the CSRF token in HTTP requests.

## Security Considerations

1. **Production Environment**:
    - Use HTTPS.
    - Secure all sensitive environment variables.
    - Restrict user roles and permissions appropriately.

2. **Database Security**:
    - Use strong credentials for your PostgreSQL instance.
    - Disable remote access to the database unless necessary.

3. **Authentication**:
    - Configure robust password policies.
    - Enable multi-factor authentication (if applicable).
