# Lead Types: Backend Report

## Architecture Verification

| Lead Type | Public API | DB Table | Admin API | Status |
|---|---|---|---|---|
| Demo Booking | `POST /api/v1/demo-requests` | `demo_requests` | `GET /api/v1/admin/demo-requests` | PASS |
| Schedule Session | `POST /api/v1/session-requests` | `session_requests` | `GET /api/v1/admin/session-requests` | PASS |
| Enquiry | `POST /api/v1/enquiries` | `enquiries` | `GET /api/v1/admin/enquiries` | PASS |

## Separation Enforcement
- **Entities & Repositories**: Completely decoupled. `DemoRequest.java`, `SessionRequest.java`, and `Enquiry.java`.
- **Database Tables**: The existing database uses isolated tables for each type (`demo_requests`, `session_requests`, `enquiries`).
- **Data Leakage**: There is zero internal overlapping of data. 

## Security & Constraints
- **CORS**: Correctly configured to explicitly allow `http://localhost:3000` and `http://localhost:3001` only.
- **Admin Validation**: `GET` routes are strictly protected by `AdminAuthFilter`.
- **Validation**: Incoming POSTs utilize `@Valid` constraints blocking mock insertions on errors.
