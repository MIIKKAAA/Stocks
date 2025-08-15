package com.nasdaqtracker.backend.service;

import com.nasdaqtracker.backend.model.dto.StockDTO;
import com.nasdaqtracker.backend.model.dto.StockHistoryDTO;

import java.util.List;

public interface StockService {
    List<StockDTO> getTop20Stocks();
    StockHistoryDTO getStockHistory(String symbol);
    List<StockDTO> searchStocks(String query);
}
