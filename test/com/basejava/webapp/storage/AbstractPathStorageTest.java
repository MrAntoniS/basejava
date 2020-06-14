package com.basejava.webapp.storage;

public class AbstractPathStorageTest extends AbstractStorageTest {

    public AbstractPathStorageTest() {
        super(new AbstractPathStorage(DIR));
    }
}