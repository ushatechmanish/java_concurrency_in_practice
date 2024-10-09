package in.ushatech;

import java.awt.*;
import java.util.EventListener;

public class ThisEscape
{
    public ThisEscape(EventSource source)
    {
        source.registerListener(new EventListener()
        {
            public void onEvent(Event e)
            {
                doSomething(e);
            }
        });

        // some more lines. The object has already escaped before the construction of object
        // so that state is not correct
    }

    private void doSomething(Event e)
    {
        // exposing the method of this causes escape of this as this can be used
        // by other classes to make changes to the internal state of this
    }
}
