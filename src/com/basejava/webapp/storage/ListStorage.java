package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {

    private List<Resume> storage = new ArrayList<>();

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
        return storage.toArray(new Resume[i]);
    }

    @Override
    protected boolean checkAvailability(String uuid) {
        return getIndex(uuid) >= 0;
    }

    @Override
    protected Resume runGet(String uuid) {
        return storage.get(getIndex(uuid));
    }

    @Override
    protected void runUpdate(Resume resume) {
        storage.set(getIndex(resume.getUuid()), resume);
    }

    @Override
    protected void runSave(Resume resume) {
        storage.add(resume);
    }

    @Override
    protected void runDelete(String uuid) {
        storage.remove(getIndex(uuid));
    }

    protected int getIndex(String uuid) {
        Resume resume = new Resume(uuid);
        return storage.indexOf(resume);
    }
}