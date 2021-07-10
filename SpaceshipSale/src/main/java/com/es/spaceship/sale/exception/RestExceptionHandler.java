package com.es.spaceship.sale.exception;

import com.es.spaceship.sale.models.entity.NotificationError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.es.spaceship.sale.models.entity.Notification;


@RestControllerAdvice
public class RestExceptionHandler {

	@ExceptionHandler({ BusinessException.class })
	public ResponseEntity<?> handleEntityNotFound(BusinessException ex) {
		return new ResponseEntity<>(NotificationError.builder().status(HttpStatus.NOT_FOUND).errorCode(HttpStatus.NOT_FOUND.toString())
				.message(ex.getMessage()).build(), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler({ TechnicalException.class })
	public ResponseEntity<?> handleEntityInternelServerError(TechnicalException ex) {
		return new ResponseEntity<>(NotificationError.builder().status(HttpStatus.INTERNAL_SERVER_ERROR).errorCode(HttpStatus.INTERNAL_SERVER_ERROR.toString())
				.message(ex.getMessage()).build(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
