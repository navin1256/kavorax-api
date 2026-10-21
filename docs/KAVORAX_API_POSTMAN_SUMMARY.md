# KavoraX API Postman Summary

## Public APIs (Frontend)
| Method | Endpoint | Purpose | Auth | Request Body | Expected Response |
|---|---|---|---|---|---|
| POST | `/api/v1/demo-requests` | Book an online demo | None | `DemoRequestDto` (name, email, etc.) | 201 Created |
| POST | `/api/v1/contact-messages` | Submit contact form | None | `ContactMessageRequest` (subject, message, etc.) | 201 Created |

## Authentication APIs
| Method | Endpoint | Purpose | Auth | Request Body | Expected Response |
|---|---|---|---|---|---|
| POST | `/api/v1/auth/login` | Admin login | None | `LoginRequest` (username, password) | 200 OK + Token/Cookie |

## Admin APIs (Admin Panel)
| Method | Endpoint | Purpose | Auth | Request Body | Expected Response |
|---|---|---|---|---|---|
| GET | `/api/v1/admin/demo-leads` | List all demo leads (Demo Bookings, Sessions, Enquiries) | Required | None | 200 OK + `AdminDemoRequestResponse[]` |
| GET | `/api/v1/admin/contact-messages` | List contact messages | Required | None | 200 OK + `AdminContactMessageResponse[]` |
| GET | `/api/v1/admin/dashboard/stats` | Get dashboard statistics | Required | None | 200 OK + `DashboardStatsDto` |

## Content APIs
*None requested at this phase. Current frontend content is static/hardcoded for visual representation.*
