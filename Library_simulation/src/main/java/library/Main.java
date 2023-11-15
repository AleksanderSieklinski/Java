package Library_simulation.src.main.java.library;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.time.LocalDate;

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

        Person student = new Person("s", "John Doe");
        Person teacher = new Person("t", "Jane Doe");

        library.borrowItem("1", student, LocalDate.now());
        library.borrowItem("2", teacher, LocalDate.now());
        library.borrowItem("3", student, LocalDate.now());
        library.borrowItem("4", teacher, LocalDate.now());

        LocalDate date = LocalDate.now().plusWeeks(1);

        System.out.println();
        library.printItemInfo("1", date);
        System.out.println();
        library.printItemInfo("2", date);
        System.out.println();
        library.printItemInfo("3", date);
        System.out.println();
        library.printItemInfo("4", date);
        System.out.println();
        library.printTotalFine(date);
        System.out.println();
        library.printOverdueItems(date);
        System.out.println();

        library.returnItem("1");
        library.returnItem("2");
        library.returnItem("3");
        library.returnItem("4");

        library.getItem("1").print_details();
        library.getItem("2").print_details();
        library.getItem("3").print_details();
        library.getItem("4").print_details();
    }
}