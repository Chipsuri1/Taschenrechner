package calculator;


import calculator.tokens.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class ScannerTest {

    @Test
    public void testCreateFailNull(){
        try {
            Scanner scanner = new Scanner(null);
        }catch (Exception exception){
            Assertions.assertEquals(RuntimeException.class, exception.getClass());
        }

    }

    @Test
    public void testGetIntegerToken(){
        Scanner scanner = new Scanner("555");
        Token nextToken = scanner.getNextToken();
        IntegerToken integerToken = (IntegerToken) nextToken;
        Assertions.assertEquals(555,integerToken.getValue());
        nextToken = scanner.getNextToken();
        Assertions.assertEquals(EndOfStreamToken.class, nextToken.getClass());
    }

    @Test
    public void testGetOperator(){
        Scanner scanner = new Scanner("+");
        Token nextToken = scanner.getNextToken();
        OperatorToken operatorToken = (OperatorToken) nextToken;
        Assertions.assertEquals(Tag.PLUS, operatorToken.getTag());
        nextToken = scanner.getNextToken();
        Assertions.assertEquals(EndOfStreamToken.class, nextToken.getClass());
    }
    @Test
    public void testIntegerOverflow(){
        Scanner scanner = new Scanner("2147483647666777");
        Assertions.assertThrows(RuntimeException.class ,scanner::getNextToken);
    }

    @Test
    public void testSeparatorTokens(){
        Scanner scanner = new Scanner("()");
        Token nextToken = scanner.getNextToken();
        Assertions.assertEquals(Tag.OPENING_BRACKET, nextToken.getTag());
        nextToken = scanner.getNextToken();
        Assertions.assertEquals(Tag.CLOSING_BRACKET, nextToken.getTag());
    }

    @Test
    public void testSimple(){
        Scanner scanner = new Scanner("5+5");
        Token nextToken = scanner.getNextToken();
        IntegerToken integerToken = (IntegerToken) nextToken;
        Assertions.assertEquals(5,integerToken.getValue());
        nextToken = scanner.getNextToken();
        OperatorToken operatorToken = (OperatorToken) nextToken;
        Assertions.assertEquals(Tag.PLUS, operatorToken.getTag());
        nextToken = scanner.getNextToken();
        integerToken = (IntegerToken) nextToken;
        Assertions.assertEquals(5, integerToken.getValue());
        nextToken = scanner.getNextToken();
        Assertions.assertEquals(EndOfStreamToken.class, nextToken.getClass());
    }

    @Test
    public void testFailSimple(){
        Scanner scanner = new Scanner("4+x");
        scanner.getNextToken();
        scanner.getNextToken();
        Assertions.assertThrows(RuntimeException.class, scanner::getNextToken);

    }
    @Test
    public void testComplex(){
        Scanner scanner = new Scanner("(5+8)-8*17+(8/5)");
        ArrayList<Token> tokenList = getAllTokens(scanner);
        Assertions.assertEquals(getList(),tokenList);

    }
    @Test
    public void testFail(){
        Scanner scanner = new Scanner("5 + [4 + 5]");
        scanner.getNextToken();
        scanner.getNextToken();
        Assertions.assertThrows(RuntimeException.class, scanner::getNextToken);
    }

    private ArrayList<Token> getAllTokens(Scanner scanner){
        ArrayList<Token> tokenList = new ArrayList<>();
        Token nextToken = scanner.getNextToken();
        tokenList.add(nextToken);
        while(nextToken.getTag() != Tag.END_OF_STREAM){
            nextToken = scanner.getNextToken();
            tokenList.add(nextToken);
        }
        return tokenList;
    }

    private ArrayList<Token> getList(){
        ArrayList<Token> list = new ArrayList<Token>();
        list.add(new SeparatorToken('('));
        list.add(new IntegerToken(5));
        list.add(new OperatorToken('+'));
        list.add(new IntegerToken(8));
        list.add(new SeparatorToken(')'));
        list.add(new OperatorToken('-'));
        list.add(new IntegerToken(8));
        list.add(new OperatorToken('*'));
        list.add(new IntegerToken(17));
        list.add(new OperatorToken('+'));
        list.add(new SeparatorToken('('));
        list.add(new IntegerToken(8));
        list.add(new OperatorToken('/'));
        list.add(new IntegerToken(5));
        list.add(new SeparatorToken(')'));
        list.add(new EndOfStreamToken());
        return list;
    }

}
