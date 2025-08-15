package com.nasdaqtracker.backend.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "stocks")
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true, nullable = false)
    private String symbol;
    
    @Column(nullable = false)
    private String name;
    
    @Column(name = "current_price")
    private Double currentPrice;
    
    @Column(name = "price_change")
    private Double priceChange;
    
    @Column(name = "price_change_percent")
    private Double priceChangePercent;
}
