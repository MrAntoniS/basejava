package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ListStorage extends AbstractStorage {

    private List<Resume> storage = new ArrayList<>();

    private static final Comparator<Resume> RESUME_COMPARATOR = new Comparator<Resume>() {
        @Override
        public int compare(Resume resume, Resume storageResume) {
            if (resume.getUuid().equals(storageResume.getUuid()) && resume.getFullName().equals(storageResume.getFullName())) {
                return 0;
            }
            return resume.getUuid().compareTo(storageResume.getUuid());
        }
    };

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
        storage.sort(RESUME_COMPARATOR);
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
    protected boolean checkAvailability(Object key) {
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