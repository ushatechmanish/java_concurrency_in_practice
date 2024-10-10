package in.ushatech;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * No explicit synchronization
 * Uses ConcurrentHashMap functionality of copy on write
 */
public class DelegatingVehicleTracker
{
    private final ConcurrentMap<String, Point> locations;

    private final Map<String, Point> unmodifiableMap;

    public DelegatingVehicleTracker(Map<String, Point> points)
    {
        locations = new ConcurrentHashMap<String, Point>(points);
        unmodifiableMap = Collections.unmodifiableMap(locations);
    }

    // live view
    // bit possible risk of modification of the state of objects inside map
    // This is a trade off between the live view vs no leakage
    public synchronized Map<String, Point> getLocations()
    {
        return unmodifiableMap;
    }

    public synchronized Point getLocation(String id)
    {
        return locations.get(id);
    }

    public synchronized void setLocation(String id, int x, int y)
    {

        if (locations.replace(id, new Point(x, y)) == null)
        {
            throw new IllegalArgumentException("No such id" + id);
        }

    }


}
