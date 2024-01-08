package library;

import java.time.LocalDate;

//This class is a subclass of LibraryItem resembling a book in a library system


public final class Book extends LibraryItem {
    private final String title;
    private final String author;
    private final String genre;
    private final String publisher;
    private int pagesNr = 100;

    public Book(String id, String title, String author, String genre, String publisher) {
        super(id);
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

    // Added public constructor
    public Book(String id, String title, String author) {
        this(id, title, author, "Unknown", "Unknown");
    }

    // Added private constructor
    private Book(String id, String title) {
        this(id, title, "Unknown", "Unknown", "Unknown");
    }

    public int getPagesNr(){return pagesNr;}

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
        return today.isAfter(dueDate);
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
        System.out.println("Number of pages: " + pagesNr);
    }
}