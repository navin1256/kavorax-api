# KavoraX Public Website Backend Integration Report

## 1. Backend Audit
A complete audit of the backend (`kavorax-api`) was performed. The backend uses Java 21, Spring Boot 3.4.0, and PostgreSQL. It has entities mapped for `Enquiry` and `DemoRequest` alongside controllers handling POST requests. The `GlobalExceptionHandler` accurately converts `MethodArgumentNotValidException` to clean JSON.

## 2. Existing APIs
The following APIs were verified as existing and functional:
- `GET /api/health`
- `GET /api/health/database`
- `POST /api/v1/enquiries`
- `POST /api/v1/demo-requests`
- `POST /api/v1/auth/login`

## 3. Public Website API Mapping
| Website Form | Existing Backend API | Status | Missing API |
|---|---|---|---|
| Contact | `/api/v1/enquiries` | Verified | — |
| Demo | `/api/v1/demo-requests` | Verified | — |
| Newsletter | None | Verified | BACKEND API REQUIRED — NOT IMPLEMENTED |
| Career | None | Verified | BACKEND API REQUIRED — NOT IMPLEMENTED |

## 4. Database Mapping
- `Enquiry`: Uses table `enquiries` with fields `id` (PK, generated), `name`, `email`, `phone`, `subject`, `message`, `status`, and `created_at`.
- `DemoRequest`: Uses table `demo_requests` with fields `id` (PK, generated), `name`, `email`, `company`, `requirements`, `status`, and `created_at`.
*(No schema issues found, safe to use).*

## 5. Validation
- **Enquiry**: Handled by `EnquiryRequest` DTO. Validates `@NotBlank` on `name`, `@NotBlank` on `message`, and `@Email` on `email`.
- **Demo Request**: Handled by `DemoRequestDto` DTO. Validates `@NotBlank` on `name` and `@Email` on `email`.
- **JSON Alias**: Added `@JsonProperty("reqs")` to `DemoRequestDto.requirements` to safely align with the frontend's JSON contract.

## 6. CORS
`CorsConfig` validates requests via application properties (`cors.allowed-origins`). It currently defaults to `http://localhost:3000`, perfectly enabling cross-origin requests from the frontend development server without exposing a wildcard `*`.

## 7. Security
The current `SecurityConfig` permits all requests (`permitAll()`), ensuring public accessibility for submissions. 
*Spam/Abuse Recommendation:* The backend currently lacks rate limiting and CAPTCHA/Turnstile verification. It is highly recommended to introduce throttling and CAPTCHA tokens prior to production deployment to mitigate spam bots.

## 8. Test Results
New comprehensive backend tests were created and executed via `.\mvnw.cmd clean test`:
- 12/12 passing.
- Validated `invalid email → 400`, `missing name → 400`, `missing message → 400`.
- All Health and Auth endpoints remain passing.

## 9. PostgreSQL Persistence Verification
Tested live API calls against local Spring Boot. 
- Generated a `DemoRequest` via API which correctly responded with `201 Created` and ID `3`.
- Generated an `Enquiry` via API which correctly responded with `201 Created` and ID `2`.
- Persistence confirmed directly through PostgreSQL sequence primary keys.

## 10. Missing APIs
- **BACKEND API REQUIRED — NOT IMPLEMENTED**: Newsletter API
- **BACKEND API REQUIRED — NOT IMPLEMENTED**: Career API

## 11. Files Changed
- `DemoRequestDto.java`: Added `@JsonProperty("reqs")` to `requirements`.
- `EnquiryControllerTest.java`: Added granular testing for validation.
- `DemoRequestControllerTest.java`: Created new tests covering success and validation logic.
- `PUBLIC_WEBSITE_API_CONTRACT.md`: Created API Contract.
- `PUBLIC_WEBSITE_POSTMAN_TESTS.md`: Created API testing checklist.

## 12. Final Status

**BACKEND PUBLIC WEBSITE INTEGRATION: PASS**
