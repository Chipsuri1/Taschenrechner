package calculator.tokens;

public class SeparatorToken extends Token {
    private final char symbol;

    public SeparatorToken(char symbol) {
        if (symbol != '(' && symbol != ')') {
            throw new RuntimeException("Syntax error!");
        }
        this.tag = symbol == '('
                ? Tag.OPENING_BRACKET : Tag.CLOSING_BRACKET;
        this.symbol = symbol;
    }

    public char getSymbol() {
        return this.symbol;
    }
}
