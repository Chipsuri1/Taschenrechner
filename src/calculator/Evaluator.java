package calculator;

import calculator.syntaxtree.Visitor;
import calculator.syntaxtree.nodes.BinOpNode;
import calculator.syntaxtree.nodes.IntegerNode;
import calculator.syntaxtree.nodes.UnaryOpNode;

public class Evaluator implements Visitor {
    @Override
    public void visit(IntegerNode node) {
        //Falls der aktuelle Syntaxknoten ein Integer-Knoten (d.h. ein Blattknoten) ist tue nichts (, da bereits beim Anlegen des Integer-Knotens der aktuelle Zahlenwert gespeichert wird)
    }

    @Override
    public void visit(BinOpNode node) {
        //Falls der aktuelle Syntaxknoten ein unärer Operator-Knoten ist speichere -n im aktuellen Syntaxknoten, wobei n diejenige Zahl ist, die im direkten Kindknoten abgespeichert ist
    }

    @Override
    public void visit(UnaryOpNode node) {
        //Falls der aktuelle Syntaxknoten ein binärer Operator-Knoten ist◦speichere n op m im aktuellen Syntaxknoten, wobei n der Wert des linken direkten Kindknotens ist und m der Wert des rechten direkten Kindknotens
    }
}
