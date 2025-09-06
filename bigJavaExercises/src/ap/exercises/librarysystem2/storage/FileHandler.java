package ap.exercises.librarysystem2.storage;

import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.List;

public interface FileHandler<T> {


    void save (T t, File file, Type type);
    void saveList(List<T> data, File file, Type type);

    List<T> loadList(File file, Type type);
    T load(File file, Type type);

    default void clean(File file){
        try(FileWriter writer = new FileWriter(file)){
            writer.write("");
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}


