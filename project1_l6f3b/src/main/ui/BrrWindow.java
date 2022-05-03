package ui;

import model.Book;
import model.SharingLibrary;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// A window that used to borrow, return and report loss of a book.
public class BrrWindow extends SubWindow {
    private Book result;
    private JLabel statement;
    private JLabel bookInformation1;
    private JLabel bookInformation2;
    private JLabel statement2;
    private JButton btnBorrow;
    private JButton btnReturn;
    private JButton btnReport;

    public BrrWindow(Book result, SharingLibrary libraryBookList) {
        super(libraryBookList);
        this.result = result;
        init();
    }

    // Modifies: this
    // Effect: initialize the window;
    private void init() {
        setThings();

        addThings();

        setBorrowBottom();

        setReturnBottom();

        setReportBottom();

        setTitle("Borrow, Return or Report Loss");
//        setSize(800, 500);
//        setLocation(500, 300);
//        setContentPane(contentPanel);
//        contentPanel.setLayout(null);
//        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
//        setVisible(true);
    }

    // Modifies: this
    // Effect; set a action listener to report bottom
    private void setReportBottom() {
        btnReport.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String resultWords;
                resultWords = library.reportBookLoss(result);
                JOptionPane.showMessageDialog(BrrWindow.this, resultWords);
                setVisible(false);
            }
        });
    }

    // Modifies: this
    // Effect; set a action listener to return bottom
    private void setReturnBottom() {
        btnReturn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String resultWords;
                resultWords = library.returnBook(result);
                JOptionPane.showMessageDialog(BrrWindow.this, resultWords);
                setVisible(false);
            }
        });
    }

    // Modifies: this
    // Effect; set a action listener to borrow bottom
    private void setBorrowBottom() {
        btnBorrow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String resultWords;
                resultWords = library.borrowBook(result);
                JOptionPane.showMessageDialog(BrrWindow.this, resultWords);
                setVisible(false);
            }
        });
    }

    // Modifies: this
    // Effect: add labels and bottoms to the contentPane
    private void addThings() {
        contentPanel.add(statement);
        contentPanel.add(bookInformation1);
        contentPanel.add(bookInformation2);
        contentPanel.add(statement2);
        contentPanel.add(btnBorrow);
        contentPanel.add(btnReturn);
        contentPanel.add(btnReport);
    }

    // Modifies: this
    // Effect: create and bound labels and bottoms to the contentPane
    private void setThings() {
        String bookName = result.getBookName();
        String authorName = result.getAuthorName();
        String location = result.getLocation();
        String status = result.getStatus();

        statement = new JLabel("Here is the book you want.");
        bookInformation1 = new JLabel("Book Name: "  + bookName + "      " + "Author Name: " + authorName);
        bookInformation2 = new JLabel("Location: " + location + "      " + "Status: " + status);
        statement2 = new JLabel("What do you want next?");
        btnBorrow = new JButton("Borrow this book");
        btnReturn = new JButton("Return this book");
        btnReport = new JButton("Report loss of this book");

        statement.setBounds(100,50,500,20);
        bookInformation1.setBounds(100,80,500,20);
        bookInformation2.setBounds(100,100,500,20);
        statement2.setBounds(100,150,500,20);
        btnBorrow.setBounds(100,180,200,20);
        btnReturn.setBounds(100,200,200,20);
        btnReport.setBounds(100,220,200,20);
    }

    public SharingLibrary getLibrary() {
        return library;
    }
}
