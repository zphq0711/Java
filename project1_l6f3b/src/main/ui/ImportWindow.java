package ui;

import model.Book;
import model.SharingLibrary;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// A window that used to import a new book to library
public class ImportWindow extends SubWindow  {


    private JLabel question;
    private JLabel lblBook;
    private JLabel lblAuthor;
    private JLabel lblLocation;
    private JTextField txtBook;
    private JTextField txtAuthor;
    private JTextField txtLocation;
    private JButton btnOK;

    public ImportWindow(SharingLibrary libraryBookList) {
        super(libraryBookList);
        init();
    }

    // Modifies: this
    // Effect: initialize the window;
    private void init() {

        createItems();
        setItems();

        addItems();

        setOkBottoms();

        setTitle("Import Book");
//        setSize(800, 500);
//        setLocation(500, 300);
//        setContentPane(contentPanel);
//        contentPanel.setLayout(null);
//        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
//        setVisible(true);

    }

    // Modifies: this
    // Effect; set a action listener to Ok bottom
    private void setOkBottoms() {
        btnOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String bookName = txtBook.getText();
                String authorName = txtAuthor.getText();
                String location = txtLocation.getText();

                doImport(bookName,authorName,location);

            }
        });

    }

    // Modifies: this
    // Effect: add labels and bottoms to the contentPane
    private void addItems() {
        contentPanel.add(question);
        contentPanel.add(lblBook);
        contentPanel.add(lblAuthor);
        contentPanel.add(lblLocation);
        contentPanel.add(txtBook);
        contentPanel.add(txtAuthor);
        contentPanel.add(txtLocation);
        contentPanel.add(btnOK);
    }

    // Modifies: this
    // Effect: bound labels and bottoms to the contentPane
    private void setItems() {
        question.setBounds(100,50,500,20);
        lblBook.setBounds(100,100,100,20);
        lblAuthor.setBounds(100,150,100,20);
        lblLocation.setBounds(100,200,100,20);
        txtBook.setBounds(300,100,100,20);
        txtAuthor.setBounds(300,150,100,20);
        txtLocation.setBounds(300,200,100,20);
        btnOK.setBounds(200, 300, 100, 20);
    }

    // Modifies: this
    // Effect: create labels and bottoms to the contentPane
    private void createItems() {
        question = new JLabel("Please, enter all the information of the book.");
        lblBook = new JLabel("Book Name:");
        lblAuthor = new JLabel("Author Name:");
        lblLocation = new JLabel("Location:");
        txtBook = new JTextField();
        txtAuthor = new JTextField();
        txtLocation = new JTextField();
        btnOK = new JButton("Import");
        setLayout(null);
    }



    // MODIFIES: this
    // EFFECTS: add a new book into the sharing library list.
    private void doImport(String bookName, String authorName, String location) {
        Book newBook = new Book(bookName, authorName, location, "available");
        library.importBook(newBook);
        JOptionPane.showMessageDialog(ImportWindow.this,"Thanks for your sharing");
        setVisible(false);
    }

    public SharingLibrary getBookList() {
        return library;
    }
}
