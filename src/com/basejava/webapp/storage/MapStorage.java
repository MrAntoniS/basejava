package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.HashMap;
import java.util.Map;

public class MapStorage extends AbstractStorage {

    private Map<String, Resume> storage = new HashMap<String, Resume>();

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public Resume[] getAll() {
        int i = storage.size();
        return storage.values().toArray(new Resume[i]);
    }

    @Override
    protected boolean checkAvailability(String uuid) {
        return storage.containsKey(uuid);
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