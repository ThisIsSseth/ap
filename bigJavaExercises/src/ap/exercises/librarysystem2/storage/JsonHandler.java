package ap.exercises.librarysystem2.storage;

import ap.exercises.librarysystem2.model.Book;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class JsonHandler<T> implements FileHandler<T>{

    private final Gson gson = new Gson();

    /** It's for one specific data type*/
    public JsonHandler() {    }

    @Override
    public void save(T data, File file, Type type) {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
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


    public static void
     main(String[] args) {
        Book book = new Book("bookName", "Author", 150, 1999, 5);
        JsonHandler<Book> jsonHandler = new JsonHandler();

//        System.out.println(book.getTitle());
//        System.out.println(book.getAuthor());
//
//        jsonHandler.save(book, new File("book.json"), Book.class);
//        Book retrievedBook = jsonHandler.load(new File("book.json"), Book.class);
//
//        System.out.println(retrievedBook.getTitle());
//        System.out.println(retrievedBook.getAuthor());

        jsonHandler.clean(new File("book.json"));

        List<Book> bookList = new ArrayList<>();
        bookList.add(book);
        bookList.add(new Book("bk", "ATH", 120, 1800, 0));
//        jsonHandler.saveList(bookList, new File("bookList.json"), Book.class); See this is where I went wrong
        jsonHandler.saveList(bookList, new File("bookList.json"), new TypeToken<List<Book>>() {}.getType());
        List<Book> retrievedBookList = jsonHandler.loadList(new File("bookList.json"), new TypeToken <List<Book>>() {}.getType());

        for (Book b : retrievedBookList) {
            System.out.println(b.getTitle());
            System.out.println(b.getAuthor());
        }
    }
}