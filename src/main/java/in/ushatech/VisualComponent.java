package in.ushatech;

import in.ushatech.annotation.ThreadSafe;

import java.util.EventListener;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

// As there are no invariants between 2 instance variables and concurrent data structure is in use
// it is multi thread safe class
@ThreadSafe
public class VisualComponent
{
    private final List<EventListener> keyListeners = new CopyOnWriteArrayList<>();
    private final List<EventListener> mouseListeners = new CopyOnWriteArrayList<>();

    public void addKeyListener(EventListener listener)
    {
        keyListeners.add(listener);
    }

    public void removeMouseListener(EventListener listener)
    {
        keyListeners.remove(listener);
    }

    public void removeKeyListener(EventListener listener)
    {
        keyListeners.remove(listener);
    }

    public void addMouseListener(EventListener listener)
    {
        keyListeners.add(listener);
    }
}
