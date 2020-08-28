import services.ConsoleReader;
import services.ConsoleWriter;
import services.ConverterToPostfix;
import services.Validator;

import java.io.IOException;
import java.util.Queue;
import java.util.Stack;

public class Calculator {
    Stack<Double> stack = new Stack<>();
    static String mathematicalExpression;

    private boolean isOperator(String s) {
        return s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/");
    }

    private double mathematicalOperation(double i, double j, String operator) {
        switch (operator) {
            case "+":
                return j + i;
            case "-":
                return j - i;
            case "*":
                return j * i;
            default:
                if (i == 0) {
                    throw new ArithmeticException("Деление на ноль запрещено.");
                }
                return j / i;
        }
    }

    public double calculateMathematicalExpression(Queue<String> queue) {
        while (!queue.isEmpty()) {
            if(isOperator(queue.peek())) {
                stack.push(mathematicalOperation(stack.pop(), stack.pop(), queue.poll()));
            } else {
                stack.push(Double.parseDouble(queue.poll()));
            }
        }
        return stack.pop();
    }

    public static void main(String[] args) {
        ConsoleWriter.printInitialMessage();
        try {
           mathematicalExpression = ConsoleReader.getMathematicalExpression();
        } catch (IOException e) {
            ConsoleWriter.printError(e);
        }

        while (!mathematicalExpression.equalsIgnoreCase("exit")) {
            try {
                Validator.isCorrectly(mathematicalExpression);
                Calculator calculator = new Calculator();
                ConverterToPostfix converterToPostfix = new ConverterToPostfix();
                Queue<String> stringQueue = converterToPostfix.convertToInfix(mathematicalExpression);
                double result = calculator.calculateMathematicalExpression(stringQueue);
                ConsoleWriter.printResultOfMathematicalExpression(result);
            } catch (Exception e) {
                ConsoleWriter.printError(e);
            }

            ConsoleWriter.printInitialMessage();

            try {
                mathematicalExpression = ConsoleReader.getMathematicalExpression();
            } catch (IOException e) {
                ConsoleWriter.printError(e);
            }
        }
    }
}
