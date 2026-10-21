# Lead Types: Public Website Report

## Architecture Verification

| Form | Public API Endpoint | End-to-End | Status |
|---|---|---|---|
| Demo Bookings | `POST /api/v1/demo-requests` | PASS | PASS |
| Schedule Session | `POST /api/v1/session-requests` | PASS | PASS |
| Enquiry (Contact Us) | `POST /api/v1/enquiries` | PASS | PASS |

## Integration Corrections
- `experience-center.vue`: Previously, the `Schedule Your Session` form was incorrectly smashing form properties into a single concatenated `reqs` string and POSTing to `demo-requests`.
- **Correction Made**: Re-wrote the `payload` schema inside the form submission handler to map explicitly to the backend `SessionRequestDto`, and rerouted the POST request natively to `/v1/session-requests`.
- `contact.vue`: Verified to correctly post independently to `/v1/enquiries`.

## Button & CTA Mapping
- All forms are independently executing to their specialized API routes, confirming that cross-contamination between generic contact questions and localized experience center appointments cannot happen.
