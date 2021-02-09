package calculator.syntaxtree;

import calculator.syntaxtree.nodes.BinOpNode;
import calculator.syntaxtree.nodes.IntegerNode;
import calculator.syntaxtree.nodes.UnaryOpNode;

public class DepthFirstIterator {
    public static void traverse(Visitable root, Visitor visitor){
        if(root instanceof IntegerNode){

        }
        if(root instanceof BinOpNode){

        }
        if(root instanceof UnaryOpNode){

        }

        throw new RuntimeException("Instance root has bad type!");
    }
}
