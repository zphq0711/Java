package persistence;

import model.Book;
import model.SharingLibrary;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Represents a reader that reads sharing library from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads sharing-library from file and returns it;
    // throws IOException if an error occurs reading data from file
    public SharingLibrary read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseSharingLibrary(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses sharing-library from JSON object and returns it
    private SharingLibrary parseSharingLibrary(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        SharingLibrary sl = new SharingLibrary(name);
        addBooks(sl, jsonObject);
        return sl;
    }

    // MODIFIES: wr
    // EFFECTS: parses books from JSON object and adds them to sharing-library
    private void addBooks(SharingLibrary sl, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("books");
        for (Object json : jsonArray) {
            JSONObject nextThingy = (JSONObject) json;
            addBook(sl, nextThingy);
        }
    }

    // MODIFIES: wr
    // EFFECTS: parses book from JSON object and adds it to sharing-library
    private void addBook(SharingLibrary sl, JSONObject jsonObject) {
        String bookName = jsonObject.getString("name");
        String authorName = jsonObject.getString("author");
        String location = jsonObject.getString("location");
        String status = jsonObject.getString("status");
        Book book = new Book(bookName, authorName, location, status);
        sl.importBook(book);
    }

}
