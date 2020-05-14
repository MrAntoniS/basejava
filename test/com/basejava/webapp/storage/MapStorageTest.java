package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MapStorageTest {
    MapStorage storage = new MapStorage();

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
    public void size() {
        assertEquals(3, storage.size());
    }

    @Test
    public void clear() {
        storage.clear();
        assertEquals(0, storage.size());
    }

    @Test
    public void getAll() {
        Resume[] actualResume = storage.getAll();
        assertEquals(actualResume, storage.getAll());
    }

    @Test
    public void checkAvailability() {
        if (storage.checkAvailability("uuid4")) {
            assertEquals(new Resume("uuid4"), storage.get("uuid4"));
        }
    }

    @Test
    public void runGet() {
        assertEquals(resume1, storage.runGet(UUID_1));
    }

    @Test
    public void runUpdate() {
        storage.runUpdate(resume1);
        assertEquals(resume1, storage.runGet(UUID_1));
    }

    @Test
    public void runSave() {
        Resume resume4 = new Resume("uuid1");
        storage.runSave(resume4);
        assertEquals(3, storage.size());
    }

    @Test(expected = AssertionError.class)
    public void runDelete() {
        storage.runDelete(UUID_1);
        assertEquals(resume1, storage.runGet(UUID_1));
    }
}