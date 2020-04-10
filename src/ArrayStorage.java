import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    private int size;

    void clear() {
        int i = 0;
        for (i = 0; i < storage.length; i++) {
            if (storage[i] == null) {
                break;
            }
        }
        Arrays.fill(storage, 0, i, null);

    }

    void save(Resume resume) {
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] == null) {
                storage[i] = resume;
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
        for (Resume resume : storage) {
            if (resume.uuid.equals(uuid)) {
                resume.uuid = "deleted";
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
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] == null) {
                break;
            }
            size = i + 1;
        }
        return size;
    }
}
