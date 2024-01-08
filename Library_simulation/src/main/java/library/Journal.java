package library;

import java.time.LocalDate;

// This class is a subclass of LibraryItem resembling a journal in a library system

public final class Journal extends LibraryItem {
    private final String eISSN;
    private final String publisher;
    private final String latestIssue;
    private final String journalURL;

    public Journal(String id, String eISSN, String publisher, String latestIssue, String journalURL) {
        super(id);
        this.eISSN = eISSN;
        this.publisher = publisher;
        this.latestIssue = latestIssue;
        this.journalURL = journalURL;
        this.isBorrowed = false;
        this.borrowedBy = null;
        this.borrowedOn = null;
        this.dueDate = null;
        this.fine = 2;
    }

    public void calculateDueDate() {
        switch (borrowedBy instanceof Student ? "s" : "t") {
            case "s":
                this.dueDate = borrowedOn.plusDays(3);
                break;
            case "t":
                this.dueDate = borrowedOn.plusDays(7);
                break;
            default:
                this.dueDate = borrowedOn.plusDays(1);
                break;
        }
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
        System.out.println("Journal id: " + id);
        System.out.println("eISSN: " + eISSN);
        System.out.println("Publisher: " + publisher);
        System.out.println("Latest Issue: " + latestIssue);
        System.out.println("Journal URL: " + journalURL);
    }
}

