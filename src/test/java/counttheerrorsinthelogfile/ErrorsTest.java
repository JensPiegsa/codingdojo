package counttheerrorsinthelogfile;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import java.io.*;
import java.util.*;

class ErrorsTest {

    // https://www.codewars.com/kata/69c3e9e48e6febc70467113d

    @Test
    @Order(1)
    @DisplayName("Single Error")
    void singleError() {
        writeLog("ERROR: Disk_Failure");

        LinkedHashMap<String, Integer> result = Errors.mapErrors();

        assertTrue(result.containsKey("disk_failure"), "Expected error 'disk_failure' to be present");
        assertEquals(1, result.get("disk_failure"), "The error 'disk_failure' should have a count of 1");
    }

    @Test
    @Order(2)
    @DisplayName("Multiple Errors")
    void multipleErrors() {
        writeLog(
                "ERROR: Network_Down\n" +
                        "ERROR: Timeout\n" +
                        "ERROR: Network_Down\n" +
                        "ERROR: Timeout\n" +
                        "ERROR: Network_Down"
        );

        LinkedHashMap<String, Integer> result = Errors.mapErrors();

        assertTrue(result.containsKey("network_down"), "Expected error 'network_down' to be present");
        assertTrue(result.containsKey("timeout"), "Expected error 'timeout' to be present");
        assertEquals(3, result.get("network_down"), "The error 'network_down' should appear three times");
        assertEquals(2, result.get("timeout"), "The error 'timeout' should appear twice");

        List<Integer> counts = new ArrayList<>(result.values());
        for (int i = 1; i < counts.size(); i++)
            assertTrue(counts.get(i - 1) >= counts.get(i), "Error counts are not in descending order");
    }

    @Test
    @Order(3)
    @DisplayName("Errors with Same Count")
    void sameCountErrors() {
        writeLog(
                "ERROR: Error_A\n" +
                        "ERROR: Error_B\n" +
                        "ERROR: Error_A\n" +
                        "ERROR: Error_B\n" +
                        "ERROR: Error_C"
        );

        LinkedHashMap<String, Integer> result = Errors.mapErrors();

        assertTrue(result.containsKey("error_a"), "Expected error 'error_a' to be present");
        assertTrue(result.containsKey("error_b"), "Expected error 'error_b' to be present");
        assertTrue(result.containsKey("error_c"), "Expected error 'error_c' to be present");
        assertEquals(2, result.get("error_a"), "The error 'error_a' should appear twice");
        assertEquals(2, result.get("error_b"), "The error 'error_b' should appear twice");
        assertEquals(1, result.get("error_c"), "The error 'error_c' should appear once");

        List<Integer> counts = new ArrayList<>(result.values());
        for (int i = 1; i < counts.size(); i++)
            assertTrue(counts.get(i - 1) >= counts.get(i), "Error counts are not in descending order");
    }

    @Test
    @Order(4)
    @DisplayName("Errors with Different Counts")
    void differentCountsOrder() {
        writeLog(
                "ERROR: alpha\n" +
                        "ERROR: beta\n" +
                        "ERROR: beta\n" +
                        "ERROR: gamma\n" +
                        "ERROR: gamma\n" +
                        "ERROR: gamma\n" +
                        "ERROR: delta"
        );

        LinkedHashMap<String, Integer> result = Errors.mapErrors();

        assertTrue(result.containsKey("alpha"), "Expected error 'alpha' to be present");
        assertTrue(result.containsKey("beta"), "Expected error 'beta' to be present");
        assertTrue(result.containsKey("gamma"), "Expected error 'gamma' to be present");
        assertTrue(result.containsKey("delta"), "Expected error 'delta' to be present");

        assertEquals(1, result.get("alpha"), "The error 'alpha' should appear once");
        assertEquals(2, result.get("beta"), "The error 'beta' should appear twice");
        assertEquals(3, result.get("gamma"), "The error 'gamma' should appear three times");
        assertEquals(1, result.get("delta"), "The error 'delta' should appear once");

        List<Integer> counts = new ArrayList<>(result.values());
        for (int i = 1; i < counts.size(); i++)
            assertTrue(counts.get(i - 1) >= counts.get(i), "Error counts are not in descending order");
    }

    void writeLog(String content) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("server.log"));
            bw.write(content);
            bw.close();
        } catch (IOException e) {

        }
    }

    @BeforeEach
    void setup() throws IOException {
        File f = new File("server.log");
        if (!f.exists())
            f.createNewFile();
    }

    @AfterEach
    void cleanup() {
        File f = new File("server.log");
        f.delete();
    }

}