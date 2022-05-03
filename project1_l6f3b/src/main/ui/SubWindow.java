package ui;

import model.SharingLibrary;

import javax.swing.*;

public abstract class SubWindow extends JFrame {
    protected JPanel contentPanel;
    protected SharingLibrary library;

    public SubWindow(SharingLibrary bookList) {
        contentPanel = new JPanel();
        library = bookList;

        setContentPane(contentPanel);
        contentPanel.setLayout(null);
        setSize(800, 500);
        setLocation(500, 300);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setVisible(true);
    }
}
