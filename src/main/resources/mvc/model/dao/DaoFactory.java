package mvc.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Nick on 15.05.2015.
 */
public abstract class DaoFactory {


    private static final String URL = "jdbc:mysql://localhost:3306/kotenkonetwork";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    private static final String DRIVER = "com.mysql.jdbc.Driver";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }



    public static ModelDao getModelDao(Connection connection){
        return new ModelDao(connection);
    }
    public static DataDao getDataDao (Connection connection){return new DataDao(connection);}
    public static ConnectionDao getConnectionDao (Connection connection){return new ConnectionDao(connection);}

    public DaoFactory() {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }




}
