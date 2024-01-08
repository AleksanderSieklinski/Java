package library;
import java.time.LocalDate;

import org.junit.Test;

// This is a test class for the library simulation

public class LibraryTest {
    @Test
    public void testFilm() {
        Film film = new Film("1", "Film Title", "Genre", "Director", 2020, 120, 8.5);
        assert(film.daysOverdue(java.time.LocalDate.now()) == 0);
        assert(!film.isOverdue(LocalDate.now()));
        assert(film.computeFine(java.time.LocalDate.now()) == 0.0);
    }

    @Test
    public void testBook() {
        Book book = new Book("1", "Book Title", "Author", "Genre", "Publisher");
        assert(book.daysOverdue(java.time.LocalDate.now()) == 0);
        assert(!book.isOverdue(LocalDate.now()));
        assert(book.computeFine(java.time.LocalDate.now()) == 0.0);
    }

    @Test
    public void testJournal() {
        Journal journal = new Journal("1", "eISSN", "Publisher", "latestIssue", "journalURL");
        assert(journal.daysOverdue(java.time.LocalDate.now()) == 0);
        assert(!journal.isOverdue(LocalDate.now()));
        assert(journal.computeFine(java.time.LocalDate.now()) == 0.0);
    }

    @Test
    public void testLibraryItem() {
        LibraryItem item = new Book("1", "Book Title", "Author", "Genre", "Publisher");
        assert(item.daysOverdue(java.time.LocalDate.now()) == 0);
    }

    @Test
    public void testLibrary() {
        Library library = new Library();
        Book book = new Book("1", "Book Title", "Author", "Genre", "Publisher");
        Journal journal = new Journal("1", "eISSN", "Publisher", "latestIssue", "journalURL");
        Film film = new Film("1", "Film Title", "Genre", "Director", 2020, 120, 8.5);
        assert(library.getNumberOfItems() == 0);
        library.addItem(book);
        library.addItem(journal);
        library.addItem(film);
        assert(library.getNumberOfItems() == 3);
        User person = new Student("Name", true);
        assert(library.getNumberOfBorrowedItems() == 0);
        library.borrowItem("1", person, java.time.LocalDate.now());
        assert(library.getNumberOfBorrowedItems() == 1);
        library.returnItem("1");
        assert(library.getNumberOfBorrowedItems() == 0);
        assert(library.getNumberOfItems() == 3);
        assert(library.dailyOperation(LocalDate.now()) == 0);
    }

    @Test
    public void testStudent() {
        User student = new Student("Name", true);
        assert(student.getName().equals("Name"));
        assert(student.getBorrowedItems().isEmpty());
        assert(student.getItemDueToday(LocalDate.now()) == null);
        assert(student.returnsOnTime());
        assert(student.FeeSum(LocalDate.now()) == 0.0);
    }

    @Test
    public void testFaculty() {
        User faculty = new Faculty("Name", true);
        assert(faculty.getName().equals("Name"));
        assert(faculty.getBorrowedItems().isEmpty());
        assert(faculty.getItemDueToday(LocalDate.now()) == null);
        assert(faculty.returnsOnTime());
        assert(faculty.FeeSum(LocalDate.now()) == 0.0);
    }
    @Test
    public void testUser() {
        User user = new Student("Name", true);
        assert(user.getName().equals("Name"));
        assert(user.getBorrowedItems().isEmpty());
        assert(user.getItemDueToday(LocalDate.now()) == null);
        assert(user.returnsOnTime());
        assert(user.FeeSum(LocalDate.now()) == 0.0);
    }
}
