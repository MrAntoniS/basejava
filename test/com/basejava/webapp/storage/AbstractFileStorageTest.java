package com.basejava.webapp.storage;

import static org.junit.Assert.*;

public class AbstractFileStorageTest extends AbstractStorageTest {

    public AbstractFileStorageTest() {
        super(new AbstractFileStorage(STORAGE_DIR));
    }
}