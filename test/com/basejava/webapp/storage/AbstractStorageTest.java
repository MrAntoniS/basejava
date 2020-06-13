package com.basejava.webapp.storage;

import com.basejava.webapp.ResumeTestData;
import com.basejava.webapp.exception.ExistStorageException;
import com.basejava.webapp.exception.NotExistStorageException;
import com.basejava.webapp.model.Resume;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public abstract class AbstractStorageTest {

    protected static final File STORAGE_DIR = new File("C:\\Users\\Антон\\Desktop\\basejava\\basejava\\storage");
    protected Storage storage;

    protected static final String UUID_1 = "uuid1";
    protected static final String UUID_2 = "uuid2";
    protected static final String UUID_3 = "uuid3";

//    protected static final String FULL_NAME_1 = "fullName1";
//    protected static final String FULL_NAME_2 = "fullName2";
//    protected static final String FULL_NAME_3 = "fullName3";
    ResumeTestData testResume = new ResumeTestData();

    public AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(testResume.getTestResume(UUID_3));
        storage.save(testResume.getTestResume(UUID_2));
        storage.save(testResume.getTestResume(UUID_1));
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
    public void getAllSorted() throws Exception {
        List<Resume> actualResume = new ArrayList<>();
        actualResume.add(testResume.getTestResume(UUID_1));
        actualResume.add(testResume.getTestResume(UUID_2));
        actualResume.add(testResume.getTestResume(UUID_3));
        assertEquals(actualResume, storage.getAllSorted());
    }

    @Test
    public void get() throws Exception {
        assertEquals(testResume.getTestResume(UUID_1), storage.get(UUID_1));
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() throws Exception {
        storage.get("dummy");
    }

    @Test
    public void update() throws Exception {
        Resume newResume = testResume.getTestResume(UUID_1);
        storage.update(newResume);
        assertEquals(newResume, storage.get(UUID_1));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() throws Exception {
        Resume testResume = new Resume("dummy", "dummy");
        storage.update(testResume);
    }

    @Test
    public void save() throws Exception {
        storage.clear();
        storage.save(testResume.getTestResume(UUID_1));
        assertEquals(1, storage.size());
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() throws Exception {
        storage.save(testResume.getTestResume(UUID_1));
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() throws Exception {
        storage.delete(UUID_1);
        assertEquals(2, storage.size());
        storage.get(UUID_1);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() throws Exception {
        storage.delete("dummy");
    }
}