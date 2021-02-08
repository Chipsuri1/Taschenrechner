package calculator.tokens;

public class IntegerToken extends Token
{
    private final int value;
    public IntegerToken(int value)
    {
        this.tag = Tag.INTEGER;
        this.value = value;
    }
    public int getValue() {return this.value;}
    @Override
    public boolean equals(Object obj)
    {
        if (! (obj instanceof IntegerToken))
        {
            return false;
        }
        IntegerToken otherToken = (IntegerToken) obj;
        return this.value == otherToken.value;
    }
    @Override
    public int hashCode()
    {
        return this.value;
    }
}