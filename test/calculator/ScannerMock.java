package calculator;

import calculator.tokens.EndOfStreamToken;
import calculator.tokens.Token;

import java.util.ArrayList;

public class ScannerMock extends Scanner{

    private int index;
    private ArrayList<Token> tokenList;

    public ScannerMock(String arithmeticExpression) {
        super(arithmeticExpression);
        index = 0;
    }

    public void prepareForTest(ArrayList<Token> tokenList){
        this.tokenList = tokenList;
    }
    @Override
    public Token getNextToken(){
        if(index >= tokenList.size())
        {
            return new EndOfStreamToken();
        }
        Token nextToken = tokenList.get(index);
        index++;
        return nextToken;
    }
}
