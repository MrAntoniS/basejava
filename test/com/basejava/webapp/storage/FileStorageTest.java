package com.basejava.webapp.storage;

import com.basejava.webapp.storage.strategy.StreamSerializationStrategy;

public class FileStorageTest extends AbstractStorageTest {

    public FileStorageTest() {
        super(new FileStorage(STORAGE_DIR, new StreamSerializationStrategy()));
    }
}