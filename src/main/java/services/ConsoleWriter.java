package services;

public class ConsoleWriter {
    public static void printInitialMessage() {
        System.out.print("Введите математическое значение, либо наберите \"exit\" для выхода из программы: ");
    }

    public static void printError(Exception e) {
        System.out.println("Ошибка при вычислении выражения. " + e.getMessage());
    }

    public static void printResultOfMathematicalExpression(double result) {
        System.out.printf("Результат математического выражения: %.2f\n", result);
    }
}
