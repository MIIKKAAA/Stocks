package com.nasdaqtracker.backend.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Map;

/**
 * DTO for {@link com.nasdaqtracker.model.StockHistory}
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StockHistoryDTO {
    private String symbol;
    private Map<LocalDate, Double> dailyPrices; // Date -> Price
}
