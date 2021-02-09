package calculator;



import calculator.syntaxtree.nodes.BinOpNode;
import calculator.syntaxtree.nodes.IntegerNode;
import calculator.syntaxtree.Visitor;
import calculator.syntaxtree.nodes.UnaryOpNode;

import java.util.ArrayList;
import java.util.List;

public class CodeGenerator implements Visitor {

    private int counter = 1;
    private final List<String> codeLines = new ArrayList<>();

    @Override
    public void visit(IntegerNode node){

    }

    @Override
    public void visit(BinOpNode node){

    }

    @Override
    public void visit(UnaryOpNode node){

    }

    public List<String> getCodeLines() {
        return codeLines;
    }
}
