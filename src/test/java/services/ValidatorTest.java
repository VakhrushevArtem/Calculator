package services;

import exception.ValidationException;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class ValidatorTest {
    private String testMathematicalExpression;
    private final String correctlyMathematicalExpression = "2 + 2 * 2";

    public ValidatorTest(String testMathematicalExpression) {
        this.testMathematicalExpression = testMathematicalExpression;
    }

    @Parameterized.Parameters
    public static List<Object[]> getMathematicalExpression() {
        return Arrays.asList(new Object[][] {
                {"2 + + 2"},
                {"test"},
                {"-2 + 2"},
                {"(3 + 4)"}
        });
    }

    @Test (expected = ValidationException.class)
    @DisplayName("Тест метода isCorrectly() с выбросом исключения.")
    public void testIsCorrectlyWithValidationException() throws ValidationException{
        Validator.isCorrectly(testMathematicalExpression);
    }

    @Test
    @DisplayName("Тест метода isCorrectly().")
    public void testIsCorrectly() throws ValidationException{
        assertTrue("Неверная запись математического выражения", Validator.isCorrectly(correctlyMathematicalExpression));
    }
}