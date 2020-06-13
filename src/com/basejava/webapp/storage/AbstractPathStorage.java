package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

public abstract class AbstractPathStorage extends AbstractStorage<Path> {
    private Path directory;

    protected AbstractPathStorage(String dir) {
        directory = Paths.get(dir);
        Objects.requireNonNull(directory, "directory must not be null");
        if (!Files.isDirectory(directory) || !Files.isWritable(directory)) {
            throw new IllegalArgumentException(dir + " is not directory or is not writable");
        }
    }

    @Override
    protected Path getSearchKey(String uuid) {
        return null;
    }

    @Override
    protected void doUpdate(Resume r, Path searchKey) {

    }

    @Override
    protected boolean isExist(Path searchKey) {
        return false;
    }

    @Override
    protected void doSave(Resume r, Path searchKey) {

    }

    @Override
    protected Resume doGet(Path searchKey) {
        return null;
    }

    @Override
    protected void doDelete(Path searchKey) {

    }

    @Override
    protected List<Resume> doCopyAll() {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public void clear() {

    }
}
