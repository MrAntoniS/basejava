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
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] == null) {
                storage[i] = resume;
                size++;
                break;
            }
        }
    }

    Resume get(String uuid) {
        int i = 0;
        for (i = 0; i < storage.length; i++) {
            if (storage[i].uuid.equals(uuid)) {
                break;
            } else if (!storage[i].uuid.equals(uuid) || storage[i] == null) {
                return null;
            }
        }
        return storage[i];
    }

    void delete(String uuid) {
        for (int i = 0; i < storage.length; i++) {
            if (storage[i].uuid.equals(uuid)) {
                storage[i].uuid = null;
                if (storage.length - 1 - i >= 0) {
                    System.arraycopy(storage, i + 1, storage, i, storage.length - 1 - i);
                }
                break;
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        int i = 0;
        for (i = 0; i < storage.length; i++) {
            if (storage[i] == null) {
                break;
            }
        }
        return Arrays.copyOf(storage, i);
    }

    int size() {
        return size;
    }
}
