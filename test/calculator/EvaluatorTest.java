package calculator;

import calculator.syntaxtree.DepthFirstIterator;
import calculator.syntaxtree.Visitable;
import calculator.syntaxtree.Visitor;
import calculator.syntaxtree.nodes.BinOpNode;
import calculator.syntaxtree.nodes.IntegerNode;
import calculator.syntaxtree.nodes.SyntaxNode;
import calculator.syntaxtree.nodes.UnaryOpNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EvaluatorTest {

    Visitable hardCodedSyntaxTree;

    @Test
    public void testEvaluationRightExpected() {
        generateSyntaxTree();
        int expectedResult = -1;// expected result of evaluation
        int actualResult;

        Evaluator evaluator = new Evaluator();
        DepthFirstIterator.traverse(hardCodedSyntaxTree, evaluator);
        actualResult = ((SyntaxNode) hardCodedSyntaxTree).getValue();

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testEvaluationWrongExpected() {
        generateSyntaxTree();
        int notExpectedResult = -2;// not expected result of evaluation
        int actualResult;

        Evaluator evaluator = new Evaluator();
        DepthFirstIterator.traverse(hardCodedSyntaxTree, evaluator);
        actualResult = ((SyntaxNode) hardCodedSyntaxTree).getValue();

        assertNotEquals(notExpectedResult, actualResult);
    }

    private void generateSyntaxTree() {
        hardCodedSyntaxTree = new UnaryOpNode("-", (new BinOpNode("/", new IntegerNode(55), new BinOpNode("+", new IntegerNode(44), new IntegerNode(5)))));
    }



    @Test
    public void testEvaluationAddition(){
        hardCodedSyntaxTree = new BinOpNode("+", new IntegerNode(10), new IntegerNode(20));
        int expectedResult = 30;// expected result of evaluation
        int actualResult;

        Evaluator evaluator = new Evaluator();
        DepthFirstIterator.traverse(hardCodedSyntaxTree, evaluator);
        actualResult = ((SyntaxNode) hardCodedSyntaxTree).getValue();

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testEvaluationSubtraction(){
        hardCodedSyntaxTree = new BinOpNode("-", new IntegerNode(10), new IntegerNode(20));
        int expectedResult = -10;// expected result of evaluation
        int actualResult;

        Evaluator evaluator = new Evaluator();
        DepthFirstIterator.traverse(hardCodedSyntaxTree, evaluator);
        actualResult = ((SyntaxNode) hardCodedSyntaxTree).getValue();

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testEvaluationMultiplication(){
        hardCodedSyntaxTree = new BinOpNode("*", new IntegerNode(10), new IntegerNode(20));
        int expectedResult = 200;// expected result of evaluation
        int actualResult;

        Evaluator evaluator = new Evaluator();
        DepthFirstIterator.traverse(hardCodedSyntaxTree, evaluator);
        actualResult = ((SyntaxNode) hardCodedSyntaxTree).getValue();

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testEvaluationDivision(){
        hardCodedSyntaxTree = new BinOpNode("/", new IntegerNode(10), new IntegerNode(20));
        int expectedResult = 0;// expected result of evaluation
        int actualResult;

        Evaluator evaluator = new Evaluator();
        DepthFirstIterator.traverse(hardCodedSyntaxTree, evaluator);
        actualResult = ((SyntaxNode) hardCodedSyntaxTree).getValue();

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testEvaluationPower(){
        hardCodedSyntaxTree = new BinOpNode("^", new IntegerNode(10), new IntegerNode(3));
        int expectedResult = 1000;// expected result of evaluation
        int actualResult;

        Evaluator evaluator = new Evaluator();
        DepthFirstIterator.traverse(hardCodedSyntaxTree, evaluator);
        actualResult = ((SyntaxNode) hardCodedSyntaxTree).getValue();

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testEvaluationException(){
        hardCodedSyntaxTree = new BinOpNode("a", new IntegerNode(10), new IntegerNode(3));

        Evaluator evaluator = new Evaluator();
        Assertions.assertThrows(RuntimeException.class, ()->{
            DepthFirstIterator.traverse(hardCodedSyntaxTree, evaluator);
        });

    }
}
