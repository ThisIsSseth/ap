package ap.exercises.MidTermLib.storage;

import java.util.List;

public interface FileHandler<T> {


    void save(List<T> data);

    List<T> load();
}


