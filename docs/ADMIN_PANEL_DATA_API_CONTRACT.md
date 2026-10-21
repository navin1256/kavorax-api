# Admin Panel Data API Contract

## Overview
This document defines the integrated data flow from the Public Website to the Admin Panel, detailing how real database records (Enquiries and Demo Requests) are persisted and securely retrieved.

## API Contracts

| Feature | Method | Endpoint | Auth | Database | Status |
|---|---|---|---|---|---|
| Enquiries | POST | `/api/v1/enquiries` | Public | `enquiries` | PASS |
| Enquiries | GET | `/api/v1/admin/enquiries` | Admin (Bearer) | `enquiries` | PASS |
| Demo Requests | POST | `/api/v1/demo-requests` | Public | `demo_requests` | PASS |
| Demo Requests | GET | `/api/v1/admin/demo-requests` | Admin (Bearer) | `demo_requests` | PASS |

---

## Examples

### 1. Enquiries

#### 1.1 Public POST Request (Submit Enquiry)
```http
POST /api/v1/enquiries
Content-Type: application/json

{
    "name": "Jane Smith",
    "email": "jane@example.com",
    "message": "I would like to learn more about KavoraX."
}
```

#### 1.2 Admin GET Request (Retrieve Enquiries)
```http
GET /api/v1/admin/enquiries
Authorization: Bearer <token>
```
**Response (200 OK):**
```json
[
  {
    "id": 1,
    "name": "Jane Smith",
    "email": "jane@example.com",
    "phone": null,
    "subject": null,
    "message": "I would like to learn more about KavoraX.",
    "status": "RECEIVED",
    "createdAt": "2026-10-05T12:00:00"
  }
]
```

### 2. Demo Requests

#### 2.1 Public POST Request (Submit Demo Request)
```http
POST /api/v1/demo-requests
Content-Type: application/json

{
    "name": "Acme Corp",
    "email": "admin@acme.com",
    "requirements": "Looking for enterprise ERP solution."
}
```

#### 2.2 Admin GET Request (Retrieve Demo Requests)
```http
GET /api/v1/admin/demo-requests
Authorization: Bearer <token>
```
**Response (200 OK):**
```json
[
  {
    "id": 1,
    "name": "Acme Corp",
    "email": "admin@acme.com",
    "company": null,
    "requirements": "Looking for enterprise ERP solution.",
    "status": "PENDING",
    "createdAt": "2026-10-05T12:00:00"
  }
]
```
