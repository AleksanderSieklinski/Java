package Library_simulation_lab_copy;

import java.time.LocalDate;

// This class is a subclass of LibraryItem resembling a film in a library system

public final class Film extends LibraryItem {
    private String title;
    private String genre;
    private String director;
    private int year;
    private int runtime;
    private double rating;

    public Film(String id, String title, String genre, String director, int year, int runtime, double rating) {
        this.id = id;
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
        new Object() {
            void calculateDueDate() {
                dueDate = borrowedOn.plusDays(2);
            }
        }.calculateDueDate();
    }

    public int daysOverdue(LocalDate today) {
        return new Object() {
            int daysOverdue() {
                if (borrowedOn == null) {
                    return 0;
                }
                return -dueDate.compareTo(today);
            }
        }.daysOverdue();
    }

    public boolean isOverdue(LocalDate today) {
        return new Object() {
            boolean isOverdue() {
                if (borrowedOn == null) {
                    return false;
                }
                return today.compareTo(dueDate) > 0;
            }
        }.isOverdue();
    }

    public double computeFine(LocalDate today) {
        return new Object() {
            double computeFine() {
                if (borrowedOn == null) {
                    return 0.0;
                }
                if (daysOverdue(today) < 0) {
                    return 0.0;
                }
                return daysOverdue(today) * fine;
            }
        }.computeFine();
    }

    public void print_details(){
        new Object() {
            void print_details() {
                System.out.println("Film id: " + id);
                System.out.println("Title: " + title);
                System.out.println("Genre: " + genre);
                System.out.println("Director: " + director);
                System.out.println("Year: " + year);
                System.out.println("Runtime: " + runtime);
                System.out.println("Rating: " + rating);
            }
        }.print_details();
    }
}