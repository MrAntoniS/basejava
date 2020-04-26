package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    protected void arraySort(Resume resume) {
        int index = Arrays.binarySearch(storage, 0, size, resume);
        int i = - (index) - 1;
        if(i != 0) {
            System.arraycopy(storage, i, storage, i + 1, size - i);
        }
        storage[i] = resume;
        size++;
    }

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}
