package com.basejava.webapp.storage;

import com.basejava.webapp.storage.strategy.DataStreamSerializer;

public class DatePathStorageTest extends AbstractStorageTest {

    public DatePathStorageTest() {
        super(new PathStorage(STORAGE_DIR.getAbsolutePath(), new DataStreamSerializer()));
    }
}