package library;

import java.time.LocalDate;
import java.util.List;

// This is an interface for a user of the library
// A user can borrow items, get a list of his borrowed items, get his name, get a random borrowed item, and get an item due today

public interface User {
    void borrowItem(LibraryItem item);
    List<LibraryItem> getBorrowedItems();
    String getName();
    LibraryItem getRandomBorrowedItem();
    LibraryItem getItemDueToday(LocalDate today);
    boolean returnsOnTime();
}
