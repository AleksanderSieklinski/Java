package library;
import org.junit.Test;

// This is a test class for the library simulation

public class LibraryTest {
    @Test
    public void testFilm() {
        Film film = new Film("1", "Film Title", "Genre", "Director", 2020, 120, 8.5);
        assert(film.daysOverdue(java.time.LocalDate.now()) == 0);
        assert(film.isOverdue(java.time.LocalDate.now()) == false);
        assert(film.computeFine(java.time.LocalDate.now()) == 0.0);
    }

    @Test
    public void testBook() {
        Book book = new Book("1", "Book Title", "Author", "Genre", "Publisher");
        assert(book.daysOverdue(java.time.LocalDate.now()) == 0);
        assert(book.isOverdue(java.time.LocalDate.now()) == false);
        assert(book.computeFine(java.time.LocalDate.now()) == 0.0);
    }

    @Test
    public void testJournal() {
        Journal journal = new Journal("1", "eISSN", "Publisher", "latestIssue", "journalURL");
        assert(journal.daysOverdue(java.time.LocalDate.now()) == 0);
        assert(journal.isOverdue(java.time.LocalDate.now()) == false);
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
        User person = new Student("Name");
        assert(library.getNumberOfBorrowedItems() == 0);
        library.borrowItem("1", person, java.time.LocalDate.now());
        assert(library.getNumberOfBorrowedItems() == 1);
        library.returnItem("1");
        assert(library.getNumberOfBorrowedItems() == 0);
    }
}
