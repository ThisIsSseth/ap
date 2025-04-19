package ap.exercises.ex3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

class Saver {

    String fileName;
    Path filePath;

    public Saver(String filename) { //C
        fileName = filename;
        filePath = Paths.get(fileName).toAbsolutePath();
        createFile();
    }

    public void writeInto(String content) {
        try {
            if (Files.exists(filePath) && Files.size(filePath) >= 0) {
                try {
                    if (Files.size(filePath) > 1024 * 1024) {
                        System.out.println("File too big!\nEmptying...");
                        Files.writeString(filePath, "");
                    }
                    Files.writeString(filePath, content, StandardOpenOption.APPEND);
                } catch (IOException e) {
                    System.out.println("Saver error1: " + e.getMessage());
                }
            } else {
                this.createFile();
                this.writeInto(content);
            }
//                    System.out.println("File is not empty.\nSaving file content...");
//                    if(backup()){
//                        System.out.println("Backup complete.\nProceeding with data saving...");
//                        writeInto(content);
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

    public List<String> readLinetoList() {
        try {
            List<String> list = List.of(Files.readString(filePath).split("\n"));
            return (list.size() > 0) ? list : null;
        } catch (IOException e) {
            System.out.println("Unable to read file\nSaver error05: " + e.getMessage());
        }
        return null;
    }
}
