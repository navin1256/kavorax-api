# KavoraX Backend — Complete File Inventory

**Project Path:** `C:\Users\aaa\Desktop\kavorax-api`  
**Audit Date:** 2026-10-17  
**Build Tool:** Apache Maven 3.9.16 (via included Maven Wrapper)  
**JDK:** OpenJDK 21 (Eclipse Adoptium 21.0.12.1+1)  
**Framework:** Spring Boot 3.4.0  

---

## 1. Complete Project Structure Tree

```text
C:\Users\aaa\Desktop\kavorax-api\
├── .env.example                                      # Example environment configuration
├── .gitattributes                                  # Line ending configurations for wrapper/scripts
├── .gitignore                                      # Git ignore specifications (contains syntax bug)
├── HELP.md                                         # Spring Boot generated starter guidance
├── java                                            # 0-byte orphan file (created accidentally in CLI)
├── KavoraX_API.postman_collection.json              # Postman collection for manual endpoint verification
├── mvnw                                            # Unix/macOS Maven Wrapper execution script
├── mvnw.cmd                                        # Windows batch Maven Wrapper execution script
├── pom.xml                                         # Maven Project Object Model (dependencies & build)
├── README.md                                       # Primary developer onboarding instructions
├── temp_admin_app/                                 # External frontend admin panel snapshot (for reference)
├── temp_experience-center.vue                      # External Vue component snapshot
├── test_apis.ps1                                   # PowerShell manual REST verification script
├── update_admin.py                                 # Python script modifying external admin panel
├── .mvn/
│   └── wrapper/
│       ├── maven-wrapper.jar                       # Maven Wrapper binary executable
│       └── maven-wrapper.properties                # Maven distribution configuration (v3.9.16)
├── .vscode/
│   └── settings.json                               # VS Code workspace Java configuration
├── docs/                                           # Project documentation and audit reports
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
│   ├── KAVORAX_BACKEND_CONTACT_MESSAGES_REPORT.md
│   ├── KAVORAX_BACKEND_INTEGRATION_REPORT.md
│   ├── KAVORAX_DEMO_LEADS_BACKEND_REPORT.md
│   ├── LEAD_TYPES_ADMIN_PANEL_REPORT.md
│   ├── LEAD_TYPES_BACKEND_REPORT.md
│   ├── LEAD_TYPES_PUBLIC_WEBSITE_REPORT.md
│   ├── LEADS_AND_SESSION_API_REPORT.md
│   ├── openapi.yaml                                # Static OpenAPI 3.0 specification
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
│   │       ├── application.properties               # Spring Boot runtime configuration
│   │       ├── static/                             # Static asset directory (empty)
│   │       └── templates/                          # Template directory (empty)
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
└── target/                                         # Maven compiled classes and test reports
```

---

## 2. File-by-File Inventory Table

| File Path | File Type | Real Responsibility | Important Dependencies | Current Status | Notes & Observations |
|---|---|---|---|---|---|
| `pom.xml` | Maven Configuration | Declares project dependencies, Java 21 properties, Spring Boot 3.4.0 parent, and build plugin. | `spring-boot-starter-data-jpa`, `spring-boot-starter-validation`, `spring-boot-starter-web`, `postgresql`, `spring-boot-starter-security`, `spring-boot-starter-test`, `spring-security-test`, `jackson-databind` | **Implemented** | Dependencies compile cleanly with Maven 3.9.16. |
| `mvnw` | Shell Script | Unix/Linux/macOS wrapper script executing Maven without global install. | Bash / POSIX shell | **Implemented** | Active wrapper script. |
| `mvnw.cmd` | Windows Batch | Windows command wrapper script executing Maven without global install. | Windows CMD / PowerShell | **Implemented** | Active wrapper script used for builds. |
| `.mvn/wrapper/maven-wrapper.properties` | Properties | Configures Apache Maven 3.9.16 download distribution for wrapper. | Maven Wrapper 3.3.4 | **Implemented** | Used automatically by `mvnw`/`mvnw.cmd`. |
| `.env.example` | Env Example | Illustrates the local database password configuration. | None | **Implemented** | Mentions `SPRING_DATASOURCE_PASSWORD`. |
| `.gitignore` | Git Config | Specifies untracked files and directories for Git source control. | Git | **Partially Implemented** | **Defect:** Line 34 reads `. e n v` with spaces; does not reliably ignore `.env`. |
| `.gitattributes` | Git Config | Specifies line endings (LF for `mvnw`, CRLF for `*.cmd`). | Git | **Implemented** | Standard configuration. |
| `README.md` | Markdown | Developer startup instructions and API documentation. | None | **Partially Implemented** | **Outdated:** References deprecated `POST /api/v1/enquiries` which no longer exists. |
| `HELP.md` | Markdown | Spring Boot initializer reference links. | None | **Implemented** | Boilerplate documentation. |
| `java` | Plain Text / Empty | 0-byte orphan file in project root. | None | **Broken / Orphan** | Accidental CLI redirect artifact; can be safely removed. |
| `KavoraX_API.postman_collection.json` | JSON | Postman collection covering System, Auth, and Public APIs. | Postman Schema v2.1 | **Partially Implemented** | Includes `/api/v1/enquiries` which returns 404 in current code. Missing admin routes. |
| `test_apis.ps1` | PowerShell | Script testing Demo, Contact, Login, Dashboard Stats, and Admin Contact APIs. | Windows PowerShell | **Implemented** | Test utility for running manual curl/REST checks. |
| `update_admin.py` | Python Script | Script used to modify external Nuxt admin panel navigation and dashboard cards. | Python 3 | **Stand-alone Script** | References external path `C:\Users\aaa\Desktop\Admin panel\kavorax-admin\app`. |
| `.vscode/settings.json` | JSON | Configures VS Code Java language server build configuration. | VS Code | **Implemented** | Sets `"java.configuration.updateBuildConfiguration": "interactive"`. |
| `src/main/resources/application.properties` | Properties | Application configuration for port, PostgreSQL datasource, JPA, and error handling. | Spring Boot, PostgreSQL Driver, Hibernate | **Implemented** | Contains hardcoded default fallback password `12345` and `ddl-auto=update`. |
| `src/main/java/com/kavorax/api/KavoraxApiApplication.java` | Java Source | Main Spring Boot application entry point and bootstrap runner. | `SpringApplication`, `@SpringBootApplication` | **Implemented** | Standard Spring Boot application launcher. |
| `src/main/java/com/kavorax/api/config/CorsConfig.java` | Java Source | WebMvcConfigurer configuring global CORS policy for `/api/**`. | `WebMvcConfigurer`, `CorsRegistry` | **Implemented** | Allows origins `http://localhost:3000,http://localhost:3001` with credentials. |
| `src/main/java/com/kavorax/api/config/DataSeeder.java` | Java Source | CommandLineRunner seeding default admin account and executing table migration/drop SQL. | `AdminUserRepository`, `PasswordEncoder`, `JdbcTemplate` | **Implemented / High Risk** | **High Risk:** Contains hardcoded `DROP TABLE enquiries` and `DROP TABLE session_requests` SQL executed at every boot. Hardcoded default admin credentials. |
| `src/main/java/com/kavorax/api/config/SecurityConfig.java` | Java Source | Spring Security configuration providing BCrypt encoder and permit-all filter chain. | `HttpSecurity`, `SecurityFilterChain`, `BCryptPasswordEncoder` | **Implemented** | Disables CSRF; permits all HTTP requests; no UserDetailsService configured. |
| `src/main/java/com/kavorax/api/security/AdminAuthFilter.java` | Java Source | Servlet filter intercepting `/api/v1/admin/**` requests to validate Bearer token. | `OncePerRequestFilter`, `HttpServletRequest`, `HttpServletResponse` | **Implemented / Security Gap** | **Security Gap:** Compares token against static hardcoded string; does not verify genuine cryptographic signature, claims, or expiration. Does not set response `Content-Type: application/json`. |
| `src/main/java/com/kavorax/api/controller/HealthController.java` | Java Source | Exposes system liveness and database connectivity health endpoints. | `JdbcTemplate`, `ResponseEntity` | **Implemented** | Provides `GET /api/health` and `GET /api/health/database`. Tested. |
| `src/main/java/com/kavorax/api/controller/AuthController.java` | Java Source | Handles admin authentication, credential verification, and token emission. | `AdminUserRepository`, `PasswordEncoder`, `LoginRequest`, `LoginResponse` | **Implemented** | Validates password with BCrypt; returns hardcoded dummy token. Directly accesses repository. Tested. |
| `src/main/java/com/kavorax/api/controller/ContactMessageController.java` | Java Source | Handles public submission of general contact messages. | `ContactMessageService`, `ContactMessageRequest`, `ContactMessage` | **Implemented** | Exposes `POST /api/v1/contact-messages`. Returns raw entity on creation. Tested. |
| `src/main/java/com/kavorax/api/controller/DemoRequestController.java` | Java Source | Handles public submission of demo booking requests. | `DemoRequestService`, `DemoRequestDto`, `DemoRequest` | **Implemented** | Exposes `POST /api/v1/demo-requests`. Returns raw entity on creation. Tested. |
| `src/main/java/com/kavorax/api/controller/admin/AdminContactMessageController.java` | Java Source | Admin endpoint listing all contact messages sorted by newest first. | `ContactMessageRepository`, `AdminContactMessageResponse` | **Implemented** | Exposes `GET /api/v1/admin/contact-messages`. Directly accesses repository. Unbounded query (no pagination). No automated tests. |
| `src/main/java/com/kavorax/api/controller/admin/AdminDashboardController.java` | Java Source | Admin endpoint aggregating lead counts and statistics for the dashboard. | `DemoRequestRepository`, `ContactMessageRepository`, `DashboardStatsDto` | **Implemented** | Exposes `GET /api/v1/admin/dashboard/stats`. Injects `ContactMessageRepository` but never uses it. Excludes contact messages from dashboard metrics. No automated tests. |
| `src/main/java/com/kavorax/api/controller/admin/AdminDemoRequestController.java` | Java Source | Admin endpoint listing all unified demo leads sorted by newest first. | `DemoRequestRepository`, `AdminDemoRequestResponse` | **Implemented** | Exposes `GET /api/v1/admin/demo-leads`. Directly accesses repository. Unbounded query (no pagination). No automated tests. |
| `src/main/java/com/kavorax/api/service/ContactMessageService.java` | Java Source | Business service creating and persisting contact messages in transactional boundary. | `ContactMessageRepository`, `ContactMessageRequest`, `ContactMessage` | **Implemented** | Standard transactional service method `createContactMessage`. |
| `src/main/java/com/kavorax/api/service/DemoRequestService.java` | Java Source | Business service creating and persisting demo requests in transactional boundary. | `DemoRequestRepository`, `DemoRequestDto`, `DemoRequest` | **Implemented** | Standard transactional service method `createDemoRequest`. |
| `src/main/java/com/kavorax/api/entity/AdminUser.java` | Java Source | JPA entity representing an admin account in PostgreSQL table `admin_users`. | JPA Annotations (`@Entity`, `@Table`, `@Column`, `@Id`) | **Implemented** | Fields: `id`, `email` (unique), `password`, `role`. Missing audit timestamps. |
| `src/main/java/com/kavorax/api/entity/ContactMessage.java` | Java Source | JPA entity representing contact messages in table `contact_messages`. | JPA Annotations, `LocalDateTime`, `@PrePersist` | **Implemented** | Fields: `id`, `name`, `email`, `phone`, `subject`, `message`, `status`, `createdAt`. |
| `src/main/java/com/kavorax/api/entity/DemoRequest.java` | Java Source | JPA entity representing demo requests, session bookings, and enquiries in `demo_requests`. | JPA Annotations, `LocalDateTime`, `@PrePersist` | **Implemented** | Unified table structure. Fields: `id`, `name`, `email`, `company`, `type`, `phone`, `requirements`, `message`, `date`, `timeSlot`, `status`, `createdAt`. Stores `date` as raw String. |
| `src/main/java/com/kavorax/api/repository/AdminUserRepository.java` | Java Source | Spring Data JPA repository for `AdminUser` data access. | `JpaRepository` | **Implemented** | Method: `findByEmail(String email)`. |
| `src/main/java/com/kavorax/api/repository/ContactMessageRepository.java` | Java Source | Spring Data JPA repository for `ContactMessage` data access. | `JpaRepository` | **Implemented** | Method: `countByStatus(String status)` (unused). |
| `src/main/java/com/kavorax/api/repository/DemoRequestRepository.java` | Java Source | Spring Data JPA repository for `DemoRequest` data access. | `JpaRepository` | **Implemented** | Methods: `countByStatus(String status)`, `countByType(String type)`. |
| `src/main/java/com/kavorax/api/dto/LoginRequest.java` | Java Source | Inbound request payload for `/api/v1/auth/login`. | Jakarta Validation (`@NotBlank`, `@Email`) | **Implemented** | Fields: `email`, `password`. |
| `src/main/java/com/kavorax/api/dto/LoginResponse.java` | Java Source | Outbound response payload for successful login containing token and user profile. | None | **Implemented** | Fields: `token`, nested `user` (`id`, `email`, `role`). |
| `src/main/java/com/kavorax/api/dto/ContactMessageRequest.java` | Java Source | Inbound request payload for `/api/v1/contact-messages`. | Jakarta Validation (`@NotBlank`, `@Email`, `@Size`) | **Implemented** | Validates lengths and email format. |
| `src/main/java/com/kavorax/api/dto/DemoRequestDto.java` | Java Source | Inbound request payload for `/api/v1/demo-requests`. | Jakarta Validation, Jackson `@JsonProperty("reqs")` | **Implemented** | Only captures `name`, `email`, `company`, `reqs`. Missing fields for `phone`, `date`, `timeSlot`, `type`. |
| `src/main/java/com/kavorax/api/dto/DashboardStatsDto.java` | Java Source | Outbound aggregate statistics payload for admin dashboard. | None | **Implemented** | Fields: `totalDemoLeads`, `totalDemoBookings`, `totalSessionRequests`, `totalEnquiries`, `newLeads`, `pendingLeads`. |
| `src/main/java/com/kavorax/api/dto/AdminContactMessageResponse.java` | Java Source | Outbound DTO for `/api/v1/admin/contact-messages`. | `LocalDateTime` | **Implemented** | DTO mirroring `ContactMessage` entity for admin consumption. |
| `src/main/java/com/kavorax/api/dto/AdminDemoRequestResponse.java` | Java Source | Outbound DTO for `/api/v1/admin/demo-leads`. | `LocalDateTime` | **Implemented** | DTO presenting unified lead details for admin consumption. |
| `src/main/java/com/kavorax/api/exception/ErrorResponse.java` | Java Source | Standard error model returned by `GlobalExceptionHandler`. | `LocalDateTime`, `Map<String, String>` | **Implemented** | Fields: `timestamp`, `status`, `error`, `message`, `validationErrors`. |
| `src/main/java/com/kavorax/api/exception/GlobalExceptionHandler.java` | Java Source | Centralized exception advice catching validation, method not allowed, bad JSON, and general exceptions. | `@RestControllerAdvice`, `@ExceptionHandler` | **Implemented** | Maps exceptions to `ErrorResponse`. Missing specific handlers for 404, DB conflicts, or unauthorized filter errors. |
| `src/test/java/com/kavorax/api/KavoraxApiApplicationTests.java` | Java Source | Full Spring Boot context integration test. | `@SpringBootTest`, JUnit 5 | **Implemented / Env Dependent** | Boots entire Spring context and connects to PostgreSQL. |
| `src/test/java/com/kavorax/api/controller/AuthControllerTest.java` | Java Source | WebMvc slice tests for `AuthController`. | `@WebMvcTest`, `@MockitoBean`, `MockMvc` | **Implemented & Passing** | Tests valid login, bad password, unknown email. 3 tests pass. |
| `src/test/java/com/kavorax/api/controller/ContactMessageControllerTest.java` | Java Source | WebMvc slice tests for `ContactMessageController`. | `@WebMvcTest`, `@MockitoBean`, `MockMvc` | **Implemented & Passing** | Tests successful creation, missing name, missing message, bad email. 4 tests pass. |
| `src/test/java/com/kavorax/api/controller/DemoRequestControllerTest.java` | Java Source | WebMvc slice tests for `DemoRequestController`. | `@WebMvcTest`, `@MockitoBean`, `MockMvc` | **Implemented & Passing** | Tests successful creation and validation failures. 2 tests pass. |
| `src/test/java/com/kavorax/api/controller/HealthControllerTest.java` | Java Source | WebMvc slice tests for `HealthController`. | `@WebMvcTest`, `@MockitoBean`, `MockMvc` | **Implemented & Passing** | Tests app health and database health endpoint responses. 2 tests pass. |
