package calculator;

import calculator.syntaxtree.Visitor;
import calculator.syntaxtree.nodes.BinOpNode;
import calculator.syntaxtree.nodes.IntegerNode;
import calculator.syntaxtree.nodes.SyntaxNode;
import calculator.syntaxtree.nodes.UnaryOpNode;


public class Evaluator implements Visitor {

    @Override
    public void visit(IntegerNode node) {
        //If integerNode then do nothing
    }

    @Override
    public void visit(BinOpNode node) {
        //if BinOpNode setValue (n op m): n: Value of left node, m: Value of right node, op: Operator of BinOpNode
        if (node.getRight() instanceof SyntaxNode && node.getLeft() instanceof SyntaxNode) {
            SyntaxNode leftIntegerNode = (SyntaxNode) node.getLeft();
            SyntaxNode rightIntegerNode = (SyntaxNode) node.getRight();
            switch (node.getOperator()) {
                case "+":
                    node.setValue(leftIntegerNode.getValue() + rightIntegerNode.getValue());
                    break;
                case "-":
                    node.setValue(leftIntegerNode.getValue() - rightIntegerNode.getValue());
                    break;
                case "*":
                    node.setValue(leftIntegerNode.getValue() * rightIntegerNode.getValue());
                    break;
                case "/":
                    node.setValue(leftIntegerNode.getValue() / rightIntegerNode.getValue());
                    break;
                case "^":
                    node.setValue((int) Math.pow(leftIntegerNode.getValue(), rightIntegerNode.getValue()));
                    break;
                default:
                    throw new RuntimeException(("BinOpNode - forbidden Operator: " + node.operator));
            }
        } else {
            throw new RuntimeException("Error - Node cannot be cast to UnaryOpNode!");
        }
    }

    @Override
    public void visit(UnaryOpNode node) {
        //if unaryOpNode setValue (-n) n: Value of subNode
        if (node.subNode instanceof SyntaxNode) {
            SyntaxNode subNode = (SyntaxNode) node.subNode;
            switch (node.getOperator()) {
                case "-":
                    node.setValue(-subNode.getValue());
                    break;
                default:
                    throw new RuntimeException(("UnaryOpNode - forbidden Operator: " + node.operator));
            }
        } else {
            throw new RuntimeException("Error - Node cannot be cast to UnaryOpNode!");
        }
    }
}
