package calculator.tokens;

public class OperatorToken extends Token
{
    private final char operator;

    public OperatorToken(char operator)
    {
        switch (operator)
        {
            case '+': this.tag = Tag.PLUS; break;
            case '-': this.tag = Tag.MINUS; break;
            case '*': this.tag = Tag.TIMES; break;
            case '/': this.tag = Tag.DIVIDE; break;
            case '^': this.tag = Tag.POWER; break;
            default: throw new RuntimeException("Syntax error!");
        }
        this.operator = operator;
    }

    public char getOperator() {
        return this.operator;
    }
}