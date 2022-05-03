package persistence;

import model.Book;
import model.SharingLibrary;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkBook(String bookName, String authorName, String location, String status,Book book) {
        assertEquals(bookName, book.getBookName());
        assertEquals(authorName, book.getAuthorName());
        assertEquals(location, book.getLocation());
        assertEquals(status, book.getStatus());
    }
}
