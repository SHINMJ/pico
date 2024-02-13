package com.example.pico.auth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.example.pico.dto.LoginUser;

public class UserLoginSuccessHandler implements AuthenticationSuccessHandler{
	
	private static final Logger logger = LoggerFactory.getLogger(UserLoginSuccessHandler.class);


	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		LoginUser user = (LoginUser) authentication.getPrincipal();
		logger.info(authentication.getName());
		logger.info(authentication.getAuthorities().toString());
		logger.info(authentication.getDetails().toString());
		logger.info(authentication.getPrincipal().toString());
		for(GrantedAuthority a : authentication.getAuthorities()){
			logger.info(a.getAuthority());
		}
		logger.info(String.valueOf(user.isAccountNonExpired()));
		logger.info(String.valueOf(user.isAccountNonLocked()));
		logger.info(String.valueOf(user.isCredentialsNonExpired()));
		logger.info(String.valueOf(user.isEnabled()));
		
		response.sendRedirect(request.getContextPath()+"/");
		
	}

}
