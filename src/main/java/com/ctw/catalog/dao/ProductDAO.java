package com.ctw.catalog.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ctw.catalog.entity.Product;

public interface ProductDAO extends JpaRepository<Product, Integer> {
	
	List<Product> findByName(String name);
	
}
