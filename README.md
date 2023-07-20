# Products Application

This is a simple Spring Boot application for managing products and user authentication. The application allows users to perform CRUD (Create, Read, Update, Delete) operations on products and provides user registration and login functionalities.

## Prerequisites

To run the application, you need to have the following installed:

- Java Development Kit (JDK) version 8 or higher
- Docker (optional, required only if using the provided docker-compose file)

## Getting Started

1. Clone the repository to your local machine:

```asgl
git clone https://github.com/your_username/products-app.git
cd products-app
```


2. (Optional) If you have Docker installed, you can run the PostgreSQL database using the provided `docker-compose.yml` file:

```agsl
docker-compose up -d
```


3. Build and run the Spring Boot application:


```agsl
./mvnw spring-boot:run
```


The application will be available at `http://localhost:8080`.

## API Endpoints

The application provides the following API endpoints:

### Authentication

- `POST /auth/register`: Register a new user with a unique login and password. Required JSON body:

  ```json
  {
    "login": "user123",
    "password": "password",
    "role": "USER"
  }
 
<br />
The role field can be either "USER" or "ADMIN". Only admins can create, update, and delete products.

-`POST /auth/login`: Authenticate a user and receive an access token. Required JSON body:
```json
{
    "login": "user123",
    "password": "password"
}
```
This endpoint will return an access token that needs to be included in the headers of subsequent requests for authentication.

## Products
- `GET /api/products`: Get a list of all products.

- `GET /api/products/{id}`: Get a specific product by its id.

- `POST /api/products`: Create a new product. Only accessible to users with the "ADMIN" role. Required JSON body:
```json
{
    "name": "Product Name",
    "price": 12.34,
    "type": "IT"
}
```
The type field can be "IT", "BEAUTY", "ELECTRONIC", or "FURNITURE".

- `DELETE /api/products/{id}`: Delete a product by its id. Only accessible to users with the "ADMIN" role.

- `PATCH /api/products/{id}`: Update a product by its id. Only accessible to users with the "ADMIN" role. Required JSON body (fields to update):
```json
{
  "name": "New Product Name",
  "price": 19.99,
  "type": "FURNITURE"
}
```
All or some of the fields can be updated.

## Users

- `GET /api/users`: Get a list of all users.

- `GET /api/users/{id}`: Get a specific user by its id.

- `DELETE /api/users/{id}`: Delete a user by its id. Only accessible to users with the "ADMIN" role.

## Security

The application uses JSON Web Tokens (JWT) for user authentication. Upon successful login, a token is generated and returned to the user. This token needs to be included in the headers of subsequent requests for authentication.

`Authorization Bearer {token}`

## Data Persistence

The application uses a PostgreSQL database for data storage. The connection details can be found in the application.properties file.

## Migrations with Flyway

The application uses Flyway for database schema management. Database migrations are stored as SQL scripts in the `resources/db/migration` directory. Flyway will automatically apply these scripts to the database on application startup, ensuring that the database schema is up-to-date.

## Technology Stack

- `Maven`: The build tool used to manage dependencies and build the application.
- `Spring Boot`: The core framework for building the application.
- `Spring Security`: Provides authentication and authorization features.
- `PostgreSQL`: Database for data storage.
- `JWT`: JSON Web Tokens for user authentication.
- `Lombok`: Reduces boilerplate code in model classes.
- `Flyway`: Manages database migrations. Migrations can be found in the `resources/db/migration` directory.
- `Docker` (optional): Containerization for the PostgreSQL database.


## Postman collection

[Download Postman Collection](./postman-collection.spring-boot-products-crud.postman_collection.json)