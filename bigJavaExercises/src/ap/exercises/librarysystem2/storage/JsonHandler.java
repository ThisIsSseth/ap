package ap.exercises.librarysystem2.storage;

import ap.exercises.librarysystem2.model.Book;
import ap.exercises.librarysystem2.model.Library;
import ap.exercises.librarysystem2.utils.DefaultLibraryCreator;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class JsonHandler<T> implements FileHandler<T>{

    private final Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
            .setPrettyPrinting()
            .create();

    /** It's for one specific data type*/
    public JsonHandler() {    }

    @Override
    public void save(T data, File file, Type type) {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(file, false))) {
            gson.toJson(data, writer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void saveList(List<T> data, File file, Type type) {
        try(Writer writer = new FileWriter(file)){
            gson.toJson(data, writer);
        }catch (IOException e){
            System.out.println("Error while saving data: " + e.getMessage());
        }
    }

    @Override
    public List<T> loadList(File file, Type type) {
        if (!file.exists() || file.length() == 0) {
            return new ArrayList<T>();
        }

        try (Reader reader = new FileReader(file)) {
            return gson.fromJson(reader, type);
        } catch (IOException e) {
            throw new RuntimeException("Failed to read JSON from file: " + file.getPath(), e);
        }
    }

    public T load(File file, Type type){
         T obj = null;
        try(BufferedReader reader = new BufferedReader(new FileReader(file))){
            obj = gson.fromJson(reader, type);
        }catch (IOException e){
            throw new RuntimeException("Failed to read JSON from file: " + file.getPath(), e);
        }
        return obj;
    }


public static void main(String[] args) {
    Library library = DefaultLibraryCreator.create();
    JsonHandler<Library> jsonHandler = new JsonHandler<Library>();
    File file = new File("test.json");
    jsonHandler.save(library, file, Library.class);
    Library recievedLibrary = jsonHandler.load(file, Library.class);
    System.out.println(recievedLibrary.getManager().toString());
}
}