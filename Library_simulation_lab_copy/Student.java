package Library_simulation_lab_copy;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.time.LocalDate;

// This is a class for a student
// A student can borrow items, get a list of his borrowed items, get his name, get a random borrowed item, and get an item due today

public final class Student extends Fee_block implements User {
    private String name;
    private List<LibraryItem> borrowedItems;
    private boolean returnsOnTime;

    public Student(String name, boolean returnsOnTime) {
        this.name = name;
        this.borrowedItems = new ArrayList<>();
        this.returnsOnTime = returnsOnTime;
    }
    @Override
    public void borrowItem(LibraryItem item) {
        new Object() {
            void borrowItem() {
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