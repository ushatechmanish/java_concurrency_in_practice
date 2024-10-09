package in.ushatech;

/*
 * Volatile to be used for status flag to determine when to exit the loop(visibility of own state
 * / or of the object they refer to or indicating an important life cycle event such as initialization
 * or shutdown )
 * Avoid volatile when verifying correctness would require subtle reasoning about the visibility
 * */
public class CorrectUseVolatile
{
    volatile boolean asleep;

    public void useVolatile()
    {
        while (!asleep)
        {
            // do some work ;
        }
    }
}
/*
 * Limitation of volatile
 * increment and decrement operation do not work in volatile
 * Locking can guarantee both visibility and atomicity
 * whereas volatile provide only visibility , no atomicity
 *
 * If the variable is conditionally updated , do not use volatile
 * if it interacts with other state variables , do not use volatile
 * If Locking is required , do not use volatile
 * */
