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
import java.util.ArrayList;

/**
 *
 * @author Nick
 */
public class Firewall extends ActiveElement {

    private ArrayList<String> notAllowedIP = new ArrayList<String>(){{

                                            }};

    public ArrayList<String> getNotAllowedIP() {
        return notAllowedIP;
    }

    public void setNotAllowedIP(String notAllowedIP) {
        this.notAllowedIP.add(notAllowedIP);
    }

    @Override
    public boolean checkCon(PathElement parent) {
       return isAddressCorrect(parent.getIP().toString());
    }

    public Firewall() {
    }
    
    public Firewall( int id, double delay, String ip, String info, double price, Network net) throws UnknownHostException {
    
        this.delay = delay;
        this.id = id;
        this.ip.getByName(ip);
        this.info = info;
        this.price = price;
        net.addElements(this);   
    }

    private boolean isAddressCorrect(String address){
        if(address == null)
            return false;
        for(String ip : this.notAllowedIP){
            if(ip == null)
                continue;
            if(ip.equals(address))
                return false;
        }
        return true;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Firewall{");
        sb.append("delay=").append(delay);
        sb.append(", id=").append(id);
        sb.append(", ip=").append(ip);
        sb.append(", info='").append(info).append('\'');
        sb.append(", price=").append(price);
        sb.append(", notAllowedIP=").append(notAllowedIP);
        sb.append('}');
        return sb.toString();
    }
}
