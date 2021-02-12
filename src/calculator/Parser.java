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

    public Visitable expr(Visitable subTree)
    {
        Tag tag = lookahead.getTag();
        if(tag == Tag.INTEGER || tag == Tag.MINUS || tag == Tag.OPENING_BRACKET)
        {
            Visitable syntaxTree = rest_expr(term(null));
            match(Tag.END_OF_STREAM);
            return syntaxTree;
        }
        throw new RuntimeException("Syntax error expr !");
    }

    private Visitable rest_expr(Visitable parameter)
    {
        Tag tag = lookahead.getTag();
        if (tag == Tag.PLUS) {
            match(Tag.PLUS);
            Visitable subTree = term(null);
            Visitable root = new BinOpNode("+",parameter, subTree);
            return rest_expr(root);
        }
        else if(tag == Tag.MINUS){
            match(Tag.MINUS);
            Visitable subTree = term(null);
            Visitable root = new BinOpNode("-", parameter, subTree);
            return rest_expr(root);
        }
        else if(tag== Tag.CLOSING_BRACKET){
            match(Tag.CLOSING_BRACKET);
            return rest_expr(parameter);
        }
        else if(tag == Tag.END_OF_STREAM){
            return parameter;
        }
        else throw new RuntimeException("Syntax error rest_expr!");
    }
    private Visitable term(Visitable subTree)
    {
        Tag tag = lookahead.getTag();
        if (tag == Tag.INTEGER || tag == Tag.MINUS || tag == Tag.OPENING_BRACKET) {
            Visitable parameter = rest_term(factor(null));
            return rest_expr(parameter);
        }
        else throw new RuntimeException("Syntax error term !");
    }
    private Visitable rest_term(Visitable parameter)
    {
        Tag tag = lookahead.getTag();
        if(tag == Tag.TIMES){
            Visitable subTree = factor(null);
            Visitable root = new BinOpNode("*", parameter, subTree);
            return rest_term(root);
        }
        else if(tag == Tag.DIVIDE){
            match(Tag.DIVIDE);
            Visitable subTree = factor(null);
            Visitable root = new BinOpNode("/", parameter, subTree);
            return rest_term(root);
        }
        else if(tag == Tag.PLUS || tag == Tag.MINUS || tag == Tag.CLOSING_BRACKET || tag == Tag.END_OF_STREAM){
            return parameter;
        }
        else throw new RuntimeException("Syntax error rest_term!");
    }
    private Visitable factor(Visitable parameter)
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
        else throw new RuntimeException("Syntax error factor!");
    }
    private Visitable pot(Visitable parameter)
    {
        Tag tag = lookahead.getTag();
        if(tag == Tag.INTEGER || tag == Tag.OPENING_BRACKET){
            return rest_pot(base(null));
        }
        else throw new RuntimeException("Syntax error pot!");
    }
    private Visitable rest_pot(Visitable parameter)
    {
        Tag tag = lookahead.getTag();
        if(tag == Tag.POWER){
            Visitable subTree = ex(null);
            Visitable root = new BinOpNode("^",parameter,subTree);
            return rest_pot(root);
        }
        else if(tag == Tag.PLUS || tag == Tag.MINUS || tag == Tag.TIMES || tag == Tag.DIVIDE || tag == Tag.CLOSING_BRACKET){
            return parameter;
        }
        else if(tag == Tag.END_OF_STREAM){
            return parameter;
        }
        else throw new RuntimeException("Syntax error rest_pot!");
    }
    private Visitable base( Visitable parameter)
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
        else throw new RuntimeException("Syntax error base !");
    }
    // Nichtterminal Ex
    private Visitable ex(Visitable parameter)
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
        else throw new RuntimeException("Syntax error ex!");
    }
    private void match(Tag tag)
    {
        if (tag == this.lookahead.getTag())
        {
            this.lookahead = this.scanner.getNextToken();
            return;
        }
        throw new RuntimeException("Syntax error match!");
    }

}
