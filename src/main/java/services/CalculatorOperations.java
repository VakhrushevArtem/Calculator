package services;

import java.util.Queue;
import java.util.Stack;

public class CalculatorOperations {
    Stack<Double> stack = new Stack<>();


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
            default:
                return div(i, j);
        }
    }

    public double calculateMathematicalExpression(Queue<String> queue) {
        while (!queue.isEmpty()) {
            if (isOperator(queue.peek())) {
                stack.push(mathematicalOperation(stack.pop(), stack.pop(), queue.poll()));
            } else {
                stack.push(Double.parseDouble(queue.poll()));
            }
        }
        return stack.pop();
    }
}
