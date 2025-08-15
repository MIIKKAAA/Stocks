# NASDAQ Top 20 Stock Tracker - PRD

**Version:** MVP v1.0   
**Author:** Miikka Vartiainen
**Date:** 14.8.2025  

---

## 1. Purpose
The NASDAQ Top 20 Stock Tracker is a web-based application that displays real-time and historical stock data for the top 20 companies listed on NASDAQ. The MVP features are: 

1. Display top 20 companies and their daily price change.  
2. Filter companies by name.  
3. Show historical price for selected company within 30 days.

---

## 2. Goals & Success Criteria
- **Primary Goal:** Provide a simple and responsive website to display NASDAQ stock data.
- **Success Criteria:**  
  - Users can view all top 20 NASDAQ companies with current prices and daily changes.  
  - Users can search companies by their name.
  - Users can view a 30-day price trend chart for a selected company.  

---

## 3. Scope

### In Scope (MVP)
- **Data Retrieval:** Use Alpha Vantage API to fetch:  
  - Current stock price  
  - Price change compared to previous day
  - Last 30 days closing prices (this might not be possible in free tier, use mock data in that case)
- **UI Features:**  
  - Table that shows 20 companies and their daily price change 
  - Search bar for filtering
  - Clickable company row to open a 30-day price chart  
- **Charts:** Line chart for 30-day historical data  
- **Basic Styling:** Responsive layout for desktop and mobile  

### Out of Scope (MVP)
- User authentication  
- Advanced analytics
- News feeds  
- Notifications/alerts  

---

## 4. User Stories

### Core Stories
1. As a user I want to see top 20 NASDAQ companies and their stock change so I can evaluate the market easily.
2. As a user I want to search for a company by name so that I can find it easily.
3. As a user I want to view a 30-day price history chart for a company so that I get a understading of the company's stock trend.

---

## 5. Functional Requirements

| ID  | Requirement                                                                                 | Priority |
|-----|---------------------------------------------------------------------------------------------|----------|
| FR1 | Fetch and display a list of top 20 NASDAQ companies and their current prices.               | High     |
| FR2 | Show daily price change                                                                     | High     |
| FR3 | Implement a search bar to filter companies                                   | High     |
| FR4 | Allow user to click on a company to open a detailed 30-day price chart.                     | High     |
| FR5 | Fetch and display historical data for the past 30 days.                                     | High     |
| FR6 | Make the application responsive for both desktop and mobile.                                | Medium   |

---

## 6. Non-Functional Requirements
- **Performance:** Data refresh should occur within 3 seconds after load.  
- **Scalability:** Should be able to easily expand to more companies or features (such as News feed or future stock data evaluation with AI).  
- **Usability:** Search should filter results in real time. 
- **Reliability:** API calls should show appropriate error messages. 

---

## 7. Data Sources
- **Alpha Vantage API** for:  
  - Current stock price  
  - Historical 30-day data (if possible in free tier, otherwise use mock data)

---

## 8. UX/UI Requirements
- **Landing Page:** Table of top 20 NASDAQ companies  
  - Columns: Company Name, Current Price, Daily Change (% and $)  
  - Daily change color-coded (green for positive, red for negative)  
- **Search Bar:** Filtering company name in real time.  
- **Chart View:** 30-day line chart with dates and stock price.

---

## 9. Technical Stack
- **Frontend:** React.js  
- **Charts:** Chart.js (via `react-chartjs-2`) for stock data charts.
- **Backend:** Java Spring Boot  
- **API:** Alpha Vantage  
- **Hosting:** TBD (e.g., AWS, Azure, or Heroku)  

---