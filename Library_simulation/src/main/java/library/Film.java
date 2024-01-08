package library;

import java.time.LocalDate;

// This class is a subclass of LibraryItem resembling a film in a library system

public final class Film extends LibraryItem {
    private final String title;
    private final String genre;
    private final String director;
    private final int year;
    private final int runtime;
    private final double rating;

    public Film(String id, String title, String genre, String director, int year, int runtime, double rating) {
        super(id);
        this.title = title;
        this.genre = genre;
        this.director = director;
        this.year = year;
        this.runtime = runtime;
        this.rating = rating;
        this.isBorrowed = false;
        this.borrowedBy = null;
        this.borrowedOn = null;
        this.dueDate = null;
        this.fine = 5;
    }

    public void calculateDueDate() {
        this.dueDate = borrowedOn.plusDays(2);
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
        System.out.println("Film id: " + id);
        System.out.println("Title: " + title);
        System.out.println("Genre: " + genre);
        System.out.println("Director: " + director);
        System.out.println("Year: " + year);
        System.out.println("Runtime: " + runtime);
        System.out.println("Rating: " + rating);
    }
}