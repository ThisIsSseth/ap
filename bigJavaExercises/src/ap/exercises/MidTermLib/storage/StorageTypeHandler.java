package ap.exercises.MidTermLib.storage;

import java.io.File;
import java.util.Scanner;

public class StorageTypeHandler {



    public static <T> FileHandler<T> create(Class<T> clazz, String path) {
        try (Scanner scanner = new Scanner(new File("config.txt"))) {
            String line = scanner.nextLine();
            if (line == null) throw new RuntimeException("Empty config");
            String[] parts = line.split("=");
            if (parts.length != 2 || !parts[0].equals("storage")) throw new RuntimeException("Invalid config");

            return switch (parts[1].trim().toLowerCase()) {
                case "sqlite" ->  new SqliteHandler<>(path, clazz);
                case "jason" ->  new JsonHandler<>(path, clazz);
                default -> new TabSplitHandler<>(path, clazz);
            };

        } catch (Exception e) {
            System.out.println("failed at reading config.txt");
        }
        return new TabSplitHandler<>(path, clazz);
    }
}
