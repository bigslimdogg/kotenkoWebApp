/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.model.models;

import mvc.model.abstract_model.ActiveElement;
import mvc.model.network.Network;
import mvc.model.pe_model.PathElement;

import java.net.UnknownHostException;

/**
 *
 * @author Nick
 */
public class Route extends ActiveElement {
    

    private boolean turnedOn;


    public Route(int id, double delay,  String ip, String info, double price, Network net) throws UnknownHostException {
        this.delay = delay;
        this.id = id;
        this.ip.getByName(ip);
        this.info = info;
        this.price = price;
        net.addElements(this);
    }

    public Route() {
    }
    
    @Override
    public boolean checkCon(PathElement parent) {
        return this.isTurnedOn();

    }

    public boolean isTurnedOn() {
        return turnedOn;
    }



    public void turnON(){
        
        this.turnedOn = true;
        
    }
    public void turnOFF(){
        
        this.turnedOn = false;
        
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Route{");
        sb.append("delay=").append(delay);
        sb.append(", id=").append(id);
        sb.append(", ip=").append(ip);
        sb.append(", info='").append(info).append('\'');
        sb.append(", price=").append(price);
        sb.append(", turnedOn=").append(turnedOn);
        sb.append('}');
        return sb.toString();
    }
}
