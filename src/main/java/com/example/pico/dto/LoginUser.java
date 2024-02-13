package com.example.pico.dto;

import com.example.pico.auth.CustomUserDetails;
import com.example.pico.entity.Member;

public class LoginUser extends CustomUserDetails {

	private Member member;
	
	public LoginUser(Member member) {
		super(member);
		this.member = member;
	}
	
	public String name() {
		return member.name();
	}
	
	public Long id() {
		return member.id();
	}

}
