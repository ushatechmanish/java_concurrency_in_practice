package in.ushatech;

import in.ushatech.annotation.NotImmutable;

@NotImmutable
public class Holder
{
    private int n;

    public Holder(int n)
    {
        this.n = n;
    }

    public void assertSanity()
    {
        if (n != n) throw new AssertionError("The statement is false");
    }
}
