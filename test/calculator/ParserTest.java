package calculator;

import calculator.syntaxtree.Visitable;
import calculator.syntaxtree.nodes.BinOpNode;
import calculator.syntaxtree.nodes.IntegerNode;
import calculator.syntaxtree.nodes.UnaryOpNode;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class ParserTest {

    @Test
    public void testSuccess()
    {
        String arithmeticExpression = "(4 + 5) * 7";
        Scanner scanner = new Scanner(arithmeticExpression);
        Parser parser = new Parser(scanner);
        Visitable expectedTree = null;
        Visitable generatedTree = null;
        assert(equals(expectedTree, generatedTree));
    }
    @Test
    public void testExpression()
    {
        Scanner scanner = new Scanner("5 - 3");
        Parser parser = new Parser(scanner);
        Visitable generatedTree = parser.expr(new BinOpNode("",null,null));
        Visitable exspectedTree = new BinOpNode("-",new IntegerNode(5),new IntegerNode(3));
        Assertions.assertTrue(equals(generatedTree,exspectedTree));
        System.out.println(((BinOpNode)generatedTree).operator);
        System.out.println(((BinOpNode)generatedTree).left);
        System.out.println(((BinOpNode)generatedTree).right);
    }

    @Test
    public void testFailure()
    {

    }

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
}
