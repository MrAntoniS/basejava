package com.basejava.webapp.storage;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ArrayStorageTest.class,
        SortedArrayStorageTest.class,
        ListStorageTest.class,
        MapUuidStorageTest.class,
        MapResumeStorageTest.class,
        ObjectPathStorageTest.class,
        ObjectFileStorageTest.class,
        JsonPathStorageTest.class,
        JsonFileStorageTest.class,
        DateFileStorageTest.class,
        DatePathStorageTest.class,
        XmlFileStorageTest.class,
        XmlPathStorageTest.class,
        SqlStorageTest.class
})
public class AllStorageTest {
}
