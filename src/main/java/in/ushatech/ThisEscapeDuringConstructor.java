package in.ushatech;

public class ThisEscapeDuringConstructor
{
    private Thread someImportantWork;

    public ThisEscapeDuringConstructor()
    {
        Runnable r = () -> System.out.println("Inside constructor");
        someImportantWork = new Thread(r);

        // lot of other time taking task

        //        someImportantWork.start();
        // Do now start the thread inside the constructor as the object is still not
        // created and the state is not safe
    }

    // instead use a method to start the thread  from an instance method
    // this method should not be private or final
    public void initialize()
    {
        someImportantWork.start();
    }
}
