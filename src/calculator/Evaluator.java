package calculator;

import calculator.syntaxtree.Visitor;
import calculator.syntaxtree.nodes.BinOpNode;
import calculator.syntaxtree.nodes.IntegerNode;
import calculator.syntaxtree.nodes.SyntaxNode;
import calculator.syntaxtree.nodes.UnaryOpNode;

public class Evaluator implements Visitor {

    @Override
    public void visit(IntegerNode node) {
        //Falls der aktuelle Syntaxknoten ein Integer-Knoten (d.h. ein Blattknoten) ist tue nichts (, da bereits beim Anlegen des Integer-Knotens der aktuelle Zahlenwert gespeichert wird)
    }

    @Override
    public void visit(BinOpNode node) {
        //Falls der aktuelle Syntaxknoten ein binärer Operator-Knoten ist◦speichere n op m im aktuellen Syntaxknoten, wobei n der Wert des linken direkten Kindknotens ist und m der Wert des rechten direkten Kindknotens
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
        //Falls der aktuelle Syntaxknoten ein unärer Operator-Knoten ist speichere -n im aktuellen Syntaxknoten, wobei n diejenige Zahl ist, die im direkten Kindknoten abgespeichert ist
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
