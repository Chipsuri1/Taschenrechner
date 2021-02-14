package calculator;

import calculator.syntaxtree.DepthFirstIterator;
import calculator.syntaxtree.Visitable;
import calculator.syntaxtree.nodes.SyntaxNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class CalculatorTest {

    @Test
    public void calculatorTest(){

        int actualIntegerResult;
        int expectedIntegerResult = -49;
        String arithmeticExpression = "-(44 + 5)";
        Scanner scanner = new Scanner(arithmeticExpression);
        Parser parser = new Parser(scanner);
        Evaluator evaluator = new Evaluator();
        CodeGenerator codeGenerator = new CodeGenerator();
        List<String> expectedCode = new ArrayList<String>();

        Visitable generatedTree = parser.start();
        DepthFirstIterator.traverse(generatedTree, evaluator);
        actualIntegerResult = ((SyntaxNode) generatedTree).getValue();

        expectedCode.add("X1 = 44");
        expectedCode.add("X2 = 5");
        expectedCode.add("X3 = X1 + X2");
        expectedCode.add("X4 = -X3");

        DepthFirstIterator.traverse(generatedTree, codeGenerator);

        Assertions.assertEquals(actualIntegerResult,expectedIntegerResult);
        Assertions.assertEquals(expectedCode,codeGenerator.getCodeLines());
    }
}
