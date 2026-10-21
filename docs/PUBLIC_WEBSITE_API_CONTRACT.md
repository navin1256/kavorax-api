# KavoraX Public Website API Contract

## Base URL
```text
http://localhost:8080/api
```

## Enquiries

### POST
```text
/v1/enquiries
```

**Request:**
```json
{
  "name": "John Doe",
  "email": "john.doe@example.com",
  "phone": "+1234567890",
  "subject": "General Inquiry",
  "message": "I would like to learn more about your services."
}
```

**Response (201 Created):**
```json
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
```

**Validation:**
- `name`: Required
- `email`: Required, must be a valid email format
- `message`: Required
- Returns HTTP `400 Bad Request` with `validationErrors` object on failure.

---

## Demo Requests

### POST
```text
/v1/demo-requests
```

**Request:**
```json
{
  "name": "Jane Smith",
  "email": "jane.smith@company.com",
  "company": "Acme Corp",
  "reqs": "Looking for enterprise features and single sign-on support."
}
```

**Response (201 Created):**
```json
{
  "id": 1,
  "name": "Jane Smith",
  "email": "jane.smith@company.com",
  "company": "Acme Corp",
  "requirements": "Looking for enterprise features and single sign-on support.",
  "status": "PENDING",
  "createdAt": "2026-10-05T12:00:00"
}
```

**Validation:**
- `name`: Required
- `email`: Required, must be a valid email format
- Returns HTTP `400 Bad Request` with `validationErrors` object on failure.

---

## CORS
Allowed development origin:
```text
http://localhost:3000
```
*(Configured via `cors.allowed-origins` property in environment/properties)*

---

## Missing APIs
**BACKEND API REQUIRED — NOT IMPLEMENTED:**
- **Newsletter**: No endpoint currently exists for newsletter subscription.
- **Career**: No endpoint currently exists for career applications.
- **Other public forms**: None discovered.
