package com.basejava.webapp.storage;

import com.basejava.webapp.storage.strategy.DataStreamSerializer;

public class DateFileStorageTest extends AbstractStorageTest {

    public DateFileStorageTest() {
        super(new FileStorage(STORAGE_DIR, new DataStreamSerializer()));
    }
}