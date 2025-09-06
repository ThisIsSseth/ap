package ap.exercises.librarysystem2.storage;

import java.io.File;
import java.lang.reflect.Type;
import java.util.List;

public class SqliteHandler<T> implements FileHandler<T>{

    public SqliteHandler(Class<T> clazz) {

    }

    @Override
    public void save(T t, File file, Type type) {
        //save a single object
    }

    @Override
    public void saveList(List<T> data, File file, Type type) {
        //save list
    }

    @Override
    public List<T> loadList(File file, Type type) {
        return List.of();
        //must change ^
    }
    public T load(File file, Type type) {
        //single obj
        return null;
    }
}
