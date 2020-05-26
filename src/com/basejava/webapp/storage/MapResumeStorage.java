package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.*;

public class MapResumeStorage extends AbstractStorage {

    private Map<String, Resume> storage = new HashMap<>();

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public List<Resume> getStorage() {
        return new ArrayList<>(storage.values());
    }

    @Override
    protected Resume getKey(String uuid) {
        return storage.get(uuid);
    }

    @Override
    protected boolean checkAvailability(Object key) {
        Resume resume = (Resume) key;
        return resume != null;
    }

    @Override
    protected Resume runGet(String uuid) {
        return storage.get(uuid);
    }

    @Override
    protected void runUpdate(Resume resume) {
        storage.put(resume.getUuid(), resume);
    }

    @Override
    protected void runSave(Resume resume) {
        storage.putIfAbsent(resume.getUuid(), resume);
    }

    @Override
    protected void runDelete(String uuid) {
        storage.remove(uuid);
    }
}