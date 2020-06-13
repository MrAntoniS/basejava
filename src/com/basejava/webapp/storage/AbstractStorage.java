package com.basejava.webapp.storage;

import com.basejava.webapp.exception.ExistStorageException;
import com.basejava.webapp.exception.NotExistStorageException;
import com.basejava.webapp.model.Resume;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;

public abstract class AbstractStorage<SK> implements Storage {

    private static final Logger LOG = Logger.getLogger(AbstractStorage.class.getName());

    protected static final Comparator<Resume> RESUME_COMPARATOR_FULL_NAME_THEN_UUID = Comparator.comparing(Resume::getFullName).thenComparing(Resume::getUuid);

    public Resume get(String uuid) {
        LOG.info("Get " + uuid);
        if (isExistKey(uuid)) {
            return runGet(uuid);
        }
        return null;
    }

    public void update(Resume resume) {
        LOG.info("Update " + resume);
        if (isExistKey(resume.getUuid())) {
            runUpdate(resume);
        }
    }

    public void save(Resume resume) {
        LOG.info("Save " + resume);
        if (isNotExistKey(resume)) {
            runSave(resume);
        }
    }

    public void delete(String uuid) {
        LOG.info("Delete " + uuid);
        if (isExistKey(uuid)) {
            runDelete(uuid);
        }
    }

    private boolean isNotExistKey(Resume resume) {
        if (checkAvailability(getKey(resume.getUuid()))) {
            LOG.warning("Resume " + resume.getUuid() + " already exist");
            throw new ExistStorageException(resume.getUuid());
        }
        return true;
    }

    private boolean isExistKey(String uuid) {
        if (!checkAvailability(getKey(uuid))) {
            LOG.warning("Resume " + uuid + " not exist");
            throw new NotExistStorageException(uuid);
        }
        return true;
    }

    public List<Resume> getAllSorted() {
        LOG.info("getAllSorted");
        List<Resume> sortedStorage = getStorage();
        sortedStorage.sort(RESUME_COMPARATOR_FULL_NAME_THEN_UUID);
        return sortedStorage;
    }

    protected abstract List<Resume> getStorage();

    protected abstract SK getKey(String uuid);

    protected abstract boolean checkAvailability(SK key);

    protected abstract Resume runGet(String uuid);

    protected abstract void runUpdate(Resume resume);

    protected abstract void runSave(Resume resume);

    protected abstract void runDelete(String uuid);
}