package com.ecommerce.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(UserException.class)
	public ResponseEntity<ErrorDetails> handleuserexception(UserException exc, WebRequest request) {
    	ErrorDetails errordetails= new ErrorDetails(LocalDateTime.now(), exc.getMessage(), request.getDescription(false));
    	return new ResponseEntity<ErrorDetails>(errordetails, HttpStatus.BAD_REQUEST);

	}
    @ExceptionHandler(CategoryExcpetion.class)
	public ResponseEntity<ErrorDetails> handleCategoryexception(CategoryExcpetion exc, WebRequest request) {
    	ErrorDetails errordetails= new ErrorDetails(LocalDateTime.now(), exc.getMessage(), request.getDescription(false));
    	return new ResponseEntity<ErrorDetails>(errordetails, HttpStatus.BAD_REQUEST);

	}
    @ExceptionHandler(ProductException.class)
   	public ResponseEntity<ErrorDetails> handleProductexception(ProductException exc, WebRequest request) {
       	ErrorDetails errordetails= new ErrorDetails(LocalDateTime.now(), exc.getMessage(), request.getDescription(false));
       	return new ResponseEntity<ErrorDetails>(errordetails, HttpStatus.BAD_REQUEST);

   	}
    
}
