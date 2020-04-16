package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int size;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void update(Resume resume) {
        String uuid = resume.getUuid();
        checkPresentOfResume(uuid);
        for (int i = 0; i < size; i++) {
            if (storage[i] == resume) {
                storage[i] = resume;
                break;
            }
        }
    }

    public void save(Resume resume) {
        String uuid = resume.getUuid();
        checkPresentOfResume(uuid);
        if (size == storage.length) {
            System.out.println("No storage");
        } else {
            storage[size] = resume;
            size++;
        }
    }

    public Resume get(String uuid) {
        checkPresentOfResume(uuid);
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return storage[i];
            }
        }
        return null;
    }

    public void delete(String uuid) {
        checkPresentOfResume(uuid);
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

    private void checkPresentOfResume(String uuid) {
        for (Resume value : storage) {
            if (value != null && value.getUuid().equals(uuid)) {
                System.out.println("Resume is present");
                break;
            } else if (value == null) {
                System.out.println("Resume is not present");
                break;
            }
        }
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