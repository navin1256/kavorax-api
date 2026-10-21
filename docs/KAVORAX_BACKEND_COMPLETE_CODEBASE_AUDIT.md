# KavoraX Backend API — Complete Codebase Audit & Technical Report

**Project Name:** KavoraX Backend API  
**Project Path:** `C:\Users\aaa\Desktop\kavorax-api`  
**Audit Completion Date:** 2026-10-17  
**Auditor:** Antigravity Advanced Agentic System  
**Audit Methodology:** Full static source inspection, configuration verification, dependency resolution, build compilation, and isolated slice test execution.

---

## 1. Executive Summary

A complete, non-destructive, evidence-based audit of the **KavoraX Backend API** codebase was conducted. The application is a Spring Boot 3.4.0 REST backend running on Java 21, built with Maven 3.9.16, and targeting a PostgreSQL relational database.

### Core Findings Overview:
1. **Compilation & Unit Health:** The project compiles cleanly without errors using `./mvnw.cmd test-compile`. Running the 11 controller WebMvc slice tests (`AuthControllerTest`, `ContactMessageControllerTest`, `DemoRequestControllerTest`, and `HealthControllerTest`) yields **11 tests run, 0 failures, 0 errors, 0 skipped**.
2. **Endpoint Inventory:** Exactly **8 REST endpoints** are actively implemented across 7 `@RestController` classes. Four are public (`/api/health`, `/api/health/database`, `/api/v1/contact-messages`, `/api/v1/demo-requests`, plus the public login route `/api/v1/auth/login`), and three are admin-protected (`/api/v1/admin/contact-messages`, `/api/v1/admin/dashboard/stats`, `/api/v1/admin/demo-leads`).
3. **Critical Security Vulnerability — Static Hardcoded JWT Token:** Admin authentication does not perform real JSON Web Token generation or cryptographic verification. Both `AuthController.java` and `AdminAuthFilter.java` rely on an identical, static, hardcoded dummy token string (`eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.dummy_token_payload.signature`). Any client supplying this exact header gains permanent, unrestricted access to all `/api/v1/admin/**` endpoints.
4. **Critical Data Risk — Destructive DDL on Application Boot:** The `DataSeeder.java` bean executes hardcoded SQL statements (`DROP TABLE enquiries`, `DROP TABLE session_requests`, and `UPDATE demo_requests`) inside a `CommandLineRunner` every time the Spring context starts. If connected to a live database containing legacy tables or data, it drops tables automatically.
5. **Contract Discrepancy & Broken Public Route (`404`):** The primary contact route documented in `README.md`, `openapi.yaml`, and `KavoraX_API.postman_collection.json` (`POST /api/v1/enquiries`) was previously deleted and replaced with `POST /api/v1/contact-messages`. Any public frontend or API client calling `/api/v1/enquiries` receives an immediate `404 Not Found`.
6. **Incomplete Lead Intake Contract:** The `DemoRequest` entity supports fields for `type`, `phone`, `requirements`, `message`, `preferred_date`, and `time_slot`. However, the public inbound DTO (`DemoRequestDto.java`) only captures `name`, `email`, `company`, and `reqs`. As a result, public users cannot submit preferred dates, time slots, phone numbers, or session requests through the API.
7. **Architectural Inconsistencies:** The service layer is bypassed by 5 out of 7 controllers (`AuthController`, `AdminContactMessageController`, `AdminDemoRequestController`, `AdminDashboardController`, and `HealthController`), which query repositories or `JdbcTemplate` directly. `AdminDashboardController` injects `ContactMessageRepository` but never reads from it, completely omitting contact messages from dashboard metrics.

---

## 2. Verified Technology Stack

| Component | Specified in Requirements | Verified in Codebase | Evidence / Location |
|---|---|---|---|
| **Language & Runtime** | Java 21 | **Verified:** OpenJDK 21.0.12.1 (Temurin) | `pom.xml:L30` (`<java.version>21</java.version>`), CLI `java -version` |
| **Framework** | Spring Boot | **Verified:** Spring Boot 3.4.0 | `pom.xml:L8` (`<version>3.4.0</version>`) |
| **Build System** | Maven | **Verified:** Apache Maven 3.9.16 via Wrapper | `.mvn/wrapper/maven-wrapper.properties:L3`, `mvnw.cmd` |
| **Data Persistence** | Spring Data JPA / Hibernate | **Verified:** Hibernate 6.x via Starter | `pom.xml:L34-36`, `application.properties:L8-10` |
| **Database Driver** | PostgreSQL JDBC Driver | **Verified:** `org.postgresql:postgresql` | `pom.xml:L51-54`, `application.properties:L4-6` |
| **Input Validation** | Bean Validation (Jakarta) | **Verified:** Jakarta Validation API | `pom.xml:L38-40` (`spring-boot-starter-validation`) |
| **Security Framework** | Spring Security 6.x | **Verified:** `spring-boot-starter-security` | `pom.xml:L61-64`, `SecurityConfig.java:L12` |
| **Testing Libraries** | JUnit 5, Mockito, MockMvc | **Verified:** Spring Boot Starter Test & Security Test | `pom.xml:L56-59`, `L65-68` |
| **CORS Support** | Configurable CORS Filter | **Verified:** `WebMvcConfigurer` | `com.kavorax.api.config.CorsConfig:L9` |
| **API Documentation** | Swagger / OpenAPI | **Partially Implemented:** Static YAML only | `docs/openapi.yaml` exists; no runtime Springdoc/Swagger UI dependency |

---

## 3. Complete Project Folder Tree

```text
C:\Users\aaa\Desktop\kavorax-api\
├── .env.example
├── .gitattributes
├── .gitignore
├── HELP.md
├── java
├── KavoraX_API.postman_collection.json
├── mvnw
├── mvnw.cmd
├── pom.xml
├── README.md
├── temp_admin_app/
├── temp_experience-center.vue
├── test_apis.ps1
├── update_admin.py
├── .mvn/
│   └── wrapper/
│       ├── maven-wrapper.jar
│       └── maven-wrapper.properties
├── .vscode/
│   └── settings.json
├── docs/
│   ├── ADMIN_PANEL_BACKEND_INTEGRATION_REPORT.md
│   ├── ADMIN_PANEL_DATA_API_CONTRACT.md
│   ├── API_SUMMARY.md
│   ├── BACKEND_ERROR_FIX_REPORT.md
│   ├── BACKEND_LEADS_INTEGRATION_REPORT.md
│   ├── BACKEND_STARTUP_TROUBLESHOOTING.md
│   ├── BACKEND_STARTUP_VERIFICATION_REPORT.md
│   ├── CORS_CONFIGURATION_REPORT.md
│   ├── INTEGRATION_REPORT.md
│   ├── KAVORAX_API_POSTMAN_SUMMARY.md
│   ├── KAVORAX_BACKEND_API_REFERENCE.md
│   ├── KAVORAX_BACKEND_CONTACT_MESSAGES_REPORT.md
│   ├── KAVORAX_BACKEND_FILE_INVENTORY.md
│   ├── KAVORAX_BACKEND_INTEGRATION_REPORT.md
│   ├── KAVORAX_DEMO_LEADS_BACKEND_REPORT.md
│   ├── LEAD_TYPES_ADMIN_PANEL_REPORT.md
│   ├── LEAD_TYPES_BACKEND_REPORT.md
│   ├── LEAD_TYPES_PUBLIC_WEBSITE_REPORT.md
│   ├── LEADS_AND_SESSION_API_REPORT.md
│   ├── openapi.yaml
│   ├── PUBLIC_WEBSITE_API_CONTRACT.md
│   ├── PUBLIC_WEBSITE_BACKEND_INTEGRATION_REPORT.md
│   └── PUBLIC_WEBSITE_POSTMAN_TESTS.md
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── kavorax/
│   │   │           └── api/
│   │   │               ├── KavoraxApiApplication.java
│   │   │               ├── config/
│   │   │               │   ├── CorsConfig.java
│   │   │               │   ├── DataSeeder.java
│   │   │               │   └── SecurityConfig.java
│   │   │               ├── controller/
│   │   │               │   ├── AuthController.java
│   │   │               │   ├── ContactMessageController.java
│   │   │               │   ├── DemoRequestController.java
│   │   │               │   ├── HealthController.java
│   │   │               │   └── admin/
│   │   │               │       ├── AdminContactMessageController.java
│   │   │               │       ├── AdminDashboardController.java
│   │   │               │       └── AdminDemoRequestController.java
│   │   │               ├── dto/
│   │   │               │   ├── AdminContactMessageResponse.java
│   │   │               │   ├── AdminDemoRequestResponse.java
│   │   │               │   ├── ContactMessageRequest.java
│   │   │               │   ├── DashboardStatsDto.java
│   │   │               │   ├── DemoRequestDto.java
│   │   │               │   ├── LoginRequest.java
│   │   │               │   └── LoginResponse.java
│   │   │               ├── entity/
│   │   │               │   ├── AdminUser.java
│   │   │               │   ├── ContactMessage.java
│   │   │               │   └── DemoRequest.java
│   │   │               ├── exception/
│   │   │               │   ├── ErrorResponse.java
│   │   │               │   └── GlobalExceptionHandler.java
│   │   │               ├── repository/
│   │   │               │   ├── AdminUserRepository.java
│   │   │               │   ├── ContactMessageRepository.java
│   │   │               │   └── DemoRequestRepository.java
│   │   │               ├── security/
│   │   │               │   └── AdminAuthFilter.java
│   │   │               └── service/
│   │   │                   ├── ContactMessageService.java
│   │   │                   └── DemoRequestService.java
│   │   └── resources/
│   │       ├── application.properties
│   │       ├── static/
│   │       └── templates/
│   └── test/
│       └── java/
│           └── com/
│               └── kavorax/
│                   └── api/
│                       ├── KavoraxApiApplicationTests.java
│                       └── controller/
│                           ├── AuthControllerTest.java
│                           ├── ContactMessageControllerTest.java
│                           ├── DemoRequestControllerTest.java
│                           └── HealthControllerTest.java
└── target/
```

---

## 4. File-by-File Inventory Summary

A comprehensive, itemized breakdown of every source file is detailed in [`docs/KAVORAX_BACKEND_FILE_INVENTORY.md`](file:///c:/Users/aaa/Desktop/kavorax-api/docs/KAVORAX_BACKEND_FILE_INVENTORY.md). The application contains:
- **Application Core:** 1 main class (`KavoraxApiApplication.java`)
- **Configuration & Security:** 4 classes (`CorsConfig.java`, `DataSeeder.java`, `SecurityConfig.java`, `AdminAuthFilter.java`)
- **Controllers:** 7 classes (4 root controllers + 3 admin sub-controllers)
- **Services:** 2 business service classes (`ContactMessageService.java`, `DemoRequestService.java`)
- **Repositories:** 3 Spring Data JPA repository interfaces (`AdminUserRepository.java`, `ContactMessageRepository.java`, `DemoRequestRepository.java`)
- **Entities:** 3 JPA entity classes (`AdminUser.java`, `ContactMessage.java`, `DemoRequest.java`)
- **DTOs:** 7 request and response models
- **Exception Handling:** 2 classes (`GlobalExceptionHandler.java`, `ErrorResponse.java`)
- **Tests:** 5 test suites (1 context test + 4 WebMvc test classes)

---

## 5. Architecture & Request Flow Analysis

### 5.1 Architecture Overview
The application follows a Spring Boot layered architectural design, but violates standard architectural boundaries in multiple places:

```
[ HTTP Client / Browser / Nuxt ]
               │
               ▼
       [ CorsConfig (/api/**) ]
               │
               ▼
     [ AdminAuthFilter (/api/v1/admin/**) ]
               │
               ▼
[ Spring Security Filter Chain (Permit All) ]
               │
               ▼
      [ DispatcherServlet ]
               │
     ┌─────────┴────────────────────────────────┐
     │                                          │
[ Public Controllers ]                 [ Admin Controllers ]
  - HealthController                     - AdminContactMessageController
  - AuthController                       - AdminDashboardController
  - ContactMessageController             - AdminDemoRequestController
  - DemoRequestController                       │
     │                 │                        │
     ▼ (Direct)        ▼ (Service)              ▼ (Direct Bypass)
[JdbcTemplate]   [ContactMessageService]  [Repositories]
                 [DemoRequestService]           │
                       │                        ▼
                       ▼                   [PostgreSQL]
                 [Repositories]
                       │
                       ▼
                 [PostgreSQL]
```

### 5.2 Layer Violations and Inconsistencies
1. **Service Layer Bypassed:**
   - `AuthController.java` directly interacts with `AdminUserRepository` and `PasswordEncoder` without an `AuthService`.
   - `AdminContactMessageController.java` directly calls `ContactMessageRepository.findAll(...)`.
   - `AdminDemoRequestController.java` directly calls `DemoRequestRepository.findAll(...)`.
   - `AdminDashboardController.java` directly queries `DemoRequestRepository` and leaves `ContactMessageRepository` unused.
   - `HealthController.java` executes SQL directly via `JdbcTemplate` without a `HealthService`.
2. **Entity Leakage in REST Layer:**
   - Both `ContactMessageController.submitContactMessage` and `DemoRequestController.submitDemoRequest` return raw JPA entity instances directly in the `ResponseEntity` body rather than dedicated outbound response DTOs.
3. **Transaction Boundaries:**
   - `@Transactional` is properly applied to `ContactMessageService.createContactMessage` and `DemoRequestService.createDemoRequest`.
   - However, `AdminContactMessageController` and `AdminDemoRequestController` execute queries without explicit read-only transaction boundaries (`@Transactional(readOnly = true)`).

---

## 6. Complete REST API Inventory

| # | HTTP Method | Endpoint | Controller Class | Handler Method | Auth Required | Request DTO | Response Body | Database Operation | Status |
|---|---|---|---|---|---|---|---|---|---|
| 1 | `GET` | `/api/health` | `HealthController` | `health()` | None | None | `Map<String, String>` | None | **Implemented & Tested** |
| 2 | `GET` | `/api/health/database` | `HealthController` | `databaseHealth()` | None | None | `Map<String, String>` | `JdbcTemplate.execute("SELECT 1")` | **Implemented & Tested** |
| 3 | `POST` | `/api/v1/auth/login` | `AuthController` | `login(LoginRequest)` | None | `LoginRequest` | `LoginResponse` | `adminUserRepository.findByEmail` | **Implemented & Tested** |
| 4 | `POST` | `/api/v1/contact-messages` | `ContactMessageController` | `submitContactMessage(ContactMessageRequest)` | None | `ContactMessageRequest` | `ContactMessage` (entity) | `contactMessageRepository.save` | **Implemented & Tested** |
| 5 | `POST` | `/api/v1/demo-requests` | `DemoRequestController` | `submitDemoRequest(DemoRequestDto)` | None | `DemoRequestDto` | `DemoRequest` (entity) | `demoRequestRepository.save` | **Implemented & Tested** |
| 6 | `GET` | `/api/v1/admin/contact-messages` | `AdminContactMessageController` | `getAllContactMessages()` | Bearer Token | None | `List<AdminContactMessageResponse>` | `contactMessageRepository.findAll` | **Implemented, Untested** |
| 7 | `GET` | `/api/v1/admin/dashboard/stats` | `AdminDashboardController` | `getDashboardStats()` | Bearer Token | None | `DashboardStatsDto` | `demoRequestRepository.count*` | **Implemented, Untested** |
| 8 | `GET` | `/api/v1/admin/demo-leads` | `AdminDemoRequestController` | `getAllDemoRequests()` | Bearer Token | None | `List<AdminDemoRequestResponse>` | `demoRequestRepository.findAll` | **Implemented, Untested** |

---

## 7. Request & Response Contract Analysis

### 7.1 Schema Definitions & Serialization Rules
- **Date/Time Formatting:** Dates generated by the backend (`createdAt`) use standard ISO-8601 strings (e.g., `2026-10-17T14:15:00.123456`). In `DemoRequest`, the user-specified booking date (`preferred_date`) is stored and serialized as a raw `String` without date or timezone parsing.
- **Field Name Mappings:** Jackson `@JsonProperty("reqs")` is used in `DemoRequestDto` to map JSON property `reqs` to the internal field `requirements`.
- **Validation Constraints:**
  - `LoginRequest`: `email` (@NotBlank, @Email), `password` (@NotBlank).
  - `ContactMessageRequest`: `name` (@NotBlank, max 100), `email` (@NotBlank, @Email, max 255), `phone` (max 20), `subject` (max 255), `message` (@NotBlank, max 2000).
  - `DemoRequestDto`: `name` (@NotBlank, max 100), `email` (@NotBlank, @Email, max 255), `company` (max 100), `requirements` (`reqs`, max 1000).

### 7.2 Inconsistent Error Response Contracts
There is no unified error response contract across the application:
1. `GlobalExceptionHandler` returns `ErrorResponse` with fields `timestamp`, `status`, `error`, `message`, `validationErrors`.
2. `AuthController` returns a single key map: `{"error": "Invalid email or password"}` on 401.
3. `AdminAuthFilter` writes raw JSON strings: `{"error": "Unauthorized - Missing or invalid token"}` without standard envelope or HTTP Content-Type headers.
4. `HealthController` returns `{"status": "DOWN", "database": "disconnected"}` on 503.

---

## 8. Database & Data Model Audit

### 8.1 Configuration & Datasource Details
- **Datasource URL:** Defaults to `jdbc:postgresql://localhost:5432/kavorax_db` (configurable via `SPRING_DATASOURCE_URL`).
- **Username:** Defaults to `kavorax_app` (configurable via `SPRING_DATASOURCE_USERNAME`).
- **Password:** Defaults to `12345` (hardcoded fallback in `application.properties:L6`).
- **Hibernate DDL Auto:** `spring.jpa.hibernate.ddl-auto=update`. (Risk: Auto-modifies schema on launch).
- **Dialect:** `org.hibernate.dialect.PostgreSQLDialect`.

### 8.2 Entity Inventory Table

| Entity | Table Name | Primary Key | Columns & Constraints | Lifecycle Callbacks | Timestamp Handling | Issues Identified |
|---|---|---|---|---|---|---|
| `AdminUser` | `admin_users` | `id` (IDENTITY) | `email` (VARCHAR, unique, not null), `password` (VARCHAR, not null), `role` (VARCHAR, not null, default "ADMIN") | None | None | Missing `createdAt` / `updatedAt` audit fields; no account lock status. |
| `ContactMessage` | `contact_messages` | `id` (IDENTITY) | `name` (VARCHAR, not null), `email` (VARCHAR, not null), `phone` (VARCHAR), `subject` (VARCHAR), `message` (TEXT, not null), `status` (VARCHAR, default "RECEIVED"), `created_at` (TIMESTAMP, not null, updatable=false) | `@PrePersist onCreate()` | `createdAt = LocalDateTime.now()` | Missing `updatedAt`; status stored as raw unvalidated string. |
| `DemoRequest` | `demo_requests` | `id` (IDENTITY) | `name` (VARCHAR, not null), `email` (VARCHAR, not null), `company` (VARCHAR), `type` (VARCHAR, default "DEMO_BOOKING"), `phone` (VARCHAR), `requirements` (TEXT), `message` (TEXT), `preferred_date` (VARCHAR), `time_slot` (VARCHAR), `status` (VARCHAR, default "PENDING"), `created_at` (TIMESTAMP, not null, updatable=false) | `@PrePersist onCreate()` | `createdAt = LocalDateTime.now()` | `preferred_date` stored as unconstrained `VARCHAR` instead of `DATE`/`LocalDate`; unindexed polymorphic types. |

### 8.3 Repository Analysis
1. `AdminUserRepository`: Inherits `JpaRepository<AdminUser, Long>`. Contains `findByEmail(String email)`. Actively used by `AuthController` and `DataSeeder`.
2. `ContactMessageRepository`: Inherits `JpaRepository<ContactMessage, Long>`. Contains `countByStatus(String status)`. The custom method is never called in any controller.
3. `DemoRequestRepository`: Inherits `JpaRepository<DemoRequest, Long>`. Contains `countByStatus(String status)` and `countByType(String type)`. Actively used by `AdminDashboardController`.

---

## 9. Authentication, Authorization & Security Audit

### 9.1 Severity Matrix & Findings Table

| ID | Finding | Severity | Affected File / Endpoint | Evidence | Impact | Recommended Remediation |
|---|---|---|---|---|---|---|
| **SEC-01** | Static Hardcoded Dummy JWT Token | **Critical** | `AdminAuthFilter.java:L16`, `AuthController.java:L35` | `private static final String DUMMY_TOKEN = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.dummy_token_payload.signature";` | Any user with the hardcoded string has permanent, irrevocable admin access. No signature verification, claims, or expiration exist. | Implement genuine JWT verification using `jjwt` or Spring Security Resource Server with HMAC-SHA256 or RSA signing and short expiration. |
| **SEC-02** | Destructive DDL Execution on Every Boot | **Critical** | `DataSeeder.java:L41,L52` | `jdbcTemplate.execute("DROP TABLE enquiries"); jdbcTemplate.execute("DROP TABLE session_requests");` | Application will permanently delete tables and records on startup if run against a database with existing tables. | Immediately remove DDL execution from `CommandLineRunner`. Use managed migrations (Liquibase or Flyway). |
| **SEC-03** | Hardcoded Credentials in Tracked Files | **High** | `application.properties:L6`, `DataSeeder.java:L14-18` | `spring.datasource.password=${SPRING_DATASOURCE_PASSWORD:12345}`, `admin@kavorax.com`, `SecureAuth_2025!` | Anyone with codebase access knows default local database and administrative passwords. | Remove default fallbacks for secrets; require environment variables in production. |
| **SEC-04** | Missing Rate Limiting / Brute Force Protection | **High** | `AuthController.java:L28`, Public Endpoints | No rate-limiting annotations, filter, or Bucket4j integration. | Attackers can execute unlimited credential guessing attacks on `/api/v1/auth/login` or spam public lead endpoints. | Integrate IP/account-level rate limiting using Bucket4j, Redis, or API gateway throttling. |
| **SEC-05** | Global Permissive Security Configuration | **Medium** | `SecurityConfig.java:L24-26` | `.authorizeHttpRequests(auth -> auth.anyRequest().permitAll())` | Spring Security itself does not protect any endpoints. Protection is deferred entirely to custom filter. | Configure Spring Security `SecurityFilterChain` with explicit `.requestMatchers("/api/v1/admin/**").hasRole("ADMIN")`. |
| **SEC-06** | Missing Content-Type Header on Auth Errors | **Medium** | `AdminAuthFilter.java:L35,L43` | `response.getWriter().write("{\"error\": ...}");` without `response.setContentType(...)` | Clients receive raw text instead of standard `application/json`, which may break frontend JSON parsing. | Call `response.setContentType("application/json")` and `response.setCharacterEncoding("UTF-8")`. |
| **SEC-07** | Defective `.gitignore` Pattern for `.env` | **Low** | `.gitignore:L34` | `. e n v` | Git will track `.env` files because the rule contains spaced characters. | Correct line to `.env`. |

---

## 10. Validation, Error Handling & Logging Audit

### 10.1 Scenario Execution Evaluation

| Scenario | Expected Behavior | Actual Implementation | Evidence | Evaluation |
|---|---|---|---|---|
| **Invalid Email in Contact** | 400 Bad Request with field error | 400 Bad Request with `validationErrors.email` | `ContactMessageControllerTest:L87-98` | **Implemented & Tested** |
| **Missing Name in Demo** | 400 Bad Request with field error | 400 Bad Request with `validationErrors.name` | `DemoRequestControllerTest:L63-75` | **Implemented & Tested** |
| **Malformed JSON Payload** | 400 Bad Request ("Malformed JSON request") | Handled by `handleNotReadable` | `GlobalExceptionHandler.java:L46-51` | **Implemented, Untested** |
| **Unsupported HTTP Method** | 405 Method Not Allowed | Handled by `handleMethodNotSupported` | `GlobalExceptionHandler.java:L39-44` | **Implemented, Untested** |
| **Non-Existent Route (404)** | 404 with structured JSON | Spring Boot default error response | No handler for `NoResourceFoundException` | **Gap: Inconsistent JSON** |
| **Database Constraint Violation** | 409 Conflict or 400 Bad Request | 500 Internal Server Error | Handled by generic `Exception.class` | **Gap: Misclassified 500** |
| **SQL Database Outage** | 503 Service Unavailable | `HealthController` returns 503 | `HealthController.java:L33` | **Implemented & Tested** |

---

## 11. Dependencies, Configuration & Environments

### 11.1 Dependency Inventory (`pom.xml`)
- `spring-boot-starter-data-jpa`: Spring Data and Hibernate ORM.
- `spring-boot-starter-validation`: Jakarta Bean Validation and Hibernate Validator.
- `spring-boot-starter-web`: Spring MVC and embedded Tomcat server.
- `jackson-databind`: JSON serialization/deserialization (redundantly specified, as starter-web already provides it).
- `org.postgresql:postgresql`: PostgreSQL JDBC driver (runtime scope).
- `spring-boot-starter-security`: Spring Security authentication/authorization framework.
- `spring-boot-starter-test`: JUnit Jupiter, Mockito, AssertJ, Spring Test (test scope).
- `spring-security-test`: Spring Security testing utilities (test scope).

### 11.2 Environment Properties Review
- `spring.application.name=kavorax-api`
- `server.port=8080`
- `server.error.include-stacktrace=never` (Good security practice: prevents stack trace leakage).
- `server.error.include-message=always`
- `cors.allowed-origins`: Configured in `CorsConfig.java` to default to `http://localhost:3000,http://localhost:3001`.

---

## 12. Testing & Build Health Audit

### 12.1 Test Suite Inventory Table

| Test File | Test Type | Class / Endpoints Covered | Assertions | Database Required | Actual Test Result |
|---|---|---|---|---|---|
| `AuthControllerTest.java` | WebMvc Slice | `AuthController` (`POST /api/v1/auth/login`) | Token presence, user email, HTTP 200, HTTP 401 | No (`@MockitoBean`) | **PASSED (3/3)** |
| `ContactMessageControllerTest.java` | WebMvc Slice | `ContactMessageController` (`POST /api/v1/contact-messages`) | HTTP 201, entity ID, status RECEIVED, validation errors | No (`@MockitoBean`) | **PASSED (4/4)** |
| `DemoRequestControllerTest.java` | WebMvc Slice | `DemoRequestController` (`POST /api/v1/demo-requests`) | HTTP 201, entity ID, status PENDING, validation errors | No (`@MockitoBean`) | **PASSED (2/2)** |
| `HealthControllerTest.java` | WebMvc Slice | `HealthController` (`GET /api/health`, `/api/health/database`) | HTTP 200, status UP, database connected | No (`@MockitoBean`) | **PASSED (2/2)** |
| `KavoraxApiApplicationTests.java` | Full `@SpringBootTest` | Application Context Bootstrap | Context loading | **Yes** (Real PostgreSQL required; triggers `DataSeeder`) | **Not Executed** (Avoided to prevent `DataSeeder` table DROP execution per Rule 8) |

### 12.2 Test Execution Evidence
- **Command Executed:**
  ```powershell
  .\mvnw.cmd test "-Dtest=AuthControllerTest,ContactMessageControllerTest,DemoRequestControllerTest,HealthControllerTest"
  ```
- **Exit Code:** `0` (BUILD SUCCESS)
- **Results:** `Tests run: 11, Failures: 0, Errors: 0, Skipped: 0`
- **Execution Time:** 32.373 seconds.

---

## 13. Frontend Integration Readiness

### 13.1 Public Website (`kavorax-web`)
1. **Contact Form:**
   - **Contract Status:** Broken if calling `/api/v1/enquiries` (returns 404).
   - **Remediation:** Public frontend must be configured to call `POST /api/v1/contact-messages`.
2. **Demo Booking / Session Request:**
   - **Contract Status:** Incomplete. Frontend demo booking or session request components expecting to submit dates, timeslots, and phone numbers cannot send them through `POST /api/v1/demo-requests` because `DemoRequestDto` drops them.

### 13.2 Admin Panel (`kavorax-admin`)
1. **Authentication:**
   - **Contract Status:** Fully compatible with `stores/adminAuth.ts`. Returns token and user object matching `{ id, email, role }`.
2. **Dashboard Statistics:**
   - **Contract Status:** Partially compatible. Returns `{ totalDemoLeads, totalDemoBookings, totalSessionRequests, totalEnquiries, newLeads, pendingLeads }`. However, contact messages are not included in any of these metrics.
3. **Leads Management:**
   - **Contract Status:** Incompatible with older documentation. Admin panel must fetch demo leads from `GET /api/v1/admin/demo-leads` (not `/api/v1/admin/demo-requests`) and contact messages from `GET /api/v1/admin/contact-messages` (not `/api/v1/admin/enquiries`).
4. **Content Management Modules:**
   - **Contract Status:** Missing. Zero endpoints exist for Products, Industries, Careers, Case Studies, or Settings.

---

## 14. Performance, Reliability & Maintainability Findings

1. **Unbounded Queries (Lack of Pagination):**
   - In both `AdminContactMessageController.java:L27` and `AdminDemoRequestController.java:L27`, queries call `findAll(Sort.by(Direction.DESC, "createdAt"))`. Once the lead database grows to tens of thousands of records, this will cause memory spikes, high database latency, and potential `OutOfMemoryError`.
2. **Unused Injected Dependencies:**
   - In `AdminDashboardController.java:L16`, `ContactMessageRepository` is injected via `@Autowired` constructor injection but is never referenced in any method.
3. **Inappropriate Column Data Types:**
   - In `DemoRequest.java:L36`, `preferred_date` is mapped as a `VARCHAR` (`String`). It lacks date validation, timezone awareness, and cannot be properly indexed or sorted as a calendar date in SQL.
4. **Variable Naming Conventions:**
   - In `ContactMessageController.java:L15` and `ContactMessageService.java:L12`, field variables are capitalized (`private final ContactMessageService ContactMessageService;`), violating standard Java camelCase conventions.

---

## 15. Confirmed Bugs and Risks

1. **Bug: Calling `POST /api/v1/enquiries` results in 404 Not Found.**
2. **Bug: Calling `GET /api/v1/admin/demo-requests` results in 404 Not Found (actual endpoint is `/demo-leads`).**
3. **Bug: Calling `GET /api/v1/admin/enquiries` results in 404 Not Found (actual endpoint is `/admin/contact-messages`).**
4. **Bug: Dashboard stats completely ignores `contact_messages` records.**
5. **Data Risk: Running the application against an existing database drops `enquiries` and `session_requests` tables via `DataSeeder.java`.**
6. **Security Risk: All admin APIs accept a fixed, static dummy token with zero cryptographic verification.**

---

## 16. Missing Features & Incomplete Implementations

1. **Real JWT Issuance & Verification:** No secret key, HMAC signing, or expiration.
2. **Public Session Request API:** No dedicated endpoint or DTO to submit a session request with preferred date/timeslot.
3. **Lead Status Management API:** No `PATCH` or `PUT` endpoints exist to update lead statuses (e.g. from `PENDING` to `CONTACTED` or `RESOLVED`).
4. **Admin User Management:** No API to change passwords, create admins, or revoke accounts.
5. **Content Management APIs:** No endpoints for products, solutions, careers, or site configuration.
6. **Database Migration Tooling:** No Flyway or Liquibase integration.

---

## 17. Recommendations Prioritized by Severity

### Priority 1: Critical (Must Address Before Production Deployment)
1. **Remove Destructive DDL from `DataSeeder.java`:** Immediately remove `DROP TABLE` statements from `CommandLineRunner`.
2. **Implement Real JWT Authentication:** Replace the static dummy token in `AuthController` and `AdminAuthFilter` with standard JJWT or Spring Security OAuth2 Resource Server token handling.
3. **Remove Hardcoded Default Passwords:** Remove default fallback passwords from `application.properties` and `DataSeeder.java`.

### Priority 2: High (Required for Proper System Integration)
4. **Provide Route Compatibility or Update Clients:** Add a redirect or controller mapping for `POST /api/v1/enquiries` pointing to `ContactMessageController`, or align public frontend documentation.
5. **Expand `DemoRequestDto`:** Add support for `phone`, `preferred_date`, `time_slot`, and `message` so public booking forms can transmit full lead details.
6. **Include Contact Messages in Dashboard Stats:** Update `AdminDashboardController` to query `contactMessageRepository` and return distinct metrics for general inquiries vs demo requests.

### Priority 3: Medium (Quality, Performance & Reliability)
7. **Implement Pagination:** Replace `findAll()` with `Pageable` (`PageRequest.of(page, size)`) on all admin list endpoints.
8. **Unify Error Responses:** Standardize all controllers, filters, and exception handlers to return the uniform `ErrorResponse` model with `Content-Type: application/json`.
9. **Fix `.gitignore`:** Correct line 34 from `. e n v` to `.env`.
10. **Refactor Service Layer Bypasses:** Introduce `AuthService` and `AdminLeadService` to restore proper 3-tier architectural boundaries.

---

## 18. Verification Limitations

1. **Live PostgreSQL Runtime Endpoints:** Live HTTP requests against `http://localhost:8080` were not executed because the server was not actively running (verified via port 8080 check) and starting the server would trigger destructive table drop operations in `DataSeeder.java`.
2. **External Frontend Verification:** Nuxt frontend repositories (`kavorax-web` and `KavoraX-Global-Admin-Panel`) reside outside this workspace root and were audited based on contracts, DTO schemas, and local reference snapshots (`temp_admin_app/`).
3. **Database Schema Verification:** Live PostgreSQL connection could not be queried directly without starting the Spring context; schema verification was conducted via entity mapping inspection.

---

## 19. Exact Files Inspected & Commands Executed

### Files Inspected
- `pom.xml`
- `mvnw`, `mvnw.cmd`, `.mvn/wrapper/maven-wrapper.properties`
- `application.properties`, `.env.example`, `.gitignore`, `.gitattributes`
- `README.md`, `HELP.md`, `test_apis.ps1`, `update_admin.py`
- `KavoraxApiApplication.java`
- `CorsConfig.java`, `DataSeeder.java`, `SecurityConfig.java`
- `AdminAuthFilter.java`
- `HealthController.java`, `AuthController.java`, `ContactMessageController.java`, `DemoRequestController.java`
- `AdminContactMessageController.java`, `AdminDashboardController.java`, `AdminDemoRequestController.java`
- `AdminUser.java`, `ContactMessage.java`, `DemoRequest.java`
- `AdminUserRepository.java`, `ContactMessageRepository.java`, `DemoRequestRepository.java`
- `ContactMessageService.java`, `DemoRequestService.java`
- `LoginRequest.java`, `LoginResponse.java`, `ContactMessageRequest.java`, `DemoRequestDto.java`
- `DashboardStatsDto.java`, `AdminContactMessageResponse.java`, `AdminDemoRequestResponse.java`
- `ErrorResponse.java`, `GlobalExceptionHandler.java`
- `AuthControllerTest.java`, `ContactMessageControllerTest.java`, `DemoRequestControllerTest.java`, `HealthControllerTest.java`, `KavoraxApiApplicationTests.java`

### Commands Executed
- `java -version` (Confirmed Java 21)
- `.\mvnw.cmd -v` (Confirmed Maven 3.9.16)
- `.\mvnw.cmd test-compile` (Confirmed clean compilation)
- `Test-NetConnection -ComputerName localhost -Port 5432` (Confirmed Postgres port 5432 is open)
- `Test-NetConnection -ComputerName localhost -Port 8080` (Confirmed backend server is not running)
- `.\mvnw.cmd test "-Dtest=AuthControllerTest,ContactMessageControllerTest,DemoRequestControllerTest,HealthControllerTest"` (11 tests executed, 11 passed)
