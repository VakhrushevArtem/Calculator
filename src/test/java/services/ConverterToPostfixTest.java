package services;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.*;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class ConverterToPostfixTest {
    private String mathematicalExpression;
    private Queue<String> postfixMathematicalExpression;
    private String emptyMathematicalExpression = "";

    public ConverterToPostfixTest(String mathematicalExpression, Queue<String> postfixMathematicalExpression) {
        this.mathematicalExpression = mathematicalExpression;
        this.postfixMathematicalExpression = postfixMathematicalExpression;
    }

    @Parameterized.Parameters
    public static Collection initialize() {
        return Arrays.asList(new Object[][] {
                {"2 + 5 * 4", new LinkedList<>(Arrays.asList(new String[] {"2", "5", "4", "*", "+"}))},
                {"7 - 3 + 6 * 2", new LinkedList<>(Arrays.asList(new String[] {"7", "3", "-", "6", "2", "*", "+"}))},
                {"24 - 5 - 10", new LinkedList<>(Arrays.asList(new String[] {"24", "5", "-", "10", "-"}))}
        });
    }

    @Test(expected = IllegalArgumentException.class)
    @DisplayName("Тест метода convertToPostfix(String mathematicalExpression) с пустым математическим выражением")
    public void convertToPostfixWithException() {
        new ConverterToPostfix().convertToPostfix(emptyMathematicalExpression);
    }

    @Test
    @DisplayName("Тест метода convertToPostfix(String mathematicalExpression)")
    public void convertToPostfixTest() {
        assertEquals(postfixMathematicalExpression, new ConverterToPostfix().convertToPostfix(mathematicalExpression));
    }
}

