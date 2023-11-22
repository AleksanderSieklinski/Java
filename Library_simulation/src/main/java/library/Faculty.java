package library;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.time.LocalDate;

// This is a class for a Faculty member
// A faculty member can borrow items, get a list of his borrowed items, get his name, get a random borrowed item, and get an item due today

public class Faculty implements User {
    private String name;
    private List<LibraryItem> borrowedItems;

    public Faculty(String name) {
        this.name = name;
        this.borrowedItems = new ArrayList<>();
    }

    @Override
    public void borrowItem(LibraryItem item) {
        // No limit for faculty
        borrowedItems.add(item);
    }

    @Override
    public List<LibraryItem> getBorrowedItems() {
        return new ArrayList<>(borrowedItems);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public LibraryItem getRandomBorrowedItem() {
        Random rand = new Random();
        return borrowedItems.get(rand.nextInt(borrowedItems.size()));
    }

    @Override
    public LibraryItem getItemDueToday(LocalDate today) {
        for (LibraryItem item : borrowedItems) {
            if (item.dueDate != null && item.dueDate.equals(today)) {
                return item;
            }
        }
        return null;
    }
}