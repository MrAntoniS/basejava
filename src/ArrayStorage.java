import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    private int size;

    void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    void save(Resume resume) {
        storage[size] = resume;
        size++;
    }

    Resume get(String uuid) {
        int i = 0;
        for (i = 0; i < size; i++) {
            if (storage[i].uuid.equals(uuid)) {
                break;
            } else if (!storage[i].uuid.equals(uuid) || storage[i] == null) {
                return null;
            }
        }
        return storage[i];
    }

    void delete(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equals(uuid)) {
                storage[i].uuid = null;
                if (size - 1 - i >= 0) {
                    System.arraycopy(storage, i + 1, storage, i, size - i);
                }
                size--;
                break;
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    int size() {
        return size;
    }
}