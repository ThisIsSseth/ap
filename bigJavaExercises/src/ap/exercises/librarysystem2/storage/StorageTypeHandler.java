package ap.exercises.librarysystem2.storage;

import ap.exercises.librarysystem2.model.Book;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Scanner;

@SuppressWarnings("ALL")
public class StorageTypeHandler {

    public static <T> FileHandler<T> create(Class<T> clazz, String path) {
        try (Scanner scanner = new Scanner(new File("config.txt"))) {
            String line = scanner.nextLine();
            if (line == null) throw new RuntimeException("Empty config");
            String[] parts = line.split("=");
            if (parts.length != 2 || !parts[0].equals("storage")) throw new RuntimeException("Invalid config");

            return switch (parts[1].trim().toLowerCase()) {
                case "sqlite" ->  new SqliteHandler<>(clazz);
                case "jason" ->  new JsonHandler<>();
                default -> new TabSplitHandler<>(clazz);
            };

        } catch (Exception e) {
            System.out.println("failed at reading config.txt");
        }
        return new TabSplitHandler<>(clazz);
    }
}
