package com.es.spaceship.sale.models.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
@Builder
public class NotificationError {

	private HttpStatus status;
	private String message;
	private List<String> errors;
	private String errorCode;

}
