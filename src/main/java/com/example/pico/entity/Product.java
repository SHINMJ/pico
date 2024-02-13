package com.example.pico.entity;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "product")
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String code;
	private String name;
	private double price;
	private long quantity;
	
	protected Product() {
		
	}
	
	private Product(String code, String name, double price, long quantity) {
		this.code = code;
		this.name = name;
		this.price = price;
		this.quantity = quantity;
	}
	
	public static Product of(String code, String name, double price, long quantity) {
		return new Product(code, name, price, quantity);
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

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		return Objects.equals(id, other.id);
	}

}
