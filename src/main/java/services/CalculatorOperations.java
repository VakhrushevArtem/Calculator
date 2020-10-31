package services;

import java.io.IOException;
import java.util.Queue;
import java.util.Stack;

public class CalculatorOperations {
    Stack<Double> stack = new Stack<>();
    static String mathematicalExpression;

    private boolean isOperator(String s) {
        return s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/");
    }

    private double sum(double i, double j) {
        return j + i;
    }

    private double subtract(double i, double j) {
        return j - i;
    }

    private double multiply(double i, double j) {
        return j * i;
    }

    private double div(double i, double j) {
        if (i == 0) {
            throw new ArithmeticException("Деление на ноль запрещено.");
        }
        return j / i;
    }

    private double mathematicalOperation(double i, double j, String operator) {
        switch (operator) {
            case "+":
                return sum(i, j);
            case "-":
                return subtract(i, j);
            case "*":
                return multiply(i, j);
            case "/":
                return div(i, j);
            default:
                throw new IllegalArgumentException("Операции с оператором \"" + operator + "\" нет.");
        }
    }

    private double calculateMathematicalExpression(Queue<String> queue) {
        while (!queue.isEmpty()) {
            if (isOperator(queue.peek())) {
                stack.push(mathematicalOperation(stack.pop(), stack.pop(), queue.poll()));
            } else {
                stack.push(Double.parseDouble(queue.poll()));
            }
        }
        return stack.pop();
    }

    public void calculate() {
        ConsoleWriter.printInitialMessage();
        try {
            while (!(mathematicalExpression = ConsoleReader.getMathematicalExpression()).equalsIgnoreCase("exit")) {
                try {
                    Validator.isCorrectly(mathematicalExpression);
                    ConverterToPostfix converterToPostfix = new ConverterToPostfix();
                    Queue<String> postfixQueue = converterToPostfix.convertToPostfix(mathematicalExpression);
                    double result = calculateMathematicalExpression(postfixQueue);
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
}
