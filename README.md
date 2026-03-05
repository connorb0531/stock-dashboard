# Stock Dashboard (Invest Mock)

A simple mock stock trading dashboard built with Spring Boot, Thymeleaf, and WebClient. The goal of this project is to learn backend API integration with Spring.

## Setup

### 1. Clone the repository

```
git clone https://github.com/yourusername/stock-dashboard.git
cd invest-mock
```

### 2. Get a Finnhub API key

Create a free API key at:
https://finnhub.io/

### 3. Set environment variable



```
API_KEY=your_api_key_here
```

### 4. Configure application properties

`src/main/resources/application.properties`

```
api.key=${API_KEY}
api.base.url=https://finnhub.io/api/v1
```

### 5. Run the application

Using Maven wrapper:

```
./mvnw spring-boot:run
```

### 6. Open the dashboard

Navigate to:

```
http://localhost:8080/
```

Search for a stock symbol (example: `AAPL`).

## Notes

- This project currently focuses on learning Spring Boot architecture and API integration.
- Future plans include:
  - portfolio tracking
  - mock buy/sell trades
  - persistent storage
  - improved UI dashboard