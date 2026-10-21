# Lead Types: Admin Panel Report

## Architecture Verification

| Lead Type | Admin API Endpoint | Admin Section | End-to-End | Status |
|---|---|---|---|---|
| Demo Booking | `GET /api/v1/admin/demo-requests` | Leads → Demo Bookings | PASS | PASS |
| Schedule Session | `GET /api/v1/admin/session-requests` | Leads → Schedule Your Session | PASS | PASS |
| Enquiry | `GET /api/v1/admin/enquiries` | Leads → Enquiries | PASS | PASS |

## Admin Navigation
- The generic "Leads & Demos" sidebar item has been permanently split into three explicit pages.
- Demo records, Session Requests, and Enquiries each load exclusively into their respective `demo-bookings.vue`, `session-requests.vue`, and `enquiries.vue` files.

## Dashboard Statistics
- **Total Leads**: Automatically tallied accurately.
- **Demo Bookings**: Exact count from `demoRequests.length`.
- **Schedule Sessions**: Exact count from `sessionRequests.length`.
- **Enquiries**: Exact count from `enquiries.length`.
- **Empty States**: All dashboards gracefully display empty string fallbacks ("No data available yet" or "No enquiries yet") in the total absence of real PostgreSQL rows. Fake leads were removed entirely.
