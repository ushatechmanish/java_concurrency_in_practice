package in.ushatech;

public class UnsafeStates
{
    private String[] states = new String[]{"AK", "AL"};

    public String[] getStates()
    {
        return states;// this will publish the states
        // correct way is to share a clone of the states
    }
}
