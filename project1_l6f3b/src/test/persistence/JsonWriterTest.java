package persistence;

import model.Book;
import model.SharingLibrary;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest extends JsonTest{

    @Test
    void testWriterInvalidFile() {
        try {
            SharingLibrary wr = new SharingLibrary("My Sharing Library");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptySharingLibrary() {
        try {
            SharingLibrary sl = new SharingLibrary("My Sharing Library");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptySharingLibrary.json");
            writer.open();
            writer.write(sl);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptySharingLibrary.json");
            sl = reader.read();
            assertEquals("My Sharing Library", sl.getName());
            assertEquals(0, sl.numBooks());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralSharingLibrary() {
        try {
            SharingLibrary sl = new SharingLibrary("My Sharing Library");
            sl.importBook(new Book("Test1", "Author1","111","borrowed"));
            sl.importBook(new Book("Test2", "Author2","222","available"));
            sl.importBook(new Book("Test3", "Author3","333","available"));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralSharingLibrary.json");
            writer.open();
            writer.write(sl);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralSharingLibrary.json");
            sl = reader.read();
            assertEquals("My Sharing Library", sl.getName());
            List<Book> books = sl.getBooks();
            assertEquals(3, books.size());
            checkBook("Test1", "Author1","111","borrowed",books.get(0));
            checkBook("Test2", "Author2","222","available",books.get(1));
            checkBook("Test3", "Author3","333","available",books.get(2));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
