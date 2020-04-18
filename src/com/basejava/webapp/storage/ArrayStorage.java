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
        String uuid = resume.getUuid();
        if (checkPresentOfResume(uuid)) {
            for (int i = 0; i < size; i++) {
                if (storage[i].getUuid().equals(uuid)) {
                    storage[i] = null;
                    storage[i] = resume;
                    break;
                }
            }
        }
    }

    public void save(Resume resume) {
        String uuid = resume.getUuid();
        if (!checkPresentOfResume(uuid)) {
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
        if (checkPresentOfResume(uuid)) {
            for (int i = 0; i < size; i++) {
                if (storage[i].getUuid().equals(uuid)) {
                    return storage[i];
                }
            }
        }
        return null;
    }

    public void delete(String uuid) {
        if (checkPresentOfResume(uuid)) {
            for (int i = 0; i < size; i++) {
                if (storage[i].getUuid().equals(uuid)) {
                    storage[i].setUuid(null);
                    if (size - 1 - i >= 0) {
                        System.arraycopy(storage, i + 1, storage, i, size - i - 1);
                    }
                    size--;
                    break;
                }
            }
        }
    }

    private boolean checkPresentOfResume(String uuid) {
        for (int i = 0; i < storage.length; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                System.out.println("Resume is present");
                return true;
            }
        }
        return false;
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