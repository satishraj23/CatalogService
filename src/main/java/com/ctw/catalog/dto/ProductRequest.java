package com.ctw.catalog.dto;

public record ProductRequest( String name,
        String brand,
        double price,
        String color,
        String size,
        String description,
        Long categoryId) {
	
}
