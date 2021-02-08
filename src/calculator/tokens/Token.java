package calculator.tokens;

public abstract class Token
{
    protected Tag tag;
    public Tag getTag() {return this.tag;}
    @Override
    public boolean equals(Object obj)
    {
        if (! (obj instanceof Token))
        {
            return false;
        }
        Token otherToken = (Token) obj;
        return this.tag == otherToken.tag;
    }
    @Override
    public int hashCode()
    {
        return this.tag.hashCode();
    }
}
