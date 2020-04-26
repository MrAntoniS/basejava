package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public int size() {
        return size;
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index != -1) {
            return storage[index];
        }
        return null;
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    //    @return array, contains only Resumes in storage (without null)

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index != -1) {
            storage[index] = resume;
        }
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index != -1) {
            if (size - 1 - index >= 0) {
                System.arraycopy(storage, index + 1, storage, index, size - index - 1);
            }
            size--;
        }
    }

    public void save(Resume resume) {
        if (getIndex(resume.getUuid()) <= -1) {
            System.out.println("Resume is not present");
            if (size == storage.length) {
                System.out.println("No storage");
            } else {
                arraySort(resume);
            }
        }
    }

    protected abstract void arraySort(Resume resume);

    protected abstract int getIndex(String uuid);
}
