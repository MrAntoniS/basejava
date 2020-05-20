package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class MapResumeStorage extends AbstractStorage {

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
    protected Resume getKey(String uuid) {
        return new Resume(uuid, "Anyone");
    }

    @Override
    protected boolean checkAvailability(Object key) {
        Resume resume = (Resume) key;
        return storage.containsValue(resume);
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
