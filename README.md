# Flight Reservation System

## Introduction

Welcome to the Flight Reservation System! This project is a comprehensive, full-stack application designed to streamline the process of booking flights. It was developed by Kuponiyi Oluwaponire Micheal, a passionate Full-Stack Developer. This system is built with modern technologies, ensuring a secure, scalable, and user-friendly experience for travelers, travel agencies, and airlines.

## Features

- **User Authentication and Authorization**: Secure JWT-based authentication.
- **Flight Search and Booking**: Search flights by criteria and book them easily.
- **Reservation Management**: Manage flight reservations with ease.
- **Payment Processing**: Secure payment processing for booking flights.
- **Admin Panel**: Tools for managing flights, users, and viewing reports.
- **Email Notifications**: Automated email notifications for booking confirmations.

## Technologies Used

- **Frontend**: React, HTML5, CSS3, JavaScript
- **Backend**: Spring Boot, Java
- **Database**: PostgreSQL
- **Image Storage**: Amazon S3
- **Authentication**: JWT, Spring Security
- **API Gateway**: Spring Cloud Gateway
- **Deployment**: Docker, Kubernetes

## Architecture

The application follows a microservices architecture, where each service is designed to handle specific business functions. Here’s a high-level overview of the architecture:

![Architecture Diagram](architecture_diagram_url)

### Components

1. **User Service**: Manages user registration, authentication, and profiles.
2. **Flight Service**: Handles flight information and search functionality.
3. **Reservation Service**: Manages flight reservations.
4. **Payment Service**: Handles payment processing.
5. **Admin Service**: Admin functionalities for managing the system.
6. **API Gateway**: Manages and routes requests to the appropriate services.

## Installation

### Prerequisites

- Java 11 or higher
- Node.js
- Docker
- Kubernetes
- PostgreSQL
- Amazon S3 account

### Backend Setup

1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/flight-reservation-system.git
   cd flight-reservation-system
   ```

2. Configure the PostgreSQL database in `application.properties`:
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/flightdb
   spring.datasource.username=yourusername
   spring.datasource.password=yourpassword
   ```

3. Build and run the backend services:
   ```bash
   ./mvnw clean install
   ./mvnw spring-boot:run
   ```

### Frontend Setup

1. Navigate to the frontend directory:
   ```bash
   cd frontend
   ```

2. Install the dependencies and start the development server:
   ```bash
   npm install
   npm start
   ```

### Docker and Kubernetes

1. Build Docker images for the services:
   ```bash
   docker build -t user-service ./user-service
   docker build -t flight-service ./flight-service
   docker build -t reservation-service ./reservation-service
   docker build -t payment-service ./payment-service
   docker build -t admin-service ./admin-service
   docker build -t api-gateway ./api-gateway
   ```

2. Deploy the application to Kubernetes:
   ```bash
   kubectl apply -f kubernetes/deployment.yml
   ```

## Usage

### User Registration and Authentication

- Register a new user.
- Login with the registered credentials to receive a JWT token.
- Use the token to access protected routes.

### Flight Search and Booking

- Search for flights by specifying departure and arrival locations, dates, and other criteria.
- Book a flight by selecting from the available options.

### Reservation Management

- View and manage your reservations.
- Modify or cancel existing reservations.

### Payment Processing

- Securely pay for your flight bookings using integrated payment gateways.

### Admin Panel

- Admin users can log in to manage flights, users, and view detailed reports.

## API Endpoints

### User Service

- `POST /api/users/register`: Register a new user.
- `POST /api/users/login`: Login and receive a JWT token.
- `GET /api/users/profile`: Get user profile details.

### Flight Service

- `GET /api/flights/search`: Search for flights based on criteria.
- `GET /api/flights/{id}`: Get details of a specific flight.

### Reservation Service

- `POST /api/reservations`: Create a new reservation.
- `GET /api/reservations/{id}`: Get details of a specific reservation.
- `PUT /api/reservations/{id}`: Modify an existing reservation.
- `DELETE /api/reservations/{id}`: Cancel a reservation.

### Payment Service

- `POST /api/payments`: Process a payment for a reservation.

### Admin Service

- `GET /api/admin/flights`: Get all flights (admin only).
- `POST /api/admin/flights`: Add a new flight (admin only).
- `PUT /api/admin/flights/{id}`: Update flight details (admin only).
- `DELETE /api/admin/flights/{id}`: Delete a flight (admin only).

## Challenges and Learnings

### Most Difficult Technical Challenge

Integrating a secure and efficient payment gateway was one of the most challenging aspects of this project. The task required thorough research, detailed error handling, and robust testing to ensure seamless transactions.

**Situation**: Ensuring secure and smooth payment transactions.
**Task**: Implement a secure payment gateway with real-time transaction updates.
**Action**: Chose Stripe for its robust API. Configured secure SSL connection and handled webhook delays.
**Result**: Successfully integrated Stripe, providing secure and real-time payment processing.

### Learnings

- Enhanced skills in Spring Boot, React, Docker, and Kubernetes.
- Gained insights into secure authentication and payment processing.
- Improved understanding of microservices architecture and project management.

## About Me

I'm Kuponiyi Oluwaponire Micheal, a passionate Full-Stack Developer dedicated to creating efficient and user-friendly applications. When I'm not coding, I enjoy exploring new technologies and traveling.

- [GitHub Repository](https://github.com/Kponire/airline_reservation_app/)
- [Deployed Project](https://flyease-reservation.netlify.app)


Thank you for checking out my project! Your feedback and thoughts are greatly appreciated. 🚀
