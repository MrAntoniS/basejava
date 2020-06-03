package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage<Integer> {

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
    public List<Resume> getStorage() {
        return storage;
    }

    @Override
    protected Integer getKey(String uuid) {
        for (int i = 0; i < storage.size(); i++) {
            if (storage.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return null;
    }

    @Override
    protected boolean checkAvailability(Integer key) {
        return key != null;
    }

    @Override
    protected Resume runGet(String uuid) {
        return storage.get(getKey(uuid));
    }

    @Override
    protected void runUpdate(Resume resume) {
        storage.set(getKey(resume.getUuid()), resume);
    }

    @Override
    protected void runSave(Resume resume) {
        storage.add(resume);
    }

    @Override
    protected void runDelete(String uuid) {
        int index = getKey(uuid);
        storage.remove(index);
    }
}