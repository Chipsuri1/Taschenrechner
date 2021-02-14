package calculator;

import calculator.syntaxtree.DepthFirstIterator;
import calculator.syntaxtree.Visitable;
import calculator.syntaxtree.Visitor;
import calculator.syntaxtree.nodes.BinOpNode;
import calculator.syntaxtree.nodes.IntegerNode;
import calculator.syntaxtree.nodes.SyntaxNode;
import calculator.syntaxtree.nodes.UnaryOpNode;
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

    private Visitable generateSyntaxTree() {
        hardCodedSyntaxTree = new UnaryOpNode("-", (new BinOpNode("/", new IntegerNode(55), new BinOpNode("+", new IntegerNode(44), new IntegerNode(5)))));
        return hardCodedSyntaxTree;
    }
}
