package in.ushatech;

import in.ushatech.annotation.NotImmutable;

@NotImmutable
public class NotImmutableHolder
{
    private int n;

    public NotImmutableHolder(int n)
    {
        this.n = n;
    }

    public void assertSanity()
    {
        if (n != n) throw new AssertionError("The statement is false");
    }
}
