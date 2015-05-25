package mvc.model.dao;

import mvc.model.abstract_model.ActiveElement;
import mvc.model.network.Network;
import mvc.model.pe_model.PathElement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Nick on 16.05.2015.
 */
public class ConnectionDao {

    private Connection connection;
    private PreparedStatement preparedStatement;

    public PreparedStatement getPreparedStatement() {
        return preparedStatement;
    }

    public Connection getConnection() {
        return connection;
    }

    ConnectionDao(Connection connection){
        this.connection = connection;
    }

    public void createConnection(ActiveElement el1, PathElement el2) throws Exception {
        if(el1 == null || el2 == null)
            throw new NullPointerException();
        preparedStatement = connection.prepareStatement("INSERT INTO connections(first_elem, second_elem) VALUES (?,?)");
        preparedStatement.setInt(1, el1.getID());
        preparedStatement.setInt(2, el2.getID());
        preparedStatement.execute();
        el1.connect(el2);
    }
    public void deleteConnections(ActiveElement el1, PathElement el2) throws SQLException {
        if(el2 == null)
            throw new NullPointerException();
        preparedStatement = connection.prepareStatement("DELETE FROM connections WHERE second_elem =?");
        preparedStatement.setInt(1,el2.getID());
        preparedStatement.execute();
        el1.disConnect(el2);
    }
    public void readAllConnections(Network net) throws Exception {
        for(PathElement elem : net.getPathElements().keySet()) {
            if (elem instanceof ActiveElement) {
                ActiveElement elem1 = (ActiveElement) elem;
                preparedStatement = connection.prepareStatement("SELECT second_elem FROM connections WHERE first_elem = ?");
                preparedStatement.setInt(1, elem1.getID());
                ResultSet rs = preparedStatement.executeQuery();
                while (rs.next()) {
                    for (PathElement elemToconnect : net.getPathElements().keySet()) {
                        if (elemToconnect.getID() == rs.getInt("second_elem"))
                            elem1.connect(elemToconnect);
                    }

                }
            }
        }
    }
}
