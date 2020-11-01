package services;

import exception.ValidationException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class ConsoleWriterTest {
    private static final PrintStream CONSOLE_STREAM = System.out;
    private static String messageFromConsole;
    private static ByteArrayOutputStream outputStreamArray;

    @Before
    public void changeSystemOut() {
        outputStreamArray = new ByteArrayOutputStream();
        PrintStream printStreamInArray = new PrintStream(outputStreamArray);
        System.setOut(printStreamInArray);
    }

    @Test
    @DisplayName("Тест метода ConsoleWriter.printInitialMessage()")
    public void printInitialMessage() {
        ConsoleWriter.printInitialMessage();
        messageFromConsole = outputStreamArray.toString();
        assertEquals("Введите математическое значение, либо наберите \"exit\" для выхода из программы: ", messageFromConsole);
    }

    @Test
    @DisplayName("Тест метода ConsoleWriter.printError()")
    public void printError() {
        ConsoleWriter.printError(new ValidationException());
        messageFromConsole = outputStreamArray.toString();
        assertTrue(messageFromConsole.startsWith("Ошибка при вычислении выражения. "));
    }

    @Test
    @DisplayName("Тест метода ConsoleWriter.printResultOfMathematicalExpression()")
    public void printResultOfMathematicalExpression() {
        ConsoleWriter.printResultOfMathematicalExpression(4);
        messageFromConsole = outputStreamArray.toString();
        assertEquals("Результат математического выражения: 4,00\n", messageFromConsole);
    }

    @After
    public void returnSystemOut() {
        System.setOut(CONSOLE_STREAM);
    }
}