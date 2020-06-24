package com.basejava.webapp.storage;

import com.basejava.webapp.storage.strategy.JsonStreamSerializer;

public class JsonFileStorageTest extends AbstractStorageTest {

    public JsonFileStorageTest() {
        super(new FileStorage(STORAGE_DIR, new JsonStreamSerializer()));
    }
}