package com.example.pico.auth;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.pico.entity.Member;

public class CustomUserDetails implements UserDetails{
	
	private final Member member;
	
	public CustomUserDetails(Member member) {
		this.member = member;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return new ArrayList<GrantedAuthority>(
                Arrays.asList(
                    new SimpleGrantedAuthority("ROLE_USER")
                )
            );
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return member.password();
	}

	@Override
	public String getUsername() {
		return member.name();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
