package com.basejava.webapp.storage;

import com.basejava.webapp.storage.strategy.XmlStreamSerializer;

public class XmlFileStorageTest extends AbstractStorageTest {

    public XmlFileStorageTest() {
        super(new FileStorage(STORAGE_DIR, new XmlStreamSerializer()));
    }
}