package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookTest {


    @Test
    public void testChangeStatus(){
        Book book1 = new Book("A story of mine","Billy John","D221","available");

        assertTrue(book1.isAvailable());
        assertFalse(book1.isBorrowed());
        assertFalse(book1.isMissing());

        book1.changeStatus("borrowed");
        assertFalse(book1.isAvailable());
        assertTrue(book1.isBorrowed());
        assertFalse(book1.isMissing());

        book1.changeStatus("missing");
        assertFalse(book1.isAvailable());
        assertFalse(book1.isBorrowed());
        assertTrue(book1.isMissing());

        book1.changeStatus("available");
        assertTrue(book1.isAvailable());
        assertFalse(book1.isBorrowed());
        assertFalse(book1.isMissing());
    }

    @Test
    public void testGetLocation() {
        Book book1 = new Book("A story of mine","Billy John","D221","available");
        Book book2 = new Book("Fighting Land", "Three Tong", "W321", "available");

        assertEquals("D221",book1.getLocation());
        assertEquals("W321",book2.getLocation());
    }
}