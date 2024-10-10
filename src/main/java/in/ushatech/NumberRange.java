package in.ushatech;

import java.util.concurrent.atomic.AtomicInteger;

public class NumberRange
{
    // Invariant constraint is that the lower < upper
    private final AtomicInteger lower = new AtomicInteger(0);
    private final AtomicInteger upper = new AtomicInteger(10);

    public void setLower(int i)
    {
        // check and set - unsafe operation
        if (i > upper.get())
        {
            throw new IllegalArgumentException("Lower bound must be greater than lower bound");
        }
        lower.set(i);
    }

    public void setUpper(int i)
    {
        // check and set - unsafe operation
        if (i < lower.get())
        {
            throw new IllegalArgumentException("Upper bound must be less than Upper bound");
        }
        upper.set(i);
    }

    public boolean isInRange(int i)
    {
        return i >= lower.get() && i <= upper.get();
    }


}
