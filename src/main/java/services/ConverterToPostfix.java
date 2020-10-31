package services;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class ConverterToPostfix {
    Stack<String> stack = new Stack<>();
    Queue<String> queue = new LinkedList<>();

    private boolean isOperator(String s) {
        return (s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/"));
    }

    private int getPriority(String s) {
        if (s.equals("/") || s.equals("*")) {
            return 2;
        } else {
            return 1;
        }
    }

    public Queue<String> convertToPostfix(String mathematicalExpression) {
        if (mathematicalExpression.isEmpty()) {
            throw new IllegalArgumentException("Математическое выражение не может быть пустое.");
        }
        String[] expression = mathematicalExpression.split(" ");
        for (String incomingElement : expression) {
            if (isOperator(incomingElement)) {
                if (stack.empty()) {
                    stack.push(incomingElement);
                } else if (getPriority(incomingElement) > getPriority(stack.peek())) {
                    stack.push(incomingElement);
                } else {
                    do {
                        queue.add(stack.pop());
                        if (stack.isEmpty()) {
                            break;
                        }
                    }
                    while (getPriority(stack.peek()) > getPriority(incomingElement));
                    stack.push(incomingElement);
                }
            } else {
                queue.add(incomingElement);
            }
        }

        while (!stack.isEmpty()) {
            queue.add(stack.pop());
        }
        return queue;
    }
}
