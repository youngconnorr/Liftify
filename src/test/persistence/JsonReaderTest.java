package persistence;

import model.Records;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class JsonReaderTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Records r = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyRecords() {
        JsonReader reader = new JsonReader("./data/testWriterEmptyRecords.json");
        try {
            Records r = reader.read();
            assertEquals("Your Records", r.getName());
            assertEquals(0, r.getPullRecords().size());
            assertEquals(0, r.getPushRecords().size());
            assertEquals(0, r.getLegsRecords().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralRecords() {
        try {
            JsonReader reader = new JsonReader("./data/testWriterGeneralRecords.json");
            Records r = reader.read();
            assertEquals("Your Records", r.getName());
            assertEquals(1, r.getPushRecords().size());
            assertEquals(1, r.getPullRecords().size());
            assertEquals(1, r.getLegsRecords().size());
            assertEquals("150", r.getPushRecords().get("bench"));
            assertEquals("200", r.getPullRecords().get("lat pulldowns"));
            assertEquals("230", r.getLegsRecords().get("squat"));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}