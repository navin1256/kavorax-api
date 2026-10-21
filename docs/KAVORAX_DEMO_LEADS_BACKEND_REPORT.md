# KavoraX Demo Leads Backend Implementation Report

## 1. Existing Architecture & Modifications
Previously, the backend used separate modules (`DemoRequest`, `SessionRequest`, and `Enquiry`) to handle three distinct types of leads. Additionally, we recently introduced `ContactMessage`.
Based on the objective, `SessionRequest` and `Enquiry` functionality has been unified under a single architecture: **Demo Leads**. The new model adds a `type` property to `DemoRequest` to securely categorize each lead while keeping backend maintenance low.

## 2. New Demo Leads Architecture
- The `DemoRequest` entity remains the unified parent, and the database table (`demo_requests`) is untouched structurally except for appended columns.
- The `DemoRequest` entity now contains new fields: `type` (defaulting to `DEMO_BOOKING`), `phone`, `message`, `date`, and `timeSlot`.
- A single unified Admin API was created at `GET /api/v1/admin/demo-leads`, mapped to `AdminDemoRequestController`. It returns an updated `AdminDemoRequestResponse` containing all unified fields.

## 3. Lead Type Implementation
The field `type` categorizes each record without using multiple schemas. Valid values are:
- `DEMO_BOOKING`
- `SESSION_REQUEST`
- `ENQUIRY`

## 4. Session Request & Enquiry Migration
- Data from the deprecated `session_requests` table is safely migrated using `DataSeeder.java` to `demo_requests` by setting `type = 'SESSION_REQUEST'` and concatenating custom session details into the `message` field to preserve all info.
- Data from the deprecated `enquiries` table is safely migrated by inserting records into `demo_requests` with `type = 'ENQUIRY'`.
- This ensures 100% preservation of records and relies cleanly on the existing `kavorax_db` using Postgres.

## 5. Demo Booking Preservation
- The public `POST /api/v1/demo-requests` functionality remains entirely intact. 
- It uses the standard `DemoRequestDto`, so backward compatibility with the frontend is fully preserved.

## 6. Contact Data Separation
- The `ContactMessage` system (`contact_messages` table and API) is completely separate and unmodified. It does not mix with Demo Leads. 
- Contact stats on the dashboard are distinct.

## 7. Database Changes
- Table modifications executed securely through JPA `ddl-auto=update` and JDBC `INSERT INTO ... SELECT` migrations. 
- New columns: `type`, `phone`, `message`, `preferred_date`, `time_slot`.
- Legacy tables `session_requests` and `enquiries` are dropped only AFTER safe data migration is executed successfully in `DataSeeder`.

## 8. Dashboard Changes
- The `AdminDashboardController` has been updated to query the specific counts of `DEMO_BOOKING`, `SESSION_REQUEST`, and `ENQUIRY` using `DemoRequestRepository.countByType(String)`.
- Replaced the hardcoded legacy references and DTO to properly output unified metrics: `totalDemoLeads`, `totalDemoBookings`, `totalSessionRequests`, `totalEnquiries`, `newLeads`, and `pendingLeads`.

## 9. Tests
- All API and integration tests have successfully executed with `mvn test`, confirming that `POST /api/v1/demo-requests` saves correctly and the unified GET endpoint populates properly.
- No compilation errors. Admin Authentication remains 100% operative.

## 10. Summary of Files Handled
- **Modified:**
  - `DemoRequest.java` (Added type, phone, message, date, timeSlot)
  - `AdminDemoRequestResponse.java`
  - `AdminDemoRequestController.java` (Refactored mapping to `/api/v1/admin/demo-leads`)
  - `DashboardStatsDto.java`
  - `AdminDashboardController.java`
  - `DemoRequestRepository.java` (Added `countByType`)
  - `DataSeeder.java` (Integrated migration logic)
  - `docs/KAVORAX_API_POSTMAN_SUMMARY.md`
- **Removed:**
  - `SessionRequest`, `Enquiry` (were already purged in preceding steps)
  - Legacy `SessionRequestController`, `AdminEnquiryController` (purged)

## 11. Remaining Issues
- None. The system operates autonomously with a clean unified Demo Leads pool while continuing to track pure `Contact Messages` safely apart.
