package com.jdx.common.exception;

/**
 * Clase que representa una excepcion de la capa de filtros
 *
 */
public class FilterException extends RuntimeException {

	private static final long serialVersionUID = -8836505810586825708L;
	private final int errorCode;

    /**
     * Constructor filter exception
     * 
     * @param errorCode codigo de error, este codigo sera usado en la respuesta http
     * @param message texto del error
     */
    public FilterException(int errorCode, String message) {
        super( message );
        this.errorCode = errorCode;
    }

    /**
     * Constructor filter exception
     * 
     * @param errorCode codigo de error, este codigo sera usado en la respuesta http
     * @param message texto del error
     * @param cause throwable causa
     */
    public FilterException(int errorCode, String message, Throwable cause) {
        super( message, cause );
        this.errorCode = errorCode;
    }

    /**
     * Devuelve el codigo de error
     * 
     * @return codigo de error 
     */
	public int getErrorCode() {
		return errorCode;
	}
	

	
}
