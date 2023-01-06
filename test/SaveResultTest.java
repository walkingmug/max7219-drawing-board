import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SaveResultTest {
    private static final String TEST_FILE_NAME = "test.txt";
    private static final String RESULT_DIR_NAME = "result";
    private static final String RESULT_FILE_NAME = "result.ino";
    private static final String REPLACEMENT_STRING = "replaced";
    private static final String EXPECTED_RESULT_FILE_CONTENTS = "line1\nline2 replaced\nline3";
    private File testFile;
    private File resultDir;
    private File resultFile;

    @Before
    public void setUp() {
        // Create a test file
        testFile = new File(TEST_FILE_NAME);
        resultDir = new File(RESULT_DIR_NAME);
        resultFile = new File(RESULT_DIR_NAME, RESULT_FILE_NAME);
        try {
            testFile.createNewFile();
        } catch (IOException e) {
            fail("Failed to create test file");
        }
    }

    @Test
    public void testSaveResult() {
        // Test that the result directory is created if it doesn't exist
        if (resultDir.exists()) {
            resultDir.delete();
        }

        // Run main with the test file and replacement string
        String[] args = {TEST_FILE_NAME, REPLACEMENT_STRING};
        SaveResult.main(args);

        // Assert that the result directory is created
        assertTrue(resultDir.exists());

        // Test that the result file is created and has the expected contents
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(resultFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
                sb.append("\n");
            }
        } catch (IOException e) {
            fail("Failed to read result file");
        }
        assertEquals(EXPECTED_RESULT_FILE_CONTENTS, sb.toString());
    }

    @After
    public void tearDown() {
        // Clean up by deleting created files
        testFile.delete();
        resultFile.delete();
        resultDir.delete();
    }
}
