package com.example.pico.dto;

import com.example.pico.entity.Gender;
import com.example.pico.entity.Member;

public class MemberRequest {
	
	private final String memberId;
	private final String password;
	private final String name;
	private final int age;
	private final String gender;
	
	
	public MemberRequest(String memberId, String password, String name, int age, String gender) {
		super();
		this.memberId = memberId;
		this.password = password;
		this.name = name;
		this.age = age;
		this.gender = gender;
	}
	
	public Member toEntity() {
		return Member.of(memberId, password, name, age, Gender.valueOf(gender));
	}

}
