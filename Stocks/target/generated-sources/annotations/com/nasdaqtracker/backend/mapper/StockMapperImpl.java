package com.nasdaqtracker.backend.mapper;

import com.nasdaqtracker.backend.model.Stock;
import com.nasdaqtracker.backend.model.StockHistory;
import com.nasdaqtracker.backend.model.dto.StockDTO;
import com.nasdaqtracker.backend.model.dto.StockHistoryDTO;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-15T13:53:08+0300",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.42.50.v20250729-0351, environment: Java 21.0.8 (Eclipse Adoptium)"
)
@Component
public class StockMapperImpl extends StockMapper {

    @Override
    public StockDTO toStockDTO(Stock stock) {
        if ( stock == null ) {
            return null;
        }

        StockDTO stockDTO = new StockDTO();

        stockDTO.setSymbol( stock.getSymbol() );
        stockDTO.setName( stock.getName() );
        stockDTO.setCurrentPrice( stock.getCurrentPrice() );
        stockDTO.setPriceChange( stock.getPriceChange() );
        stockDTO.setPriceChangePercent( stock.getPriceChangePercent() );

        return stockDTO;
    }

    @Override
    public StockHistoryDTO toStockHistoryDTO(StockHistory stockHistory, Stock stock) {
        if ( stockHistory == null && stock == null ) {
            return null;
        }

        StockHistoryDTO.StockHistoryDTOBuilder stockHistoryDTO = StockHistoryDTO.builder();

        if ( stockHistory != null ) {
            Map<LocalDate, Double> map = stockHistory.getDailyPrices();
            if ( map != null ) {
                stockHistoryDTO.dailyPrices( new LinkedHashMap<LocalDate, Double>( map ) );
            }
        }
        if ( stock != null ) {
            stockHistoryDTO.symbol( stock.getSymbol() );
        }

        return stockHistoryDTO.build();
    }
}
