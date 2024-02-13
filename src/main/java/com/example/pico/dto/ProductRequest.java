package com.example.pico.dto;

import com.example.pico.entity.Product;

public class ProductRequest {
	private final String code;
	private final String name;
	private final double price;
	private final long quantity;
	
	public ProductRequest(String code, String name, double price, long quantity) {
		super();
		this.code = code;
		this.name = name;
		this.price = price;
		this.quantity = quantity;
	}

	public Product toEntity() {
		return Product.of(this.code, this.name, this.price, this.quantity);
	}
}
