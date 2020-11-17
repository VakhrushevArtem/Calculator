package services;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class CalculatorOperationsTest {
    Double expectedResult;
    Queue<String> postfixQueue;
    Queue<String> postfixQueueWithDivisionByZero = new LinkedList<String>(Arrays.asList("3", "4", "0", "/", "+"));
    CalculatorOperations calculatorOperations;

    public CalculatorOperationsTest(Double expectedResult, Queue<String> postfixQueue) {
        this.expectedResult = expectedResult;
        this.postfixQueue = postfixQueue;
    }

    @Parameterized.Parameters
    public static Collection initialize() {
        return Arrays.asList(new Object[][]{
                {22.0, new LinkedList<String>(Arrays.asList("2", "5", "4", "*", "+"))},
                {16.0, new LinkedList<String>(Arrays.asList("7", "3", "-", "6", "2", "*", "+"))},
                {9.0, new LinkedList<String>(Arrays.asList("24", "5", "-", "10", "-"))}
        });
    }

    @Before
    public void initializeCalculatorOperations() {
        calculatorOperations = new CalculatorOperations();
    }

    @Test
    @DisplayName("Тест метода calculateMathematicalExpression(Queue<String> queue).")
    public void calculateMathematicalExpressionTest() {
        assertEquals(expectedResult, calculatorOperations.calculateMathematicalExpression(postfixQueue), 0.001);
    }

    @Test(expected = ArithmeticException.class)
    @DisplayName("Тест метода calculateMathematicalExpression(Queue<String> queue) c делением на нуль.")
    public void calculateMathematicalExpressionWithDivisionByZero() {
        new CalculatorOperations().calculateMathematicalExpression(postfixQueueWithDivisionByZero);
    }
}