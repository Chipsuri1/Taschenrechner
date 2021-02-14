package calculator;

import calculator.syntaxtree.Visitable;
import calculator.syntaxtree.nodes.BinOpNode;
import calculator.syntaxtree.nodes.IntegerNode;
import calculator.syntaxtree.nodes.UnaryOpNode;
import calculator.tokens.IntegerToken;
import calculator.tokens.Tag;
import calculator.tokens.Token;

public class Parser {
    private final Scanner scanner;
    private Token lookahead;

    public Parser(Scanner scanner)
    {
        this.scanner = scanner;
        this.lookahead = this.scanner.getNextToken();
    }
    public Visitable start()
    {
        Visitable syntaxTree = expr(null);
        match(Tag.END_OF_STREAM);
        return syntaxTree;
    }

    public Visitable expr(Visitable subTree)
    {
        Tag tag = lookahead.getTag();
        if(tag == Tag.INTEGER || tag == Tag.MINUS || tag == Tag.OPENING_BRACKET)
        {
            Visitable syntaxTree = rest_expr(term(null));
            return syntaxTree;
        }
        throw new RuntimeException("Syntax error!");
    }

    private Visitable rest_expr(Visitable syntaxTree)
    {
        Tag tag = lookahead.getTag();
        if (tag == Tag.PLUS) {
            match(Tag.PLUS);
            Visitable subTree = term(null);
            Visitable root = new BinOpNode("+",syntaxTree, subTree);
            return rest_expr(root);
        }
        else if(tag == Tag.MINUS){
            match(Tag.MINUS);
            Visitable subTree = term(null);
            Visitable root = new BinOpNode("-", syntaxTree, subTree);
            return rest_expr(root);
        }
        else if(tag== Tag.CLOSING_BRACKET){
            return syntaxTree;
        }
        else if(tag == Tag.END_OF_STREAM){
            return syntaxTree;
        }
        else throw new RuntimeException("Syntax error!");
    }

    private Visitable term(Visitable syntaxTree)
    {
        Tag tag = lookahead.getTag();
        if (tag == Tag.INTEGER || tag == Tag.MINUS || tag == Tag.OPENING_BRACKET) {
            Visitable subTree = rest_term(factor(null));
            return subTree;
        }
        else throw new RuntimeException("Syntax error!");
    }

    private Visitable rest_term(Visitable syntaxTree)
    {
        Tag tag = lookahead.getTag();
        if(tag == Tag.TIMES){
            match(Tag.TIMES);
            Visitable subTree = factor(null);
            Visitable root = new BinOpNode("*", syntaxTree, subTree);
            return rest_term(root);
        }
        else if(tag == Tag.DIVIDE){
            match(Tag.DIVIDE);
            Visitable subTree = factor(null);
            Visitable root = new BinOpNode("/", syntaxTree, subTree);
            return rest_term(root);
        }
        else if(tag == Tag.PLUS || tag == Tag.MINUS || tag == Tag.CLOSING_BRACKET || tag == Tag.END_OF_STREAM){
            return syntaxTree;
        }
        else throw new RuntimeException("Syntax error!");
    }

    private Visitable factor(Visitable syntaxTree)
    {
        Tag tag = lookahead.getTag();
        if(tag == Tag.INTEGER || tag == Tag.OPENING_BRACKET){
            return pot(null);
        }
        else if(tag == Tag.MINUS){
            match(Tag.MINUS);
            Visitable subTree = factor(null);
            Visitable root = new UnaryOpNode("-",subTree);
            return root;
        }
        else throw new RuntimeException("Syntax error!");
    }

    private Visitable pot(Visitable syntaxTree)
    {
        Tag tag = lookahead.getTag();
        if(tag == Tag.INTEGER || tag == Tag.OPENING_BRACKET){
            return rest_pot(base(null));
        }
        else throw new RuntimeException("Syntax error!");
    }

    private Visitable rest_pot(Visitable syntaxTree)
    {
        Tag tag = lookahead.getTag();
        if(tag == Tag.POWER){
            match(Tag.POWER);
            Visitable subTree = ex(null);
            Visitable root = new BinOpNode("^",syntaxTree,subTree);
            return rest_pot(root);
        }
        else if(tag == Tag.PLUS || tag == Tag.MINUS || tag == Tag.TIMES || tag == Tag.DIVIDE || tag == Tag.CLOSING_BRACKET){
            return syntaxTree;
        }
        else if(tag == Tag.END_OF_STREAM){
            return syntaxTree;
        }
        else throw new RuntimeException("Syntax error!");
    }

    private Visitable base( Visitable syntaxTree)
    {
        Tag tag = lookahead.getTag();
        if(tag == Tag.OPENING_BRACKET)
        {
            match(Tag.OPENING_BRACKET);
            Visitable expression = expr(null);
            match(Tag.CLOSING_BRACKET);

            return expression;
        }
        else if(tag == Tag.INTEGER)
        {
            int value = ((IntegerToken)lookahead).getValue();
            match(Tag.INTEGER);
            Visitable integerNode = new IntegerNode(value);
            return integerNode;
        }
        else throw new RuntimeException("Syntax error!");
    }

    private Visitable ex(Visitable syntaxTree)
    {
        Tag tag = lookahead.getTag();
        if(tag == Tag.INTEGER){
            int value = ((IntegerToken)lookahead).getValue();
            match(Tag.INTEGER);
            Visitable integerNode = new IntegerNode(value);
            return integerNode;
        }
        else if(tag == Tag.MINUS){
            match(Tag.MINUS);
            Visitable subTree = ex(null);
            Visitable root = new UnaryOpNode("-",subTree);
            return root;
        }
        else if(tag == Tag.OPENING_BRACKET)
        {
            match(Tag.OPENING_BRACKET);
            Visitable expression = expr(null);
            match(Tag.CLOSING_BRACKET);

            return expression;
        }
        else throw new RuntimeException("Syntax error!");
    }

    private void match(Tag tag)
    {
        if (tag == this.lookahead.getTag())
        {
            this.lookahead = this.scanner.getNextToken();
            return;
        }
        throw new RuntimeException("Syntax error!");
    }

}
