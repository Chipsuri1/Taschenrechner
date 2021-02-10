package calculator.syntaxtree;

import calculator.syntaxtree.nodes.BinOpNode;
import calculator.syntaxtree.nodes.IntegerNode;
import calculator.syntaxtree.nodes.UnaryOpNode;

public class DepthFirstIterator {
    public static void traverse(Visitable root, Visitor visitor) {
        if (root instanceof IntegerNode) {
            root.accept(visitor);
            return;
        }
        if (root instanceof BinOpNode) {
            BinOpNode opNode = (BinOpNode) root;
            DepthFirstIterator.traverse(opNode.left, visitor);
            DepthFirstIterator.traverse(opNode.right, visitor);
            opNode.accept(visitor);
            return;
        }
        if (root instanceof UnaryOpNode) {
            UnaryOpNode opNode = (UnaryOpNode) root;
            DepthFirstIterator.traverse(opNode.subNode, visitor);
            opNode.accept(visitor);
            return;
        }

        throw new RuntimeException("Instance root has bad type!");
    }
}
