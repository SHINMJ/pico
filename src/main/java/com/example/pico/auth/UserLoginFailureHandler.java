package com.example.pico.auth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

public class UserLoginFailureHandler implements AuthenticationFailureHandler{
	
	private static final Logger logger = LoggerFactory.getLogger(UserLoginFailureHandler.class);

	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		logger.info(exception.getLocalizedMessage());
		logger.info(exception.getMessage());
		logger.info(request.getParameter("password"));
//		for(StackTraceElement s : exception.getStackTrace()){
//			logger.info(s.getClassName());
//			logger.info(s.getFileName());
//			logger.info(s.getMethodName());
//			logger.info(s.getLineNumber()+"");
//			logger.info(s.isNativeMethod()+"");
//		}
	
		
		request.setAttribute("errMsg",exception.getMessage());
		request.getRequestDispatcher("/WEB-INF/views/login-form.jsp").forward(request, response);
		
	}

}
