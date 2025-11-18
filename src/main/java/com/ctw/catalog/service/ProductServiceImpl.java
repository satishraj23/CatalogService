package com.ctw.catalog.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ctw.catalog.dao.ProductDAO;
import com.ctw.catalog.dto.ProductResponse;
import com.ctw.catalog.mapper.ProductMapper;

@Service
public class ProductServiceImpl  implements ProductService{
	
	@Autowired
	ProductDAO productDAO;
	
	@Override
	public List<ProductResponse> getProductByProductName(String productName){
		
		return productDAO.findByName(productName)
				.stream()
				.map(ProductMapper::mapToProductResponse)
				.collect(Collectors.toList());
		
	}

}
