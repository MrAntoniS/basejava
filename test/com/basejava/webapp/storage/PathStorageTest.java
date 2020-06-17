package com.basejava.webapp.storage;

import com.basejava.webapp.storage.strategy.StreamSerializationStrategy;

public class PathStorageTest extends AbstractStorageTest {

    public PathStorageTest() {
        super(new PathStorage(DIR, new StreamSerializationStrategy()));
    }
}