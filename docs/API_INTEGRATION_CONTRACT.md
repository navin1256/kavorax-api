# KavoraX Backend — API Integration Contract

**Document Version:** 2.0.0  
**Specification Date:** 2026-10-17  
**Base URL:** `http://localhost:8080/api`  
**API Prefix:** `/v1` (System health at `/health`)  
**Security Standard:** Stateless HTTP Bearer Authentication (Cryptographically Signed JWT HS256)

---

## 1. Authentication Architecture & Protocol

### 1.1 Token Standard
- Tokens are JSON Web Tokens (JWT) signed using **HMAC-SHA256 (HS256)** with a 256-bit secret key.
- Token claims include:
  - `sub`: Admin email address
  - `userId`: Numeric identifier of the administrator
  - `role`: Role string (e.g., `ADMIN`)
  - `iat`: Unix issuance timestamp
  - `exp`: Unix expiration timestamp (default: 24 hours / 86,400,000 ms)

### 1.2 Authorization Header
To access protected admin endpoints (`/api/v1/admin/**`), clients must supply the token in the HTTP Authorization header:
```http
Authorization: Bearer <jwt_token>
```

### 1.3 CORS Policy
- Allowed origins configurable via `CORS_ALLOWED_ORIGINS` (defaults: `http://localhost:3000,http://localhost:3001`).
- Permitted HTTP methods: `GET, POST, PUT, DELETE, OPTIONS`.
- Headers: All (`*`).
- Credentials: `true`.
- Pre-flight `OPTIONS` requests pass through automatically without requiring Bearer authentication.

---

## 2. API Endpoints Catalog

### 2.1 System & Health Endpoints

#### `GET /api/health`
- **Access:** Public (No authentication required)
- **Description:** Verifies application liveness and status.
- **Response (200 OK):**
  ```json
  {
    "status": "UP",
    "service": "kavorax-api"
  }
  ```

#### `GET /api/health/database`
- **Access:** Public
- **Description:** Verifies PostgreSQL connectivity using `SELECT 1`.
- **Response (200 OK):**
  ```json
  {
    "status": "UP",
    "database": "connected"
  }
  ```
- **Error Response (503 Service Unavailable):**
  ```json
  {
    "status": "DOWN",
    "database": "disconnected"
  }
  ```

---

### 2.2 Authentication Endpoints

#### `POST /api/v1/auth/login`
- **Access:** Public
- **Description:** Verifies administrator credentials, enforces brute-force rate-limiting, and issues a signed JWT.
- **Request Body (`LoginRequest`):**
  ```json
  {
    "email": "admin@kavorax.com",
    "password": "SecureAuth_2025!"
  }
  ```
  - `email` (string, required): Valid email address.
  - `password` (string, required): Admin password.
- **Success Response (200 OK):**
  ```json
  {
    "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbkBrYXZvcmF4LmNvbSIsInVzZXJJZCI6MSwicm9sZSI6IkFETUlOIiwiZXhwIjoxNjk3NjQ4MDAwfQ.signature",
    "user": {
      "id": 1,
      "email": "admin@kavorax.com",
      "role": "ADMIN"
    }
  }
  ```
- **Error Responses:**
  - `401 Unauthorized`: Invalid email or password.
    ```json
    {
      "timestamp": "2026-10-17T15:30:00.000",
      "status": 401,
      "error": "Unauthorized",
      "message": "Invalid email or password"
    }
    ```
  - `429 Too Many Requests`: Account temporarily locked due to excessive failed attempts (5 consecutive failures = 15-minute lockout).
    ```json
    {
      "timestamp": "2026-10-17T15:30:00.000",
      "status": 429,
      "error": "Too Many Requests",
      "message": "Account temporarily locked due to excessive failed login attempts. Please try again later."
    }
    ```

---

### 2.3 Public Intake Endpoints

#### `POST /api/v1/contact-messages` & `POST /api/v1/enquiries`
- **Access:** Public (Dual routing provides 100% backward compatibility for `/enquiries`)
- **Description:** Receives public general inquiries and contact submissions. Persists to `contact_messages`.
- **Request Body (`ContactMessageRequest`):**
  ```json
  {
    "name": "Jane Doe",
    "email": "jane.doe@example.com",
    "phone": "+1-555-0199",
    "subject": "Enterprise Solutions Inquiry",
    "message": "We would like to discuss deploying KavoraX across 10 retail locations."
  }
  ```
  - `name` (string, required, max 100): Sender name.
  - `email` (string, required, max 255): Valid email.
  - `phone` (string, optional, max 20): Contact phone.
  - `subject` (string, optional, max 255): Inquiry subject.
  - `message` (string, required, max 2000): Message content.
- **Response (201 Created):**
  ```json
  {
    "id": 1,
    "name": "Jane Doe",
    "email": "jane.doe@example.com",
    "phone": "+1-555-0199",
    "subject": "Enterprise Solutions Inquiry",
    "message": "We would like to discuss deploying KavoraX across 10 retail locations.",
    "status": "RECEIVED",
    "createdAt": "2026-10-17T15:30:00.123456"
  }
  ```

#### `POST /api/v1/demo-requests`
- **Access:** Public
- **Description:** Submits a demo booking request. Persists to `demo_requests` with `type = 'DEMO_BOOKING'` (default).
- **Request Body (`DemoRequestDto`):**
  ```json
  {
    "name": "John Smith",
    "email": "john.smith@acme.com",
    "company": "Acme Corp",
    "reqs": "Need cloud billing and ERP modules",
    "phone": "+1-555-0144",
    "date": "2026-11-05",
    "timeSlot": "02:00 PM - 03:00 PM",
    "message": "Interested in multi-outlet franchise management"
  }
  ```
  - `name` (string, required, max 100)
  - `email` (string, required, max 255)
  - `company` (string, optional, max 100)
  - `reqs` / `requirements` (string, optional, max 1000)
  - `phone` (string, optional, max 30)
  - `date` / `preferredDate` (string, optional, max 50)
  - `timeSlot` (string, optional, max 50)
  - `message` (string, optional, max 2000)
- **Response (201 Created):**
  ```json
  {
    "id": 1,
    "type": "DEMO_BOOKING",
    "name": "John Smith",
    "email": "john.smith@acme.com",
    "company": "Acme Corp",
    "requirements": "Need cloud billing and ERP modules",
    "phone": "+1-555-0144",
    "date": "2026-11-05",
    "timeSlot": "02:00 PM - 03:00 PM",
    "message": "Interested in multi-outlet franchise management",
    "status": "PENDING",
    "createdAt": "2026-10-17T15:30:00.654321"
  }
  ```

#### `POST /api/v1/session-requests`
- **Access:** Public
- **Description:** Dedicated endpoint for scheduling an experience center session. Persists to `demo_requests` with `type = 'SESSION_REQUEST'`.
- **Request Body:** Accepts same schema as `DemoRequestDto`. If `type` is not specified, it defaults automatically to `"SESSION_REQUEST"`.
- **Response (201 Created):** Same structure as demo request with `"type": "SESSION_REQUEST"`.

---

### 2.4 Admin Data Endpoints (Protected by Bearer JWT)

#### `GET /api/v1/admin/dashboard/stats`
- **Access:** Admin Bearer Token
- **Description:** Aggregates real-time metrics across demo bookings, session requests, and contact messages.
- **Response (200 OK):**
  ```json
  {
    "totalDemoLeads": 25,
    "totalDemoBookings": 12,
    "totalSessionRequests": 5,
    "totalEnquiries": 8,
    "newLeads": 3,
    "pendingLeads": 22,
    "totalContactMessages": 8
  }
  ```

#### `GET /api/v1/admin/contact-messages` & `GET /api/v1/admin/enquiries`
- **Access:** Admin Bearer Token (Dual routing ensures admin panel compatibility)
- **Description:** Returns paginated contact inquiries sorted by newest first.
- **Query Parameters:**
  - `page` (integer, optional, default: 0): Page index.
  - `size` (integer, optional, default: 50, max: 100): Page size.
  - `sortBy` (string, optional, default: "createdAt"): Sort column (`createdAt`, `name`, `email`).
  - `direction` (string, optional, default: "desc"): Sort direction (`asc`, `desc`).
- **Response Headers:**
  - `X-Total-Count`: Total number of records matching query.
  - `X-Total-Pages`: Total number of available pages.
  - `X-Current-Page`: Current page index.
  - `X-Page-Size`: Effective page size.
- **Response Body (200 OK):** Returns JSON Array `[ { ... } ]` matching Nuxt frontend `Array.isArray(data)` expectations.
  ```json
  [
    {
      "id": 1,
      "name": "Jane Doe",
      "email": "jane.doe@example.com",
      "phone": "+1-555-0199",
      "subject": "Enterprise Solutions Inquiry",
      "message": "We would like to discuss deploying KavoraX across 10 retail locations.",
      "status": "RECEIVED",
      "createdAt": "2026-10-17T15:30:00.123456"
    }
  ]
  ```

#### `GET /api/v1/admin/demo-leads`, `GET /api/v1/admin/demo-requests`, & `GET /api/v1/admin/session-requests`
- **Access:** Admin Bearer Token
- **Routing Intelligence:**
  - `GET /api/v1/admin/demo-leads`: Returns all leads (or filter by `?type=...`).
  - `GET /api/v1/admin/demo-requests`: Automatically defaults filter to `type=DEMO_BOOKING`.
  - `GET /api/v1/admin/session-requests`: Automatically defaults filter to `type=SESSION_REQUEST`.
- **Query Parameters:**
  - `type` (string, optional): Filter by `DEMO_BOOKING`, `SESSION_REQUEST`, or `ENQUIRY`.
  - `page` (integer, optional, default: 0)
  - `size` (integer, optional, default: 50, max: 100)
  - `sortBy` (string, optional, default: "createdAt")
  - `direction` (string, optional, default: "desc")
- **Response Headers:** `X-Total-Count`, `X-Total-Pages`, `X-Current-Page`, `X-Page-Size`.
- **Response Body (200 OK):**
  ```json
  [
    {
      "id": 1,
      "type": "DEMO_BOOKING",
      "name": "John Smith",
      "email": "john.smith@acme.com",
      "company": "Acme Corp",
      "requirements": "Need cloud billing and ERP modules",
      "phone": "+1-555-0144",
      "date": "2026-11-05",
      "timeSlot": "02:00 PM - 03:00 PM",
      "message": "Interested in multi-outlet franchise management",
      "status": "PENDING",
      "createdAt": "2026-10-17T15:30:00.654321"
    }
  ]
  ```

---

## 3. Standardized Error Handling

All error responses across the backend conform to the standard `ErrorResponse` schema:

```json
{
  "timestamp": "2026-10-17T15:30:00.123456",
  "status": 400,
  "error": "Bad Request",
  "message": "Validation failed",
  "validationErrors": {
    "name": "Name is required",
    "email": "Email must be valid"
  }
}
```

### HTTP Status Code Mapping:
- `400 Bad Request`: Field validation error, malformed JSON, or illegal argument.
- `401 Unauthorized`: Missing, expired, malformed, or invalid Bearer token.
- `403 Forbidden`: Authenticated user lacks `ROLE_ADMIN` authority.
- `404 Not Found`: Requested route or resource does not exist.
- `405 Method Not Allowed`: HTTP method not supported for route.
- `429 Too Many Requests`: Account locked by rate-limiting after repeated failed login attempts.
- `500 Internal Server Error`: Unhandled server exception.
- `503 Service Unavailable`: Database connectivity failure.
