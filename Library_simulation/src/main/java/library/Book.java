package Library_simulation.src.main.java.library;

import java.time.LocalDate;

//This class is a subclass of LibraryItem resembling a book in a library system


public class Book extends LibraryItem {
    private String title;
    private String author;
    private String genre;
    private String publisher;

    public Book(String id, String title, String author, String genre, String publisher) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.publisher = publisher;
        this.isBorrowed = false;
        this.borrowedBy = null;
        this.borrowedOn = null;
        this.dueDate = null;
        this.fine = 0.5;
    }

    public void calculateDueDate() {
        this.dueDate = borrowedOn.plusDays(14);
    }

    public int daysOverdue(LocalDate today) {
        if (borrowedOn == null) {
            return 0;
        }
        return -dueDate.compareTo(today);
    }

    public boolean isOverdue(LocalDate today) {
        if (borrowedOn == null) {
            return false;
        }
        return today.compareTo(dueDate) > 0;
    }

    public double computeFine(LocalDate today) {
        if (borrowedOn == null) {
            return 0.0;
        }
        if (daysOverdue(today) < 0) {
            return 0.0;
        }
        return daysOverdue(today) * fine;
    }

    public void print_details(){
        System.out.println("Book id: " + id);
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
        System.out.println("Genre: " + genre);
        System.out.println("Publisher: " + publisher);
    }
}