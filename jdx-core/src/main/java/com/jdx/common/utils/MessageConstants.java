package com.jdx.common.utils;

/**
 * Clase de constantes que contiene las claves de los mensajes del archivo de lenguaje
 *
 */
public class MessageConstants {

	/**
	 * Nomenclatura de nombrado
	 * 
	 *  Prefijo:
	 *  WAR: Warning mensaje
	 *  ERR: Error mensaje
	 *  OK: Success mensaje
	 *  
	 */
	
	
	
	
	public static final String ERROR_TECHNICAL = "err.technical.message";
	
	public static final String OPERATION_SUCCESS = "ok.operation.message";
	
	public static final String VALIDATION_SAMEID_OVERLAPPING_ERROR = "war.sameid.validation.message";
	
	public static final String CONCURRENCY_ERROR = "err.concurrency.message";
	
	public static final String PK_ERROR = "err.pk.message";
	
	public static final String PK_INVALID = "err.pk.invalid.message";
	
	public static final String SECURITY_EXCEPTION = "err.security.message";
	
	public static final String SESSION_EXPIRED = "err.session.message";
	
	
	/**
	 * Constructor privado para ocultar constructor publico
	 */
	private MessageConstants() {}
		
}
