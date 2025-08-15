package com.nasdaqtracker.backend.controller;

import com.nasdaqtracker.backend.model.dto.StockDTO;
import com.nasdaqtracker.backend.model.dto.StockHistoryDTO;
import com.nasdaqtracker.backend.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stocks")
public class StockController {

    private final StockService stockService;

    @Autowired
    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @GetMapping
    public ResponseEntity<List<StockDTO>> getTop20Stocks() {
        return ResponseEntity.ok(stockService.getTop20Stocks());
    }

    @GetMapping("/{symbol}/history")
    public ResponseEntity<StockHistoryDTO> getStockHistory(@PathVariable String symbol) {
        return ResponseEntity.ok(stockService.getStockHistory(symbol));
    }

    @GetMapping("/search")
    public ResponseEntity<List<StockDTO>> searchStocks(@RequestParam String query) {
        return ResponseEntity.ok(stockService.searchStocks(query));
    }
}
