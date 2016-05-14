package com.jdx.common.exception;

/**
 * Clase que representa una excepcion técnica de la capa Dao
 *
 */
public class RepoTechnicalException extends RuntimeException {


	private static final long serialVersionUID = 3379648581885078800L;

	/**
	 * Constructor excepcion tecnica de capa DAO
	 */
    public RepoTechnicalException() {
    }

    /**
     * Constructor excepcion tecnica de capa DAO
     * 
     * @param message mensaje de error
     */
    public RepoTechnicalException(String message) {
        super( message );
    }

    /**
     * Constructor excepcion tecnica de capa DAO
     * 
     * @param message mensaje de error
     * @param cause excepcion causa
     */
    public RepoTechnicalException(String message, Throwable cause) {
        super( message, cause );
    }

    /**
     * Constructor excepcion tecnica de capa DAO
     * 
     * @param cause excepcion causa
     */
    public RepoTechnicalException(Throwable cause) {
        super( cause );
    }

}
