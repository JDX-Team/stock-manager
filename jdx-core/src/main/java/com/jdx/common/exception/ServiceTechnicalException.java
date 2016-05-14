package com.jdx.common.exception;

/**
 * Clase que representa una excepcion técnica de la capa de servicio
 *
 */
public class ServiceTechnicalException extends RuntimeException {

	private static final long serialVersionUID = 6718866169244924125L;

	/**
	 * Constructor excepcion tecnica de capa Service
	 */
	public ServiceTechnicalException() {
    }

	/**
     * Constructor excepcion tecnica de capa Service
     * 
     * @param message mensaje de error
     */
    public ServiceTechnicalException(String message) {
        super( message );
    }

    /**
     * Constructor excepcion tecnica de capa Service
     * 
     * @param message mensaje de error
     * @param cause excepcion causa
     */
    public ServiceTechnicalException(String message, Throwable cause) {
        super( message, cause );
    }

    /**
     * Constructor excepcion tecnica de capa Service
     * 
     * @param cause excepcion causa
     */
    public ServiceTechnicalException(Throwable cause) {
        super( cause );
    }

}
