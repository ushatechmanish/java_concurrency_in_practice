package in.ushatech;
//
//public class Widget
//{
//    public synchronized void doSomething(){}
//}
public class LoginWidget extends Widget
{
    public synchronized void doSomething()
    {
        System.out.println(toString()+ "calling doSomething");
        super.doSomething();
    }
}
/*
* If the intrinsic locks are not reentrant the thread will not be able to aquire lock
* for the super class method and there will be a deadlock
*
* The JVM records the owner and count of the aquisition of a lock
* when the same thread tries to aquire the same lock only the count is increased
* and finally when the aquisition count is 0 , the lock is released for aquisition
* by other threads
*
* */