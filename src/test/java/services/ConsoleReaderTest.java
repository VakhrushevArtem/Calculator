package services;

import org.junit.*;
import org.junit.jupiter.api.DisplayName;
import java.io.*;

import static org.junit.jupiter.api.Assertions.*;


public class ConsoleReaderTest {
    private static final InputStream CONSOLE_STREAM = System.in;
    private static final String INPUT_TEST_TEXT = "test";


    @BeforeClass
    public static void changeSystemIn() {
        System.setIn(new ByteArrayInputStream(INPUT_TEST_TEXT.getBytes()));
    }

    @Test
    @DisplayName("Тест метода ConsoleReader.getMathematicalExpression()")
    public void getMathematicalExpressionTest() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            assertArrayEquals(INPUT_TEST_TEXT.getBytes(), reader.readLine().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @AfterClass
    public static void returnSystemIn() {
        System.setIn(CONSOLE_STREAM);
    }
}