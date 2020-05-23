package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.Arrays;
import java.util.Comparator;

public class SortedArrayStorage extends AbstractArrayStorage {

    private static final Comparator<Resume> RESUME_COMPARATOR = (o1, o2) -> o1.getUuid().compareTo(o2.getUuid());

    @Override
    protected Integer getKey(String uuid) {
        Resume searchKey = new Resume(uuid, "Anyone");
        return Arrays.binarySearch(storage, 0, size, searchKey, RESUME_COMPARATOR);
    }

    @Override
    protected void saveResume(Resume resume, int index) {
        int saveIndex = -(index) - 1;
        System.arraycopy(storage, saveIndex, storage, saveIndex + 1, size - saveIndex);
        storage[saveIndex] = resume;
    }

    @Override
    protected void deleteResume(int index) {
        if (size - 1 - index >= 0) {
            System.arraycopy(storage, index + 1, storage, index, size - index - 1);
        }
    }
}