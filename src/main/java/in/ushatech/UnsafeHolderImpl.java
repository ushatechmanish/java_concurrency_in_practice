package in.ushatech;

import in.ushatech.annotation.NotThreadSafe;

@NotThreadSafe
public class UnsafeHolderImpl
{
    public NotImmutableHolder holder; // Since this is public , it may be accessed

    // before initialize is run . Partially constructed object may be accessible
    // Even after initialize the visibility issues will be there
    public void initialize()
    {
        holder = new NotImmutableHolder(42);
    }
}
