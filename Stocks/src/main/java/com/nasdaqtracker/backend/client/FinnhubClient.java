package com.nasdaqtracker.backend.client;

import com.nasdaqtracker.backend.model.dto.StockDTO;
import com.nasdaqtracker.backend.model.dto.StockHistoryDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Component
public class FinnhubClient {

    // Injected from application.properties
    @Value("${finnhub.api.key}")
    private String apiKey;

    // RestTemplate for making HTTP requests
    private final RestTemplate restTemplate = new RestTemplate();
    // Base URLs for Finnhub API
    private static final String FINNHUB_QUOTE_URL = "https://finnhub.io/api/v1/quote";
    private static final String FINNHUB_PROFILE_URL = "https://finnhub.io/api/v1/stock/profile2";
    
    // List of top 5 stocks (AAPL, GOOGL, MSFT)
    private static final List<String> TOP_20_STOCKS = Arrays.asList(
           "AAPL", "GOOGL", "MSFT", "TSLA", "NVDA", 
           "META", "AMZN", "JPM", "BAC", "WFC", "V", "BMY", 
           "JNJ", "CVX", "XOM", "MCD", "C", "BAC", "WFC", "V"
    );

    public List<StockDTO> getTop20Stocks() {
        List<StockDTO> stocks = new ArrayList<>();
 
        for (String symbol : TOP_20_STOCKS) {
            try {
                // Quote endpoint: returns fields c (current), d (change), dp (percent)
                String quoteUrl = FINNHUB_QUOTE_URL + "?symbol=" + symbol + "&token=" + apiKey;
                Map<String, Object> quote = restTemplate.getForObject(quoteUrl, Map.class);
 
                if (quote == null || quote.isEmpty() || quote.get("c") == null) {
                    System.out.println("No quote data for " + symbol + ": " + quote);
                    continue;
                }
 
                double current = Double.parseDouble(quote.get("c").toString());
                double change = quote.get("d") != null ? Double.parseDouble(quote.get("d").toString()) : 0.0;
                double changePercent = quote.get("dp") != null ? Double.parseDouble(quote.get("dp").toString()) : 0.0;
 
                // Fetch company name
                String name = symbol;
                try {
                    String profileUrl = FINNHUB_PROFILE_URL + "?symbol=" + symbol + "&token=" + apiKey;
                    Map<String, Object> profile = restTemplate.getForObject(profileUrl, Map.class);
                    if (profile != null && profile.get("name") != null) {
                        name = profile.get("name").toString();
                    }
                } catch (Exception ignored) {
                }
 
                stocks.add(new StockDTO(symbol, name, current, change, changePercent));
            } catch (Exception e) {
                System.out.println("Error fetching data for " + symbol + ": " + e.getMessage());
            }
        }
 
        return stocks;
    }
    
    public StockHistoryDTO getStockHistory(String symbol) {
        // TODO
        return new StockHistoryDTO();
    }

    public List<StockDTO> searchStocks(String query) {
        // TODO
        return List.of();
    }
}
