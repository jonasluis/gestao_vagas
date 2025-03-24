# 💼 Gestão Vagas

A comprehensive job management system built with Spring Boot, designed to connect companies and job seekers.

![Job Management Platform](https://images.unsplash.com/photo-1454165804606-c3d57bc86b40?w=1200&auto=format&fit=crop&q=80)

## 🎯 Overview

Gestão Vagas is a robust platform that facilitates job posting, application management, and candidate tracking. It provides separate interfaces for companies and candidates, ensuring a streamlined recruitment process.

## ⚡ Key Features

- 🏢 **Company Portal**
  - Post and manage job openings
  - Review applications
  - Company profile management
  - Candidate search and filtering

- 👤 **Candidate Portal**
  - Professional profile creation
  - Job application tracking
  - Resume/CV management
  - Job search and filtering

- 🔐 **Security**
  - JWT Authentication
  - Role-based access control
  - Secure password handling
  - API endpoint protection

## 🛠️ Technologies

### Backend
- Java 17
- Spring Boot 3
- Spring Security
- Spring Data JPA
- PostgreSQL
- JWT Authentication
- Maven

### Development Tools
- Docker
- JUnit 5
- Swagger/OpenAPI
- Maven

## 📋 Prerequisites

- Java 17+
- Maven
- Docker & Docker Compose
- PostgreSQL

## 🚀 Getting Started

1. Clone the repository:
```bash
git clone https://github.com/jonasluis/gestao_vagas.git
```

2. Navigate to project directory:
```bash
cd gestao_vagas
```

3. Start PostgreSQL using Docker:
```bash
docker-compose up -d
```

4. Build the project:
```bash
mvn clean install
```

5. Run the application:
```bash
mvn spring-boot:run
```

The API will be available at `http://localhost:8080`

## 📚 API Documentation

Once the application is running, access the API documentation at:
- Swagger UI: `http://localhost:8080/swagger-ui.html`
- OpenAPI JSON: `http://localhost:8080/v3/api-docs`

## 🗄️ Database Schema

```
companies
├── id (UUID)
├── name
├── website
├── description
└── created_at

jobs
├── id (UUID)
├── company_id (FK)
├── title
├── description
├── requirements
├── salary_range
└── created_at

candidates
├── id (UUID)
├── name
├── email
├── password
└── created_at

applications
├── id (UUID)
├── job_id (FK)
├── candidate_id (FK)
├── status
└── applied_at
```

## 🧪 Testing

Run the test suite:
```bash
mvn test
```

Generate test coverage report:
```bash
mvn verify
```

## 📦 Project Structure

```
gestao_vagas/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── jonasluis/
│   │   │           └── gestao_vagas/
│   │   │               ├── controllers/
│   │   │               ├── dtos/
│   │   │               ├── entities/
│   │   │               ├── repositories/
│   │   │               ├── services/
│   │   │               └── security/
│   │   └── resources/
│   │       └── application.properties
│   └── test/
│       └── java/
└── pom.xml
```

## 🔒 Security Considerations

- Passwords are encrypted using BCrypt
- JWT tokens for authentication
- Role-based authorization
- Input validation and sanitization
- Rate limiting on API endpoints

## 🤝 Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## 👤 Author

**Jonas Luis**
- GitHub: [@jonasluis](https://github.com/jonasluis)

## 📄 License

This project is licensed under the MIT License.

## 🙏 Acknowledgments

- Spring Boot community for excellent documentation
- All contributors who have helped improve this project
- PostgreSQL community for the robust database system

---

Made with ❤️ by Jonas Luis
