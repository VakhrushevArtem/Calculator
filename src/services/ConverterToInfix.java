package services;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class ConverterToInfix {
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

    public Queue<String> convertToInfix(String mathematicalExpression) {
        String[] expression = mathematicalExpression.split(" ");
        for (String incomingElement : expression) {
            if (isOperator(incomingElement)) {
                if (stack.empty()) {
                    stack.push(incomingElement);
                } else if (getPriority(incomingElement) > getPriority(stack.peek())) {
                    stack.push(incomingElement);
                } else {
                    while (getPriority(stack.peek()) > getPriority(incomingElement)) {
                        queue.add(stack.pop());
                    }
                }
            } else {
                queue.add(incomingElement);
            }
        }
        return queue;
    }
}
