package com.example.pico.dto;

public class ProductResponse {
	private final Long id;
	private final String code;
	private final String name;
	private final double price;
	private final long quantity;
	
	public ProductResponse(Long id, String code, String name, double price, long quantity) {
		super();
		this.id = id;
		this.code = code;
		this.name = name;
		this.price = price;
		this.quantity = quantity;
	}
	
	public Long id() {
		return this.id;
	}
	
	public String code() {
		return this.code;
	}
	
	public String name() {
		return this.name;
	}
	
	public double price() {
		return this.price;
	}
	
	public long quantity() {
		return this.quantity;
	}
}
