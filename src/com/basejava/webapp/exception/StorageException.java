package com.basejava.webapp.exception;

public class StorageException extends RuntimeException {
    private final Object informationObject;

    public StorageException(String message, Object informationObject) {
        super(message);
        this.informationObject = informationObject;
    }

    public StorageException(String message, Object informationObject, Exception e) {
        super(message, e);
        this.informationObject = informationObject;
    }

    public Object getUuid() {
        return informationObject;
    }
}
