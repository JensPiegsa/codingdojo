package gildedrose;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GoldenMasterTest {

    @Test

    public void testCreateGoldenMaster() throws FileNotFoundException {
        System.setOut(new PrintStream(
                new FileOutputStream(new File("goldenMaster.txt"))
        ));
        TexttestFixture.main(new String[]{"16"});
    }

    @Test
    public void testCompareGoldenMaster() throws IOException {

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(
                out
        ));
        TexttestFixture.main(new String[]{"16"});
        File file = new File("goldenMaster.txt");
        assertEquals(new String(Files.readAllBytes(file.toPath())),new String(out.toByteArray()));

    }
}
