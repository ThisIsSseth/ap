package ap.exercises.ex3;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;

public class Main_EX3_LM_1_2 {
    public static void main(String[] args) {
        final String title1 = "Big Java", title2 = "Cooking Book", author1 = "Jack Bauer", author2 = "Gordon Ramsey", Publisher1 = "P1",
                Publisher2 = "P2", firstName1 = "Naghi", lastName1 = "Mamooli", major1 = "Athletics", firstName2 = "Rahmat", lastName2 = "Hezar Jarib ", major = "Biology";
        final int page1 = 1000, page2 = 786, publishingY1 = 1995, publishingY2 = 2000, id1 = 400100200, id2 = 400100201;

        File studentFile = new File("../ap/exercises/ex3/student.txt");
        File bookFile = new File("../ap/exercises/ex3/book.txt");

        Book book1 = new Book();
        Book book2 = new Book();
        Student student1 = new Student();
        Student student2 = new Student();

        book1.setTitle(title1);
        book1.setAuthor(author1);
        book1.setPages(page1);
        book1.setPublisher(Publisher1);
        book1.setPublicationYear(publishingY1);

        book2.setTitle(title2);
        book2.setAuthor(author2);
        book2.setPages(page2);
        book2.setPublisher(Publisher2);
        book2.setPublicationYear(publishingY2);

        student1.setFirstName(firstName1);
        student1.setLastName(lastName1);
        student1.setMajor(major1);
        student1.setId(id1);

        student2.setFirstName(firstName2);
        student2.setLastName(lastName2);
        student2.setMajor(major1);
        student2.setId(id2);

        Student[] studentList = new Student[4]; // B
        Book[] bookList = new Book[3];
        byte i = 0;
        for (; i < studentList.length; i++) {
            studentList[i] = new Student();
            studentList[i].setFirstName("firstName" + i);
            studentList[i].setLastName("lastName" + i);
            studentList[i].setMajor("major" + i);
            studentList[i].setId(((int) i));
        }
        i = 0;
        for (; i < bookList.length; i++) {
            bookList[i] = new Book();
            bookList[i].setTitle("title" + i);
            bookList[i].setAuthor("author" + i);
            bookList[i].setPages(i);
            bookList[i].setPublisher("publisher" + i);
            bookList[i].setPublicationYear(i);
        }
        studentList[0] = student1;
        studentList[1] = student2;
        bookList[0] = book1;
        bookList[1] = book2;

        Saver sSaver = new Saver(studentFile.getName());

        for (Student a : studentList) {
            sSaver.writeInto(a.firstName + ",");
            sSaver.writeInto(a.lastName + ",");
            sSaver.writeInto(a.id + ",");
            sSaver.writeInto(a.major + ",");
            sSaver.writeInto("\n");
        }
        Saver bSaver = new Saver(bookFile.getName());
        for (Book b : bookList) {
            bSaver.writeInto(b.title + ",");
            bSaver.writeInto(b.author + ",");
            bSaver.writeInto(b.publisher + ",");
            bSaver.writeInto(b.pages + ",");
            bSaver.writeInto(b.publicationYear + ",");
            bSaver.writeInto("\n");
        }

    }


    public static class Book {
        private String title;
        private String author;
        private String publisher;
        private int pages;
        private int publicationYear;

        public int getPublicationYear() {
            return publicationYear;
        }

        public int getPages() {
            return pages;
        }

        public String getPublisher() {
            return publisher;
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

        public void setPublisher(String publisher) {
            this.publisher = publisher;
        }

        public void setPages(int pages) {
            this.pages = pages;
        }

        public void setPublicationYear(int publicationYear) {
            this.publicationYear = publicationYear;
        }

    }

    public static class Student {
        private String firstName;
        private String lastName;
        private int id;
        private String major;

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

    private static class Saver {
        String fileName = new String();
        Path filePath;

        public Saver(String filename) { //C
            fileName = filename;
            filePath = Paths.get(fileName).toAbsolutePath();
            createFile();
        }

        public void writeInto(String content) {
                if (true) {
                    try {
                        Files.writeString(filePath, content, StandardOpenOption.APPEND);
                    } catch (IOException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                }
                else {
                    try {
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

        private boolean nullFileCheck() {
            if (Files.exists(filePath)) {
                try {
                    String content = Files.readString(filePath);
                    return content.isEmpty();
                } catch (Exception e) {
                    return false;
                }
            } else {
                createFile();
                return true;
            }
        }
    }

    public static Student studentStringToObj(String line) {
        Student temp = new Student();
        for (String s : line.split(",")) {
            temp.setFirstName(s);
            temp.setLastName(s);
            temp.setId(Integer.parseInt(s));
            temp.setMajor(s);
        }
        return temp;
    }

}


