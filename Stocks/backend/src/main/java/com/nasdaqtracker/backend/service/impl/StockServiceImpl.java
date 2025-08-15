package com.nasdaqtracker.backend.service.impl;

import com.nasdaqtracker.backend.client.FinnhubClient;
import com.nasdaqtracker.backend.model.dto.StockDTO;
import com.nasdaqtracker.backend.model.dto.StockHistoryDTO;
import com.nasdaqtracker.backend.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// Service implementations for StockService
@Service
public class StockServiceImpl implements StockService {

    
    private final FinnhubClient FinnhubClient;

    // FinnhubClient contains the logic for fetching data from Finnhub API
    @Autowired
    public StockServiceImpl(FinnhubClient FinnhubClient) {
        this.FinnhubClient = FinnhubClient;
    }

    @Override
    public List<StockDTO> getTop20Stocks() {
        return FinnhubClient.getTop20Stocks();
    }

    @Override
    public StockHistoryDTO getStockHistory(String symbol) {
        return FinnhubClient.getStockHistory(symbol);
    }

    @Override
    public List<StockDTO> searchStocks(String query) {
        return FinnhubClient.searchStocks(query);
    }
}
