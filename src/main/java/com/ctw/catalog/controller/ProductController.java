package com.ctw.catalog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ctw.catalog.dto.ProductResponse;
import com.ctw.catalog.service.ProductService;


@RestController
@RequestMapping("/api/products")
public class ProductController {
	
	@Autowired
	ProductService productService;

	
	@GetMapping("/products/pname/{productName}")
	public List<ProductResponse> getProductByName(@PathVariable String productName) {
		
		return productService.getProductByProductName(productName);
	}
	
}
