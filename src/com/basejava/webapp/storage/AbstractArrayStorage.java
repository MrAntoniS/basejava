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

    protected static final Comparator<Resume> RESUME_COMPARATOR = new Comparator<Resume>() {
        @Override
        public int compare(Resume resume, Resume storageResume) {
            if (resume.getUuid().equals(storageResume.getUuid()) && resume.getFullName().equals(storageResume.getFullName())) {
                return 0;
            }
            return resume.getUuid().compareTo(storageResume.getUuid());
        }
    };

    public int size() {
        return size;
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    //    @return array, contains only Resumes in storage (without null)

    public List<Resume> getAllSorted() {
        List<Resume> newStorage = Arrays.asList(Arrays.copyOf(storage, size));
        newStorage.sort(RESUME_COMPARATOR);
        return newStorage;
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