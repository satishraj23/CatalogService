package com.ctw.catalog.mapper;

import com.ctw.catalog.dto.ProductResponse;
import com.ctw.catalog.entity.Product;

public class ProductMapper {

	public static ProductResponse mapToProductResponse(Product product) {

		ProductResponse productResponse = new ProductResponse(product.getProduct_id(), product.getName(), product.getBrand(),
				product.getPrice(), product.getColor(), product.getSize(), product.getDescription(),
				product.getCategoryName());

		return productResponse;

	}

}
