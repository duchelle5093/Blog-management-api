# Blog Management API

A RESTful API for managing blog articles and comments built with Spring Boot.

## Overview

This API allows users to:
- Create, read, update, and delete blog articles
- Add comments to articles
- View all comments for a specific article

## Technology Stack

- **Java 21**
- **Spring Boot 3.4.5** - Framework for building production-ready applications
- **Spring Data JPA** - Data persistence using JPA 
- **Lombok** - Reduces boilerplate code
- **PostgreSQL** - Database to store articles and comments
- **Swagger/OpenAPI** - API documentation
- **Maven** - Build and dependency management

## Project Structure

```
src/main/java/com/nathan/blogmanagementapi/
├── controller       # REST Controllers
├── model            # Entity classes
├── repository       # Data repositories
├── service          # Business logic
├── dto              # Data Transfer Objects
├── exception        # Custom exceptions and handlers
└── config           # Configuration classes
```

## Models

### Article
- `id`: Long (Primary Key)
- `title`: String (Required, 3-100 characters)
- `content`: String (Required)
- `createdAt`: LocalDateTime (Auto-generated)
- `updatedAt`: LocalDateTime (Auto-updated)
- One-to-Many relationship with Comments

### Comment
- `id`: Long (Primary Key)
- `content`: String (Required)
- `article`: Article (Required, Many-to-One relationship)
- `createdAt`: LocalDateTime (Auto-generated)

## API Endpoints

### Article Endpoints

| Method | URL                  | Description            | Status Codes            |
|--------|----------------------|------------------------|-------------------------|
| GET    | /api/articles        | Get all articles       | 200                     |
| GET    | /api/articles/{id}   | Get article by ID      | 200, 404               |
| POST   | /api/articles        | Create a new article   | 201, 400               |
| PUT    | /api/articles/{id}   | Update an article      | 200, 400, 404          |
| DELETE | /api/articles/{id}   | Delete an article      | 204, 404               |

### Comment Endpoints

| Method | URL                           | Description                | Status Codes      |
|--------|-------------------------------|----------------------------|-------------------|
| GET    | /api/comments/article/{id}    | Get comments for article   | 200, 404         |
| POST   | /api/comments                 | Add a comment to article   | 201, 400, 404    |

## Setup and Running

### Prerequisites
- Java 21 or higher
- Maven
- PostgreSQL database

### Database Configuration
Edit `src/main/resources/application.properties` to configure your database:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/blog_management_api
spring.datasource.username=your_username
spring.datasource.password=your_password
```

### Building the Project
```bash
mvn clean install
```

### Running the Application
```bash
mvn spring:boot run
```
Or run the JAR file:
```bash
java -jar target/blog-management-api-0.0.1-SNAPSHOT.jar
```

The application will be available at: http://localhost:8080

## API Documentation

Swagger UI is integrated for API documentation and testing. Access it at:
- http://localhost:8080/swagger-ui.html

API documentation JSON is available at:
- http://localhost:8080/api-docs

## How to Use

### Creating an Article
```bash
curl -X POST 'http://localhost:8080/api/articles' \
-H 'Content-Type: application/json' \
-d '{
    "title": "Getting Started with Spring Boot",
    "content": "Spring Boot makes it easy to create stand-alone, production-grade Spring based Applications."
}'
```

### Getting All Articles
```bash
curl -X GET 'http://localhost:8080/api/articles'
```

### Adding a Comment to an Article
```bash
curl -X POST 'http://localhost:8080/api/comments' \
-H 'Content-Type: application/json' \
-d '{
    "content": "Great article! Thanks for sharing.",
    "articleId": 1
}'
```

## Error Handling

The API uses standard HTTP status codes to indicate the success or failure of requests:

- `200 OK`: Successful operation
- `201 Created`: Resource successfully created
- `204 No Content`: Resource successfully deleted
- `400 Bad Request`: Invalid request format or data
- `404 Not Found`: Resource not found
- `500 Internal Server Error`: Server-side error

## License

This project is licensed under the MIT License - see the LICENSE file for details.

