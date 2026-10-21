# KavoraX Backend — REST API Reference & Contract Specification

**Base URL (Local):** `http://localhost:8080`  
**API Version Prefix:** `/api/v1` (except Health at `/api/health`)  
**Specification Source:** Extracted directly from Controller mappings, DTO classes, and Security filter code.  
**Audit Date:** 2026-10-17  

---

## 1. Summary of Implemented Endpoints

| # | HTTP Method | Route | Controller Method | Access / Auth | Request Body | Response Payload | Verification Status |
|---|---|---|---|---|---|---|---|
| 1 | `GET` | `/api/health` | `HealthController.health()` | Public | None | `{"status": "UP", "service": "kavorax-api"}` | **Automated Test Verified** |
| 2 | `GET` | `/api/health/database` | `HealthController.databaseHealth()` | Public | None | `{"status": "UP", "database": "connected"}` or `503` | **Automated Test Verified** |
| 3 | `POST` | `/api/v1/auth/login` | `AuthController.login()` | Public | `LoginRequest` | `LoginResponse` (`token`, `user`) | **Automated Test Verified** |
| 4 | `POST` | `/api/v1/contact-messages` | `ContactMessageController.submitContactMessage()` | Public | `ContactMessageRequest` | `ContactMessage` (raw entity) | **Automated Test Verified** |
| 5 | `POST` | `/api/v1/demo-requests` | `DemoRequestController.submitDemoRequest()` | Public | `DemoRequestDto` | `DemoRequest` (raw entity) | **Automated Test Verified** |
| 6 | `GET` | `/api/v1/admin/contact-messages` | `AdminContactMessageController.getAllContactMessages()` | Admin Bearer Token | None | `List<AdminContactMessageResponse>` | **Source Code Inspected** (No tests) |
| 7 | `GET` | `/api/v1/admin/dashboard/stats` | `AdminDashboardController.getDashboardStats()` | Admin Bearer Token | None | `DashboardStatsDto` | **Source Code Inspected** (No tests) |
| 8 | `GET` | `/api/v1/admin/demo-leads` | `AdminDemoRequestController.getAllDemoRequests()` | Admin Bearer Token | None | `List<AdminDemoRequestResponse>` | **Source Code Inspected** (No tests) |

---

## 2. Authentication & Authorization Specification

### 2.1 Mechanism
- Admin endpoints (any path starting with `/api/v1/admin`) are intercepted by `AdminAuthFilter`.
- Required Header: `Authorization: Bearer <token>`
- **Current Implementation Limitation:** The filter checks for a static, hardcoded dummy token:
  `eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.dummy_token_payload.signature`
- Tokens do not expire, do not have cryptographic verification, and are not tied to active database sessions.
- Pre-flight `OPTIONS` requests are passed through without token verification for CORS compatibility.

### 2.2 Error Format on Auth Failure
- When missing or malformed header:
  - HTTP Status: `401 Unauthorized`
  - Response Body: `{"error": "Unauthorized - Missing or invalid token"}`
  - Note: Response is sent without `Content-Type: application/json` header (defaults to `text/plain` or container default).
- When token does not match dummy string:
  - HTTP Status: `401 Unauthorized`
  - Response Body: `{"error": "Unauthorized - Invalid token"}`

---

## 3. Detailed Endpoint Contracts

### 3.1 Health Check — Application Liveness
- **Method / Path:** `GET /api/health`
- **Controller:** `com.kavorax.api.controller.HealthController:L21-24`
- **Authentication:** None (Public)
- **Headers:** None required
- **Query Parameters:** None
- **Success Response (HTTP 200 OK):**
  ```json
  {
    "status": "UP",
    "service": "kavorax-api"
  }
  ```
- **Verification Status:** Automated Test Verified (`HealthControllerTest.testHealth()`).

---

### 3.2 Health Check — Database Connectivity
- **Method / Path:** `GET /api/health/database`
- **Controller:** `com.kavorax.api.controller.HealthController:L26-35`
- **Authentication:** None (Public)
- **Mechanism:** Executes `SELECT 1` via Spring `JdbcTemplate`.
- **Success Response (HTTP 200 OK):**
  ```json
  {
    "status": "UP",
    "database": "connected"
  }
  ```
- **Failure Response (HTTP 503 Service Unavailable):**
  ```json
  {
    "status": "DOWN",
    "database": "disconnected"
  }
  ```
- **Verification Status:** Automated Test Verified (`HealthControllerTest.testDatabaseHealthSuccess()`).

---

### 3.3 Admin Authentication — Login
- **Method / Path:** `POST /api/v1/auth/login`
- **Controller:** `com.kavorax.api.controller.AuthController:L28-43`
- **Authentication:** None (Public)
- **Request Headers:**
  - `Content-Type: application/json`
- **Request Body Schema (`LoginRequest`):**

| Field | Type | Required | Constraints | Description |
|---|---|---|---|---|
| `email` | String | Yes | `@NotBlank`, `@Email` | Admin email address |
| `password` | String | Yes | `@NotBlank` | Plaintext password to verify against BCrypt hash |

- **Sample Request (Tested):**
  ```json
  {
    "email": "admin@kavorax.com",
    "password": "SecureAuth_2025!"
  }
  ```
- **Success Response (HTTP 200 OK - Tested):**
  ```json
  {
    "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.dummy_token_payload.signature",
    "user": {
      "id": 1,
      "email": "admin@kavorax.com",
      "role": "ADMIN"
    }
  }
  ```
- **Failure Response — Invalid Credentials (HTTP 401 Unauthorized - Tested):**
  ```json
  {
    "error": "Invalid email or password"
  }
  ```
- **Failure Response — Validation Error (HTTP 400 Bad Request - Tested):**
  ```json
  {
    "timestamp": "2026-10-17T14:15:00.000",
    "status": 400,
    "error": "Bad Request",
    "message": "Validation failed",
    "validationErrors": {
      "email": "Email must be valid",
      "password": "Password is required"
    }
  }
  ```
- **Verification Status:** Automated Test Verified (`AuthControllerTest` — 3 test scenarios).

---

### 3.4 Public — Submit Contact Message
- **Method / Path:** `POST /api/v1/contact-messages`
- **Controller:** `com.kavorax.api.controller.ContactMessageController:L21-25`
- **Authentication:** None (Public)
- **Request Headers:**
  - `Content-Type: application/json`
- **Request Body Schema (`ContactMessageRequest`):**

| Field | Type | Required | Constraints | Description |
|---|---|---|---|---|
| `name` | String | Yes | `@NotBlank`, `@Size(max = 100)` | Sender full name |
| `email` | String | Yes | `@NotBlank`, `@Email`, `@Size(max = 255)` | Sender email address |
| `phone` | String | No | `@Size(max = 20)` | Optional contact phone |
| `subject` | String | No | `@Size(max = 255)` | Optional enquiry subject |
| `message` | String | Yes | `@NotBlank`, `@Size(max = 2000)` | Message body |

- **Sample Request (Tested):**
  ```json
  {
    "name": "Bob Brown",
    "email": "bob@example.com",
    "phone": "+1-555-0199",
    "subject": "Enterprise Pricing Inquiry",
    "message": "Could you please provide enterprise pricing details for multiple outlets?"
  }
  ```
- **Success Response (HTTP 201 Created - Tested):**
  *Note: Controller returns raw `ContactMessage` entity directly.*
  ```json
  {
    "id": 1,
    "name": "Bob Brown",
    "email": "bob@example.com",
    "phone": "+1-555-0199",
    "subject": "Enterprise Pricing Inquiry",
    "message": "Could you please provide enterprise pricing details for multiple outlets?",
    "status": "RECEIVED",
    "createdAt": "2026-10-17T14:15:00.123456"
  }
  ```
- **Failure Response — Validation Error (HTTP 400 Bad Request - Tested):**
  ```json
  {
    "timestamp": "2026-10-17T14:15:00.000",
    "status": 400,
    "error": "Bad Request",
    "message": "Validation failed",
    "validationErrors": {
      "name": "Name is required",
      "email": "Email must be valid",
      "message": "Message is required"
    }
  }
  ```
- **Verification Status:** Automated Test Verified (`ContactMessageControllerTest` — 4 test scenarios).

---

### 3.5 Public — Submit Demo Booking Request
- **Method / Path:** `POST /api/v1/demo-requests`
- **Controller:** `com.kavorax.api.controller.DemoRequestController:L21-25`
- **Authentication:** None (Public)
- **Request Headers:**
  - `Content-Type: application/json`
- **Request Body Schema (`DemoRequestDto`):**

| Field | JSON Key | Type | Required | Constraints | Description |
|---|---|---|---|---|---|
| `name` | `name` | String | Yes | `@NotBlank`, `@Size(max = 100)` | Lead contact name |
| `email` | `email` | String | Yes | `@NotBlank`, `@Email`, `@Size(max = 255)` | Lead contact email |
| `company` | `company` | String | No | `@Size(max = 100)` | Company or organization |
| `requirements` | `reqs` | String | No | Jackson `@JsonProperty("reqs")`, `@Size(max = 1000)` | Business requirements |

*Important Finding:* The request DTO does NOT accept `phone`, `date`, `timeSlot`, `message`, or `type`. Any client submitting those fields will have them silently ignored. When saved, `type` defaults to `"DEMO_BOOKING"` and `status` defaults to `"PENDING"`.

- **Sample Request (Tested):**
  ```json
  {
    "name": "Jane Smith",
    "email": "jane.smith@acme.com",
    "company": "Acme Corporation",
    "reqs": "Need cloud POS integration with 5 terminal licenses."
  }
  ```
- **Success Response (HTTP 201 Created - Tested):**
  *Note: Controller returns raw `DemoRequest` entity directly.*
  ```json
  {
    "id": 1,
    "type": "DEMO_BOOKING",
    "name": "Jane Smith",
    "email": "jane.smith@acme.com",
    "phone": null,
    "company": "Acme Corporation",
    "requirements": "Need cloud POS integration with 5 terminal licenses.",
    "message": null,
    "date": null,
    "timeSlot": null,
    "status": "PENDING",
    "createdAt": "2026-10-17T14:15:00.654321"
  }
  ```
- **Failure Response — Validation Error (HTTP 400 Bad Request - Tested):**
  ```json
  {
    "timestamp": "2026-10-17T14:15:00.000",
    "status": 400,
    "error": "Bad Request",
    "message": "Validation failed",
    "validationErrors": {
      "name": "Name is required",
      "email": "Email must be valid"
    }
  }
  ```
- **Verification Status:** Automated Test Verified (`DemoRequestControllerTest` — 2 test scenarios).

---

### 3.6 Admin — List All Contact Messages
- **Method / Path:** `GET /api/v1/admin/contact-messages`
- **Controller:** `com.kavorax.api.controller.admin.AdminContactMessageController:L25-41`
- **Authentication:** Admin Bearer Token required
- **Request Headers:**
  - `Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.dummy_token_payload.signature`
- **Query Parameters:** None supported (no pagination or filtering)
- **Database Interaction:** `contactMessageRepository.findAll(Sort.by(Direction.DESC, "createdAt"))`
- **Success Response (HTTP 200 OK - Illustrative):**
  ```json
  [
    {
      "id": 12,
      "name": "Bob Brown",
      "email": "bob@example.com",
      "phone": "+1-555-0199",
      "subject": "Enterprise Pricing Inquiry",
      "message": "Could you please provide enterprise pricing details for multiple outlets?",
      "status": "RECEIVED",
      "createdAt": "2026-10-17T14:15:00.123456"
    }
  ]
  ```
- **Failure Response — Missing Token (HTTP 401 Unauthorized):**
  ```json
  {"error": "Unauthorized - Missing or invalid token"}
  ```
- **Verification Status:** Source Code Inspected. No automated unit/mock tests currently cover this controller.

---

### 3.7 Admin — Aggregate Dashboard Statistics
- **Method / Path:** `GET /api/v1/admin/dashboard/stats`
- **Controller:** `com.kavorax.api.controller.admin.AdminDashboardController:L25-36`
- **Authentication:** Admin Bearer Token required
- **Request Headers:**
  - `Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.dummy_token_payload.signature`
- **Query Parameters:** None
- **Database Queries Performed:**
  - `demoRequestRepository.count()` -> `totalDemoLeads`
  - `demoRequestRepository.countByType("DEMO_BOOKING")` -> `totalDemoBookings`
  - `demoRequestRepository.countByType("SESSION_REQUEST")` -> `totalSessionRequests`
  - `demoRequestRepository.countByType("ENQUIRY")` -> `totalEnquiries`
  - `demoRequestRepository.countByStatus("NEW")` -> `newLeads`
  - `demoRequestRepository.countByStatus("PENDING")` -> `pendingLeads`
- **Success Response (HTTP 200 OK - Illustrative):**
  ```json
  {
    "totalDemoLeads": 45,
    "totalDemoBookings": 20,
    "totalSessionRequests": 15,
    "totalEnquiries": 10,
    "newLeads": 5,
    "pendingLeads": 40
  }
  ```
- **Critical Architectural Note:** The `ContactMessageRepository` is injected into this controller but never queried. As a result, records from the `contact_messages` table are completely excluded from dashboard stats. The `totalEnquiries` metric counts rows in `demo_requests` where `type = 'ENQUIRY'` (legacy migrated records).
- **Verification Status:** Source Code Inspected. No automated tests exist.

---

### 3.8 Admin — List All Demo Leads (Unified)
- **Method / Path:** `GET /api/v1/admin/demo-leads`
- **Controller:** `com.kavorax.api.controller.admin.AdminDemoRequestController:L25-45`
- **Authentication:** Admin Bearer Token required
- **Request Headers:**
  - `Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.dummy_token_payload.signature`
- **Query Parameters:** None supported (no pagination or type filtering)
- **Database Interaction:** `demoRequestRepository.findAll(Sort.by(Direction.DESC, "createdAt"))`
- **Success Response (HTTP 200 OK - Illustrative):**
  ```json
  [
    {
      "id": 101,
      "type": "DEMO_BOOKING",
      "name": "Jane Smith",
      "email": "jane.smith@acme.com",
      "phone": null,
      "company": "Acme Corporation",
      "requirements": "Need cloud POS integration with 5 terminal licenses.",
      "message": null,
      "date": null,
      "timeSlot": null,
      "status": "PENDING",
      "createdAt": "2026-10-17T14:15:00.654321"
    },
    {
      "id": 88,
      "type": "SESSION_REQUEST",
      "name": "Alice Johnson",
      "email": "alice@foodchain.com",
      "phone": "+91-9876543210",
      "company": "Food Chain Ltd",
      "requirements": null,
      "message": "Business: Restaurant, Outlets: 4, Modules: Billing, Inventory",
      "date": "2026-10-25",
      "timeSlot": "14:00 - 15:00",
      "status": "PENDING",
      "createdAt": "2026-10-10T11:20:00.000000"
    }
  ]
  ```
- **Verification Status:** Source Code Inspected. No automated tests exist.

---

## 4. Contract Discrepancies, Broken Routes, and Inconsistencies

### 4.1 Broken / Non-Existent Routes Documented in README or Postman
1. **`POST /api/v1/enquiries`:**
   - Documented in: `README.md`, `openapi.yaml`, `ADMIN_PANEL_DATA_API_CONTRACT.md`, `KavoraX_API.postman_collection.json`.
   - Reality: `EnquiryController` was deleted. Calling this endpoint produces **HTTP 404 Not Found**.
   - Remediation: Either reinstate `EnquiryController` as an alias to `ContactMessageController` or update public documentation and frontend API clients to use `POST /api/v1/contact-messages`.

2. **`GET /api/v1/admin/enquiries`:**
   - Documented in: `ADMIN_PANEL_DATA_API_CONTRACT.md`.
   - Reality: Does not exist. Calling this endpoint produces **HTTP 404 Not Found**.

3. **`GET /api/v1/admin/demo-requests`:**
   - Documented in: `ADMIN_PANEL_DATA_API_CONTRACT.md`.
   - Reality: Does not exist. The actual implemented path in `AdminDemoRequestController` is `/api/v1/admin/demo-leads`.

4. **`GET /api/v1/auth/me` and `GET /api/v1/admin/leads`:**
   - Documented in: `docs/API_SUMMARY.md`.
   - Reality: Neither route exists in any controller.

### 4.2 Error Response Format Inconsistencies

The application produces four incompatible error payload structures depending on which component intercepts the error:

1. **Validation & Controller Errors (`GlobalExceptionHandler`):**
   ```json
   {
     "timestamp": "2026-10-17T14:15:00",
     "status": 400,
     "error": "Bad Request",
     "message": "Validation failed",
     "validationErrors": { "fieldName": "error text" }
   }
   ```
2. **Authentication Credential Failure (`AuthController`):**
   ```json
   {
     "error": "Invalid email or password"
   }
   ```
3. **Filter Token Rejection (`AdminAuthFilter`):**
   ```json
   {"error": "Unauthorized - Missing or invalid token"}
   ```
4. **Database Failure (`HealthController`):**
   ```json
   {
     "status": "DOWN",
     "database": "disconnected"
   }
   ```

*Recommendation:* Standardize all error responses to use `ErrorResponse` with appropriate HTTP status codes and standard JSON envelope.
