package com.basejava.webapp.storage;

import com.basejava.webapp.exception.StorageException;
import com.basejava.webapp.model.Resume;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
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
    protected List<Resume> getStorage() {

        return null;
    }

    @Override
    protected Path getKey(String uuid) {
        return null;
    }

    @Override
    protected boolean checkAvailability(Path key) {
        return false;
    }

    @Override
    protected Resume runGet(String uuid) {
        return null;
    }

    @Override
    protected void runUpdate(Resume resume) {

    }

    @Override
    protected void runSave(Resume resume) {

    }

    @Override
    protected void runDelete(String uuid) {

    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public void clear() {

    }
}
