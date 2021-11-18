package edu.ifam.aranoua.controller.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import edu.ifam.aranoua.domain.exception.StardardError;
import edu.ifam.aranoua.service.exception.ObjectNotFoundException;

@ControllerAdvice
public class ExceptionHandlerController {

	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StardardError>ObjectNotFound(
			
			ObjectNotFoundException e, HttpServletRequest request){
		StardardError error = new StardardError (HttpStatus.NOT_FOUND.value(), e.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
}
