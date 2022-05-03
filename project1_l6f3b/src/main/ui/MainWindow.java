package ui;

import model.SharingLibrary;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;


public class MainWindow extends JFrame implements ActionListener {
    private JLabel statement1;
    private JLabel statement2;
    private JButton btnFind;
    private JButton btnImport;
    private JButton btnSave;
    private JButton btnLoad;
    private JButton btnQuit;
    private JButton btnCheck;
    private JPanel contentPane;


    private static final String JSON_STORE = "./data/sharingLibrary.json";
    private SharingLibrary libraryBookList;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    public MainWindow() {
        contentPane = new JPanel();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        init();
    }

    // Modifies: this
    // Effect: initialize the window;
    private void init() {
        createItems();
        boundItems();
        addItems();

        btnCheck.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ShowBookWindow(libraryBookList);
            }
        });

        btnFind.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SearchWindow(libraryBookList);
            }
        });

        setImportBottim();

        setSaveBottom();
        setLoadBottom();
        btnQuit.addActionListener(this);

        setBackground();
    }

    // MODIFIES: this
    // Effect: set a action listener to the set import bottom
    private void setImportBottim() {
        btnImport.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ImportWindow imports = new ImportWindow(libraryBookList);
                libraryBookList = imports.getBookList();
            }
        });
    }

    // Effect: set a action listener to the save bottom
    private void setSaveBottom() {
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveSharingLibrary();
            }
        });
    }

    // Modifies: this
    // Effect: set a action listener to the load bottom
    private void setLoadBottom() {
        btnLoad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadSharingLibrary();
            }
        });
    }

    // Modifies: this
    // Effect: add labels and bottoms to the contentPane
    private void addItems() {
        contentPane.add(statement1);
        contentPane.add(statement2);
        contentPane.add(btnFind);
        contentPane.add(btnImport);
        contentPane.add(btnSave);
        contentPane.add(btnLoad);
        contentPane.add(btnQuit);
        contentPane.add(btnCheck);
    }

    // Modifies: this
    // Effect: bound labels and bottoms to the contentPane
    private void boundItems() {
        statement1.setBounds(300, 30, 500, 20);
        statement2.setBounds(320, 50, 500, 20);
        btnCheck.setBounds(100, 150, 500, 20);
        btnFind.setBounds(100, 200, 500, 20);
        btnImport.setBounds(100, 250, 500, 20);
        btnSave.setBounds(100, 300, 500, 20);
        btnLoad.setBounds(100, 350, 500, 20);
        btnQuit.setBounds(100, 400, 500, 20);

        setLayout(null);
    }

    // Modifies: this
    // Effect: create labels and bottoms to the contentPane
    private void createItems() {
        ImageIcon middleIcon = createImageIcon("images/middle.gif");
        statement1 = new JLabel("Welcome to sharing library!");
        statement2 = new JLabel("What do you want?");
        btnCheck = new JButton("Check all the books in library");
        btnFind = new JButton("Find a book you want");
        btnImport = new JButton("Import a book to the library");
        btnSave = new JButton("Save sharing library to file");
        btnLoad = new JButton("Load sharing library from file");
        btnQuit = new JButton("Quit",middleIcon);
        btnQuit.setVerticalTextPosition(AbstractButton.CENTER);
        btnQuit.setHorizontalTextPosition(AbstractButton.RIGHT);

    }

    // Effect: set the main window and the contentPane
    private void setBackground() {

        setContentPane(contentPane);
        contentPane.setLayout(null);
        libraryBookList = new SharingLibrary("Generousness Sharing Library");
        setTitle("Generousness Sharing Library");
        setSize(800, 500);
        setLocation(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    // Effect: close the window if main window's action listener get a action;
    @Override
    public void actionPerformed(ActionEvent e) {
        System.exit(0);
    }

    // EFFECTS: saves the sharing-library to file
    protected void saveSharingLibrary() {
        try {
            jsonWriter.open();
            jsonWriter.write(libraryBookList);
            jsonWriter.close();
            String re = "Saved " + libraryBookList.getName() + " to " + JSON_STORE;
            JOptionPane.showMessageDialog(MainWindow.this, re);
        } catch (FileNotFoundException e) {
            String re = "Unable to write to file: " + JSON_STORE;
            JOptionPane.showMessageDialog(MainWindow.this, re);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads sharing-library from file
    public void loadSharingLibrary() {

        try {
            libraryBookList = jsonReader.read();
            String re = "Loaded " + libraryBookList.getName() + " from " + JSON_STORE;
            JOptionPane.showMessageDialog(MainWindow.this, re);
        } catch (IOException e) {
            String re = "Unable to read from file: " + JSON_STORE;
            JOptionPane.showMessageDialog(MainWindow.this, re);
        }
    }

    /** Returns an ImageIcon, or null if the path was invalid. */
    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = MainWindow.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
}
