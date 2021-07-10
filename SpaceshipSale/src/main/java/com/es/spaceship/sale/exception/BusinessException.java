package com.es.spaceship.sale.exception;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

@SuppressWarnings("serial")
public class BusinessException extends SolunionRestException {

	private static final String errorCode = "001";

	public static final String errorBattlePending = "Tiene las siguientes batallas pendientes: ";
	public static final String errorBattleChallengeStatus = "Batalla no se puede iniciar no ha sido Aceptada ";
	public static final String errorChallengeFinished = "Batalla ha sido finalizada ";
	public static final String errorShipBattleEmpty = "No dispones de naves para atacar ";
	public static final String errorShipDefenseEmpty = "No dispones de naves para defender ";
	public static final String forbidenForShipDefenseEmpty = "No puede desafiar a un usuario que no dispone de naves para defenderse ";
	public static final String errorNickNotExist = "Nick no existe";

	public BusinessException(String message) {
		this(message, null);
	}

	public BusinessException(String message, Throwable cause) {
		super(message, cause);
	}

	public BusinessException(MessageSource messageSource) {
		super(messageSource.getMessage(errorCode, null, LocaleContextHolder.getLocale()), errorCode);
	}

}
