package com.jdx.common.exception;

/**
 * Clase que representa una excepcion funcional de la capa de servicio
 *
 */
public class ServiceFunctionalException extends RuntimeException {

	private static final long serialVersionUID = 6718866169244924125L;

	/**
	 * Constructor excepcion funcional de capa Service
	 */
	public ServiceFunctionalException() {
	}
	
	/**
     * Constructor excepcion funcional de capa Service
     * 
     * @param message mensaje de error
     */
	public ServiceFunctionalException(String message) {
		super(message);
	}

	/**
     * Constructor excepcion funcional de capa Service
     * 
     * @param message mensaje de error
     * @param cause excepcion causa
     */
	public ServiceFunctionalException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
     * Constructor excepcion funcional de capa Service
     * 
     * @param cause excepcion causa
     */
	public ServiceFunctionalException(Throwable cause) {
		super(cause);
	}

}
