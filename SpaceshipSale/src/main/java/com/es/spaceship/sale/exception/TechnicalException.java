package com.es.spaceship.sale.exception;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

public class TechnicalException extends SolunionRestException {

	private static final long serialVersionUID = 1L;
	private static final String errorCode = "001";

	public TechnicalException(MessageSource messageSource) {
		super(messageSource.getMessage(errorCode, null, LocaleContextHolder.getLocale()), errorCode);
	}
}
