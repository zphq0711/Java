package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

public class SharingLibrary implements Writable {
    String name;
    List<Book> libraryBookList;

    // constructor
    // EFFECTS:contact has a empty list of books
    public SharingLibrary(String name) {
        this.name = name;
        libraryBookList = new ArrayList<>();
    }

    // EFFECTS: return the name of sharing library
    public String getName() {
        return name;
    }

    // EFFECTS: returns number of books in this sharing library
    public int numBooks() {
        return libraryBookList.size();
    }

    // REQUIRES: the book exists in the library
    // MODIFIES: this
    // EFFECTS: If the book is available, then changing the status of the book to "borrowed"
    // and show "Borrowed Successfully". If the the book is borrowed, then shows "Sorry, the book has been
    // borrowed". If the the book is missing, then shows "Sorry, the book is missing".
    public String borrowBook(Book book) {
        if (book.getStatus().equals("available")) {
            libraryBookList.remove(book);
            book.changeStatus("borrowed");
            libraryBookList.add(book);
            return ("Borrowed Successfully");
        } else if (book.getStatus().equals("borrowed")) {
            return ("Sorry, the book has been borrowed");
        } else {
            return ("Sorry, the book is missing");
        }
    }

    // REQUIRES: the book exists in the library
    // MODIFIES: this
    // EFFECTS: If the status of the book is borrowed,then changing the status to available,
    // and shows "Return Successfully". If the status of the book is available, then shows
    // "You didn't borrow this book in our library". If the status of the book is missing, then shows
    //  "You return the book too late, and you need to pay $20 as punishment", and changes the status to available
    public String returnBook(Book book) {
        if (book.getStatus().equals("borrowed")) {
            libraryBookList.remove(book);
            book.changeStatus("available");
            libraryBookList.add(book);
            return ("Return Successfully");
        } else if (book.getStatus().equals("available")) {
            return ("You didn't borrow this book in our library");
        } else {
            libraryBookList.remove(book);
            book.changeStatus("available");
            libraryBookList.add(book);
            return ("You return the book too late, and you need to pay $20 as punishment");
        }
    }

    // REQUIRES: The book exists in the sharing library.
    // MODIFIES: this
    // EFFECT: if the the status is not true, makes the status to be true and returns "Reporting loss successfully",
    // otherwise, returns "This book has been reported"
    public String reportBookLoss(Book book) {
        if (!(book.getStatus().equals("missing"))) {
            libraryBookList.remove(book);
            book.changeStatus("missing");
            libraryBookList.add(book);
            return ("Reporting loss successfully");
        } else {
            return ("This book has been reported");
        }
    }

    // MODIFIES: this
    // EFFECTS: Add a new book into the library.
    public void importBook(Book newBook) {
        libraryBookList.add(newBook);
    }

    //EFFECTS: return the list of books in the library
    public List<Book> getBooks() {
        return libraryBookList;
    }

    // REQUIRES: The book exists in the library
    // EFFECTS: return the book will the given book name
    public Book searchBookByBookName(String bookName) {
        Book result = new Book("", "", "", "");
        for (Book book : libraryBookList) {
            if (book.getBookName().equals(bookName)) {
                result = book;
            }
        }
        return result;
    }

    // REQUIRES: The book exists in the library
    // EFFECTS: return the book will the given author name
    public List<Book> searchBookByAuthorName(String authorName) {
        List<Book> results = new ArrayList<>();
        for (Book book : libraryBookList) {
            if (book.getAuthorName().equals(authorName)) {
                results.add(book);
            }
        }
        return results;
    }

    // EFFECTS: return the list of book names
    public List<String> summaryList(List<Book> books) {
        List<String> names = new ArrayList<>();
        for (Book book : books) {
            names.add(book.getBookName());
        }
        return names;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("books", libraryBookListToJson());
        return json;
    }

    private JSONArray libraryBookListToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Book b : libraryBookList) {
            jsonArray.put(b.toJson());
        }

        return jsonArray;
    }
}
