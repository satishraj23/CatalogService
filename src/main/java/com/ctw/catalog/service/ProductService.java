package com.ctw.catalog.service;

import java.util.List;

import com.ctw.catalog.dto.ProductResponse;

public interface ProductService {

	public List<ProductResponse> getProductByProductName(String prodcutName);

}
