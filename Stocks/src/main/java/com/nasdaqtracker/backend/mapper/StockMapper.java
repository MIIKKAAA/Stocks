package com.nasdaqtracker.backend.mapper;

import com.nasdaqtracker.backend.model.Stock;
import com.nasdaqtracker.backend.model.StockHistory;
import com.nasdaqtracker.backend.model.dto.StockDTO;
import com.nasdaqtracker.backend.model.dto.StockHistoryDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.time.LocalDate;
import java.util.Map;
import java.util.Objects;

@Mapper(componentModel = "spring")
public abstract class StockMapper {
    
    public static final StockMapper INSTANCE = Mappers.getMapper(StockMapper.class);

    @Mapping(target = "symbol", source = "symbol")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "currentPrice", source = "currentPrice")
    @Mapping(target = "priceChange", source = "priceChange")
    @Mapping(target = "priceChangePercent", source = "priceChangePercent")
    public abstract StockDTO toStockDTO(Stock stock);
    
    @Mapping(target = "symbol", source = "stock.symbol")
    @Mapping(target = "dailyPrices", source = "stockHistory.dailyPrices")
    public abstract StockHistoryDTO toStockHistoryDTO(StockHistory stockHistory, Stock stock);
    
    /**
     * Converts a map with Object keys to a map with LocalDate keys.
     * This is needed because JPA sometimes returns Object type for map keys.
     */
    protected Map<LocalDate, Double> map(Map<Object, Double> source) {
        if (source == null) {
            return null;
        }
        // Create a new map with the correct key type
        java.util.HashMap<LocalDate, Double> result = new java.util.HashMap<>();
        source.forEach((key, value) -> {
            if (key instanceof LocalDate) {
                result.put((LocalDate) key, value);
            } else if (key != null) {
                // Try to parse the key as LocalDate if it's a String
                try {
                    LocalDate date = LocalDate.parse(Objects.toString(key));
                    result.put(date, value);
                } catch (Exception e) {
                    // Skip invalid date formats
                }
            }
        });
        return result;
    }
    
    /**
     * Single-parameter version of toStockHistoryDTO that only takes a Stock
     */
    public StockHistoryDTO toStockHistoryDTO(Stock stock) {
        if (stock == null) {
            return null;
        }
        return StockHistoryDTO.builder()
                .symbol(stock.getSymbol())
                .build();
    }
}
