# Spring Boot REST API with Spring Security

This is a sample Spring Boot project that demonstrates how to build a REST API with Spring Security. The application uses Java and includes integration with an H2 database and JWT tokens for authentication.

## Features

- Secure REST API endpoints using Spring Security.
- Two roles: CUSTOMER and ADMINISTRATOR.
- H2 in-memory database integration.
- JWT token-based authentication.

## Prerequisites

- Java Development Kit (JDK) installed.
- Maven build tool installed.

## Getting Started

1. Clone the repository:

   ```
   git clone https://github.com/xllWhiteReaper/spring_boot.git

   ```

2. Switch branches:

   ```
   git checkout develop
   ```

3. Enter to the corresponding directory:

   ```
   cd security_v2
   ```

4. Build the project:

   ```
   mvn clean install
   ```

5. Run the application:

   ```
   mvn spring-boot:run
   ```

6. The application will start running on http://localhost:8080.

## Endpoints

### Authentication

- `POST /auth/login`: Authenticates a user and generates a JWT token. Requires a valid username and password in the request body. Returns the generated token.

### Products

- `GET /api/products`: Retrieves all products. Requires a valid JWT token with CUSTOMER or ADMINISTRATOR role.
- `POST /api/products`: Creates a new product. Requires a valid JWT token with ADMINISTRATOR role. Requires the product details in the request body.

## Security Configuration

The project includes the following security configuration:

- JWT authentication and authorization.
- Two roles: CUSTOMER and ADMINISTRATOR.
- Custom authentication filter (`JwtAuthenticationFilter`) to validate JWT tokens.
- Custom authentication service (`AuthenticationService`) to authenticate users.
- Configuration class (`HttpSecurityConfig`) to configure security settings.

## Database Configuration

The application uses an H2 in-memory database for simplicity. The users are loaded through another class (`UserCommandLineRunner`) at the start of the program.

## Project Structure

The project follows a standard Maven project structure, with the main classes located in the `src/main/java` directory. The relevant packages and classes are as follows:

- `com.xllWhiteReaper.security_v2`: Main package.
  - `com.xllWhiteReaper.security_v2.controllers`: Contains the REST API controllers.
    - `AuthenticationController`: Controller for handling authentication-related endpoints.
    - `ProductController`: Controller for handling product-related endpoints.
  - `com.xllWhiteReaper.security_v2.config`: Contains security-related configuration.
    - `JwtAuthenticationFilter`: Custom authentication filter for JWT token validation.
    - `SecurityBeansInjector`: Custom class to customize the beans to be used by the app.
    - `HttpSecurityConfig`: Configuration class for Spring Security settings.
  - `com.xllWhiteReaper.security_v2.models`: Contains the data models.
  - `com.xllWhiteReaper.security_v2.repositories`: Contains the repositories for database access.

## License

This project is licensed under the Apache 2.0 License. Visit the [LICENSE](https://www.apache.org/licenses/LICENSE-2.0) page for more information.

## Conclusion

This project serves as a starting point for building a Spring Boot REST API with Spring Security. It demonstrates the integration of an H2 database and the usage of JWT tokens for authentication. Feel free to modify and expand upon it according to your specific requirements.

For more details, refer to the source code and javadoc comments within the project.

Happy coding!
