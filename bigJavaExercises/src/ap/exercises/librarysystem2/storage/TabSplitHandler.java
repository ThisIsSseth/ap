package ap.exercises.librarysystem2.storage;

import java.util.List;

public class TabSplitHandler<T> implements FileHandler<T>{


    public <T> TabSplitHandler(String path, Class<T> clazz) {

    }

    @Override
    public void save(List<T> data) {

    }

    @Override
    public List<T> load() {
        return List.of();
    }
}
