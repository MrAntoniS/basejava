package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10_000];
    private int size;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void update(Resume resume) {
        if (checkPresentOfResume(resume.getUuid()) != 10_000) {
            storage[checkPresentOfResume(resume.getUuid())] = resume;
        }
    }

    public void save(Resume resume) {
        if (checkPresentOfResume(resume.getUuid()) == 10_000) {
            System.out.println("Resume is not present");
            if (size == storage.length) {
                System.out.println("No storage");
            } else {
                storage[size] = resume;
                size++;
            }
        }
    }

    public Resume get(String uuid) {
        if (checkPresentOfResume(uuid) != 10_000) {
            return storage[checkPresentOfResume(uuid)];
        }
        return null;
    }

    public void delete(String uuid) {
        if (checkPresentOfResume(uuid) != 10_000) {
            storage[checkPresentOfResume(uuid)].setUuid(null);
            if (size - 1 - checkPresentOfResume(uuid) >= 0) {
                System.arraycopy(storage, checkPresentOfResume(uuid) + 1, storage, checkPresentOfResume(uuid), size - checkPresentOfResume(uuid) - 1);
            }
            size--;
        }
    }

    private int checkPresentOfResume(String uuid) {
        for (int i = 0; i < storage.length; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                System.out.println("Resume is present");
                return i;
            }
        }
        return 10_000;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }
}