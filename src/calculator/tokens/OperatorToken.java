package calculator.tokens;

public class OperatorToken extends Token
{
    private final char operator;

    public OperatorToken(char operator)
    {
        switch (operator) {
            case '+' -> this.tag = Tag.PLUS;
            case '-' -> this.tag = Tag.MINUS;
            case '*' -> this.tag = Tag.TIMES;
            case '/' -> this.tag = Tag.DIVIDE;
            case '^' -> this.tag = Tag.POWER;
            default -> throw new RuntimeException("Syntax error!");
        }
        this.operator = operator;
    }

    public char getOperator() {
        return this.operator;
    }
}