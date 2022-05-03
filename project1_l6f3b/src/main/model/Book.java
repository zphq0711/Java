package model;

import org.json.JSONObject;
import persistence.Writable;

public class Book implements Writable {
    String bookName;
    String authorName;
    String location;
    String status;

    // constructor
    // EFFECTS:contact has given book name, given author name and location, and status is available
    public Book(String bookName, String authorName, String location, String status) {
        this.bookName = bookName;
        this.authorName = authorName;
        this.location = location;
        this.status = status;
    }

    public String getBookName() {
        return bookName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public String getLocation() {
        return location;
    }

    public String getStatus() {
        return status;
    }

    // EFFECTS: produce true if the book is available
    public boolean isAvailable() {
        return  (status.equals("available"));
    }

    // EFFECTS: produce true if the book is borrowed by others.
    public boolean isBorrowed() {
        return (status.equals("borrowed"));
    }

    // EFFECTS: produce true if the book is missing
    public boolean isMissing() {
        return (status.equals("missing"));
    }


    // MODIFIES: this
    // EFFECT: change the status of the book
    public void changeStatus(String newStatus) {
        this.status = newStatus;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", bookName);
        json.put("author", authorName);
        json.put("location", location);
        json.put("status", status);
        return json;
    }
}
