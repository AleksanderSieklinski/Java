package code;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

class MathExpressionException extends Exception {
    public MathExpressionException(String message) {
        super(message);
    }
}
class SyntaxErrorException extends MathExpressionException {
    public SyntaxErrorException(String symbol) {
        super("Syntax error: " + symbol + " expected");
    }
}
class ZeroDivisionException extends MathExpressionException {
    public ZeroDivisionException() {
        super("Zero division error");
    }
}
class UndefinedVariableException extends MathExpressionException {
    public UndefinedVariableException(String variable) {
        super("Runtime error: " + variable + " undefined");
    }
}
class IntegerLimitException extends MathExpressionException {
    public IntegerLimitException() {
        super("Integer limit (>32-bit required)");
    }
}
class IllegalCharacterException extends MathExpressionException {
    public IllegalCharacterException() {
        super("Illegal characters");
    }
}
class MathExpressionEvaluator {
    private static int getResult(String expression) throws IntegerLimitException, SyntaxErrorException {
        int result = 0;
        String[] parts = expression.split("[+\\-*/=]");
        for (String part : parts) {
            try {
                int number = Integer.parseInt(part.trim());
                if (expression.startsWith(part)) {
                    result = number;
                } else {
                    char operator = expression.charAt(expression.indexOf(part) - 1);
                    switch (operator) {
                        case '+':
                            result += number;
                            break;
                        case '-':
                            result -= number;
                            break;
                        case '*':
                            result *= number;
                            break;
                        case '/':
                            result /= number;
                            break;
                        default:
                            throw new SyntaxErrorException(String.valueOf(operator));
                    }
                }
            } catch (NumberFormatException e) {
                throw new IntegerLimitException();
            }
        }
        return result;
    }
    public int evaluate(String expression) throws MathExpressionException {
        for (char c : expression.toCharArray()) {
            if (!Character.isDigit(c) && "+-*/()= ".indexOf(c) == -1) {
                if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) {
                    throw new UndefinedVariableException(String.valueOf(c));
                } else {
                    throw new IllegalCharacterException();
                }
            }
        }
        if (expression.contains("/0")) {
            throw new ZeroDivisionException();
        }
        if (!expression.endsWith("=")) {
            throw new SyntaxErrorException("=");
        }
        if (expression.contains("(") && !expression.contains(")")) {
            throw new SyntaxErrorException(")");
        }
        if (expression.contains(")") && !expression.contains("(")) {
            throw new SyntaxErrorException("(");
        }
        return getResult(expression);
    }
}

public class FileAnalyzer {
    private final MathExpressionEvaluator evaluator = new MathExpressionEvaluator();
    public void evaluateFile(String filePath) {
        try (Scanner scanner = new Scanner(new File(filePath))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                try {
                    int result = evaluator.evaluate(line);
                    System.out.println(result);
                } catch (MathExpressionException e) {
                    System.out.println(e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
    public static void main(String[] args) {
        FileAnalyzer analyzer = new FileAnalyzer();
        analyzer.evaluateFile("src/main/resources/expressions.txt");
    }
}