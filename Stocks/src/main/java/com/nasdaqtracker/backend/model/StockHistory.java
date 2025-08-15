package com.nasdaqtracker.backend.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.util.Map;

@Data
@Entity
@Table(name = "stock_history")
public class StockHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "stock_id", nullable = false)
    private Stock stock;
    
    @ElementCollection
    @CollectionTable(name = "daily_prices", joinColumns = @JoinColumn(name = "history_id"))
    @MapKeyColumn(name = "date")
    @Column(name = "price")
    private Map<LocalDate, Double> dailyPrices;
    
    @Column(name = "recorded_date")
    private LocalDate recordedDate;
}
