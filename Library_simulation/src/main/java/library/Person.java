package Library_simulation.src.main.java.library;

import java.util.ArrayList;
import java.util.List;

public class Person {
    private String id;
    private String name;
    List<LibraryItem> borrowedItems;

    public Person(String id, String name) {
        this.id = id;
        this.name = name;
        borrowedItems = new ArrayList<>();
    }

    public void borrowItem(LibraryItem item) {
        // Studenci mogą wypożyczyć maksymalnie jeden film w tym samym czasie, trzy książki i trzy czasopisma. Nie ma limitu dla nauczycieli
        if(id.charAt(0) == 's'){
            if (item instanceof Film) {
                for (LibraryItem i : borrowedItems) {
                    if (i instanceof Film) {
                        System.out.println(name +" you can't borrow more than one film at the same time");
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
                    System.out.println(name +" you can't borrow more than three books at the same time");
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
                    System.out.println(name +" you can't borrow more than three journals at the same time");
                    return;
                }
            }
        }
        borrowedItems.add(item);
    }

    public void returnItem(LibraryItem item) {
        borrowedItems.remove(item);
    }

    public void printBorrowedItems() {
        System.out.println("Borrowed items:");
        for (LibraryItem item : borrowedItems) {
            System.out.println(item.id);
        }
    }

    public String Get_Id() {
        return id;
    }

    public String Get_Name() {
        return name;
    }

    public boolean is_borrowed(String id) {
        for (LibraryItem item : borrowedItems) {
            if (item.id.equals(id)) {
                return true;
            }
        }
        return false;
    }
}
