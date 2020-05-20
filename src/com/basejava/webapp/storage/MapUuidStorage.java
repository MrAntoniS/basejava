package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.*;

public class MapUuidStorage extends AbstractStorage {

    private TreeMap<String, Resume> storage = new TreeMap<>();

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public List<Resume> getAllSorted() {
        return (List<Resume>) new ArrayList(storage.values());
    }

    @Override
    protected String getKey(String uuid) {
        return uuid;
    }

    @Override
    protected boolean checkAvailability(Object key) {
        String uuid = (String) key;
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
