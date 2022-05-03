package ui;

import model.Book;
import model.SharingLibrary;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class SharingBookManagerApp {
    private static final String JSON_STORE = "./data/sharingLibrary.json";
    private SharingLibrary libraryBookList;
    private Scanner input;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;


    // EFFECTS: runs the SharingBookManager application
    public SharingBookManagerApp() {

        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runSharingBookManager();
    }


    // MODIFIES: this
    // EFFECTS: processes user input
    private void runSharingBookManager() {
        boolean keepGoing = true;
        String command;

        init();

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nGoodbye!");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("f")) {
            doFindBook();
        } else if (command.equals("i")) {
            doImport();
        } else if (command.equals("r")) {
            doReportLoss();
        } else if (command.equals("s")) {
            saveSharingLibrary();
        } else if (command.equals("l")) {
            loadSharingLibrary();
        } else {
            System.out.println("\t Selection not valid...");
        }
    }

    // EFFECTS: saves the sharing-library to file
    protected void saveSharingLibrary() {
        try {
            jsonWriter.open();
            jsonWriter.write(libraryBookList);
            jsonWriter.close();
            System.out.println("Saved " + libraryBookList.getName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads sharing-library from file
    public void loadSharingLibrary() {
        try {
            libraryBookList = jsonReader.read();
            System.out.println("Loaded " + libraryBookList.getName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: report the loss of books
    private void doReportLoss() {
        System.out.println("\t What book do you want to report loss?");
        System.out.println("\t Please enter the book name");
        String name = input.next();

        Book result = libraryBookList.searchBookByBookName(name);
        if (result.getBookName().equals(name)) {
            System.out.println("Here is the book we can find.\n");
            System.out.println("\t Book Name: " + result.getBookName() + "\t Author: " + result.getAuthorName());
            System.out.println("\t Location: " + result.getLocation() + "\t Status: " + result.getStatus());
            doMakeSure(result);
        } else {
            System.out.println("Sorry! There is no this book in our library.\n");
        }
    }

    // EFFECTS: make sure that the user wants to report loss of this book.
    private void doMakeSure(Book result) {
        System.out.println("Are you sure this is the book you want to report?\n");
        System.out.println("If yes, please enter 1\n");
        int command = input.nextInt();

        if (command == 1) {
            System.out.println(libraryBookList.reportBookLoss(result));
        }
    }

    // MODIFIES: this
    // EFFECTS: add a new book into the sharing library list.
    private void doImport() {
        System.out.println("\t Enter the book name of the book you want to import.");
        String bookName = input.next();
        System.out.println("\t Enter the book author of the book you want to import.");
        String authorName = input.next();
        System.out.println("\t Enter the location of the book you will put your book.");
        String location = input.next();
        String status = "available";
        Book newBook = new Book(bookName, authorName, location, status);
        libraryBookList.importBook(newBook);
        System.out.println("\n The book is imported to the library.");
        System.out.println("\n Thanks for your sharing");

    }

    // EFFECTS: search a book by a given book name or a given author name. If use book name to search, return a book
    // and if use author name to search, return a list of book name written by the author.
    private void doFindBook() {
        boolean selected = selectSearch();
        System.out.print("What book do you want? Please enter the book name or author name \n");

        String name = input.next();

        if (selected) {
            Book result = libraryBookList.searchBookByBookName(name);
            if (result.getBookName().equals(name)) {
                System.out.println("Here is the book you want!\n");
                System.out.println("\t Book Name: " + result.getBookName() + "\t Author: " + result.getAuthorName());
                System.out.println("\t Location: " + result.getLocation() + "\t Status: " + result.getStatus());
                doBorrow(result);
            } else {
                System.out.println("Sorry! There is no this book in our library.\n");
            }

        } else {
            List<Book> results = libraryBookList.searchBookByAuthorName(name);
            if (results.isEmpty()) {
                System.out.println("Sorry! There is no this author's book in our library.\n");
            } else {
                System.out.println("Here are the books you want.\n");
                System.out.println(libraryBookList.summaryList(results));
                doSelect(results);
            }
        }


    }

    // EFFECTS: select which book does the user want.
    private void doSelect(List<Book> books) {
        System.out.println("Which one do you want?.\n");
        System.out.println("If you want the first one, please enter 1.\t");
        System.out.println("If you want the second one, please enter 2.\t");
        System.out.println("And so on...\t");
        int n = input.nextInt() - 1;

        if (n >= 0 && n <= books.size() - 1) {
            Book result = books.get(n);
            doBorrow(result);
        } else {
            System.out.println("This number is invalid!\t");
        }


    }

    // MODIFIES: this
    // EFFECTS: conducts a book borrowing or a book returning
    private void doBorrow(Book book) {
        System.out.println("What do you want next?\n");
        String selection = "";

        while (!(selection.equals("b") || selection.equals("r"))) {
            System.out.println("b for borrowing this book");
            System.out.println("r for returning this book");
            selection = input.next();
            selection = selection.toLowerCase();
        }

        if (selection.equals("b")) {
            System.out.println(libraryBookList.borrowBook(book));
        } else {
            System.out.println(libraryBookList.returnBook(book));
        }
    }

    // EFFECTS: prompts user to select the way of searching book by book name or author.
    private boolean selectSearch() {
        String selection = "";  // force entry into loop

        while (!(selection.equals("b") || selection.equals("a"))) {
            System.out.println("b for searching book by a book name");
            System.out.println("a for searching books by a author name");
            selection = input.next();
            selection = selection.toLowerCase();
        }

        return selection.equals("b");
    }


    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nWelcome to Sharing Book!");
        System.out.println("\nWhat do you want?");
        System.out.println("\tf -> Find a book you want");
        System.out.println("\ti -> Import a book to the library");
        System.out.println("\tr -> Report loss of a book");
        System.out.println("\ts -> save sharing library to file");
        System.out.println("\tl -> load sharing library from file");
        System.out.println("\tq -> Quit");
    }

    // MODIFIES: this
    // EFFECTS: initializes library and my book list
    private void init() {
        libraryBookList = new SharingLibrary("Generousness Sharing Library");

        input = new Scanner(System.in);
    }


}
