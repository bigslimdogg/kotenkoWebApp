
package mvc.model.abstract_model;

import mvc.model.pe_model.PathElement;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public abstract class PassiveElement implements PathElement{
    
    protected final double delay = 0;
    protected int id;
    protected double price;
    protected InetAddress ip;
    protected String info;
    private ArrayList<PathElement> connections = new ArrayList<PathElement>();
    @Override
    public int getID() {
        return id;
    }

    @Override
    public void setID(int id) {
        this.id = id;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String getInfo() {
        return info;
    }

    @Override
    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public ArrayList<PathElement> getConnections() {
        return connections;
    }

    @Override
    public double getDelay() {
        return this.delay;
    }

    @Override
    public InetAddress getIP() {
       return ip;
    }

    @Override
    public void setDelay(double newDelay) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setIP(String ip) {
        try {
            this.ip = InetAddress.getByName(ip);
        } catch (UnknownHostException ex) {
            Logger.getLogger(ActiveElement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
