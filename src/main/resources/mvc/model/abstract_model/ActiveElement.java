/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.model.abstract_model;

import mvc.model.models.Hub;
import mvc.model.models.Switch;
import mvc.model.my_exceptions.AccessException;
import mvc.model.my_exceptions.AlreadyExistException;
import mvc.model.pe_model.PathElement;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nick
 */
public abstract class ActiveElement implements PathElement{
    
    protected double delay;
    protected int id;
    protected InetAddress ip;
    protected String info;
    protected double price;
    protected ArrayList<PathElement> connections = new ArrayList<PathElement>();
    
    public ActiveElement() {
    }

    public ArrayList<PathElement> getConnections(){
        return connections;
    }

    @Override
    public double getDelay() {
        return delay;
    }

    @Override
    public void setDelay(double delay) {
        this.delay = delay;
    }

    @Override
    public int getID() {
        return this.id;
    }

    @Override
    public void setID(int id) {
        this.id = id;
    }

    @Override
    public InetAddress getIP() {
        return this.ip;
    }

    @Override
    public void setIP(String ip) {
        try {
            this.ip = InetAddress.getByName(ip);
        } catch (UnknownHostException ex) {
            Logger.getLogger(ActiveElement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    public String getInfo() {
        return this.info;
    }

    @Override
    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public double getPrice() {
        return this.price;
    }

    @Override
    public void setPrice(double price) {
        this.price = price;
    }

    public void connect(PathElement elToConnect)throws Exception{

        if(elToConnect == null){
            throw new NullPointerException();
        }
        for(PathElement elem : connections)
            if(elem == elToConnect)
            {
                throw new AlreadyExistException();
            }
        if(elToConnect instanceof Switch){
            Switch el = (Switch) elToConnect;
            if(el.getUnitAmount() < 1)
                throw new AccessException();
        }
        if(elToConnect instanceof Hub){
            Hub el = (Hub) elToConnect;
            if(el.getUnitAmount() < 1)
                throw new AccessException();
        }
        this.getConnections().add(elToConnect);
        elToConnect.getConnections().add(this);
    }
    public void disConnect(PathElement elTodisconnect){
        this.connections.remove(elTodisconnect);
        elTodisconnect.getConnections().remove(this);
    }
}
