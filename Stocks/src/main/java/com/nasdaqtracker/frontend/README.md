# NASDAQ Frontend (React + Vite)

A minimal landing page that lists stocks from the Spring Boot backend.

## Commands

- Install deps
  npm install

- Start dev server
  npm run dev

The Vite dev server proxies API calls to http://localhost:8080 via `/api`.

## Expected backend endpoint

GET http://localhost:8080/api/stocks -> returns JSON array of:

[
  {
    "symbol": "AAPL",
    "name": "Apple Inc.",
    "currentPrice": 190.12,
    "priceChange": -1.23,
    "priceChangePercent": -0.64
  }
]
