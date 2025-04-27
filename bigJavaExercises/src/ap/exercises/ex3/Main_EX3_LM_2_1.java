package ap.exercises.ex3;

import java.io.File;
import java.util.*;

public class Main_EX3_LM_2_1 {

    static List<Integer> studentId = new ArrayList<>();
    static Student[] studentArr = new Student[10];
    static Book[] bookArr = new Book[10];
    static File studentFile = new File("../ap/exercises/ex3/student1.txt");
    static File bookFile = new File("../ap/exercises/ex3/book1.txt");

    public static void main(String[] args) {
        initializing();
        while (true) {
            menu();
        }
    }

    private static void initializing() {
        System.out.println("Initializing...");

        final String title1 = "Big Java";
        final String author1 = "Jack Bauer";
        final int page1 = 1000;
        final int publishingY1 = 1995;

        final String title2 = "Cooking Book";
        final String author2 = "Gordon Ramsey";
        final int page2 = 786;
        final int publishingY2 = 2000;

        final String firstName1 = "Naghi";
        final String lastName1 = "Mamooli";
        final int id1 = 400100200;
        final String major1 = "Athletics";

        final String firstName2 = "Rahmat";
        final String lastName2 = "Hezar Jarib";
        final int id2 = 400100201;
        final String major2 = "Biology";

        Book book1 = new Book(title1, author1, page1, publishingY1);
        Book book2 = new Book(title2, author2, page2, publishingY2);
        Student student1 = new Student(firstName1, lastName1, id1, major1);
        Student student2 = new Student(firstName2, lastName2, id2, major2);

        for (byte i = 0; i < studentArr.length; i++) {
            studentArr[i] = new Student("first name" + i, "last name" + i, IdSet(), "major" + i);
        }
        Random random = new Random();
        int randomPage, pYear;
        for (byte i = 0; i < bookArr.length; i++) {
            randomPage = 100 + random.nextInt(200);
            pYear = 1900 + random.nextInt(125);
            bookArr[i] = new Book("Title" + i, "Author" + i, randomPage, pYear);
        }
        studentArr[0] = student1;
        studentArr[1] = student2;
        bookArr[0] = book1;
        bookArr[1] = book2;
    }

    static int IdSet() {
        final int lowerLimit = 100000000;
        final int interval = 1000000000;
        Random random = new Random();
        int id;
        do {
            id = lowerLimit + random.nextInt(interval);
        } while (studentId.contains(id));
        studentId.add(id);
        return id;
    }

    static void menu() {
        Scanner sc = new Scanner(System.in);
        byte option = -1;
        do {
            System.out.println("""
                    Choose an option:
                    1. Print data
                    2. Save data to file
                    3. Read data from file
                    4. Search
                    5. Borrow book
                    6. Re-initialize data
                    0. Exit""");
            try {
                option = sc.nextByte();
                sc.nextLine();
                operation(option);
            } catch (InputMismatchException e) {
                System.out.println("menuError: " + e.getClass().getSimpleName() + "\nInvalid input");
                sc.nextLine();
            } catch (NoSuchElementException e) {
                System.out.println("menuError:" + e.getClass().getSimpleName());
                sc.nextLine();
            }
        } while (option != 0);
    }

    private static void operation(byte option) {
        switch (option) {
            case 1: {
                printData();
                break;
            }
            case 2: {
                file(2);
                break;
            }
            case 3: {
                file(3);
                break;
            }
            case 4: {
                searchStudentByName(true);
                break;
            }
            case 5: {
                lending();
                break;
            }
            case 6: {
                initializing();
                break;
            }
            case 0:
                System.exit(0);
                break;
            default:
                System.out.println("Invalid option");
                break;
        }
    }

    private static void lending() {
        System.out.println("Showing lending status: ");
        for (Book book : bookArr) {
            System.out.printf("%-10s, %-12s%n", book.getTitle(), (book.getBorrower() != 0) ? book.getBorrower() : "not Borrowed");
        }
        System.out.println("if you want to borrow a book, enter your id:\n(to return enter 0)");
        Scanner sc = new Scanner(System.in);
        int id = sc.nextInt();
        sc.nextLine();
        if (id == 0) {
            menu();
        } else {
            borrow(id);
        }
    }

    private static void borrow(int id) {
        Scanner sc = new Scanner(System.in);
        if (searchBookByBorrower(id)) {
            System.out.println("You already borrowed book\nreturn the book to borrow another\nWould you like to return your book?[1.Y/else.N]");
            int answer = sc.nextInt();
            if (answer == 1) {
                for (int i = 0; i < bookArr.length; i++) {
                    if (bookArr[i].getBorrower() == id) {
                        bookArr[i].setBorrower(0);
                    }
                }
            }
        } else if (!searchStudentByID(id)) {
            System.out.println("There is no student with the id " + id);
        }
        else {
            System.out.println("Enter the Title of an unborrowed book:");
            String bookTitle = sc.nextLine();
            if (searchBookByTitle(bookTitle))
                for (int i = 0; i < bookArr.length; i++) {
                    if (bookArr[i].getTitle().equals(bookTitle)) {
                        bookArr[i].setBorrower(id);
                        System.out.println("Book " + bookArr[i].getTitle() + " borrowed by" + bookArr[i].getBorrower());
                    }
                }
            else {
                System.out.println("There is no book with the title " + bookTitle);
            }
        }
    }


    private static void file(int option) {
        Saver studentSaver = new Saver(studentFile.getName());
        Saver bookSaver = new Saver(bookFile.getName());
        switch (option) {
            case 2: {
                saveData(studentSaver, bookSaver);
                break;
            }
            case 3: {
                readData(studentSaver, bookSaver);
                break;
            }
        }
    }

    private static void saveData(Saver studentSaver, Saver bookSaver) {
        System.out.println("Trying to Save the data to files...");
        for (Student a : studentArr) {
            studentSaver.writeInto(a.getFirstName() + ",");
            studentSaver.writeInto(a.getLastName() + ",");
            studentSaver.writeInto(a.getId() + ",");
            studentSaver.writeInto(a.getMajor() + ",");
            studentSaver.writeInto("\n");
        }
        for (Book b : bookArr) {
            bookSaver.writeInto(b.getTitle() + ",");
            bookSaver.writeInto(b.getAuthor() + ",");
            bookSaver.writeInto(b.getPages() + ",");
            bookSaver.writeInto(b.getPublicationYear() + ",");
            bookSaver.writeInto(b.getBorrower() + ",");
            bookSaver.writeInto("\n");
        }
        System.out.println("Done!");
    }

    private static void readData(Saver studentSaver, Saver bookSaver) {
        System.out.println("Reading students data...");
        List<String> temp = studentSaver.readLinetoList();
        for (byte i = 0; i < studentArr.length; i++) {
            studentArr[studentArr.length - 1 - i] = studentStringToObj(temp.get(temp.size() - 1 - i));
        }
        System.out.println("Reading books data...");
        for (byte i = 1; i <= studentArr.length; i++) {
            bookArr[bookArr.length - i] = bookStringToObj(temp.get(temp.size() - i));
        }
        System.out.println("Done!");
    }

    private static void printData() {
        for (Student student : studentArr) {
            System.out.println("Name: " + student.getFirstName() + " " + student.getLastName() + " ID: " + student.getId() + " Major: " + student.getMajor());
        }
        System.out.println("Books: ");
        for (Book book : bookArr) {
            System.out.printf("Title: %-12s" + book.getTitle() + " Author: %-15s" + book.getAuthor() + " Pages: %-3d" + book.getPages() + " Publication Year: %-4d" + book.getPublicationYear() + " Borrower: %-10d" + ((book.getBorrower() != 0) ? book.getBorrower() : "not Borrowed"));
        }
    }

    static Student studentStringToObj(String line) {
        String[] s = line.split(",");
        if (s.length == 4) {
            return new Student(s[0], s[1], Integer.parseInt(s[2]), s[3]);
        } else return new Student("firstName", "lastName", IdSet(), "major");
    }

    static Book bookStringToObj(String line) {
        String[] s = line.split(",");
        if (s.length == 4) {
            Book book = new Book(s[0], s[1], Integer.parseInt(s[2]), Integer.parseInt(s[3]));
            book.setBorrower(Integer.parseInt(s[4]));
            return book;
        } else return new Book("defaultTitle", "default author", 100, 1995);
    }

    static boolean searchBookByBorrower(int Id) {
        for (Book book : bookArr) {
            if (book.getBorrower() == Id)
                return true;
        }
        return false;
    }

    static boolean searchBookByTitle(String Title) {
        for (Book book : bookArr) {
            if (Objects.equals(book.getTitle(), Title))
                return true;
        }
        return false;
    }

    static boolean searchStudentByID(int ID) {
        for (Integer id : studentId) {
            if (id == ID) return true;
        }
        return false;
    }

    static int searchStudentByName(boolean print) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter student first name: ");
        String firstName = sc.nextLine();
        for (Student a : studentArr) {
            if (a.getFirstName().equals(firstName)) {
                if (print) {
                    System.out.println("Found student with ID: " + a.getId());
                }
                return a.getId();
            }
        }
        if (print) System.out.println("StudentKargah not found");
        return -1;
    }
}
