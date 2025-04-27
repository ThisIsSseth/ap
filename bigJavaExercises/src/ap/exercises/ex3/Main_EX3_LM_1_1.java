package ap.exercises.ex3;

public class Main_EX3_LM_1_1 {
    public static void main(String[] args) {
        final String title1 = "Big Java", title2 = "Cooking Book", author1 = "Jack Bauer", author2 = "Gordon Ramsey", Publisher1 = "P1" ,
                Publisher2 = "P2", firstName1 = "Naghi", lastName1 = "Mamooli", major1 = "Athletics", firstName2 = "Rahmat", lastName2 = "Hezar Jarib ", major = "Biology";
        final int page1 = 1000, page2 = 786, publishingY1 = 1995, publishingY2 = 2000, id1 = 400100200, id2 = 400100201;

        Book book1 = new Book();
        Book book2 = new Book();
        Student student1 = new Student();
        Student student2 = new Student();

        book1.setTitle(title1);      book1.setAuthor(author1);
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
}
