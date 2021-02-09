package calculator.syntaxtree;

public interface Visitable {

    void accept(Visitor visitor);
}
