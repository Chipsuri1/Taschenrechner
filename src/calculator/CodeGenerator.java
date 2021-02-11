package calculator;



import calculator.syntaxtree.nodes.BinOpNode;
import calculator.syntaxtree.nodes.IntegerNode;
import calculator.syntaxtree.Visitor;
import calculator.syntaxtree.nodes.SyntaxNode;
import calculator.syntaxtree.nodes.UnaryOpNode;

import java.util.ArrayList;
import java.util.List;

public class CodeGenerator implements Visitor {

    private int counter = 1;
    private final List<String> codeLines = new ArrayList<>();

    @Override
    public void visit(IntegerNode node){
        node.setVariable(getVariableName());
        incrementCounter();

        codeLines.add(node.getVariable() + " = " + node.getValue());
    }

    @Override
    public void visit(UnaryOpNode node){
        node.setVariable(getVariableName());
        incrementCounter();

        SyntaxNode subNode = (SyntaxNode) node.getSubNode();

        codeLines.add(node.getVariable() + " = " + node.getOperator() + subNode.getVariable());
    }

    @Override
    public void visit(BinOpNode node){
        node.setVariable(getVariableName());
        incrementCounter();

        IntegerNode leftIntegerNode = (IntegerNode)node.getLeft();
        IntegerNode rightIntegerNode = (IntegerNode)node.getRight();

        codeLines.add(node.getVariable() + " = " + leftIntegerNode.getVariable() + " " + node.getOperator() + " " + rightIntegerNode.getVariable());
    }

    private String getVariableName(){
        return "X" + counter;
    }

    private void incrementCounter(){
        counter++;
    }

    public List<String> getCodeLines() {
        return codeLines;
    }
}
