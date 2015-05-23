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
public class Switch extends ActiveElement{

    private int unitAmount;

    public Switch(int id, double delay,  String ip, String info, double price, int unitAmount, Network net) throws UnknownHostException {
        this.delay = delay;
        this.id = id;
        this.ip.getByName(ip);
        this.info = info;
        this.price = price;
        this.unitAmount = unitAmount;
        net.addElements(this);
    }

    public Switch() {
    }
    
    @Override
    public boolean checkCon(PathElement parent) {
        return true;
    }

    public int getUnitAmount() {
        return unitAmount;
    }

    public void setUnitAmount(int unitAmount) {
        this.unitAmount = unitAmount;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Switch{");
        sb.append("delay=").append(delay);
        sb.append(", id=").append(id);
        sb.append(", ip=").append(ip);
        sb.append(", info='").append(info).append('\'');
        sb.append(", price=").append(price);
        sb.append(", unitAmount=").append(unitAmount);
        sb.append('}');
        return sb.toString();
    }
}
