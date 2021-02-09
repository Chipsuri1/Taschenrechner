package calculator.syntaxtree.nodes;

public abstract class SyntaxNode {

    public Integer value;
    public String variable;


    public Integer getValue() {
        return value;
    }

    public String getVariable() {
        return variable;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public void setVariable(String variable) {
        this.variable = variable;
    }
}
