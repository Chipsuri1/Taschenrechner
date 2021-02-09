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
        node.setVariable(getVariableName(0));
        incrementCounter();

        codeLines.add(node.getVariable() + " = " + node.getValue());
    }

    @Override
    public void visit(UnaryOpNode node){
        node.setVariable(getVariableName(0));
        incrementCounter();

        codeLines.add(node.getVariable() + " = " + node.getOperator() + getVariableName(1));
    }

    @Override
    public void visit(BinOpNode node){
        node.setVariable(getVariableName(0));
        incrementCounter();

        codeLines.add(node.getVariable() + " = " + getVariableName(1) + " " + node.getOperator() + " " + getVariableName(2));
    }

    private String getVariableName(int offSet){
        return "X" + (counter-offSet);
    }

    private void incrementCounter(){
        counter++;
    }

    public List<String> getCodeLines() {
        return codeLines;
    }
}
