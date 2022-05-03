package ui;

import model.Book;
import model.SharingLibrary;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

// A window that used to search a book by its book name or author name
public class SearchWindow extends SubWindow implements ActionListener {
    private JTextField txtBookName;
    private JTextField txtAuthorName;
    private JLabel lblBookName;
    private JLabel lblAuthorName;
    private JLabel sentence1;
    private JLabel sentence2;
    private JButton btnSearch;
    private JButton btnExit;

    public SearchWindow(SharingLibrary libraryBookList) {
        super(libraryBookList);

        init();
    }

    // Modifies: this
    // Effect: initialize the window;
    private void init() {
        setThings();

        setOkBottom();

        btnExit.addActionListener(this);

        addThings();


        setTitle("Search Book");
//        setSize(800, 500);
//        setLocation(500, 300);
//        setContentPane(contentPanel);
//        contentPanel.setLayout(null);
//        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
//        setVisible(true);
    }

    // Modifies: this
    // Effect: set a action listener to the OK bottom
    private void setOkBottom() {
        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String bookName = txtBookName.getText();
                String authorName = txtAuthorName.getText();
                Book resultOfBookName = library.searchBookByBookName(bookName);
                List<Book> resultOfAuthorName = library.searchBookByAuthorName(authorName);
                isBookExist(bookName, resultOfBookName, resultOfAuthorName);
            }
        });
    }

    // Modifies: this
    // Effect: if the book exists and we find it by its book name, open BrrWindow;
    //         if the book exists and we find it by its author name, open SelectWindow;
    //         if the book doesn't exist, open a pop window with "Don't find the book" on it.
    private void isBookExist(String bookName, Book resultOfBookName, List<Book> resultOfAuthorName) {
        if (!(bookName.equals(""))) {
            if (resultOfBookName.getBookName().equals(bookName)) {
                BrrWindow brr = new BrrWindow(resultOfBookName, library);
                library = brr.getLibrary();
            } else if (!(resultOfAuthorName.isEmpty())) {
                SelectBookWindow select = new SelectBookWindow(resultOfAuthorName, library);
                library = select.getLibrary();
            } else {
                JOptionPane.showMessageDialog(SearchWindow.this,"Don't find the book");
            }
        } else {
            if (!(resultOfAuthorName.isEmpty())) {
                SelectBookWindow select = new SelectBookWindow(resultOfAuthorName, library);
                library = select.getLibrary();
            } else {
                JOptionPane.showMessageDialog(SearchWindow.this,"Don't find the book");
            }
        }
    }

    // Modifies: this
    // Effect: create and bound labels and bottoms to the contentPane
    private void setThings() {
        lblBookName = new JLabel("Book Name");
        lblAuthorName = new JLabel("Author Name");
        sentence1 = new JLabel("Enter the book name or author name of the book you want.");
        sentence2 = new JLabel("Be careful: you just need to fill one of the box.");

        lblBookName.setBounds(100, 120, 100, 20);
        lblAuthorName.setBounds(100, 140, 100, 20);
        sentence1.setBounds(50, 50, 400, 20);
        sentence2.setBounds(50,70,400,20);
        setLayout(null);

        txtBookName = new JTextField(50);
        txtAuthorName = new JTextField(50);
        txtBookName.setBounds(220, 120, 100, 20);
        txtAuthorName.setBounds(220, 140, 100, 20);

        btnSearch = new JButton("Search");
        btnExit = new JButton("Exit");
        btnSearch.setBounds(100, 200, 100, 20);
        btnExit.setBounds(220, 200, 100, 20);
    }

    // Modifies: this
    // Effect: add labels and bottoms to the contentPane
    private void addThings() {
        contentPanel.add(sentence1);
        contentPanel.add(sentence2);
        contentPanel.add(lblBookName);
        contentPanel.add(lblAuthorName);
        contentPanel.add(txtBookName);
        contentPanel.add(txtAuthorName);
        contentPanel.add(btnSearch);
        contentPanel.add(btnExit);
    }

    // Effect: hide the window if SearchWindow's action listener get a action;
    @Override
    public void actionPerformed(ActionEvent e) {
        setVisible(false);
    }
}
