package calculator.syntaxtree.nodes;

import calculator.syntaxtree.Visitable;
import calculator.syntaxtree.Visitor;

public class UnaryOpNode extends SyntaxNode implements Visitable {

    public String operator;
    public Visitable subNode;

    public UnaryOpNode(String operator, Visitable subNode){
        this.operator = operator;
        this.subNode = subNode;
    }


    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public String getOperator() {
        return operator;
    }

    @Override
    public Integer getValue() {
        return super.getValue();
    }
}
