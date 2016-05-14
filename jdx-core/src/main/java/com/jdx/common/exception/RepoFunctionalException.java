package com.jdx.common.exception;

/**
 * Clase que representa una excepcion funcional de la capa Dao
 *
 */
public class RepoFunctionalException extends RuntimeException {


	private static final long serialVersionUID = 3379648581885078800L;

	/**
	 * Constructor excepcion funcional de capa DAO
	 */
    public RepoFunctionalException() {
    }

    /**
     * Constructor excepcion funcional de capa DAO
     * 
     * @param message mensaje de error
     */
    public RepoFunctionalException(String message) {
        super( message );
    }

    /**
     * Constructor excepcion funcional de capa DAO
     * 
     * @param message mensaje de error
     * @param cause excepcion causa
     */
    public RepoFunctionalException(String message, Throwable cause) {
        super( message, cause );
    }

    /**
     * Constructor excepcion funcional de capa DAO
     * 
     * @param cause excepcion causa
     */
    public RepoFunctionalException(Throwable cause) {
        super( cause );
    }

}
