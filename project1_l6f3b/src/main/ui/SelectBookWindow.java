package ui;

import model.Book;
import model.SharingLibrary;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

// A window that used to select a book from books that written by one author
public class SelectBookWindow extends SubWindow {
    List<Book> results;

    private JLabel statement1;
    private JLabel statement2;
    private JLabel books;
    private JTextField txtNum;
    private JButton btnOK;

    public SelectBookWindow(List<Book> results,SharingLibrary libraryBookList) {
        super(libraryBookList);
        this.results = results;

        init();
    }

    // Modifies: this
    // Effect: initialize the window;
    private void init() {
        setThings();

        btnOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String number = txtNum.getText();
                int num = Integer.parseInt(number) - 1;
                isNumAvailable(num);

            }
        });

        contentPanel.add(statement1);
        contentPanel.add(books);
        contentPanel.add(statement2);
        contentPanel.add(txtNum);
        contentPanel.add(btnOK);


        setTitle("Select A Book You Want");
//        setSize(800, 500);
//        setLocation(500, 300);
//        setContentPane(contentPanel);
//        contentPanel.setLayout(null);
//        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
//        setVisible(true);
    }

    // Modifies: this
    // Effect: if the number is valid, open BrrWindow
    //         else open a pop window with "This number is invalid!" on it.
    private void isNumAvailable(int num) {
        if (num >= 0 && num <= results.size() - 1) {
            Book finalResult = results.get(num);
            BrrWindow brr = new BrrWindow(finalResult,library);
            library = brr.getLibrary();
        } else {
            JOptionPane.showMessageDialog(SelectBookWindow.this, "This number is invalid!");
        }
    }

    // Modifies: this
    // Effect: create and bound labels and bottoms to the contentPane
    private void setThings() {
        String names = summaryList(results);
        statement1 = new JLabel("Here are some books written by this author");
        statement2 = new JLabel("Which one do you want? Enter 1 for the first; 2 for the second and so on.");
        books = new JLabel(names);
        txtNum = new JTextField();
        btnOK = new JButton("OK");

        statement1.setBounds(100,30,500,20);
        books.setBounds(100,50,500,20);
        statement2.setBounds(100,100,500,20);
        txtNum.setBounds(100,120,100,20);
        btnOK.setBounds(150,150,100,20);
    }


    // Effect: convert list of book into String contains all book names
    public String summaryList(List<Book> books) {
        int length = books.size();
        int i;
        String names = "";
        for (i = 0; i < length; i++) {
            int number = i + 1;
            String num = String.valueOf(number);
            Book b = books.get(i);
            String newBookName = b.getBookName();
            names = names + "        " + num + "." + newBookName;
        }
        return names;
    }

    public SharingLibrary getLibrary() {
        return library;
    }
}
