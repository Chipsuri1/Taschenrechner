package calculator.syntaxtree;

import calculator.syntaxtree.nodes.BinOpNode;
import calculator.syntaxtree.nodes.IntegerNode;
import calculator.syntaxtree.nodes.UnaryOpNode;

public interface Visitor {

    public void visit(IntegerNode node);
    public void visit(BinOpNode node);
    public void visit(UnaryOpNode node);

}
