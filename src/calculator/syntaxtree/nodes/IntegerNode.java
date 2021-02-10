package calculator.syntaxtree.nodes;

import calculator.syntaxtree.Visitable;
import calculator.syntaxtree.Visitor;

public class IntegerNode extends SyntaxNode implements Visitable {

    public IntegerNode(int value){
        this.value = value;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public Integer getValue() {
        return super.getValue();
    }
}
