package com.ctw.catalog.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	
	
	@GetMapping("/helloCatalog")
	public String helloCatalogService() {
		return "Hello from Catalog Service";	
	}

}
