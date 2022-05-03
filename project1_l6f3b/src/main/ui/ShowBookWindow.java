package ui;

import model.Book;
import model.SharingLibrary;

import javax.swing.*;
import java.util.List;

// A window that used to show all books that already in the library
public class ShowBookWindow extends SubWindow {

    private JLabel lblBookName;
    private JLabel lblAuthorName;
    private JLabel lblLocation;
    private JLabel lblStatus;
    private JLabel lblLabel;

    public ShowBookWindow(SharingLibrary bookList) {
        super(bookList);
        List<Book> books = library.getBooks();

        showBooks(books);


        init();
    }

    private void showBooks(List<Book> books) {
        int length = books.size();

        int i;
        for (i = 0; i < length; i++) {
            int number = i + 1;
            String num = String.valueOf(number);
            Book b = books.get(i);
            String newBookName = b.getBookName();
            String newAuthorName = b.getAuthorName();
            String newLocation = b.getLocation();
            String newStatus = b.getStatus();

            JLabel lblNewLabel = new JLabel(num);
            lblNewLabel.setBounds(20,30 + (20 * number),50,20);
            contentPanel.add(lblNewLabel);

            createBookAndAuthorName(number, newBookName, newAuthorName);

            JLabel lblNewLocation = new JLabel(newLocation);
            lblNewLocation.setBounds(420,30 + (20 * number),100,20);
            contentPanel.add(lblNewLocation);

            JLabel lblNewStatus = new JLabel(newStatus);
            lblNewStatus.setBounds(520,30 + (20 * number),100,20);
            contentPanel.add(lblNewStatus);
        }
    }

    private void createBookAndAuthorName(int number, String newBookName, String newAuthorName) {
        JLabel lblNewBookName = new JLabel(newBookName);
        lblNewBookName.setBounds(70,30 + (20 * number),200,20);
        contentPanel.add(lblNewBookName);

        JLabel lblNewAuthorName = new JLabel(newAuthorName);
        lblNewAuthorName.setBounds(270,30 + (20 * number),150,20);
        contentPanel.add(lblNewAuthorName);
    }

    private void init() {
        lblBookName = new JLabel("Book Name");
        lblAuthorName = new JLabel("Author Name");
        lblLocation = new JLabel("Location");
        lblStatus = new JLabel("Status");
        lblLabel = new JLabel("Label");

        lblLabel.setBounds(20,20,50,20);
        lblBookName.setBounds(70,20,200,20);
        lblAuthorName.setBounds(270,20,150,20);
        lblLocation.setBounds(420,20,100,20);
        lblStatus.setBounds(520,20,100,20);

        contentPanel.add(lblBookName);
        contentPanel.add(lblAuthorName);
        contentPanel.add(lblLocation);
        contentPanel.add(lblStatus);
        contentPanel.add(lblLabel);

        setTitle("Show All Books");

//        createWindow();
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
//    private void createWindow() {
//        setContentPane(contentPane);
//        contentPane.setLayout(null);

//        setSize(800, 500);
//        setLocation(500, 300);
//        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
//        setVisible(true);
//    }
}
