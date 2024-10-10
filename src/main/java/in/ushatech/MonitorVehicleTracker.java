package in.ushatech;

import in.ushatech.annotation.ThreadSafe;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@ThreadSafe
public class MonitorVehicleTracker
{
    private final Map<String, MutablePoint> locations;

    public MonitorVehicleTracker(Map<String, MutablePoint> locations)
    {
        this.locations = deepCopy(locations);
    }

    // possible performance issue if lot of vehicles are there
    public synchronized Map<String, MutablePoint> getLocations()
    {
        return deepCopy(locations);
    }

    public synchronized MutablePoint getLocation(String id)
    {
        MutablePoint location = locations.get(id);
        if (location == null)
        {
            return null;
        }
        // return a copy of the MutablePoint
        return new MutablePoint(locations.get(id));
    }

    public synchronized void setLocation(String id, int x, int y)
    {
        MutablePoint location = locations.get(id);
        if (location == null)
        {
            throw new IllegalArgumentException("No such id" + id);
        }
        location.x = x;
        location.y = y;
    }

    private Map<String, MutablePoint> deepCopy(Map<String, MutablePoint> locations)
    {
        Map<String, MutablePoint> copy = new HashMap<>();
        for (String id : locations.keySet())
        {
            copy.put(id, new MutablePoint(locations.get(id)));
        }
        // possible stale data issue . if the application is ok with not real time location
        // it should not be an issue
        // Benefit is that client can not change the object state
        return Collections.unmodifiableMap(copy);
    }
}
