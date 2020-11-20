package services;

import org.junit.*;
import java.io.*;

import static org.junit.Assert.*;


public class ConsoleReaderTest {
    private static final InputStream CONSOLE_STREAM = System.in;
    private static final String INPUT_TEST_TEXT = "test";


    @BeforeClass
    public static void changeSystemIn() {
        System.setIn(new ByteArrayInputStream(INPUT_TEST_TEXT.getBytes()));
    }

    @Test
    public void getMathematicalExpressionTest() {
        try {
            assertEquals(INPUT_TEST_TEXT, ConsoleReader.getMathematicalExpression());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @AfterClass
    public static void returnSystemIn() {
        System.setIn(CONSOLE_STREAM);
    }
}