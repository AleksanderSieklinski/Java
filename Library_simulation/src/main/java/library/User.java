package library;

import java.time.LocalDate;
import java.util.List;

// This is an interface for a user of the library
// A user can borrow items, get a list of his borrowed items, get his name, get a random borrowed item, and get an item due today
// A user can also check if he returns items on time and see his borrowed items as well as the sum of his fines

public interface User {
    void borrowItem(LibraryItem item);
    List<LibraryItem> getBorrowedItems();
    String getName();
    LibraryItem getRandomBorrowedItem();
    LibraryItem getItemDueToday(LocalDate today);
    boolean returnsOnTime();
    default void seeBorrowedItems(String name, List<LibraryItem> borrowedItems) {
        System.out.println();
        System.out.println(name + " has borrowed:");
        for (LibraryItem item : borrowedItems) {
            item.print_details();
        }
        System.out.println();
    }
    default double FeeSum(LocalDate today) {
        double sum = 0;
        for (LibraryItem item : getBorrowedItems()) {
            sum += item.computeFine(today);
        }
        return sum;
    }
}
