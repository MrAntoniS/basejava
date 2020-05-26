package com.basejava.webapp.storage;

import com.basejava.webapp.exception.ExistStorageException;
import com.basejava.webapp.exception.NotExistStorageException;
import com.basejava.webapp.model.Resume;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public abstract class AbstractStorage implements Storage {

    protected static final Comparator<Resume> RESUME_COMPARATOR_FULL_NAME_THEN_UUID = Comparator.comparing(Resume::getFullName).thenComparing(Resume::getUuid);

    public Resume get(String uuid) {
        if (isExistKey(uuid)) {
            return runGet(uuid);
        }
        return null;
    }

    public void update(Resume resume) {
        if (isExistKey(resume.getUuid())) {
            runUpdate(resume);
        }
    }

    public void save(Resume resume) {
        if (isNotExistKey(resume)) {
            runSave(resume);
        }
    }

    public void delete(String uuid) {
        if (isExistKey(uuid)) {
            runDelete(uuid);
        }
    }

    private boolean isNotExistKey(Resume resume) {
        if (checkAvailability(getKey(resume.getUuid()))) {
            throw new ExistStorageException(resume.getUuid());
        }
        return true;
    }

    private boolean isExistKey(String uuid) {
        if (!checkAvailability(getKey(uuid))) {
            throw new NotExistStorageException(uuid);
        }
        return true;
    }

    public List<Resume> getAllSorted() {
        List<Resume> sortedStorage = getStorage();
        sortedStorage.sort(RESUME_COMPARATOR_FULL_NAME_THEN_UUID);
        return sortedStorage;
    }

    protected abstract List<Resume> getStorage();

    protected abstract Object getKey(String uuid);

    protected abstract boolean checkAvailability(Object key);

    protected abstract Resume runGet(String uuid);

    protected abstract void runUpdate(Resume resume);

    protected abstract void runSave(Resume resume);

    protected abstract void runDelete(String uuid);
}