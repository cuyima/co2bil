package de.vlaasch.co2bil.exceptions;

public class RemoteServiceException extends RuntimeException {
    public RemoteServiceException() {
        super();
    }

    public RemoteServiceException(String message) {
        super(message);
    }
}
