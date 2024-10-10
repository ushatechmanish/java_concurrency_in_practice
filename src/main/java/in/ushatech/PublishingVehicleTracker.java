package in.ushatech;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * No explicit synchronization
 * Uses ConcurrentHashMap functionality of copy on write
 */
public class PublishingVehicleTracker
{
    // Used only for internal mutation in a thread safe with fine grained locking
    // to improve efficiency
    private final ConcurrentMap<String, SafePoint> locations;

    // Read only but backed collection for showing live view of the vehicles
    private final Map<String, SafePoint> unmodifiableMap;

    public PublishingVehicleTracker(Map<String, SafePoint> SafePoints)
    {
        locations = new ConcurrentHashMap<String, SafePoint>(SafePoints);
        // backed map
        unmodifiableMap = Collections.unmodifiableMap(locations);
    }

    // live view
    // bit possible risk of modification of the state of objects inside map
    // This is a trade off between the live view vs no leakage
    public synchronized Map<String, SafePoint> getLocations()
    {
        return unmodifiableMap;
    }

    public synchronized SafePoint getLocation(String id)
    {
        return locations.get(id);
    }

    public synchronized void setLocation(String id, int x, int y)
    {

        if (!locations.containsKey(id))
        {
            throw new IllegalArgumentException("No such id" + id);
        }
        locations.put(id, new SafePoint(x, y));

    }


}
