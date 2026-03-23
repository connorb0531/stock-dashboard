# Stock Dashboard (Invest Mock)

A simple mock stock trading dashboard built with Spring Boot, Thymeleaf, and WebClient. The goal of this project is to learn backend API integration with Spring.

## Setup

#### **Requires JDK 21+**

### 1. Clone the repository

```
git clone https://github.com/yourusername/stock-dashboard.git
cd invest-mock
```

### 2. Get a Finnhub API key

Create a free API key at:
https://finnhub.io/

### 3. Set environment variables

```
API_KEY
ADMIN_USERNAME
ADMIN_PASSWORD
JWT_KEY=your-secret-key-at-least-32-chars-long
```

### 4. Run the application

Using Maven wrapper:

```
./mvnw spring-boot:run
```

### 5. Open the dashboard

Navigate to:

```
http://localhost:8080/
```
### 6. Auth flow
- /auth/signup: create an account with username and password
- /auth/login: enter credentials. On success, a JWT is stored in an HttpOnly cookie
- Admin users: The user created with ADMIN_USERNAME and ADMIN_PASSWORD has admin rights and can access /users to manage accounts.


## Notes

- This project currently focuses on learning Spring Boot architecture and API integration.
- Future plans include:
  - portfolio tracking
  - mock buy/sell trades
  - improved UI dashboard