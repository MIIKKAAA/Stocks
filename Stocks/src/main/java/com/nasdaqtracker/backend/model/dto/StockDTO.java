package com.nasdaqtracker.backend.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockDTO {
    private String symbol;
    private String name;
    private Double currentPrice;
    private Double priceChange;
    private Double priceChangePercent;
}

