package mvc.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Nick on 15.05.2015.
 */
public abstract class DaoFactory {

    public static Connection chooseConnectionFromPool(DaoConnectionPool pool){
        Connection connection = null;
        try {
            connection = pool.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        pool.releaseConnection(connection);
        return connection;
    }

    public static ModelDao getModelDao(){
        return new ModelDao(chooseConnectionFromPool(DaoConnectionPool.getInstance()));
    }
    public static DataDao getDataDao (){return new DataDao(chooseConnectionFromPool(DaoConnectionPool.getInstance()));}
    public static ConnectionDao getConnectionDao (){return new ConnectionDao(chooseConnectionFromPool(DaoConnectionPool.getInstance()));}

}
