package library;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import java.time.LocalDate;
import java.util.Random;

// This is the main class of the library simulation.
// It reads the data from the csv files and creates the library items.
// It also creates the students and teachers and borrows the items.
// It then prints the details of the items and returns them.

public class Main {
    public static void main(String[] args) {
        Library library = new Library();
        String id = "1";

        try (Scanner scanner = new Scanner(new File("movies.csv"))) {
            scanner.nextLine();
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] fields = line.split(";");
                if(fields.length < 9) {
                    continue;
                }
                Film film = new Film(id,fields[1], fields[2], fields[4], Integer.parseInt(fields[6]), Integer.parseInt(fields[7]), Double.parseDouble(fields[8]));
                id = Integer.toString(Integer.parseInt(id) + 1);
                library.addItem(film);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Could not find movies.csv");
        }

        try (Scanner scanner = new Scanner(new File("jlist.csv"))) {
            scanner.nextLine();
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] fields = line.split(";");
                if(fields.length < 13) {
                    continue;
                }
                Journal journal = new Journal(id,fields[3], fields[4], fields[6], fields[12]);
                id = Integer.toString(Integer.parseInt(id) + 1);
                library.addItem(journal);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Could not find jlist.csv");
        }

        try (Scanner scanner = new Scanner(new File("books.csv"))) {
            scanner.nextLine();
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] fields = line.split(";");
                if(fields.length < 5) {
                    continue;
                }
                Book book = new Book(id,fields[0], fields[1], fields[2], fields[4]);
                id = Integer.toString(Integer.parseInt(id) + 1);
                library.addItem(book);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Could not find books.csv");
        }

        final double alphaBook = 0.05;
        final double alphaJournal = 0.08;
        final double alphaFilm = 0.05;
        final double beta = 0.02;
        Random random = new Random();
        User usertable[] = new User[100];
        LocalDate today = LocalDate.now();

        for (int i = 0; i < 100; i++) {
            if (i < 80) {
                usertable[i] = new Student("Student" + i);
            } else {
                usertable[i] = new Faculty("Faculty" + i);
            }
        }

        for (int i = 0; i < 365; i++) {
            System.out.println("Date: " + today);
            today = today.plusDays(1);
            for (User user : usertable) {
                if (random.nextDouble() <= alphaBook) {
                    LibraryItem item = library.getRandomAvailableItem(user,today);
                    if (item != null) {
                        user.borrowItem(item);
                    }
                }
                if (random.nextDouble() <= alphaJournal) {
                    LibraryItem item = library.getRandomAvailableItem(user,today);
                    if (item != null) {
                        user.borrowItem(item);
                    }
                }
                if (random.nextDouble() <= alphaFilm) {
                    LibraryItem item = library.getRandomAvailableItem(user,today);
                    if (item != null) {
                        user.borrowItem(item);
                    }
                }
                if (random.nextDouble() <= beta && !user.getBorrowedItems().isEmpty()) {
                    LibraryItem item = user.getRandomBorrowedItem();
                    library.returnItem(item.id);
                }
                LibraryItem item;
                while ((item = user.getItemDueToday(today)) != null) {
                    library.returnItem(item.id);
                }
            }
            library.dailyOperation(today);
        }

        Scanner input = new Scanner(System.in);
        System.out.println("Do you want to see the loans? (y/n)");
        String answer = input.nextLine();
        if (answer.equals("y")) {
            library.showLoans();
        }
        input.close();
    }
}