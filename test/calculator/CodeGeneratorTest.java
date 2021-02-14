package calculator;

import calculator.syntaxtree.DepthFirstIterator;
import calculator.syntaxtree.Visitable;
import calculator.syntaxtree.nodes.BinOpNode;
import calculator.syntaxtree.nodes.IntegerNode;
import calculator.syntaxtree.nodes.UnaryOpNode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CodeGeneratorTest {

    CodeGenerator codeGenerator;

    @BeforeEach
    public void setup(){
        codeGenerator = new CodeGenerator();
    }

    @Test
    public void testCodeGeneratorSuccess() {
        Visitable validSyntaxTree = generateValidSyntaxTree();

        List<String> expectedCode = new ArrayList<String>();
        expectedCode.add("X1 = 44");
        expectedCode.add("X2 = 5");
        expectedCode.add("X3 = X1 + X2");
        expectedCode.add("X4 = -X3");

        DepthFirstIterator.traverse(validSyntaxTree, codeGenerator);

        assertEquals(expectedCode, codeGenerator.getCodeLines());
    }

    @Test
    public void testCodeGeneratorFail() {
        Visitable invalidSyntaxTree = generateInvalidSyntaxTree();

        List<String> expectedCode = new ArrayList<String>();
        expectedCode.add("X1 = 44");
        expectedCode.add("X2 = 5");
        expectedCode.add("X3 = X1 + X2");
        expectedCode.add("X4 = -X3");

        DepthFirstIterator.traverse(invalidSyntaxTree, codeGenerator);

        assertNotEquals(expectedCode, codeGenerator.getCodeLines());
    }

    private Visitable generateValidSyntaxTree(){
        Visitable syntaxTreeValid = new UnaryOpNode("-", new BinOpNode("+", new IntegerNode(44), new IntegerNode(5)));
        return syntaxTreeValid;
    }

    private Visitable generateInvalidSyntaxTree(){
        Visitable syntaxTreeInvalid = new UnaryOpNode("-", new BinOpNode("-", new IntegerNode(44), new IntegerNode(5)));
        return syntaxTreeInvalid;
    }

}
