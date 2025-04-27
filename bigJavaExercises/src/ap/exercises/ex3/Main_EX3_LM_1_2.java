package ap.exercises.ex3;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Random;

public class Main_EX3_LM_1_2 {
    public static void main(String[] args) {
        final String title1 = "Big Java", title2 = "Cooking Book", author1 = "Jack Bauer", author2 = "Gordon Ramsey", Publisher1 = "P1",
                Publisher2 = "P2", firstName1 = "Naghi", lastName1 = "Mamooli", major1 = "Athletics", firstName2 = "Rahmat", lastName2 = "Hezar Jarib ", major2 = "Biology";
        final int page1 = 1000, page2 = 786, publishingY1 = 1995, publishingY2 = 2000, id1 = 400100200, id2 = 400100201;

        File studentFile = new File("../ap/exercises/ex3/student.txt");
        File bookFile = new File("../ap/exercises/ex3/book.txt");

        Book book1 = new Book(title1,author1,page1,publishingY1);
        Book book2 = new Book(title2,author2,page2,publishingY2);
        Student student1 = new Student(firstName1, lastName1, id1, major1);
        Student student2 = new Student(firstName2,lastName2,id2, major2);

        Student[] studentList = new Student[4]; // B
        Book[] bookList = new Book[3];
        Random rande = new Random();
        byte i = 0;
        for (; i < studentList.length; i++) {
            studentList[i] = new Student("firstName" + i ,"lastName" + i,100000000 + rande.nextInt(300000000) , "major" + i  );

        }
        i = 0;
        for (; i < bookList.length; i++) {
            bookList[i] = new Book("title" + i,"author" + i, rande.nextInt(305) + 1, 1990 + rande.nextInt(36));
        }
        studentList[0] = student1;
        studentList[1] = student2;
        bookList[0] = book1;
        bookList[1] = book2;

        Saver sSaver = new Saver(studentFile.getName());
        for (Student a : studentList) {
            sSaver.writeInto(a.getFirstName() + ",");
            sSaver.writeInto(a.getLastName() + ",");
            sSaver.writeInto(a.getId() + ",");
            sSaver.writeInto(a.getMajor() + ",");
            sSaver.writeInto("\n");
        }
        Saver bSaver = new Saver(bookFile.getName());
        for (Book b : bookList) {
            bSaver.writeInto(b.getTitle() + ",");
            bSaver.writeInto(b.getAuthor() + ",");
            bSaver.writeInto(b.getPages() + ",");
            bSaver.writeInto(b.getPublicationYear() + ",");
            bSaver.writeInto("\n");
        }

    }



    static Student studentStringToObj(String line) {
        String[] s = line.split(",");
        return new Student(s[0], s[1], Integer.parseInt(s[2]), s[3]);
    }


    static class Book {

        private String title;
        private String author;
        private int pages;
        private int publicationYear;
        public Book(String title, String author, int pages, int publicationYear) {
            this.title = title;
            this.author = author;
            this.pages = pages;
            this.publicationYear = publicationYear;
        }

        public int getPublicationYear() {
            return publicationYear;
        }

        public int getPages() {
            return pages;
        }

        public String getAuthor() {
            return author;
        }

        public String getTitle() {
            return title;
        }


        public void setTitle(String title) {
            this.title = title;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public void setPages(int pages) {
            this.pages = pages;
        }

        public void setPublicationYear(int publicationYear) {
            this.publicationYear = publicationYear;
        }


    }

    static class Student {

        private String firstName;
        private String lastName;
        private int id;
        private String major;
        public Student(String firstName, String lastName, int id, String major) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.id = id;
            this.major = major;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }


        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getMajor() {
            return major;
        }

        public void setMajor(String major) {
            this.major = major;
        }


    }

    static class Saver {
        String fileName;

        Path filePath;
        public Saver(String filename) { //C
            fileName = filename;
            filePath = Paths.get(fileName).toAbsolutePath();
            createFile();
        }
        public void writeInto(String content) {
            try {
                if (Files.exists(filePath) && Files.size(filePath) > 0 ) {
                    try {
                        Files.writeString(filePath, content, StandardOpenOption.APPEND);
                    } catch (IOException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                }
                else {
                    try {
                        createFile();
                        Files.writeString(filePath, "");
                    } catch (IOException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
//                    System.out.println("File is not empty.\nSaving file content...");
//                    if(backup()){
//                        System.out.println("Backup complete.\nProceeding with data saving...");
//                        writeInto(content);
//                    }

                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }


//        private boolean backup(){
//            try {
//                Files.writeString(filePath, "");
//            } catch (IOException e) {
//                System.out.println("Error: " + e.getMessage());
//            }
//            return true;
//        }

        private void createFile() {
            if (!Files.exists(filePath)) {
                try {
                    Files.createFile(filePath);
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
        }


    }
}

