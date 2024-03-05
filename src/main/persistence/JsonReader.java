package persistence;

import model.Records;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// Represents a reader that reads Records from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads records from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Records read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseRecords(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses records from JSON object and returns it
    private Records parseRecords(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        Records r = new Records(name);
        addRecords(r, jsonObject);
        return r;
    }

    // MODIFIES: r
    // EFFECTS: parses categories from JSON object
    private void addRecords(Records r, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("Records");
        for (Object json : jsonArray) {
            JSONObject record = (JSONObject) json;
            addCategory(r, record);
        }
    }

    // MODIFIES: r
    // EFFECTS: parses workout and weight from JSON object and adds it to records
    private void addCategory(Records r, JSONObject record) {
        if (record.has("push")) {
            JSONObject innerObject = record.getJSONObject("push");
            String workout = innerObject.getString("Workout");
            String weight = innerObject.getString("Weight");

            r.addWorkoutToRecord("push", workout, weight);

        } else if (record.has("pull")) {
            JSONObject innerObject = record.getJSONObject("pull");
            String workout = innerObject.getString("Workout");
            String weight = innerObject.getString("Weight");

            r.addWorkoutToRecord("pull", workout, weight);

        } else if (record.has("legs")) {
            JSONObject innerObject = record.getJSONObject("legs");
            String workout = innerObject.getString("Workout");
            String weight = innerObject.getString("Weight");

            r.addWorkoutToRecord("legs", workout, weight);

        }
    }

}
