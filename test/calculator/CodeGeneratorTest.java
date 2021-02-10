package calculator;

import calculator.syntaxtree.DepthFirstIterator;
import calculator.syntaxtree.Visitable;
import calculator.syntaxtree.nodes.BinOpNode;
import calculator.syntaxtree.nodes.IntegerNode;
import calculator.syntaxtree.nodes.UnaryOpNode;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CodeGeneratorTest {

    @Test
    public void testCodeGeneration() {
        Visitable syntaxTree = generateSyntaxTree();
        List<String> expectedCode = new ArrayList<String>();
        expectedCode.add("X1 = 44");
        expectedCode.add("X2 = 5");
        expectedCode.add("X3 = X1 + X2");
        expectedCode.add("X4 = -X3");

        CodeGenerator generator = new CodeGenerator();
        DepthFirstIterator.traverse(syntaxTree, generator);
        assertEquals(expectedCode, generator.getCodeLines());
    }

    private Visitable generateSyntaxTree(){
        Visitable syntaxTree = new UnaryOpNode("-", new BinOpNode("+", new IntegerNode(44), new IntegerNode(5)));
        return syntaxTree;
    }

}
