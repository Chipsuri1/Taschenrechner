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

    private static boolean equals(Visitable v1, Visitable v2)
    {
        if (v1 == v2)
            return true;
        if (v1 == null)
            return false;
        if (v2 == null)
            return false;
        if (v1.getClass() != v2.getClass())
            return false;
        if (v1.getClass() == IntegerNode.class)
        {
            IntegerNode op1 = (IntegerNode) v1;
            IntegerNode op2 = (IntegerNode) v2;
            return op1.value.equals(op2.value);
        }
        if (v1.getClass() == UnaryOpNode.class)
        {
            UnaryOpNode op1 = (UnaryOpNode) v1;
            UnaryOpNode op2 = (UnaryOpNode) v2;
            return op1.operator.equals(op2.operator) &&
                    equals(op1.subNode, op2.subNode);
        }
        if (v1.getClass() == BinOpNode.class)
        {
            BinOpNode op1 = (BinOpNode) v1;
            BinOpNode op2 = (BinOpNode) v2;
            return op1.operator.equals(op2.operator) &&
                    equals(op1.left, op2.left) &&
                    equals(op1.right, op2.right);
        }
        throw new IllegalStateException("Invalid Nodetype");
    }
}
