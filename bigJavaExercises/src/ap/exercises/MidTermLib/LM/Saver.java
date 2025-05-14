package ap.exercises.MidTermLib.LM;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class Saver {


    String fileName;
    Path filePath;

    /**
     * @param filename adds .txt on its own
     */
    public Saver(String filename) { //C
        fileName = filename + ".txt";
        filePath = Paths.get(fileName).toAbsolutePath();
        createFile();
    }

    public void writeInto(String content){
        try {
            long fileSize = Files.size(filePath);
            if (Files.exists(filePath) && fileSize >= 0) {
                try {
                    if (fileSize > 1024 * 1024) {
                        System.out.println("File too big! Emptying...");
                        Files.writeString(filePath, "", StandardOpenOption.TRUNCATE_EXISTING);
                    }
                    Files.writeString(filePath, content);
                } catch (IOException e) {
                    System.out.println("Saver error1: " + e.getMessage());
                }
            } else {
                this.createFile();
                this.writeInto(content);
            }
        } catch (IOException ex) {
            System.out.println("Saver error02: " + ex.getMessage());
        }
    }

    public void appendInto(String content) {
        try {
            long fileSize = Files.size(filePath);
            if (Files.exists(filePath) && fileSize >= 0) {
                try {
                    if (fileSize > 1024 * 1024) {
                        System.out.println("File too big! Emptying...");
                        Files.writeString(filePath, "", StandardOpenOption.TRUNCATE_EXISTING);
                    }
                    Files.writeString(filePath, content, StandardOpenOption.APPEND);
                } catch (IOException e) {
                    System.out.println("Saver error1: " + e.getMessage());
                }
            } else {
                this.createFile();
                this.appendInto(content);
            }
//                    System.out.println("File is not empty.\nSaving file content...");
//                    if(backup()){
//                        System.out.println("Backup complete.\nProceeding with data saving...");
//                        appendInto(content);
//                    }
        } catch (IOException ex) {
            System.out.println("Saver error02: " + ex.getMessage());
        }

    }


//        private boolean backup(){
//            try {
//                Files.writeString(filePath, "");
//            } catch (IOException e) {
//                System.out.println("Saver error03: " + e.getMessage());
//            }
//            return true;
//        }

    private void createFile() {
        if (!Files.exists(filePath)) {
            try {
                Files.createFile(filePath);
            } catch (Exception e) {
                System.out.println("Saver error04: " + e.getMessage());
            }
        }
    }

    /**
     * Reads line by line from the file and returns a String List of lines, null if the file is empty
     */
    public List<String> readLinetoList() {
        try {
            List<String> list = List.of(Files.readString(filePath).split("\n"));
            return (!list.isEmpty()) ? list : null;
        } catch (IOException e) {
            System.out.println("Unable to read file\nSaver error05: " + e.getMessage());
        }
        return null;
    }

    public List<String> readBySpecificRegex(String regex) {
        try {
            List<String> list = List.of(Files.readString(filePath).split(regex));
            return (!list.isEmpty()) ? list : null;
        } catch (Exception e) {
            System.out.println("Unable to read file\nSaver error06: " + e.getMessage());
        }
        return null;
    }
}
