package com.basejava.webapp.storage;

import com.basejava.webapp.exception.StorageException;
import com.basejava.webapp.model.Resume;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int STORAGE_LIMIT = 10_000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public int size() {
        return size;
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    protected List<Resume> getStorage() {
        return Arrays.asList(Arrays.copyOf(storage, size));
    }

    @Override
    protected boolean checkAvailability(Object key) {
        return (Integer) key >= 0;
    }

    @Override
    protected Resume runGet(String uuid) {
        return storage[getKey(uuid)];
    }

    @Override
    protected void runUpdate(Resume resume) {
        storage[getKey(resume.getUuid())] = resume;
    }

    @Override
    protected void runSave(Resume resume) {
        if (size == STORAGE_LIMIT) {
            throw new StorageException("Storage is full", resume.getUuid());
        } else {
            saveResume(resume, getKey(resume.getUuid()));
            size++;
        }
    }

    @Override
    protected void runDelete(String uuid) {
        deleteResume(getKey(uuid));
        storage[size - 1] = null;
        size--;
    }

    protected abstract Integer getKey(String uuid);

    protected abstract void saveResume(Resume resume, int index);

    protected abstract void deleteResume(int index);
}