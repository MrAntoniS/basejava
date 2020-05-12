package com.basejava.webapp.storage;

import com.basejava.webapp.exception.ExistStorageException;
import com.basejava.webapp.exception.NotExistStorageException;
import com.basejava.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index >= 0) {
            return implementGet(index);
        }
        throw new NotExistStorageException(uuid);
    }

    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index >= 0) {
            implementUpdate(index, resume);
        } else {
            throw new NotExistStorageException(resume.getUuid());
        }
    }

    public void save(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index >= 0) {
            throw new ExistStorageException(resume.getUuid());
        } else {
            implementSave(index, resume);
        }
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index >= 0) {
            implementDelete(index);
        } else {
            throw new NotExistStorageException(uuid);
        }
    }

    protected abstract int getIndex(String uuid);

    protected abstract Resume implementGet(int index);

    protected abstract void implementUpdate(int index, Resume resume);

    protected abstract void implementSave(int index, Resume resume);

    protected abstract void implementDelete(int index);
}

