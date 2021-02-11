package calculator;

import calculator.tokens.*;

public class Scanner {
    private final String arithmeticExpression;
    private int peek;

    public Scanner(String arithmeticExpression) {
        if (arithmeticExpression == null) {
            throw new RuntimeException("Syntax error !");
        }
        // remove all white spaces to simplify extracting tokens --------------
        arithmeticExpression = arithmeticExpression.replaceAll("\\s+", "");
        if (arithmeticExpression.isEmpty()) {
            throw new RuntimeException("Syntax error !");
        }
        // initialize members -------------------------------------------------
        this.arithmeticExpression = arithmeticExpression;
        peek = 0;
    }

    public Token getNextToken() {
        // end of arithmetic expression reached -------------------------------
        if (peek >= arithmeticExpression.length()) {
            return new EndOfStreamToken();
        }
        // extract token ------------------------------------------------------

        if (Character.isDigit(arithmeticExpression.toCharArray()[peek])) {
            long value = (long) Character.getNumericValue(arithmeticExpression.toCharArray()[peek]);
            int loopCounter = 1;
            while (Character.isDigit(arithmeticExpression.toCharArray()[peek + loopCounter])) {
                value *= (long) 10;
                value += (long) Character.getNumericValue(arithmeticExpression.toCharArray()[peek + loopCounter]);
                if ((long) Integer.MAX_VALUE < value) {
                    throw new RuntimeException("Syntax Error");
                }
                loopCounter++;
                if (peek + loopCounter >= arithmeticExpression.length()) {
                    break;
                }
            }
            peek += loopCounter;
            return new IntegerToken((int) value);
        }
        switch (arithmeticExpression.toCharArray()[peek]) {
            case '+', '-', '*', '/', '^' -> {
                OperatorToken operatorToken = new OperatorToken(arithmeticExpression.toCharArray()[peek]);
                peek++;
                return operatorToken;
            }
            case '(', ')' -> {
                SeparatorToken separatorToken = new SeparatorToken(arithmeticExpression.toCharArray()[peek]);
                peek++;
                return separatorToken;
            }
        }

        throw new RuntimeException("Syntax error");
    }
}
