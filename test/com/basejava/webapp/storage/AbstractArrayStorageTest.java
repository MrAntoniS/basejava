package com.basejava.webapp.storage;

import com.basejava.webapp.exception.StorageException;
import com.basejava.webapp.model.Resume;
import org.junit.Test;

import static com.basejava.webapp.storage.AbstractArrayStorage.STORAGE_LIMIT;

public abstract class AbstractArrayStorageTest extends AbstractStorageTest {

    public AbstractArrayStorageTest(Storage storage) {
        super(storage);
    }

    @Test(expected = StorageException.class)
    public void saveOverflow() throws Exception {
        int i = 0;
        for (i = 3; i < STORAGE_LIMIT; i++) {
            storage.save(new Resume("UUID_" + i, "NAME_" + i));
        }
        storage.save(new Resume("UUID_" + i, "NAME_" + i));
    }
}