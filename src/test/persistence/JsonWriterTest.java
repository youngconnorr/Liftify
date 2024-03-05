package persistence;

import model.Records;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class JsonWriterTest {
    //NOTE TO CPSC 210 STUDENTS: the strategy in designing tests for the JsonWriter is to
    //write data to a file and then use the reader to read it back in and check that we
    //read in a copy of what was written out.

    @Test
    void testWriterInvalidFile() {
        try {
            Records r = new Records("Your Records");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            Records r = new Records("Your Records");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyWorkroom.json");
            writer.open();
            writer.write(r);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyWorkroom.json");
            r = reader.read();
            assertEquals("Your Records", r.getName());
            assertEquals(0, r.getPullRecords().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            Records r = new Records("Your Records");
            r.addWorkoutToRecord("push", "bench", "150");
            r.addWorkoutToRecord("pull", "lat pulldowns", "200");
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralWorkroom.json");
            writer.open();
            writer.write(r);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralWorkroom.json");
            r = reader.read();
            assertEquals("Your Records", r.getName());
            assertEquals(1, r.getPushRecords().size());
            assertEquals(1, r.getPullRecords().size());
            assertEquals("150", r.getPushRecords().get("bench"));
            assertEquals("200", r.getPullRecords().get("lat pulldowns"));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}