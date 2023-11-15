package Library_simulation.src.main.java.library;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

// This class is a library system that stores LibraryItems and allows borrowing and returning of items by Persons

public class Library {
    private List<LibraryItem> items;

    public Library() {
        items = new ArrayList<>();
    }

    public void addItem(LibraryItem item) {
        items.add(item);
    }

    public void borrowItem(String id, Person borrower, LocalDate borrowDate) {
        for (LibraryItem item : items) {
            if (item.id.equals(id)) {
                borrower.borrowItem(item);
                if(borrower.is_borrowed(id)){
                    item.isBorrowed = true;
                    item.borrowedBy = borrower;
                    item.borrowedOn = borrowDate;
                    item.calculateDueDate();
                }
                return;
            }
        }
    }

    public void returnItem(String id) {
        for (LibraryItem item : items) {
            if (item.id.equals(id) && item.isBorrowed) {
                item.borrowedBy.returnItem(item);
                item.isBorrowed = false;
                item.borrowedBy = null;
                item.borrowedOn = null;
                item.dueDate = null;
                return;
            }
        }
    }

    public void printOverdueItems(LocalDate today) {
        System.out.println("Overdue items id:");
        for (LibraryItem item : items) {
            if (item.isOverdue(today)) {
                System.out.println(item.id);
            }
        }
    }

    public void printTotalFine(LocalDate today) {
        double totalFine = 0.0;
        System.out.println("Total fine:");
        for (LibraryItem item : items) {
            totalFine += item.computeFine(today);
        }
        System.out.println(totalFine);
    }

    public void printItemInfo(String id, LocalDate today) {
        for (LibraryItem item : items) {
            if (item.id.equals(id) && item.isBorrowed) {
                System.out.println(item.getClass().getName());
                System.out.println("Id: " + item.id);
                System.out.println("Is borrowed: " + item.isBorrowed);
                System.out.println("Borrowed by: " + item.borrowedBy.Get_Name());
                System.out.println("Borrowed on: " + item.borrowedOn);
                System.out.println("Due date: " + item.dueDate);
                if (item.isOverdue(today)) {
                    System.out.println("Fine: " + item.computeFine(today));
                }
                return;
            }
            else if (item.id.equals(id) && !item.isBorrowed) {
                System.out.println(item.getClass().getName());
                System.out.println("Id: " + item.id);
                System.out.println("Is borrowed: " + item.isBorrowed);
                return;
            }
        }
    }

    public int getNumberOfItems() {
        return items.size();
    }

    public int getNumberOfBorrowedItems() {
        int count = 0;
        for (LibraryItem item : items) {
            if (item.isBorrowed) {
                count++;
            }
        }
        return count;
    }

    public LibraryItem getItem(String id) {
        for (LibraryItem item : items) {
            if (item.id.equals(id)) {
                return item;
            }
        }
        return null;
    }
}