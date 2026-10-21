# API Summary Report

## Overview
This report documents the current REST APIs available in the KavoraX backend (`kavorax-api`) and their integration status with the admin frontend.

## Environment Configuration
- **Backend Base URL**: `http://localhost:8080`
- **Frontend Configuration**: `NUXT_PUBLIC_API_BASE=http://localhost:8080` (Needs to be set in frontend `.env`)

## API Endpoint Table

| ID | Module | Method | Endpoint | Purpose | Authentication | Request Fields | Success Response | Error Codes | Frontend Consumer | Status |
|---|---|---|---|---|---|---|---|---|---|---|
| H01 | System | GET | `/api/health` | App health | None | None | `{status: "UP"}` | None | Unconnected | Implemented and tested |
| H02 | System | GET | `/api/health/database` | DB health | None | None | `{status: "UP"}` | 503 | Unconnected | Implemented and tested |
| P01 | Public | POST | `/api/v1/enquiries` | Submit enquiry | None | name, email, message, phone, subject | 201 Created | 400 | Landing Page | Implemented, not tested |
| P02 | Public | POST | `/api/v1/demo-requests` | Submit demo request | None | name, email, company, reqs | 201 Created | 400 | Landing Page | Implemented, not tested |
| A01 | Auth | POST | `/api/v1/auth/login` | Admin login | None | email, password | 200 OK w/ JWT | 401 | Admin Login (`/admin/login`) | Planned, not implemented |
| A02 | Auth | GET | `/api/v1/auth/me` | Current user | JWT | None | 200 OK | 401, 403 | Admin Guard | Planned, not implemented |
| D01 | Admin | GET | `/api/v1/admin/dashboard/stats`| Stats | JWT | None | 200 OK | 401, 403 | Admin Dashboard | Planned, not implemented |
| L01 | Admin | GET | `/api/v1/admin/leads` | List leads | JWT | None | 200 OK | 401, 403 | Admin Leads | Planned, not implemented |

## Local Testing Instructions
The backend APIs can be tested using Swagger UI (if configured) or Postman.
Ensure PostgreSQL is running and `SPRING_DATASOURCE_PASSWORD` is configured in your terminal before running `./mvnw spring-boot:run`.

## Known Limitations
- The admin endpoints for content management (Products, Industries, Careers, etc.) have not been implemented yet.
- The `kavorax-admin` frontend workspace is not fully accessible to the IDE agent in this session (outside workspace root), requiring manual or command-line integration for the API client modifications.
