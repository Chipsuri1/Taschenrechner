package calculator;

import calculator.syntaxtree.Visitable;
import calculator.syntaxtree.nodes.BinOpNode;
import calculator.syntaxtree.nodes.IntegerNode;
import calculator.syntaxtree.nodes.UnaryOpNode;
import calculator.tokens.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class ParserTest {

    //standalone test
    @Test
    public void testSuccessMock()
    {
        String arithmeticExpression = "(4 + 5) * 7";
        ScannerMock mock = new ScannerMock(arithmeticExpression);
        mock.prepareForTest(getListForSuccessMock());
        Parser parser = new Parser(mock);
        Visitable expectedTree = new BinOpNode("*",new BinOpNode("+",new IntegerNode(4),new IntegerNode(5)),new IntegerNode(7));
        Visitable generatedTree = parser.start();
        Assertions.assertTrue(equals(expectedTree, generatedTree));
    }

    // test in combination with scanner
    @Test
    public void testSuccess()
    {
        String arithmeticExpression = "(4 + 5) * 7";
        Scanner scanner = new Scanner(arithmeticExpression);
        Parser parser = new Parser(scanner);
        Visitable expectedTree = new BinOpNode("*",new BinOpNode("+",new IntegerNode(4),new IntegerNode(5)),new IntegerNode(7));
        Visitable generatedTree = parser.start();
        Assertions.assertTrue(equals(expectedTree, generatedTree));
    }

    // standalone test
    @Test
    public void testFailureMock()
    {
        String arithmeticExpression = "(4 + 5) /* 7";
        ScannerMock mock = new ScannerMock(arithmeticExpression);
        mock.prepareForTest(getListForFailureMock());
        Parser parser = new Parser(mock);
        Assertions.assertThrows(RuntimeException.class, parser::start);
    }

    // test in combination with scanner
    @Test
    public void testFailure()
    {
        String arithmeticExpression = "(4 + 5) /* 7";
        Scanner scanner = new Scanner(arithmeticExpression);
        Parser parser = new Parser(scanner);
        Assertions.assertThrows(RuntimeException.class, parser::start);
    }


    // tests of the basic functions
    @Test
    public void testExpressionPlus()
    {
        Scanner scanner = new Scanner("5 + 3");
        Parser parser = new Parser(scanner);
        Visitable generatedTree = parser.start();
        Visitable expectedTree = new BinOpNode("+",new IntegerNode(5),new IntegerNode(3));
        Assertions.assertTrue(equals(generatedTree,expectedTree));
    }

    @Test
    public void testExpressionMinus()
    {
        Scanner scanner = new Scanner("5 - 3");
        Parser parser = new Parser(scanner);
        Visitable generatedTree = parser.start();
        Visitable expectedTree = new BinOpNode("-",new IntegerNode(5),new IntegerNode(3));
        Assertions.assertTrue(equals(generatedTree,expectedTree));
    }

    @Test
    public void testExpressionDivide()
    {
        Scanner scanner = new Scanner("5 / 3");
        Parser parser = new Parser(scanner);
        Visitable generatedTree = parser.start();
        Visitable expectedTree = new BinOpNode("/",new IntegerNode(5),new IntegerNode(3));
        Assertions.assertTrue(equals(generatedTree,expectedTree));
    }

    @Test
    public void testExpressionTimes()
    {
        Scanner scanner = new Scanner("5 * 3");
        Parser parser = new Parser(scanner);
        Visitable generatedTree = parser.start();
        Visitable expectedTree = new BinOpNode("*",new IntegerNode(5),new IntegerNode(3));
        Assertions.assertTrue(equals(generatedTree,expectedTree));
    }

    @Test
    public void testExpressionPower()
    {
        Scanner scanner = new Scanner("5 ^ 3");
        Parser parser = new Parser(scanner);
        Visitable generatedTree = parser.start();
        Visitable expectedTree = new BinOpNode("^",new IntegerNode(5),new IntegerNode(3));
        Assertions.assertTrue(equals(generatedTree,expectedTree));
    }

    // method to compare two syntax trees
    private static boolean equals(Visitable v1, Visitable v2)
    {
        if (v1 == v2)
            return true;
        if (v1 == null)
            return false;
        if (v2 == null)
            return false;
        if (v1.getClass() != v2.getClass())
            return false;
        if (v1.getClass() == IntegerNode.class)
        {
            IntegerNode op1 = (IntegerNode) v1;
            IntegerNode op2 = (IntegerNode) v2;
            return op1.value.equals(op2.value);
        }
        if (v1.getClass() == UnaryOpNode.class)
        {
            UnaryOpNode op1 = (UnaryOpNode) v1;
            UnaryOpNode op2 = (UnaryOpNode) v2;
            return op1.operator.equals(op2.operator) &&
                    equals(op1.subNode, op2.subNode);
        }
        if (v1.getClass() == BinOpNode.class)
        {
            BinOpNode op1 = (BinOpNode) v1;
            BinOpNode op2 = (BinOpNode) v2;
            return op1.operator.equals(op2.operator) &&
                    equals(op1.left, op2.left) &&
                    equals(op1.right, op2.right);
        }
        throw new IllegalStateException("Ungueltiger Knotentyp");
    }

    // methods to generate the simulated tokenLists
    public ArrayList<Token> getListForSuccessMock(){
        ArrayList<Token> tokenList = new ArrayList<Token>();
        tokenList.add(new SeparatorToken('('));
        tokenList.add(new IntegerToken(4));
        tokenList.add(new OperatorToken('+'));
        tokenList.add(new IntegerToken(5));
        tokenList.add(new SeparatorToken(')'));
        tokenList.add(new OperatorToken('*'));
        tokenList.add(new IntegerToken(7));
        tokenList.add(new EndOfStreamToken());

        return tokenList;
    }

    public ArrayList<Token> getListForFailureMock(){
        ArrayList<Token> tokenList = new ArrayList<Token>();
        tokenList.add(new SeparatorToken('('));
        tokenList.add(new IntegerToken(4));
        tokenList.add(new OperatorToken('+'));
        tokenList.add(new IntegerToken(5));
        tokenList.add(new SeparatorToken(')'));
        tokenList.add(new OperatorToken('/'));
        tokenList.add(new OperatorToken('*'));
        tokenList.add(new IntegerToken(7));
        tokenList.add(new EndOfStreamToken());

        return tokenList;
    }
}
