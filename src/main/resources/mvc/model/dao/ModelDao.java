package mvc.model.dao;

import mvc.model.models.*;
import mvc.model.my_exceptions.DaoException;
import mvc.model.network.Network;
import mvc.model.pe_model.PathElement;

import java.net.UnknownHostException;
import java.sql.*;

/**
 * Created by Nick on 16.05.2015.
 */
public class ModelDao {

    private Connection connection;
    private Statement statement;
    private PreparedStatement preparedStatement;

    public Connection getConnection() {
        return connection;
    }

    public Statement getStatement() {
        return statement;
    }



    public PreparedStatement getPreparedStatement() {
        return preparedStatement;
    }



    ModelDao(Connection connection){
        this.connection = connection;
    }

    public int createPc()throws SQLException {
        int id = 0;
        statement = connection.createStatement();
        statement.execute("INSERT INTO pathelement() VALUE();");
        statement.execute("INSERT INTO pc(id_pc) VALUE(last_insert_id()); ");
        ResultSet rs = statement.executeQuery("SELECT last_insert_id() FROM pc");
        while (rs.next()){
            id = rs.getInt(1);
        }
        return id;
    }
    public int createCable()throws SQLException{
        statement = connection.createStatement();
        statement.execute("INSERT INTO pathelement VALUE()");
        statement.execute("INSERT INTO cable(id_cable) VALUE(last_insert_id()); ");
        ResultSet rs = statement.executeQuery("SELECT last_insert_id()");
        if(rs.next())
            return rs.getInt(1);
        else return 0;
    }
    public int createHub()throws SQLException{
        statement = connection.createStatement();
        statement.execute("INSERT INTO pathelement VALUE()");
        statement.execute("INSERT INTO hub(id_hub)  VALUE(last_insert_id()); ");
        ResultSet rs = statement.executeQuery("SELECT last_insert_id()");
        rs.next();
        return rs.getInt(1);
    }
    public int createFirewall()throws SQLException{
        statement = connection.createStatement();
        statement.execute("INSERT INTO pathelement VALUE()");
        statement.execute("INSERT INTO firewall(id_firewall) VALUE(last_insert_id()); ");
        ResultSet rs = statement.executeQuery("SELECT last_insert_id()");
        rs.next();
        return rs.getInt(1);
    }
    public int createSwitch()throws SQLException{
        statement = connection.createStatement();
        statement.execute("INSERT INTO pathelement VALUE()");
        statement.execute("INSERT INTO switch(id_switch) VALUE(last_insert_id()); ");
        ResultSet rs = statement.executeQuery("SELECT last_insert_id()");
        rs.next();
        return rs.getInt(1);
    }
    public int createRoute()throws SQLException{
        statement = connection.createStatement();
        statement.execute("INSERT INTO pathelement VALUE()");
        statement.execute("INSERT INTO route(id_route,turned_on) VALUES(last_insert_id(),TRUE ); ");
        ResultSet rs = statement.executeQuery("SELECT last_insert_id()");
        rs.next();
        return rs.getInt(1);
    }

    public void deletePc(PathElement model)throws SQLException{
        if(model == null)
            throw new NullPointerException();
        preparedStatement = connection.prepareStatement("DELETE FROM pc WHERE id_pc = ?");
        preparedStatement.setInt(1, model.getID());
        preparedStatement = connection.prepareStatement("DELETE FROM pathelement WHERE id_elem = ?");
        preparedStatement.setInt(1, model.getID());
        preparedStatement.execute();
    }
    public void deleteCable(PathElement model)throws SQLException{
        if(model == null)
            throw new NullPointerException();
        preparedStatement = connection.prepareStatement("DELETE FROM cable WHERE id_pc = ?");
        preparedStatement.setInt(1, model.getID());
        preparedStatement = connection.prepareStatement("DELETE FROM pathelement WHERE id_elem = ?");
        preparedStatement.setInt(1, model.getID());
        preparedStatement.execute();
    }
    public void deleteFirewall(PathElement model)throws SQLException{
        if(model == null)
            throw new NullPointerException();
        preparedStatement = connection.prepareStatement("DELETE FROM firewall WHERE id_pc = ?");
        preparedStatement.setInt(1, model.getID());
        preparedStatement = connection.prepareStatement("DELETE FROM pathelement WHERE id_elem = ?");
        preparedStatement.setInt(1, model.getID());
        preparedStatement.execute();
    }
    public void deleteHub(PathElement model)throws SQLException{
        if(model == null)
            throw new NullPointerException();
        preparedStatement = connection.prepareStatement("DELETE FROM hub WHERE id_pc = ?");
        preparedStatement.setInt(1, model.getID());
        preparedStatement = connection.prepareStatement("DELETE FROM pathelement WHERE id_elem = ?");
        preparedStatement.setInt(1, model.getID());
        preparedStatement.execute();
    }
    public void deleteRoute(PathElement model)throws SQLException{
        if(model == null)
            throw new NullPointerException();
        preparedStatement = connection.prepareStatement("DELETE FROM route WHERE id_pc = ?");
        preparedStatement.setInt(1, model.getID());
        preparedStatement = connection.prepareStatement("DELETE FROM pathelement WHERE id_elem = ?");
        preparedStatement.setInt(1, model.getID());
        preparedStatement.execute();
    }
    public void deleteSwitch(PathElement model)throws SQLException{
        if(model == null)
            throw new NullPointerException();
        preparedStatement = connection.prepareStatement("DELETE FROM switch WHERE id_pc = ?");
        preparedStatement.setInt(1, model.getID());
        preparedStatement = connection.prepareStatement("DELETE FROM pathelement WHERE id_elem = ?");
        preparedStatement.setInt(1, model.getID());
        preparedStatement.execute();
    }
    public void updateIp(PathElement model, String attribute) throws SQLException, DaoException {
        if(model == null)
            throw new NullPointerException();
        if(attribute == null)
            throw new NullPointerException();
        preparedStatement = connection.prepareStatement("SELECT id_ip FROM ip WHERE ip_name = ?");
        preparedStatement.setString(1, attribute);
        ResultSet rs = preparedStatement.executeQuery();
        int ipNumber = rs.getInt("id_ip");
        if(rs.wasNull()){
            throw new DaoException();
        }
        else {
            preparedStatement = connection.prepareStatement("UPDATE pathelement SET ip = ? WHERE id_elem = ?");
            preparedStatement.setInt(1, ipNumber);
            preparedStatement.setInt(2, model.getID());
            preparedStatement.execute();
        }
    }

    public void updateInfo(PathElement model, String attribute)throws SQLException {
        if(model == null)
            throw new NullPointerException();
        if(attribute == null)
            throw new NullPointerException();
        preparedStatement = connection.prepareStatement("UPDATE pathelement SET info = ? WHERE id_elem = ? ;");
        preparedStatement.setString(1, attribute);
        preparedStatement.setInt(2, model.getID());
        preparedStatement.execute();
    }

    public void updateDelay(PathElement model, double attribute)throws SQLException {
        if(model == null)
            throw new NullPointerException();
        preparedStatement = connection.prepareStatement("UPDATE pathelement SET delay = ? WHERE id_elem = ? ;");
        preparedStatement.setDouble(1, Double.valueOf(attribute));
        preparedStatement.setInt(2, model.getID());
        preparedStatement.execute();
    }

    public void updatePrice(PathElement model , double attribute)throws SQLException {
        if(model == null)
            throw new NullPointerException();
        preparedStatement = connection.prepareStatement("UPDATE pathelement SET delay = ? WHERE id_elem = ? ;");
        preparedStatement.setDouble(1, Double.valueOf(attribute));
        preparedStatement.setInt(2,model.getID());
        preparedStatement.execute();
    }

    public void updateNotAllowedIp(PathElement model, String attribute) throws SQLException, DaoException {
        if(model == null)
            throw new NullPointerException();
        if(attribute == null)
            throw new NullPointerException();
        preparedStatement = connection.prepareStatement("SELECT id_wrong_ip FROM wrong_ip WHERE ip_name = ?");
        preparedStatement.setString(1, attribute);
        ResultSet rs = preparedStatement.executeQuery();
        int ipNumber = rs.getInt("id_wrong_ip");
        if(rs.wasNull()){
            throw new DaoException();
        }
        else {
            preparedStatement = connection.prepareStatement("UPDATE firewall SET wrong_ip = ? WHERE id_firewall = ?");
            preparedStatement.setInt(1, ipNumber);
            preparedStatement.setInt(2, model.getID());
            preparedStatement.execute();
        }
    }
    public void updateTurnOn(PathElement model, String attribute)throws SQLException{
        if(model == null)
            throw new NullPointerException();
        if(attribute == null)
            throw new NullPointerException();
        preparedStatement = connection.prepareStatement("UPDATE route SET turned_on = ? WHERE id_route = ?");
        preparedStatement.setBoolean(1, Boolean.valueOf(attribute));
        preparedStatement.setInt(2, model.getID());
        preparedStatement.execute();
    }
    public void updateUnitAmount(PathElement model, int attribute)throws SQLException{
        if(model == null)
            throw new NullPointerException();
        if(model instanceof Hub){
            preparedStatement = connection.prepareStatement("UPDATE hub SET units = ? WHERE id_route = ?");
            preparedStatement.setInt(1, Integer.valueOf(attribute));
            preparedStatement.setInt(2, model.getID());
            preparedStatement.execute();
        }
        if(model instanceof Switch){
            preparedStatement = connection.prepareStatement("UPDATE Switch SET units = ? WHERE id_route = ?");
            preparedStatement.setInt(1, Integer.valueOf(attribute));
            preparedStatement.setInt(2, model.getID());
            preparedStatement.execute();
        }
    }
    public void updateTypeOfCable(PathElement model, String attribute) throws SQLException, DaoException {
        if(model == null)
            throw new NullPointerException();
        if(attribute == null)
            throw new NullPointerException();
        preparedStatement = connection.prepareStatement("SELECT id_cable_type FROM cable_type WHERE type_name = ?");
        preparedStatement.setString(1, attribute);
        ResultSet rs = preparedStatement.executeQuery();
        int ipNumber = rs.getInt("id_cable_type");
        if(rs.wasNull()){
            throw new DaoException();
        }
        else{
            preparedStatement = connection.prepareStatement("UPDATE cable SET cable_type = ? WHERE id_cable = ?");
            preparedStatement.setInt(1, ipNumber);
            preparedStatement.setInt(2,model.getID());
            preparedStatement.execute();
        }
    }
    public Cable readCable(int key, Network net)throws SQLException{
        if(net == null)
            throw new NullPointerException();
        preparedStatement = connection.prepareStatement("select cable.id_cable, pathelement.price,pathelement.info, cable_type.type_name from pathelement join cable on cable.id_cable = pathelement.id_elem join cable_type on cable.cable_type = cable_type.id_cable_type where cable.id_cable = ?;");
        preparedStatement.setInt(1, key);
        ResultSet rs = preparedStatement.executeQuery();
        rs.next();
        if(rs.next()) {
            Cable cable = new Cable(rs.getInt("id_cable"), rs.getDouble("price"), rs.getString("info"), TypeOfCable.valueOf(rs.getString("type_name")), net);
            return cable;
        }
        else
            return null;
    }
    public Firewall readFirewall(int key, Network net) throws SQLException, UnknownHostException {
        if(net == null)
            throw new NullPointerException();
        preparedStatement = connection.prepareStatement("select id_firewall, delay, ip.ip_name, info, price from pathelement join ip on pathelement.ip = ip.id_ip join firewall on firewall.id_firewall = pathelement.id_elem where firewall.id_firewall = ?;");
        preparedStatement.setInt(1, key);
        ResultSet rs = preparedStatement.executeQuery();
        rs.next();
        if(rs.next()) {
            Firewall firewall = new Firewall(rs.getInt("id_firewall"), rs.getDouble("delay"), rs.getString("ip_name"), rs.getString("Info"), rs.getDouble("price"), net);
            preparedStatement = connection.prepareStatement("SELECT wrong_ip.ip_name FROM wrong_ip WHERE wrong_ip.id_for_firewall = ?");
            preparedStatement.setInt(1, key);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                firewall.setNotAllowedIP(rs.getString("wrong_ip"));
            }
            return firewall;
        }
        else
            return null;
    }
    public PC readPc(int key, Network net) throws SQLException, UnknownHostException {
        if(net == null)
            throw new NullPointerException();
        preparedStatement = connection.prepareStatement("select pc.id_pc, pathelement.delay, ip.ip_name,pathelement.info, pathelement.price  from pathelement" +
                " join pc on pc.id_pc = pathelement.id_elem = ? " +
                " join ip on pathelement.ip = ip.id_ip");
        preparedStatement.setInt(1, key);
        ResultSet rs = preparedStatement.executeQuery();
        if(rs.next()) {
            PC pc = new PC(rs.getInt("id_pc"), rs.getDouble("delay"), rs.getString("ip_name"), rs.getString("info"), rs.getDouble("price"), net);
            return pc;
        }
        else
            return null;
    }
    public Hub readHub(int key, Network net)throws SQLException{
        if(net == null)
            throw new NullPointerException();
        preparedStatement = connection.prepareStatement("select hub.id_hub, pathelement.price,pathelement.info, hub.units from pathelement join hub on hub.id_hub = pathelement.id_elem where id_elem = ?;");
        preparedStatement.setInt(1, key);
        ResultSet rs = preparedStatement.executeQuery();
        rs.next();
        if(rs.next()) {
            Hub hub = new Hub(rs.getInt("id_hub"), rs.getDouble("price"), rs.getString("info"), rs.getInt("units"), net);
            return hub;
        }
        else
            return null;
    }
    public Route readRoute(int key, Network net) throws SQLException, UnknownHostException {
        if(net == null)
            throw new NullPointerException();
        preparedStatement = connection.prepareStatement("select route.id_route, pathelement.delay,ip.ip_name,pathelement.info, pathelement.price,  route.turned_on from pathelement join ip on pathelement.ip = ip.id_ip join route on route.id_route = pathelement.id_elem where id_elem = ?;");
        preparedStatement.setInt(1, key);
        ResultSet rs = preparedStatement.executeQuery();
        rs.next();
        if(rs.next()) {
            Route route = new Route(rs.getInt("id_route"), rs.getDouble("delay"), rs.getString("ip_name"), rs.getString("info"), rs.getDouble("price"), net);
            if (rs.getBoolean("turned_on") == true) {route.turnON();}
            else{route.turnOFF();}
            return route;
        }
        else
            return null;
    }
    public Switch readSwitch(int key, Network net) throws SQLException, UnknownHostException {
        if(net == null)
            throw new NullPointerException();
        preparedStatement = connection.prepareStatement("select switch.id_switch, pathelement.delay,ip.ip_name, pathelement.info, pathelement.price, switch.units from pathelement join ip on pathelement.ip = ip.id_ip join switch on switch.id_switch = pathelement.id_elem where id_elem = ?;");
        preparedStatement.setInt(1, key);
        ResultSet rs = preparedStatement.executeQuery();
        rs.next();
        if(rs.next()) {
            Switch sw = new Switch(rs.getInt("id_elem"), rs.getDouble("delay"), rs.getString("ip_name"), rs.getString("info"), rs.getDouble("price"), rs.getInt("units"), net);
            return sw;
        }
        else
            return null;
    }

    public void readAllModels(Network net) throws SQLException, UnknownHostException {
        statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("SELECT id_cable FROM cable");
        while(rs.next()){
            readCable(rs.getInt("id_cable"),net);
        }
        rs.close();
        statement.close();
        statement = connection.createStatement();
        rs = statement.executeQuery("SELECT id_firewall FROM firewall");
        while(rs.next()){
            readFirewall(rs.getInt("id_firewall"), net);
        }
        rs.close();
        statement.close();
        statement = connection.createStatement();
        rs = statement.executeQuery("SELECT id_pc FROM pc");
        while(rs.next()){
            readPc(rs.getInt("id_pc"), net);
        }
        rs.close();
        statement.close();
        statement = connection.createStatement();
        rs = statement.executeQuery("SELECT id_hub FROM hub");
        while(rs.next()){
            readHub(rs.getInt("id_hub"),net);
        }
        rs.close();
        statement.close();
        statement = connection.createStatement();
        rs = statement.executeQuery("SELECT id_route FROM route");
        while(rs.next()){
            readRoute(rs.getInt("id_route"),net);
        }
        rs.close();
        statement.close();
        statement = connection.createStatement();
        rs = statement.executeQuery("SELECT id_switch FROM switch");
        while(rs.next()){
            readSwitch(rs.getInt("id_switch"),net);
        }
        rs.close();
        statement.close();
    }


}
