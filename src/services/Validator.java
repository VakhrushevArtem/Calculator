package services;

import exception.ValidationException;

import java.util.Arrays;

public class Validator {
    public static boolean isCorrectly(String mathematicalExpression) throws Exception {
        if (mathematicalExpression.matches("[[:alpha:]]") || mathematicalExpression.contains("=")) {
            throw new ValidationException("Выражение содержит недопустимые символы.");
        }
        if (Arrays.stream(mathematicalExpression.split(" ")).filter("(" ::equals).count() !=
                Arrays.stream(mathematicalExpression.split(" ")).filter(")" ::equals).count()) {
            throw new ValidationException("Неверное количество скобок.");
        }
        mathematicalExpression = mathematicalExpression.replace(" ", "");
        if(mathematicalExpression.matches("[+\\-*^\\\\]{2,}")) {
            throw new ValidationException("Выражение записано неверно");
        }
        return true;
    }
}
