package calculator.tokens;

public class EndOfStreamToken extends Token
{
    public EndOfStreamToken()
    {
        this.tag = Tag.END_OF_STREAM;
    }
}