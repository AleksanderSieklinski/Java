package library;

import java.time.LocalDate;

// This class is an abstract class resembling a library item in a library system
// It is the superclass of Book, Film and Journal

public sealed abstract class LibraryItem permits Book, Film, Journal {
    protected String id;
    protected boolean isBorrowed;
    protected User borrowedBy;
    protected LocalDate borrowedOn;
    protected LocalDate dueDate;
    protected double fine;

    public abstract int daysOverdue(LocalDate today);
    public abstract boolean isOverdue(LocalDate today);
    public abstract double computeFine(LocalDate today);
    public abstract void calculateDueDate();
    public abstract void print_details();
}