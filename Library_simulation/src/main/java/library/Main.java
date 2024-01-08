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

public final class Main {
    public static void main(String[] args) {
        Library library = new Library();
        String id = "1";

        try (Scanner scanner = new Scanner(new File("src/main/data/movies.csv"))) {
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

        try (Scanner scanner = new Scanner(new File("src/main/data/jlist.csv"))) {
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

        try (Scanner scanner = new Scanner(new File("src/main/data/books.csv"))) {
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
        // if no csv files are found, the program will not run
        if (id.equals("1")) {
            System.out.println("No csv files found");
            return;
        }
        final double alphaBook = 0.05;
        final double alphaJournal = 0.08;
        final double alphaFilm = 0.05;
        final double beta = 0.02;
        Random random = new Random();
        User[] usertable = new User[100];
        LocalDate today = LocalDate.now();

        for (int i = 0; i < 100; i++) {
            boolean returnsOnTime = i < 67;
            if (i < 80) {
                usertable[i] = new Student("Student" + i, returnsOnTime);
            } else {
                usertable[i] = new Faculty("Faculty" + i, false);
            }
        }

        for (int i = 0; i < 365; i++) {
            System.out.println("Date: " + today);
            today = today.plusDays(1);
            for (User user : usertable) {
                if((today.getDayOfYear() == 60 || today.getDayOfYear() == 120) && (user.equals(usertable[76]) || user.equals(usertable[88]))){
                    user.seeBorrowedItems(user.getName(), user.getBorrowedItems());
                    System.out.println(user.getName() + " has to pay " + user.FeeSum(today) + " PLN\n");
                    user.seeBorrowedItems(user.getName(), user.getBorrowedItems());
                    System.out.println(user.getName() + " has to pay " + user.FeeSum(today) + " PLN\n");
                }
                if (random.nextDouble() <= beta && !user.getBorrowedItems().isEmpty()) {
                    library.returnItem(user.getRandomBorrowedItem().id);
                }
                LibraryItem item;
                while ((item = user.getItemDueToday(today)) != null && user.returnsOnTime()) {
                    library.returnItem(item.id);
                }
                if (user instanceof Student student) {
                    if (student.Block_borrow(today)){
                        continue;
                    }
                }
                else if (user instanceof Faculty faculty) {
                    if (faculty.Block_borrow(today)){
                        continue;
                    }
                }
                if (random.nextDouble() <= alphaBook) {
                    library.getRandomAvailableItem(user,today);
                }
                if (random.nextDouble() <= alphaJournal) {
                    library.getRandomAvailableItem(user,today);
                }
                if (random.nextDouble() <= alphaFilm) {
                    library.getRandomAvailableItem(user,today);
                }
            }
            System.out.println("Overdue items fine:" + library.dailyOperation(today));
        }
        for(User user : usertable){
            System.out.println(user.getName() + " has to pay " + user.FeeSum(today) + " PLN");
        }
        library.printOverdueUsers(today);
        library.showStatistics(today);
        Scanner input = new Scanner(System.in);
        System.out.println("Do you want to see the loans? (y/n)");
        String answer = input.nextLine();
        if (answer.equals("y")) {
            library.showLoans();
        }
        input.close();
    }
}