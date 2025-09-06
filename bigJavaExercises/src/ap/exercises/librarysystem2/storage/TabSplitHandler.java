package ap.exercises.librarysystem2.storage;

import java.io.File;
import java.lang.reflect.Type;
import java.util.List;

public class TabSplitHandler<T> implements FileHandler<T>{


    public <T> TabSplitHandler(Class<T> clazz) {

    }

    @Override
    public void save(T t, File file, Type type) {

    }

    @Override
    public void saveList(List<T> data, File file, Type type) {

    }

    @Override
    public List<T> loadList(File file, Type type) {
        //loads list
        return null;
    }

    @Override
    public T load(File file, Type type) {
        return null;
    }
}
