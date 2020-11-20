package services;

import exception.ValidationException;


public class Validator {
    public static boolean isCorrectly(String mathematicalExpression) throws ValidationException {
        if (mathematicalExpression.matches(".*[a-zA-Z].*") || mathematicalExpression.contains("=")) {
            throw new ValidationException("Выражение содержит недопустимые символы.");
        }
        if (mathematicalExpression.contains("(") || mathematicalExpression.contains(")")) {
            throw new ValidationException("Использование скобок недопустимо");
        }
        String temp = mathematicalExpression.replace(" ", "");
        if (temp.matches(".*[+\\-*^\\\\]{2,}.*")) {
            throw new ValidationException("Выражение записано неверно");
        }
        if (mathematicalExpression.startsWith("-")) {
            throw new ValidationException("Использование отрицательных чисел недопустимо");
        }
        return true;
    }
}
