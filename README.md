# KavoraX Global — Enterprise Backend REST API

[![Java](https://img.shields.io/badge/Java-21-orange.svg)](https://adoptium.net/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.4.x-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-15%2B%20%7C%2018-blue.svg)](https://www.postgresql.org/)
[![Security](https://img.shields.io/badge/Spring%20Security-JWT%20(HMAC--SHA256)-red.svg)](https://github.com/jwtk/jjwt)
[![Build](https://img.shields.io/badge/Build-Maven%20Passing-success.svg)](pom.xml)

Production-ready, hardened REST API backend for **KavoraX Global Private Limited**, serving the public enterprise Nuxt website and administrative management console.

---

## 📋 Table of Contents

1. [Architecture & Tech Stack](#-architecture--tech-stack)
2. [Prerequisites](#-prerequisites)
3. [Step 1: Clone the Repository](#step-1-clone-the-repository)
4. [Step 2: Database Setup (PostgreSQL)](#step-2-database-setup-postgresql)
5. [Step 3: Environment Configuration](#step-3-environment-configuration)
6. [Step 4: Install Dependencies & Build Packages](#step-4-install-dependencies--build-packages)
7. [Step 5: Run the Application](#step-5-run-the-application)
8. [Step 6: Verify Startup & Health Checks](#step-6-verify-startup--health-checks)
9. [Step 7: Run Automated Tests](#step-7-run-automated-tests)
10. [API Endpoints Overview](#-api-endpoints-overview)
11. [Project Directory Structure](#-project-directory-structure)
12. [Troubleshooting](#-troubleshooting)

---

## 🏛 Architecture & Tech Stack

- **Language:** Java 21 (LTS)
- **Framework:** Spring Boot 3.4.x
- **Database:** PostgreSQL (15+ / 18 supported)
- **Data Access:** Spring Data JPA / Hibernate (non-destructive DDL)
- **Authentication & Security:**
  - Spring Security 6 (Stateless Session)
  - Cryptographic JWT with HMAC-SHA256 (`io.jsonwebtoken:jjwt:0.12.6`)
  - BCrypt password hashing (`BCryptPasswordEncoder`)
  - Brute-force defense with thread-safe login rate-limiting (`LoginAttemptService`)
- **Build Tool:** Apache Maven with pre-packaged Maven Wrapper (`mvnw` / `mvnw.cmd`)
- **API Contracts:** Fully standardized JSON schemas with unified `ErrorResponse`

---

## ⚙ Prerequisites

Before setting up the project, ensure your local environment meets the following requirements:

| Tool | Minimum Version | Check Command | Download Link |
| :--- | :--- | :--- | :--- |
| **Java JDK** | **21 (LTS)** | `java -version` | [Eclipse Adoptium Temurin 21](https://adoptium.net/temurin/releases/?version=21) |
| **PostgreSQL** | **15+ / 18** | `psql --version` | [PostgreSQL Downloads](https://www.postgresql.org/download/) |
| **Git** | **2.30+** | `git --version` | [Git Downloads](https://git-scm.com/downloads) |
| **Maven** | *(Optional)* | `mvn -version` | Maven Wrapper (`mvnw`) is included |

> **Note for Windows Users:** Ensure `JAVA_HOME` environment variable is set to your JDK 21 installation directory and added to your system `PATH`.

---

## 🚀 Step-by-Step Setup Guide

### Step 1: Clone the Repository

Open your terminal or command prompt and clone the repository:

```bash
git clone https://github.com/navin1256/kavorax-api.git
cd kavorax-api
```

---

### Step 2: Database Setup (PostgreSQL)

1. Ensure the PostgreSQL service is running on your machine:
   - **Windows:** Check `Services` (`services.msc`) -> Ensure `postgresql-x64-XX` is **Running**.
   - **macOS (Homebrew):** `brew services start postgresql@16`
   - **Linux:** `sudo systemctl start postgresql`

2. Open PostgreSQL CLI (`psql`) or your database GUI client (pgAdmin, DBeaver):
   ```bash
   psql -U postgres
   ```

3. Execute the following SQL commands to create the dedicated database and application user:
   ```sql
   -- Create database
   CREATE DATABASE kavorax_db;

   -- Create application user with password
   CREATE USER kavorax_app WITH ENCRYPTED PASSWORD 'your_secure_password_here';

   -- Grant permissions to database
   GRANT ALL PRIVILEGES ON DATABASE kavorax_db TO kavorax_app;

   -- Connect to the database and grant schema privileges (PostgreSQL 15+)
   \c kavorax_db
   GRANT ALL ON SCHEMA public TO kavorax_app;
   ```

---

### Step 3: Environment Configuration

The application reads its configuration from environment variables or `application.properties`.

1. Copy the example environment file:
   - **Windows (PowerShell):**
     ```powershell
     Copy-Item .env.example .env
     ```
   - **macOS / Linux:**
     ```bash
     cp .env.example .env
     ```

2. Open `.env` (or configure your IDE / terminal environment variables) and set your local credentials:

   ```env
   # PostgreSQL Connection
   SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/kavorax_db
   SPRING_DATASOURCE_USERNAME=kavorax_app
   SPRING_DATASOURCE_PASSWORD=your_secure_password_here

   # JWT Configuration (Must be at least 32 characters / 256 bits for HMAC-SHA256)
   JWT_SECRET=kavorax_super_secret_jwt_key_replace_this_in_production_min_256_bits!
   JWT_EXPIRATION_MS=86400000

   # Admin Database Seeding (true for local development, false in production)
   APP_SEED_ADMIN_ENABLED=true

   # Configurable CORS Allowed Origins
   CORS_ALLOWED_ORIGINS=http://localhost:3000,http://localhost:3001
   ```

---

### Step 4: Install Dependencies & Build Packages

Use the included Maven Wrapper (`mvnw` / `mvnw.cmd`) to download dependencies and compile the project without needing a manual Maven installation:

#### Windows (PowerShell / CMD):
```powershell
.\mvnw.cmd clean compile
```

#### macOS / Linux:
```bash
chmod +x ./mvnw
./mvnw clean compile
```

To build a standalone production JAR package (skipping tests for speed):
```bash
# Windows
.\mvnw.cmd clean package -DskipTests

# macOS / Linux
./mvnw clean package -DskipTests
```
The compiled executable JAR will be located at: `target/kavorax-api-0.0.1-SNAPSHOT.jar`.

---

### Step 5: Run the Application

You can start the backend using either the Maven Wrapper or by running the packaged JAR.

#### Option A: Running with Maven Spring Boot Plugin (Recommended for Development)

- **Windows:**
  ```powershell
  .\mvnw.cmd spring-boot:run
  ```

  *(If passing database password inline):*
  ```powershell
  .\mvnw.cmd spring-boot:run "-Dspring-boot.run.arguments=--spring.datasource.password=your_secure_password_here"
  ```

- **macOS / Linux:**
  ```bash
  ./mvnw spring-boot:run
  ```

#### Option B: Running the Packaged JAR

```bash
java -jar target/kavorax-api-0.0.1-SNAPSHOT.jar
```

#### What Happens on Application Startup:
1. **Database Connection:** Connects to PostgreSQL at `localhost:5432/kavorax_db`.
2. **Schema Verification:** Hibernate safely verifies and updates tables (`admin_users`, `contact_messages`, `demo_requests`) non-destructively without dropping any data.
3. **Admin User Seeding:** If `APP_SEED_ADMIN_ENABLED=true`, initializes the default administrator if not already present:
   - **Email:** `admin@kavorax.com`
   - **Password:** `SecureAuth_2025!`
4. **Server Ready:** The Tomcat web server listens on port **`8080`**.

---

### Step 6: Verify Startup & Health Checks

Once the server has started, verify health status in your browser or via curl:

#### 1. Application Liveness:
```bash
curl http://localhost:8080/api/health
```
**Expected Response:**
```json
{
  "status": "UP",
  "application": "kavorax-api",
  "version": "1.0.0"
}
```

#### 2. Database Connectivity:
```bash
curl http://localhost:8080/api/health/database
```
**Expected Response:**
```json
{
  "status": "UP",
  "database": "PostgreSQL",
  "detail": "Database connection is healthy and responsive"
}
```

---

### Step 7: Run Automated Tests

The repository contains an automated test suite verifying JWT token signing, claims extraction, tamper detection, brute-force rate-limiting, dual-route mappings, and admin authorization:

#### Windows:
```powershell
.\mvnw.cmd test "-Dspring.datasource.password=your_secure_password_here"
```

#### macOS / Linux:
```bash
SPRING_DATASOURCE_PASSWORD=your_secure_password_here ./mvnw test
```

**Expected Test Output:**
```text
[INFO] Results:
[INFO] Tests run: 26, Failures: 0, Errors: 0, Skipped: 0
[INFO] BUILD SUCCESS
```

---

## 📡 API Endpoints Overview

For complete schemas, request/response payloads, and error codes, refer to the full [API Integration Contract](docs/API_INTEGRATION_CONTRACT.md).

### 1. Public Intake Endpoints (No Token Required)

| Method | Endpoint | Description |
| :--- | :--- | :--- |
| `POST` | `/api/v1/contact-messages` | Public Contact Form intake |
| `POST` | `/api/v1/enquiries` | Legacy/Dual-mapped route for website compatibility |
| `POST` | `/api/v1/demo-requests` | Public Request a Demo form intake |
| `POST` | `/api/v1/session-requests` | Public Schedule a Session form intake |

### 2. Authentication

| Method | Endpoint | Description | Auth Required |
| :--- | :--- | :--- | :--- |
| `POST` | `/api/v1/auth/login` | Administrator authentication (issues JWT Bearer token) | None |

**Default Seed Credentials (Local Development):**
- **Email:** `admin@kavorax.com`
- **Password:** `SecureAuth_2025!`

### 3. Protected Admin Endpoints (`Authorization: Bearer <token>` Required)

| Method | Endpoint | Query Parameters | Description |
| :--- | :--- | :--- | :--- |
| `GET` | `/api/v1/admin/dashboard/stats` | None | Aggregated counts across demos, sessions, and enquiries |
| `GET` | `/api/v1/admin/contact-messages` | `page=0&size=20&sortBy=createdAt&sortDir=desc` | Paginated contact submissions (Dual: `/admin/enquiries`) |
| `GET` | `/api/v1/admin/demo-leads` | `page=0&size=20&type=ALL` | Paginated demo/session submissions (Dual: `/admin/demo-requests`) |

*Pagination metadata is returned in response headers: `X-Total-Count`, `X-Total-Pages`, `X-Current-Page`, `X-Page-Size`.*

---

## 📁 Project Directory Structure

```text
kavorax-api/
├── .mvn/wrapper/                      # Maven wrapper configuration and binary
├── docs/                              # Comprehensive architectural reports & API contracts
│   ├── API_INTEGRATION_CONTRACT.md    # Master API specification
│   ├── BACKEND_INTEGRATION_READINESS_REPORT.md
│   ├── openapi.yaml                   # OpenAPI 3.0 specification
│   └── KAVORAX_API_POSTMAN_SUMMARY.md # Postman testing guide
├── src/
│   ├── main/
│   │   ├── java/com/kavorax/api/
│   │   │   ├── KavoraxApiApplication.java # Spring Boot entry point
│   │   │   ├── config/                # SecurityConfig, CorsConfig, DataSeeder
│   │   │   ├── controller/            # REST controllers (public & admin sub-packages)
│   │   │   ├── dto/                   # Strongly typed request & response DTOs
│   │   │   ├── entity/                # JPA entities (AdminUser, ContactMessage, DemoRequest)
│   │   │   ├── exception/             # GlobalExceptionHandler & ErrorResponse
│   │   │   ├── repository/            # Spring Data JPA repositories
│   │   │   ├── security/              # JwtService, JwtAuthenticationFilter, LoginAttemptService
│   │   │   └── service/               # Core business services (AuthService, etc.)
│   │   └── resources/
│   │       └── application.properties # Main application configuration
│   └── test/java/com/kavorax/api/     # Unit, integration, and security test suites
├── .env.example                       # Environment variable template
├── .gitignore                         # Git ignore file (safeguards .env, target/, etc.)
├── KavoraX_API.postman_collection.json# Complete Postman test collection
├── mvnw / mvnw.cmd                    # Maven wrapper scripts
├── pom.xml                            # Project dependencies and Maven build configuration
└── README.md                          # Project documentation
```

---

## 🛠 Troubleshooting

### 1. `Connection to localhost:5432 refused`
- **Cause:** PostgreSQL is not running or listening on a different port.
- **Fix:** Check if PostgreSQL service is running (`Get-Service postgresql*` on Windows or `systemctl status postgresql` on Linux). Verify `SPRING_DATASOURCE_URL` in `.env`.

### 2. `password authentication failed for user "kavorax_app"`
- **Cause:** Database credentials do not match PostgreSQL settings.
- **Fix:** Update `SPRING_DATASOURCE_PASSWORD` in your `.env` or recreate the user credentials in `psql`:
  ```sql
  ALTER USER kavorax_app WITH PASSWORD 'your_password';
  ```

### 3. `Port 8080 was already in use`
- **Cause:** Another process is occupying port `8080`.
- **Fix:**
  - Find and stop the process:
    - **Windows:** `Get-Process -Id (Get-NetTCPConnection -LocalPort 8080).OwningProcess | Stop-Process`
    - **macOS / Linux:** `lsof -ti:8080 | xargs kill -9`
  - Or specify an alternative port in `.env`: `SERVER_PORT=8081`

### 4. `JWT Secret too short (WeakKeyException)`
- **Cause:** JJWT HMAC-SHA256 requires a secret of at least 256 bits (32 characters).
- **Fix:** Ensure `JWT_SECRET` in `.env` has at least 32 characters.

---

## 📄 License & Attribution

Copyright &copy; 2026 **KavoraX Global Private Limited**. All rights reserved.  
Internal enterprise backend — proprietary and confidential.
