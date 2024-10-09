package in.ushatech;

import java.awt.*;
import java.util.EventListener;

public class SafeListener
{
    private final EventListener listener;

    //    public SafeListener(EventSource source)
    // make constructor private and remove the source as it is not being used
    private SafeListener()
    {

//        source.registerListener(new EventListener()
//        {
//            public void onEvent(Event e)
//            {
//                doSomething(e);
//            }
//        });
        // 1. create the listener but do not register it in a different method
        listener = new EventListener()
        {
            public void onEvent(Event e)
            {
                doSomething(e);
            }
        };

        // some more code
    }

    // add public static factory method to return the object so that the constructor completes
    public static SafeListener getInstance(EventSource source)
    {
        SafeListener safe = new SafeListener();
        source.registerListener(safe.listener); // listnere registered not in constructor to make sure the this
        // object is not escaped
        return safe;
    }


    private void doSomething(Event e)
    {
        // exposing the method of this causes escape of this as this can be used
        // by other classes to make changes to the internal state of this
    }
}
