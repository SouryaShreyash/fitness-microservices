# Fitness Microservices Platform

A full-stack fitness tracking platform built using Spring Boot microservices architecture, API Gateway, Service Discovery, centralized configuration, and a modern React frontend.

---

# Tech Stack

## Backend

* Java 17
* Spring Boot
* Spring Cloud Gateway
* Spring Security
* Keycloak Authentication
* Eureka Service Discovery
* Spring Cloud Config Server
* Maven
* REST APIs
* MongoDB / MySQL

## Frontend

* React.js
* Vite
* JavaScript
* CSS

---

# Microservices Architecture

```text
fitness-microservices
│
├── gateway                # API Gateway
├── userservice            # User management service
├── activityservice        # Workout/activity tracking service
├── aiservice              # AI-based fitness recommendations
├── configserver           # Centralized configuration server
├── eureka                 # Eureka discovery server
└── fitness_frontend       # React frontend
```

---

# Features

* User Registration & Authentication
* JWT / Keycloak-based Security
* API Gateway Routing
* Service Discovery using Eureka
* Centralized Configuration Management
* Workout & Activity Tracking
* AI-based Recommendations
* Microservices Communication
* Scalable Architecture
* Responsive Frontend UI

---

# System Architecture

```text
Frontend (React)
       │
       ▼
API Gateway
       │
 ┌─────┼───────────────┐
 │     │               │
 ▼     ▼               ▼
User  Activity      AI Service
Service Service
       │
       ▼
 Database

Additional Components:
- Eureka Server
- Config Server
- Keycloak Authentication
```

---

# Getting Started

## Prerequisites

Make sure you have installed:

* Java 17+
* Maven
* Node.js
* Git
* MongoDB / MySQL
* IntelliJ IDEA or VS Code

---

# Clone Repository

```bash
git clone https://github.com/SouryaShreyash/fitness-microservices.git
cd fitness-microservices
```

---

# Running the Backend Services

## 1. Start Eureka Server

```bash
cd eureka
mvn spring-boot:run
```

## 2. Start Config Server

```bash
cd configserver
mvn spring-boot:run
```

## 3. Start User Service

```bash
cd userservice
mvn spring-boot:run
```

## 4. Start Activity Service

```bash
cd activityservice
mvn spring-boot:run
```

## 5. Start AI Service

```bash
cd aiservice
mvn spring-boot:run
```

## 6. Start API Gateway

```bash
cd gateway
mvn spring-boot:run
```

---

# Running Frontend

```bash
cd fitness_frontend
npm install
npm run dev
```

Frontend runs on:

```text
http://localhost:5173
```

---

# Eureka Dashboard

```text
http://localhost:8761
```

---

# API Gateway

```text
http://localhost:8080
```

---

# Example API Endpoints

## User Service

```http
POST /api/users/register
POST /api/users/login
GET /api/users/profile
```

## Activity Service

```http
POST /api/activity/add
GET /api/activity/all
```

## AI Service

```http
GET /api/ai/recommendation
```

---

# Security

This project uses:

* JWT Authentication
* Keycloak Integration
* Secure API Gateway Routing
* Role-Based Access Control

---

# Future Improvements

* Docker Containerization
* Kubernetes Deployment
* CI/CD Pipeline
* Redis Caching
* OpenAPI / Swagger Documentation
* Real-time Notifications
* Advanced AI Fitness Suggestions
* Mobile Application

---

# Learning Outcomes

This project demonstrates:

* Microservices Architecture
* Distributed Systems
* API Gateway Implementation
* Service Discovery
* Centralized Configuration
* Secure Authentication
* Full Stack Development
* Scalable Backend Design

---

# Author

## Shreyash Sourya

Computer Science Undergraduate

GitHub:

[https://github.com/SouryaShreyash](https://github.com/SouryaShreyash)

---

# License

This project is developed for educational and learning purposes.
