package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;
import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.assertEquals;

public class ListStorageTest {
    ListStorage storage = new ListStorage();

    private String UUID_1 = "uuid1";
    private String UUID_2 = "uuid2";
    private String UUID_3 = "uuid3";
    Resume resume1 = new Resume(UUID_1);
    Resume resume2 = new Resume(UUID_2);
    Resume resume3 = new Resume(UUID_3);

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.runSave(resume1);
        storage.runSave(resume2);
        storage.runSave(resume3);
    }

    @Test
    public void size() throws Exception {
        assertEquals(3, storage.size());
    }

    @Test
    public void clear() throws Exception {
        storage.clear();
        assertEquals(0, storage.size());
    }

    @Test
    public void getAll() throws Exception {
        Resume[] actualResume = storage.getAll();
        assertEquals(actualResume, storage.getAll());
    }

    @Test
    public void checkAvailability() throws Exception {
        if (storage.checkAvailability(UUID_1)) {
            assertEquals(resume1, storage.runGet(UUID_1));
        }
    }

    @Test
    public void runGet() throws Exception {
        assertEquals(resume1, storage.runGet(UUID_1));
    }

    @Test
    public void runUpdate() throws Exception {
        storage.runUpdate(resume1);
        assertEquals(resume1, storage.runGet(UUID_1));
    }

    @Test
    public void runSave() throws Exception {
        storage.runSave(resume1);
        Resume[] actualResume = storage.getAll();
        actualResume[0] = resume1;
        actualResume[1] = resume2;
        actualResume[2] = resume3;
        actualResume[3] = resume1;
    }

    @Test
    public void runDelete() throws Exception {
        storage.runDelete(UUID_1);
        if (storage.getIndex(UUID_1) < 0) {
            assertEquals(2, storage.size());
        } else {
            assertEquals(3, storage.size());
        }
    }

    @Test
    public void getIndex() throws Exception {
        assertEquals(0, storage.getIndex(UUID_1));
    }
}