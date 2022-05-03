package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SharingLibraryTest {
    Book book1;
    Book book2;
    Book book3;
    Book book4;
    Book book5;
    SharingLibrary sharingLibraryBookList;

    @BeforeEach
    public void runBefore() {
        book1 = new Book("A story of mine", "Billy John", "D221", "available");
        book2 = new Book("Fighting Land", "Three Tong", "W321", "available");
        book3 = new Book("Breaking Sky", "Tu Dou", "W333", "borrowed");
        book4 = new Book("Infinite space", "Tomato", "W212", "missing");
        book5 = new Book("Fighting Land2", "Three Tong", "W322", "available");
        sharingLibraryBookList = new SharingLibrary("testLibrary");
    }

    @Test
    public void testImportBook(){
        List<Book> books1 = sharingLibraryBookList.getBooks();
        assertEquals(0,books1.size());

        sharingLibraryBookList.importBook(book1);
        List<Book> books2 = sharingLibraryBookList.getBooks();
        assertEquals(1,books2.size());
        Book firstBook = books2.get(0);
        assertEquals(book1,firstBook);

        sharingLibraryBookList.importBook(book2);
        List<Book> books3 = sharingLibraryBookList.getBooks();
        assertEquals(2,books3.size());
    }

    @Test
    public void testBorrowBook() {
        sharingLibraryBookList.importBook(book1);
        sharingLibraryBookList.importBook(book2);
        sharingLibraryBookList.importBook(book3);
        sharingLibraryBookList.importBook(book4);

        String result1 = sharingLibraryBookList.borrowBook(book1);
        assertEquals("Borrowed Successfully",result1);
        assertEquals("borrowed",book1.getStatus());

        String result2 = sharingLibraryBookList.borrowBook(book2);
        assertEquals("Borrowed Successfully",result2);
        assertEquals("borrowed",book2.getStatus());

        String result3 = sharingLibraryBookList.borrowBook(book3);
        assertEquals("Sorry, the book has been borrowed",result3);
        assertEquals("borrowed",book3.getStatus());

        String result4 = sharingLibraryBookList.borrowBook(book4);
        assertEquals("Sorry, the book is missing",result4);
        assertEquals("missing",book4.getStatus());
    }

    @Test
    public void testReturnBook() {
        sharingLibraryBookList.importBook(book1);
        sharingLibraryBookList.importBook(book2);
        sharingLibraryBookList.importBook(book3);
        sharingLibraryBookList.importBook(book4);

        String result1 = sharingLibraryBookList.returnBook(book1);
        assertEquals("You didn't borrow this book in our library",result1);
        assertEquals("available",book1.getStatus());

        String result2 = sharingLibraryBookList.returnBook(book2);
        assertEquals("You didn't borrow this book in our library",result2);
        assertEquals("available",book2.getStatus());

        String result3 = sharingLibraryBookList.returnBook(book3);
        assertEquals("Return Successfully",result3);
        assertEquals("available",book3.getStatus());

        String result4 = sharingLibraryBookList.returnBook(book4);
        assertEquals("You return the book too late, and you need to pay $20 as punishment",result4);
        assertEquals("available",book4.getStatus());
    }

    @Test
    public void testReportBookLoss() {
        sharingLibraryBookList.importBook(book1);
        sharingLibraryBookList.importBook(book2);


        String result1 = sharingLibraryBookList.reportBookLoss(book1);
        assertEquals("Reporting loss successfully",result1);
        assertEquals("missing",book1.getStatus());

        String result2 = sharingLibraryBookList.reportBookLoss(book2);
        assertEquals("Reporting loss successfully",result2);
        assertEquals("missing",book2.getStatus());

        String result3 = sharingLibraryBookList.reportBookLoss(book2);
        assertEquals("This book has been reported",result3);
        assertEquals("missing",book2.getStatus());

    }

    @Test
    public void testSearchBookByBookName() {
        sharingLibraryBookList.importBook(book1);
        sharingLibraryBookList.importBook(book2);
        sharingLibraryBookList.importBook(book3);
        sharingLibraryBookList.importBook(book4);

        String name1 = book1.getBookName();
        String name2 = book2.getBookName();
        String name3 = book3.getBookName();
        String name4 = book4.getBookName();

        assertEquals(book1, sharingLibraryBookList.searchBookByBookName(name1));
        assertEquals(book2, sharingLibraryBookList.searchBookByBookName(name2));
        assertEquals(book3, sharingLibraryBookList.searchBookByBookName(name3));
        assertEquals(book4, sharingLibraryBookList.searchBookByBookName(name4));
    }

    @Test
    public void testSearchBookByAuthorName() {
        sharingLibraryBookList.importBook(book1);
        sharingLibraryBookList.importBook(book2);
        sharingLibraryBookList.importBook(book3);
        sharingLibraryBookList.importBook(book4);
        sharingLibraryBookList.importBook(book5);

        String name1 = book1.getAuthorName();
        String name2 = book2.getAuthorName();
        String name3 = book3.getAuthorName();
        String name4 = book4.getAuthorName();
        String name5 = book5.getAuthorName();

        List<Book> results1 = sharingLibraryBookList.searchBookByAuthorName(name1);
        assertEquals(1,results1.size());
        assertEquals(book1,results1.get(0));

        List<Book> results2 = sharingLibraryBookList.searchBookByAuthorName(name2);
        assertEquals(2,results2.size());
        assertEquals(book2,results2.get(0));
        assertEquals(book5,results2.get(1));

        List<Book> results3 = sharingLibraryBookList.searchBookByAuthorName(name3);
        assertEquals(1,results3.size());
        assertEquals(book3,results3.get(0));

        List<Book> results4 = sharingLibraryBookList.searchBookByAuthorName(name4);
        assertEquals(1,results4.size());
        assertEquals(book4,results4.get(0));

        List<Book> results5 = sharingLibraryBookList.searchBookByAuthorName(name5);
        assertEquals(2,results5.size());
        assertEquals(book2,results5.get(0));
        assertEquals(book5,results5.get(1));
    }

    @Test
    public void testSummaryList() {
        List<Book> books = new ArrayList<>();
        books.add(book1);
        books.add(book2);
        books.add(book3);
        books.add(book4);
        List<String> names = sharingLibraryBookList.summaryList(books);
        assertEquals(4,names.size());
        assertEquals("A story of mine",names.get(0));
        assertEquals("Fighting Land",names.get(1));
        assertEquals("Breaking Sky",names.get(2));
        assertEquals("Infinite space",names.get(3));

    }
}
