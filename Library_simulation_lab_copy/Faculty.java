package Library_simulation_lab_copy;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.time.LocalDate;

// This is a class for a Faculty member
// A faculty member can borrow items, get a list of his borrowed items, get his name, get a random borrowed item, and get an item due today

public final class Faculty extends Fee_block implements User {
    private String name;
    private List<LibraryItem> borrowedItems;
    private boolean returnsOnTime;

    public Faculty(String name, boolean returnsOnTime) {
        this.name = name;
        this.borrowedItems = new ArrayList<>();
        this.returnsOnTime = returnsOnTime;
    }

    @Override
    public void borrowItem(LibraryItem item) {
        new Object() {
            void borrowItem() {
                borrowedItems.add(item);
            }
        }.borrowItem();
    }

    @Override
    public List<LibraryItem> getBorrowedItems() {
        return new Object() {
            List<LibraryItem> getBorrowedItems() {
                return new ArrayList<>(borrowedItems);
            }
        }.getBorrowedItems();
    }

    @Override
    public String getName() {
        return new Object() {
            String getName() {
                return name;
            }
        }.getName();
    }

    @Override
    public LibraryItem getRandomBorrowedItem() {
        return new Object() {
            LibraryItem getRandomBorrowedItem() {
                Random rand = new Random();
                return borrowedItems.get(rand.nextInt(borrowedItems.size()));
            }
        }.getRandomBorrowedItem();
    }

    @Override
    public LibraryItem getItemDueToday(LocalDate today) {
        return new Object() {
            LibraryItem getItemDueToday() {
                for (LibraryItem item : borrowedItems) {
                    if (item.dueDate != null && item.dueDate.equals(today)) {
                        return item;
                    }
                }
                return null;
            }
        }.getItemDueToday();
    }

    @Override
    public boolean returnsOnTime() {
        return new Object() {
            boolean returnsOnTime() {
                return returnsOnTime;
            }
        }.returnsOnTime();
    }

    @Override
    public boolean Block_borrow(LocalDate today) {
        return new Object() {
            boolean Block_borrow() {
                if (FeeSum(today) > max_fee) {
                    System.out.println(name + " you are blocked from borrowing");
                    return true;
                }
                return false;
            }
        }.Block_borrow();
    }
}