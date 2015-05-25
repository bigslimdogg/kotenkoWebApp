package mvc.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Nick on 16.05.2015.
 */
public class DataDao {

    private Connection connection;
    private PreparedStatement preparedStatement;

    public Connection getConnection() {
        return connection;
    }

    public PreparedStatement getPreparedStatement() {
        return preparedStatement;
    }

    DataDao(Connection connection){
        this.connection = connection;
    }

    public void addTypeOfCable(String attribute) throws SQLException {
        if(attribute == null)
            throw new NullPointerException();
        preparedStatement = connection.prepareStatement("INSERT INTO cable_type(type_name) VALUE (?);");
        preparedStatement.setString(1, attribute);
        preparedStatement.execute();
    }
    public void addIp(String attribute) throws SQLException {
        if(attribute == null)
            throw new NullPointerException();
        preparedStatement = connection.prepareStatement("INSERT INTO ip(ip_name) VALUE (?)");
        preparedStatement.setString(1, attribute);
        preparedStatement.execute();
    }
    public void addWrongIp(String attribute) throws SQLException {
        if(attribute == null)
            throw new NullPointerException();
        preparedStatement = connection.prepareStatement("INSERT INTO wrong_ip(ip_name) VALUE (?)");
        preparedStatement.setString(1, attribute);
        preparedStatement.execute();
    }
    public void delleteWrongIp(String attribute) throws SQLException {
        if(attribute == null)
            throw new NullPointerException();
        preparedStatement = connection.prepareStatement("DELETE FROM wrong_ip WHERE ip_name = ?");
        preparedStatement.setString(1, attribute);
        preparedStatement.execute();
    }
    public void delleteIp(String attribute) throws SQLException {
        if(attribute == null)
            throw new NullPointerException();
        preparedStatement = connection.prepareStatement("DELETE FROM ip WHERE ip_name = ?");
        preparedStatement.setString(1, attribute);
        preparedStatement.execute();
    }
    public void delleteTypeOfCable(String attribute) throws SQLException {
        if (attribute == null)
            throw new NullPointerException();
        preparedStatement = connection.prepareStatement("DELETE FROM cable_type WHERE type_name = ?");
        preparedStatement.setString(1, attribute);
        preparedStatement.execute();
    }


}
