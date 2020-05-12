package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

public interface Storage {

    int size();

    void clear();

    Resume[] getAll();

    Resume get(String uuid);

    void update(Resume r);

    void save(Resume r);

    void delete(String uuid);

}
