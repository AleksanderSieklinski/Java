package Library_simulation_lab_copy;

import java.time.LocalDate;

// This class is a subclass of LibraryItem resembling a journal in a library system

public final class Journal extends LibraryItem {
    private String eISSN;
    private String publisher;
    private String latestIssue;
    private String journalURL;

    public Journal(String id, String eISSN, String publisher, String latestIssue, String journalURL) {
        this.id = id;
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
        new Object() {
            void calculateDueDate() {
                switch (borrowedBy instanceof Student ? "s" : "t") {
                    case "s":
                        dueDate = borrowedOn.plusDays(3);
                        break;
                    case "t":
                        dueDate = borrowedOn.plusDays(7);
                        break;
                    default:
                        dueDate = borrowedOn.plusDays(1);
                        break;
                }
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
                System.out.println("Journal id: " + id);
                System.out.println("eISSN: " + eISSN);
                System.out.println("Publisher: " + publisher);
                System.out.println("Latest Issue: " + latestIssue);
                System.out.println("Journal URL: " + journalURL);
            }
        }.print_details();
    }
}