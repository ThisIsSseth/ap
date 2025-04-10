package ap.exercises.ex3;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;

public class Main_EX3_LM_1_3 {
    static List<Integer> studentId = new ArrayList<>();

    public static void main(String[] args) {
        final String title1 = "Big Java", title2 = "Cooking Book", author1 = "Jack Bauer", author2 = "Gordon Ramsey", firstName1 = "Naghi", lastName1 = "Mamooli", major1 = "Athletics", firstName2 = "Rahmat", lastName2 = "Hezar Jarib ", major2 = "Biology";
        final int page1 = 1000, page2 = 786, publishingY1 = 1995, publishingY2 = 2000, id1 = 400100200, id2 = 400100201;

        File studentFile = new File("../ap/exercises/ex3/student.txt");
        File bookFile = new File("../ap/exercises/ex3/book.txt");

        Book book1 = new Book(title1, author1, page1, publishingY1);
        Book book2 = new Book(title2, author2, page2, publishingY2);
        Student student1 = new Student(firstName1, lastName1, id1, major1);
        Student student2 = new Student(firstName2, lastName2, id2, major2);

        Student[] studentArr = new Student[4];
        Book[] bookArr = new Book[3];

        byte i = 0;
        Random rande = new Random();
        List<String> temp;
        Scanner sc = new Scanner(System.in);
        boolean lilFlag;

        for (; i < studentArr.length; i++) {
            studentArr[i] = new Student("firstName" + i, "lastName" + i, IdSet(100000000 + rande.nextInt(300000000)), "major" + i);
        }
        for (i = 0; i < bookArr.length; i++) {
            bookArr[i] = new Book("title" + i, "author" + i, rande.nextInt(305) + 1, 1990 + rande.nextInt(36));
        }
        studentArr[0] = student1;
        studentId.add(studentArr[0].getId());
        studentArr[1] = student2;
        studentId.add(studentArr[1].getId());
        bookArr[0] = book1;
        bookArr[1] = book2;

        System.out.println("Showing info saved so far...\n Students: ");
        for (Student student : studentArr) {
            System.out.println("Name: " + student.getFirstName() + " " + student.getLastName() + " ID: " + student.getId() +
                    " Major: " + student.getMajor());
        }
        System.out.println("Books: ");
        for (Book book : bookArr) {
            System.out.println("Title: " + book.getTitle()
                    + " Author: " + book.getAuthor()
                    + " Pages: " + book.getPages()
                    + " Publication Year: " + book.getPublicationYear());
        }

        System.out.println("Trying to Save the data to files...");
        Saver sSaver = new Saver(studentFile.getName());
        for (Student a : studentArr) {
            sSaver.writeInto(a.getFirstName() + ",");
            sSaver.writeInto(a.getLastName() + ",");
            sSaver.writeInto(a.getId() + ",");
            sSaver.writeInto(a.getMajor() + ",");
            sSaver.writeInto("\n");
        }
        Saver bSaver = new Saver(bookFile.getName());
        for (Book b : bookArr) {
            bSaver.writeInto(b.getTitle() + ",");
            bSaver.writeInto(b.getAuthor() + ",");
            bSaver.writeInto(b.getPages() + ",");
            bSaver.writeInto(b.getPublicationYear() + ",");
            bSaver.writeInto("\n");
        }

        System.out.println("Now reading the data...");
        temp = sSaver.readLinetoList();
        /*for (i = 0; i < Math.min(temp.size(), studentArr.length); i++) {
            studentArr[i] = studentStringToObj(temp.get(i));
        }
        temp = bSaver.readLinetoList();
        for (i = 0; i < (Math.min(temp.size(), bookArr.length)) ; i++) {
            bookArr[i] = bookStringToObj(temp.get(i));
        }*/
        // Let's do above but backward
        for (i = 1; 0 < studentArr.length - i ; i++) {
            studentArr[studentArr.length - i] = studentStringToObj(temp.get(temp.size() - i));
        }
        temp = bSaver.readLinetoList();
        for (i = 1; 0 < studentArr.length - i ; i++) {
            bookArr[bookArr.length - i] = bookStringToObj(temp.get(temp.size() - i));
        }
        System.out.println("Done!");

        System.out.println("Showing info saved so far...\n Students: ");
        for (Student student : studentArr) {
            System.out.println("Name: " + student.getFirstName() + " " + student.getLastName() + " ID: " + student.getId() +
                    " Major: " + student.getMajor());
        }
        System.out.println(" Books: ");
        for (Book book : bookArr) {
            System.out.println("Title: " + book.getTitle()
                    + " Author: " + book.getAuthor()
                    + " Pages: " + book.getPages()
                    + " Publication Year: " + book.getPublicationYear());
        }

         i = 1;
        String name;
        while (i != 0) {
            System.out.println("Type a name of student to search for: ");
            name = sc.nextLine();
            if (searchByName(name, studentArr) > 0) {
                System.out.println("Student found!\nID: " + searchByName(name, studentArr));
            } else {
                System.out.println("Student not found!");
            }
            System.out.println("0. Exit\n1. Search another name");
            lilFlag = false;
            while (!lilFlag) {
                try {
                    i = sc.nextByte();
                    sc.nextLine();
                    lilFlag = true;
                } catch (InputMismatchException e) {
                    System.out.println("Please enter a valid option!");
                }
            }
        }
    }


    static Student studentStringToObj(String line) {
        String[] s = line.split(",");
        return new Student(s[0], s[1], Integer.parseInt(s[2]), s[3]);
    }
    static Book bookStringToObj(String line) {
        String[] s = line.split(",");
        return new Book(s[0], s[1], Integer.parseInt(s[2]), Integer.parseInt(s[3]));
    }

    static boolean searchByID(int ID) {
        for (Integer id : studentId) {
            if (id == ID) return true;
        }
        return false;
    }

    static int searchByName(String firstName, Student[] stdArr){
        for (Student a : stdArr){
            if (a.getFirstName().equals(firstName)){
                return a.getId();
            }
        }
        return -1;
    }

    static int IdSet(int id) {
        Random rande = new Random();
        if (searchByID(id))
            id = IdSet(100000000 + rande.nextInt(300000000));
        return id;
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
            studentId.add(id);
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
                if (Files.exists(filePath) && Files.size(filePath) >= 0) {
                    try {
                        if (Files.size(filePath) > 1024 * 1024) {
                            System.out.println("File too big!\nEmptying...");
                            Files.writeString(filePath, "");
                        }
                        Files.writeString(filePath, content, StandardOpenOption.APPEND);
                    } catch (IOException e) {
                        System.out.println("Error: " + e.getMessage());
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
                System.out.println("Error: " + ex.getMessage());
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

        public List<String> readLinetoList() {
            try {
                return List.of(Files.readString(filePath).split("\n"));
            } catch (IOException e) {
                System.out.println("Unable to read file\nError: " + e.getMessage());
            }
            return null;
        }
    }
}

