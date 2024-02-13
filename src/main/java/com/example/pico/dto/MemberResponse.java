package com.example.pico.dto;

import com.example.pico.entity.Member;

public class MemberResponse {
	private final String memberId;
	private final String name;
	private final int age;
	private final String gender;
	
	public MemberResponse(String memberId, String name, int age, String gender) {
		super();
		this.memberId = memberId;
		this.name = name;
		this.age = age;
		this.gender = gender;
	}
	
	
}
