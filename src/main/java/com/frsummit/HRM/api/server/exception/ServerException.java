package com.frsummit.HRM.api.server.exception;

import java.rmi.RemoteException;

/**
 * 
 * @author hfoko
 *
 */
public class ServerException extends RemoteException {

    private static final long serialVersionUID = 6687053166704264373L;

    /**
     * 
     */
    public ServerException() {
        super();
    }

    /**
     * 
     */
    public ServerException( String message ) {
        super( message );
    }

    /**
     * 
     * @param message
     * @param cause
     */
    public ServerException( String message, Throwable cause ) {
        super( message, cause );
    }

}
