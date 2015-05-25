package mvc.model.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Полина on 23.05.2015.
 */
public class DaoConnectionPool {
    private Queue<Connection> connectionFreeQueue;
    private Queue<Connection> connectionBusyQueue;
    private final String URL = "jdbc:mysql://localhost:3306/kotenkonetwork";
    private final String USERNAME = "root";
    private final String PASSWORD = "root";
    private final String DRIVER = "com.mysql.jdbc.Driver";

    private static DaoConnectionPool poolConnection;

    private DaoConnectionPool() {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        connectionFreeQueue = new LinkedList<Connection>();
        connectionBusyQueue = new LinkedList<Connection>();
    }

    public static DaoConnectionPool getInstance() {

        if (poolConnection == null)
            poolConnection = new DaoConnectionPool();
        return poolConnection;
    }

    public void releaseConnection(Connection connection) {
        connectionFreeQueue.add(connection);
        connectionBusyQueue.remove(connection);
    }

    public Connection getConnection() throws SQLException {
        if (connectionFreeQueue.isEmpty())
            connectionFreeQueue.add(java.sql.DriverManager.getConnection(URL, USERNAME, PASSWORD));
        Connection connection = connectionFreeQueue.poll();
        connectionBusyQueue.add(connection);
        return connection;
    }
}
