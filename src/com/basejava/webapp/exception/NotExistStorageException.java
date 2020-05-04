package com.basejava.webapp.exception;

public class NotExistStorageException extends StorageException {
    public NotExistStorageException(String uuid) {
        super("Resume " + uuid + "  is not present in storage", uuid);
    }
}
