package com.example.pico.dto;

public class PrizeResponse {
	private final Long id;
	private final String name;
	
	public PrizeResponse(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	
	
}
