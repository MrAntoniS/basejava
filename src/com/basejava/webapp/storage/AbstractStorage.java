package com.basejava.webapp.storage;

import com.basejava.webapp.exception.ExistStorageException;
import com.basejava.webapp.exception.NotExistStorageException;
import com.basejava.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

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

    public boolean isNotExistKey(Resume resume) {
        if (checkAvailability(getKey(resume.getUuid()))) {
            throw new ExistStorageException(resume.getUuid());
        }
        return true;
    }

    public boolean isExistKey(String uuid) {
        if (!checkAvailability(getKey(uuid))) {
            throw new NotExistStorageException(uuid);
        }
        return true;
    }

    protected abstract Object getKey(String uuid);

    protected abstract boolean checkAvailability(Object key);

    protected abstract Resume runGet(String uuid);

    protected abstract void runUpdate(Resume resume);

    protected abstract void runSave(Resume resume);

    protected abstract void runDelete(String uuid);
}