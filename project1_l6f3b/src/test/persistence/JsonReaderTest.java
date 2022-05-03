package persistence;

import model.Book;
import model.SharingLibrary;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest extends JsonTest{

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            SharingLibrary sl = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptySharingLibrary() {
        JsonReader reader = new JsonReader("./data/testReaderEmptySharingLibrary.json");
        try {
            SharingLibrary sl = reader.read();
            assertEquals("My Sharing Library", sl.getName());
            assertEquals(0, sl.numBooks());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralSharingLibrary.json");
        try {
            SharingLibrary sl = reader.read();
            assertEquals("My Sharing Library", sl.getName());
            List<Book> books = sl.getBooks();
            assertEquals(2, books.size());
            checkBook("test1", "author1", "1111", "available",books.get(0));
            checkBook("test2", "author2", "2222", "missing",books.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
