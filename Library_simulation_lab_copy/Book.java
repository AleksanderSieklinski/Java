package Library_simulation_lab_copy;

import java.time.LocalDate;

//This class is a subclass of LibraryItem resembling a book in a library system


public final class Book extends LibraryItem {
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
        new Object() {
            void calculateDueDate() {
                dueDate = borrowedOn.plusDays(14);
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
                System.out.println("Book id: " + id);
                System.out.println("Title: " + title);
                System.out.println("Author: " + author);
                System.out.println("Genre: " + genre);
                System.out.println("Publisher: " + publisher);
            }
        }.print_details();
    }
}