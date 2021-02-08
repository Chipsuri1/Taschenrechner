package calculator;

import calculator.tokens.EndOfStreamToken;
import calculator.tokens.Token;

public class Scanner
{
    private /*final*/ String arithmeticExpression;
    private int peek;
    public Scanner(String arithmeticExpression)
    {
        if (arithmeticExpression == null)
        {
            throw new RuntimeException("Syntax error !");
        }
        // remove all white spaces to simplify extracting tokens --------------
        arithmeticExpression = arithmeticExpression.replaceAll("\\s+", "");
        if (arithmeticExpression.isEmpty())
        {
            throw new RuntimeException("Syntax error !");
        }
        // initialize members -------------------------------------------------
        this.arithmeticExpression = arithmeticExpression;
        peek = 0;
    }

    public Token getNextToken()
    {
        // end of arithmetic expression reached -------------------------------
        if (peek >= arithmeticExpression.length())
        {
            return new EndOfStreamToken();
        }
        // extract token ------------------------------------------------------
        return null;
    }
}
