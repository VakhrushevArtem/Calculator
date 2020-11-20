import services.*;

import java.io.IOException;
import java.util.Queue;

public class Calculator {
    static String mathematicalExpression;

    public static void run() {
        ConsoleWriter.printInitialMessage();
        try {
            while (!(mathematicalExpression = ConsoleReader.getMathematicalExpression()).equalsIgnoreCase("exit")) {
                try {
                    Validator.isCorrectly(mathematicalExpression);
                    ConverterToPostfix converterToPostfix = new ConverterToPostfix();
                    Queue<String> postfixQueue = converterToPostfix.convertToPostfix(mathematicalExpression);
                    CalculatorOperations calculatorOperations = new CalculatorOperations();
                    double result = calculatorOperations.calculateMathematicalExpression(postfixQueue);
                    ConsoleWriter.printResultOfMathematicalExpression(result);
                } catch (Exception e) {
                    ConsoleWriter.printError(e);
                }
                ConsoleWriter.printInitialMessage();
            }
        } catch (IOException e) {
            ConsoleWriter.printError(e);
        }
    }

    public static void main(String[] args) {
        run();
    }
}
