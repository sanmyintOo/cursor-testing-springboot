# Student Management System

A Spring Boot application for managing student records with JWT authentication.

## Prerequisites

- Java 17
- PostgreSQL
- Gradle
- Docker and Docker Compose (for containerized deployment)

## Local Development Setup

1. Create a PostgreSQL database named `student_management`
2. Update the database credentials in `src/main/resources/application.properties` if needed
3. Build the project:
   ```bash
   ./gradlew build
   ```
4. Run the application:
   ```bash
   ./gradlew bootRun
   ```

## Docker Deployment

### Using Docker Compose (Recommended)

1. Build and start the containers:
   ```bash
   docker-compose up --build
   ```
   This will:
   - Build the application container
   - Start PostgreSQL database
   - Create necessary volumes and networks
   - Start the application

2. To run in detached mode:
   ```bash
   docker-compose up -d
   ```

3. To stop the containers:
   ```bash
   docker-compose down
   ```

4. To view logs:
   ```bash
   docker-compose logs -f
   ```

### Manual Docker Build

1. Build the Docker image:
   ```bash
   docker build -t student-management .
   ```

2. Run the container:
   ```bash
   docker run -p 8080:8080 student-management
   ```

## API Documentation

Once the application is running, you can access the Swagger UI at:
```
http://localhost:8080/swagger-ui.html
```

## Features

- CRUD operations for student management
- JWT-based authentication
- PostgreSQL database
- Swagger API documentation
- Spring Security integration
- Log4j2 logging with file output
- Docker containerization

## API Endpoints

- GET `/api/students` - Get all students
- GET `/api/students/{id}` - Get student by ID
- POST `/api/students` - Create a new student
- PUT `/api/students/{id}` - Update a student
- DELETE `/api/students/{id}` - Delete a student

## Security

The application uses JWT (JSON Web Token) for authentication. Make sure to:
1. Include the JWT token in the Authorization header for protected endpoints
2. Format: `Authorization: Bearer <your-token>`

## Default Admin Credentials

- Username: `admin`
- Password: `admin123`

## Logging

Logs are stored in the `logs` directory:
- Application logs: `logs/application.log`
- Logs are also available in the Docker container at `/app/logs`

## Docker Environment Variables

The following environment variables can be configured in the `docker-compose.yml`:

- `SPRING_DATASOURCE_URL`: Database connection URL
- `SPRING_DATASOURCE_USERNAME`: Database username
- `SPRING_DATASOURCE_PASSWORD`: Database password
- `POSTGRES_DB`: PostgreSQL database name
- `POSTGRES_USER`: PostgreSQL username
- `POSTGRES_PASSWORD`: PostgreSQL password 