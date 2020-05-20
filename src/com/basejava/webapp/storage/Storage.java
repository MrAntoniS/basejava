package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.List;

public interface Storage {

    int size();

    void clear();

    List<Resume> getAllSorted();

    Resume get(String uuid);

    void update(Resume r);

    void save(Resume r);

    void delete(String uuid);

}
