package in.ushatech;

import in.ushatech.annotation.NotThreadSafe;

@NotThreadSafe
public class MutableInteger
{
    // out-of-thin-air-safety means the thread will read a value which was written by some other thread
    private int value;

    // out-of-thin-air-safety
    // means the thread will read a value which was written by some other thread
    // due to 64 bit operations done in 2 parts of 32 bit operations
    // so it is possible a read could be from 2 different writes with different 32 bit operation
    // so you can see value which was not intended to be written by any previous writes

    // The solution is to declare them volatile or guarded by a lock


    // The locking provided the visbility

    // In multithreaded environment do not use shared mutable long and double values unless
    // they are guared by lock or declared volatile


    public int getValue()
    {
        return value;
    }

    public void setValue(int value)
    {
        this.value = value;
    }
}
