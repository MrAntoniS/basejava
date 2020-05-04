package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }

    @Override
    protected void saveResume(Resume resume, int index) {
        int saveIndex = -(index) - 1;
        if (saveIndex != 0) {
            System.arraycopy(storage, saveIndex, storage, saveIndex + 1, size - saveIndex);
        }
        storage[saveIndex] = resume;
    }

    @Override
    protected void deleteResume(int index) {
        if (size - 1 - index >= 0) {
            System.arraycopy(storage, index + 1, storage, index, size - index - 1);
        }
    }
}
