# Leads and Session API Report

## Overview
This document verifies the end-to-end integration of the backend data flow from the KavoraX Public Website to PostgreSQL, and out to the Admin API. All endpoints have been implemented natively to accept their precise fields and persist genuine database records. 

*Note: The frontend currently incorrectly multiplexes "Schedule Your Session" payloads into `POST /api/v1/demo-requests` by concatenating fields. We have fully implemented the correct dedicated `POST /api/v1/session-requests` backend API to match the specific form schema, as instructed, ensuring the backend is ready for the frontend to switch over.*

## API Status & Verification

| Feature | Public POST API | Admin GET API | PostgreSQL | Status |
|---|---|---|---|---|
| Enquiry | `/api/v1/enquiries` | `/api/v1/admin/enquiries` | Yes | PASS |
| Demo Request | `/api/v1/demo-requests` | `/api/v1/admin/demo-requests` | Yes | PASS |
| Schedule Session | `/api/v1/session-requests` | `/api/v1/admin/session-requests` | Yes | PASS |

---

## 1. Enquiry Flow (PASS)

**POST Request Example:**
```http
POST /api/v1/enquiries
Content-Type: application/json

{
  "name": "John Doe",
  "email": "john.doe@example.com",
  "phone": "+1234567890",
  "subject": "General Inquiry",
  "message": "I would like to learn more about your services."
}
```

**Admin GET Response Example:**
```json
[
  {
    "id": 1,
    "name": "John Doe",
    "email": "john.doe@example.com",
    "phone": "+1234567890",
    "subject": "General Inquiry",
    "message": "I would like to learn more about your services.",
    "status": "RECEIVED",
    "createdAt": "2026-10-05T12:00:00"
  }
]
```

---

## 2. Demo Request Flow (PASS)

**POST Request Example:**
```http
POST /api/v1/demo-requests
Content-Type: application/json

{
  "name": "Jane Smith",
  "email": "jane.smith@company.com",
  "company": "Acme Corp",
  "requirements": "Looking for enterprise features and single sign-on support."
}
```

**Admin GET Response Example:**
```json
[
  {
    "id": 1,
    "name": "Jane Smith",
    "email": "jane.smith@company.com",
    "company": "Acme Corp",
    "requirements": "Looking for enterprise features and single sign-on support.",
    "status": "PENDING",
    "createdAt": "2026-10-05T12:00:00"
  }
]
```

---

## 3. Schedule Session Flow (PASS)

**POST Request Example:**
```http
POST /api/v1/session-requests
Content-Type: application/json

{
  "name": "Jane Doe",
  "email": "jane@example.com",
  "company": "Jane Cafe",
  "phone": "555-1234",
  "businessType": "Cafes",
  "outlets": "1 outlet",
  "selectedModules": ["Inventory", "CRM"],
  "date": "2026-10-10",
  "timeSlot": "09:00 AM - 10:00 AM"
}
```

**Admin GET Response Example:**
```json
[
  {
    "id": 1,
    "name": "Jane Doe",
    "email": "jane@example.com",
    "phone": "555-1234",
    "company": "Jane Cafe",
    "businessType": "Cafes",
    "outlets": "1 outlet",
    "selectedModules": "Inventory, CRM",
    "date": "2026-10-10",
    "timeSlot": "09:00 AM - 10:00 AM",
    "status": "PENDING",
    "createdAt": "2026-10-05T15:05:26"
  }
]
```

---

## Summary of Validations
- **Valid Requests:** Evaluated to `201 Created` with successful persistence into respective `enquiries`, `demo_requests`, and `session_requests` tables.
- **Invalid Requests:** Missing required fields (e.g. `email`) gracefully trigger `400 Bad Request` via `@Valid` annotations and do NOT commit fake or incomplete rows to PostgreSQL.
- **Admin Endpoints:** Successfully retrieved by `GET` using authorized Bearer dummy tokens, returning exact PostgreSQL rows marshalled strictly into DTOs (Data Transfer Objects), fully masking arbitrary internal data. Empty tables correctly return HTTP 200 with an empty collection `[]`.
- **CORS/Preflight:** Both `http://localhost:3000` and `http://localhost:3001` are allowed. Preflight `OPTIONS` calls correctly bypass strict auth-enforcement logic, eliminating browser blockages.
