# Account Managment Application
This project is an Account Management Application API built using Spring Boot, leveraging a [Microservice Architecture](#microservice). It consists of five distinct applications, each responsible for specific features and functions within the system. The use of microservices allows for scalability, maintainability, and flexibility in development and deployment.
## Microservices Overview
### 1. Discovery Server
The Discovery Server acts as the central point for service registration and discovery within the microservices architecture. It enables dynamic scaling by allowing services to register themselves and discover other services without needing hardcoded endpoints.

* **Technology Used:** `Spring Cloud Netflix Eureka`
* **PORT:** `8761`
* **Functionality:**
  - Manages service instances and tracks their availability.
  - Allows dynamic scaling and service health monitoring.

### 2. API Gateway
The API Gateway serves as the entry point for all client requests. It routes incoming requests to the appropriate microservice and handles cross-cutting concerns like authentication, logging, and rate limiting. Running on 

* **Technology Used:** `Spring Cloud Gateway`
* **PORT:** `8080`
* **Functionality:**
  - Routes client requests to the appropriate microservices.
  - Performs authentication checks using JWT tokens.
  - Provides load balancing and centralized logging.

### 3. Account Management Service
The Account Management Service handles all operations related to account creation, modification, and deletion. It is responsible for maintaining the account data and ensuring data integrity.

* **Technology Used:** `Spring Boot`, `Spring Data JPA`
* **PORT:** `8081`
* **Functionality:**
  - Manages CRUD operations for account data.
  - Ensures data consistency and integrity using JPA.
  - Interacts with the database for data storage and retrieval.

### 4. User Management Service
The User Management Service is responsible for managing user profiles, permissions, and roles. It ensures secure access control and user data management.

* **Technology Used:** `Spring Boot`, `Spring Data JPA`
* **PORT:** `8082`
* **Functionality:**
  - Handles user registration, updates, and deletions.
  - Manages user roles and permissions.
  - Utilizes JPA for efficient data queries and persistence.

### 5. Auth Server
The Auth Server handles authentication and authorization processes within the application. It issues and validates JWT tokens to ensure secure access to protected resources.

* **Technology Used:** `Spring Security`, `JWT`
* **PORT:** `8888`
* **Functionality:**
  - Authenticates users and issues JWT tokens.
  - Validates JWT tokens for API requests.
  - Manages user sessions and token expiration.

### 6. Config Server
The Config Server centralizes the management of configuration properties for all microservices. It ensures consistency and simplifies the configuration management process.

* **Technology Used:** `Spring Cloud Config`
* **PORT:** `3005`
* **Functionality:**
  - Provides centralized configuration management for all microservices.
  - Supports dynamic property updates without restarting services.
  - Integrates with version control systems for configuration versioning.

## Key Technologies
* **`JWT(JSON Web Token)`:** Used for secure authentication and authorization, providing a stateless way to manage user sessions.
* **`RabbitMQ`:** Implements message queuing for asynchronous communication between services, improving scalability and resilience.
* **`Docker & Docker Compose`:** Facilitates containerization and orchestration of the microservices, enabling consistent development and deployment environments.
* **`JPA (Java Persistance API)`:** Simplifies database interactions by providing a robust framework for data queries and persistence.

## Docker Compose
The application uses Docker Compose to run and manage all microservices in a unified environment. This setup ensures that all components can be easily deployed and scaled across different environments.

+ **Docker Compose File:** The `docker-compose.yml` file defines the services, networks, and volumes for the application. It orchestrates the startup order and configuration of each microservice.
+ **Benifits:**
  - Simplifies development by running all services locally in isolated containers.
  - Ensures consistency between development, testing, and production environments.
  - Allows for easy scaling and management of services.

## Getting Started
To run the application, ensure you have Docker and Docker Compose installed.<br /><br />
Open `CMD` > `Open the folder where docker compose file is` > `docker-compose up`.<br /><br />
This command will build and start all microservices, along with their dependencies, as defined in the `docker-compose.yml` file.

# Microservice
This project utilizes a microservice architecture, which is a modern approach to building software applications. Microservices decompose an application into a collection of small, autonomous services, each focused on a specific business capability. This architecture provides several advantages over traditional monolithic architectures, including flexibility, scalability, and ease of maintenance.

### Key Characteristics:
* **Independence:** Each microservice can be developed, deployed, and scaled independently. This allows for parallel development and faster iteration cycles.

* **Decentralized Data Management:** Each microservice can manage its own database or data storage, which allows for tailored data management solutions and reduces the risk of centralized data bottlenecks.

* **Scalability:** Services can be independently scaled according to their specific resource requirements, optimizing performance and resource utilization.

* **Technology Diversity:** Different technologies or programming languages can be used for different services, allowing developers to choose the best tools for each specific task.

* **Fault Isolation:** Failures in one microservice do not necessarily affect the entire system, improving the overall resilience of the application.

* **Continuous Deployment:** The independent nature of microservices enables frequent updates and deployments without affecting other parts of the system.

### Benefits:
Microservices are ideal for complex and large-scale applications, enabling the development of scalable and maintainable systems. This architecture is particularly well-suited for cloud environments and supports agile development practices and continuous delivery.
