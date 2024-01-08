package library;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.time.LocalDate;

// This is a class for a student that can borrow items, get a list of his borrowed items, get his name, get a random borrowed item, and get an item due today

public final class Student extends Fee_block implements User {
    private final String name;
    private final List<LibraryItem> borrowedItems;
    private final boolean returnsOnTime;

    public Student(String name, boolean returnsOnTime) {
        this.name = name;
        this.borrowedItems = new ArrayList<>();
        this.returnsOnTime = returnsOnTime;
    }

    @Override
    public void borrowItem(LibraryItem item) {
        if (item instanceof Film) {
            for (LibraryItem i : borrowedItems) {
                if (i instanceof Film) {
                    //System.out.println(name +" you can't borrow more than one film at the same time");
                    return;
                }
            }
        } else if (item instanceof Book) {
            int count = 0;
            for (LibraryItem i : borrowedItems) {
                if (i instanceof Book) {
                    count++;
                }
            }
            if (count >= 3) {
                //System.out.println(name +" you can't borrow more than three books at the same time");
                return;
            }
        } else if (item instanceof Journal) {
            int count = 0;
            for (LibraryItem i : borrowedItems) {
                if (i instanceof Journal) {
                    count++;
                }
            }
            if (count >= 3) {
                //System.out.println(name +" you can't borrow more than three journals at the same time");
                return;
            }
        }
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

    @Override
    public boolean returnsOnTime() {
        return returnsOnTime;
    }

    @Override
    public boolean Block_borrow(LocalDate today) {
        if (FeeSum(today) > max_fee) {
            System.out.println(name + " you are blocked from borrowing");
            return true;
        }
        return false;
    }
}