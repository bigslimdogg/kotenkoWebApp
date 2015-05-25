/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.model.network;

import mvc.model.pe_model.PathElement;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Nick
 */
public class Network {
    
    private HashMap<PathElement,ArrayList<PathElement>> elements = new HashMap<PathElement,ArrayList<PathElement>>();

    public Network() {
    }

    public void addElements(PathElement elToAdd) {

            elements.put(elToAdd, elToAdd.getConnections());
    }
    public void deleteElements(PathElement elToDel) {

            elements.remove(elToDel);
    }

    public HashMap<PathElement,ArrayList<PathElement>>  getPathElements(){
        
        return elements;
    }

}
