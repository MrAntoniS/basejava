package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.ArrayList;

public class ListStorage extends AbstractStorage {

    ArrayList<Resume> storage = new ArrayList<>();

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
    protected Resume implementGet(int index) {
        return storage.get(index);
    }

    @Override
    protected void implementUpdate(int index, Resume resume) {
        storage.set(index, resume);
    }

    @Override
    protected void implementSave(int index, Resume resume) {
        storage.add(resume);
    }

    @Override
    protected void implementDelete(int index) {
        storage.remove(index);
    }

    @Override
    protected int getIndex(String uuid) {
        Resume resume = new Resume(uuid);
        return storage.indexOf(resume);
    }
}
