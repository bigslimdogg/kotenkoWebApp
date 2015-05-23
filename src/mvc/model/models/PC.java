/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.model.models;

import mvc.model.abstract_model.ActiveElement;
import mvc.model.network.Network;
import mvc.model.pe_model.PathElement;

import java.net.InetAddress;
import java.net.UnknownHostException;


public class PC extends ActiveElement {


    public PC( int id, double delay, String ip, String info, double price, Network net) throws UnknownHostException {
        this.delay = delay;
        this.id = id;
        this.ip = InetAddress.getByName(ip);
        this.info = info;
        this.price = price;
        net.addElements(this);
    }

    public PC() {
    }
    
    @Override
    public boolean checkCon(PathElement parent) {
        return true;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PC{");
        sb.append("delay=").append(delay);
        sb.append(", id=").append(id);
        sb.append(", ip=").append(ip);
        sb.append(", info='").append(info).append('\'');
        sb.append(", price=").append(price);
        sb.append('}');
        return sb.toString();
    }
}
