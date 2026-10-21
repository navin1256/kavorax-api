# KavoraX Public Website Postman Tests

| # | Method | Endpoint | Purpose | Body | Expected Status | Actual Status |
|---|---|---|---|---|---|---|
| 1 | GET | `/api/health` | System health check | None | 200 OK | 200 OK |
| 2 | GET | `/api/health/database` | Database connection check | None | 200 OK | 200 OK |
| 3 | POST | `/api/v1/enquiries` | Submit general enquiry | `{"name":"John Enquiry","email":"john.enquiry@example.com","phone":"+1234567890","subject":"General Inquiry","message":"I would like to learn more."}` | 201 Created | 201 Created |
| 4 | POST | `/api/v1/demo-requests` | Submit demo request | `{"name":"Jane Demo","email":"jane.demo@company.com","company":"Acme Corp","reqs":"Looking for SSO."}` | 201 Created | 201 Created |
| 5 | POST | `/api/v1/auth/login` | Admin login | `{"email":"admin@kavorax.com","password":"***"}` | 200 OK | 200 OK |
