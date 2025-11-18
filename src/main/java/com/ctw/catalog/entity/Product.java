package com.ctw.catalog.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "products", schema = "productms")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long product_id;

	private String name;
	private String brand;
	private double price;
	private String color;
	private String size;
	private String description;
	private String categoryName;

	public Long getProduct_id() {
		return product_id;
	}

	public String getName() {
		return name;
	}

	public String getBrand() {
		return brand;
	}

	public double getPrice() {
		return price;
	}

	public String getColor() {
		return color;
	}

	public String getSize() {
		return size;
	}

	public String getDescription() {
		return description;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setProduct_id(Long product_id) {
		this.product_id = product_id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
}
