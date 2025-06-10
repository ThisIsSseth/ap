package ap.exercises.librarysystem2.storage;

import java.util.List;

public class SqliteHandler<T> implements FileHandler<T>{

    public <T> SqliteHandler(String path, Class<T> clazz) {

    }

    @Override
    public void save(List<T> data) {

    }

    @Override
    public List<T> load() {
        return List.of();
    }
}
