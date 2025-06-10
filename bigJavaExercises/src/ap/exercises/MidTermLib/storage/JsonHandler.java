package ap.exercises.MidTermLib.storage;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class JsonHandler<T> implements FileHandler<T> {

    private final Gson gson = new Gson();
    private final File file;
    private final Type type;

    public JsonHandler(String filePath, Class<T> clazz) {
        this.file = new File(filePath);
        this.type = TypeToken.getParameterized(List.class, clazz).getType();
    }

    @Override
    public void save(List<T> data){
        try(Writer writer = new FileWriter(file)){
            gson.toJson(data,writer);
        }catch (IOException e){
            System.out.println("Error while saving data: " + e.getMessage());
        }
    }

    @Override
    public List<T> load() {
        if (!file.exists() || file.length() == 0) {
            return new ArrayList<>();
        }

        try (Reader reader = new FileReader(file)) {
            return gson.fromJson(reader, type);
        } catch (IOException e) {
            throw new RuntimeException("Failed to read JSON from file: " + file.getPath(), e);
        }
    }
}
