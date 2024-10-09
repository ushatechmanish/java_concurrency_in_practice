package in.ushatech;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;

public class ThreadConfinementJdbcExample
{
    private static ArrayBlockingQueue<Connection> connectionPool = new ArrayBlockingQueue<>();

    static
    {
        for (int i = 0; i < 10; i++)
        {
            try
            {
                Connection connection = DriverManager.getConnection("jdbc:yourdb://localhost:3306/db", "user",
                        "password");
                connectionPool.add(connection);
            } catch (SQLException e)
            {
                e.printStackTrace();
            }

        }
    }

    static public Connection getConnection() throws InterruptedException
    {
        return connectionPool.take();
    }

    static public void returnConnection(Connection connection)
    {
        connectionPool.offer(connection);
    }

    // Thread confinement as only one connection will be used to process one request and no sharing is allowed
    static void processRequest()
    {
        try
        {
            Connection connection = getConnection();
            System.out.println("Using connection for processing");
            returnConnection(connection);

        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }


    }


}
