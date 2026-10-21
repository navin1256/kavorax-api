# KavoraX API

This is the backend REST API for KavoraX, built with Java 21, Spring Boot, and PostgreSQL.

## Prerequisites

- **Java 21** (Eclipse Temurin recommended)
- **PostgreSQL 18**
- **Maven** (optional if using the included `mvnw` wrapper)

## Environment Configuration

Before running the application, you need to configure your environment variables. 
Copy the provided `.env.example` to `.env` or set these in your system/IDE:

```env
SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/kavorax_db
SPRING_DATASOURCE_USERNAME=kavorax_app
SPRING_DATASOURCE_PASSWORD=your_local_password_here
```

*Note: Never commit passwords to source control. The `.env` file is excluded in `.gitignore`.*

## Database Setup

1. Ensure PostgreSQL is running on `localhost:5432`.
2. Create the database: `CREATE DATABASE kavorax_db;`
3. Create the user: `CREATE USER kavorax_app WITH ENCRYPTED PASSWORD 'your_local_password_here';`
4. Grant privileges: `GRANT ALL PRIVILEGES ON DATABASE kavorax_db TO kavorax_app;`

*(Adjust the schema owner/permissions as needed based on your local setup)*

## Local Startup

You can run the application using the Maven Wrapper:

### Windows:
```cmd
.\mvnw spring-boot:run
```

### macOS/Linux:
```bash
./mvnw spring-boot:run
```

The server will start on `http://localhost:8080`.

## API Endpoints

- **Health Check:** `GET /api/health`
- **Database Health Check:** `GET /api/health/database`
- **Submit Enquiry:** `POST /api/v1/enquiries`
- **Submit Demo Request:** `POST /api/v1/demo-requests`

## Testing

Run the automated tests using:

```cmd
.\mvnw test
```
