package com.example.pico.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler {
	
	@ExceptionHandler(BizException.class)
    public ResponseEntity<String> handleException(BizException ex) {
        
		return ResponseEntity.badRequest().body(ex.getMessage());
    }

}
