package com.ctw.catalog.dto;

public record ProductResponse(Long id,
        String name,
        String brand,
        double price,
        String color,
        String size,
        String description,
        String categoryName) {

}
