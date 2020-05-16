package com.basejava.webapp.storage;

import com.basejava.webapp.exception.ExistStorageException;
import com.basejava.webapp.exception.NotExistStorageException;
import com.basejava.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    public Resume get(String uuid) {
        if (checkAvailability(getKey(uuid))) {
            return runGet(uuid);
        }
        throw new NotExistStorageException(uuid);
    }

    public void update(Resume resume) {
        if (checkAvailability(getKey(resume.getUuid()))) {
            runUpdate(resume);
        } else {
            throw new NotExistStorageException(resume.getUuid());
        }
    }

    public void save(Resume resume) {
        if (checkAvailability(getKey(resume.getUuid()))) {
            throw new ExistStorageException(resume.getUuid());
        } else {
            runSave(resume);
        }
    }

    public void delete(String uuid) {
        if (checkAvailability(getKey(uuid))) {
            runDelete(uuid);
        } else {
            throw new NotExistStorageException(uuid);
        }
    }

    protected abstract Object getKey(String uuid);

    protected abstract boolean checkAvailability(Object key);

    protected abstract Resume runGet(String uuid);

    protected abstract void runUpdate(Resume resume);

    protected abstract void runSave(Resume resume);

    protected abstract void runDelete(String uuid);
}