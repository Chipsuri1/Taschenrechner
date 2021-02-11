package calculator;


import calculator.tokens.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

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
        try {
            scanner.getNextToken();
        }catch (Exception exception){
            Assertions.assertEquals(RuntimeException.class, exception.getClass());
        }
    }
}
