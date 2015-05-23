
package mvc.model.pe_model;

import java.net.InetAddress;
import java.util.ArrayList;


public interface PathElement {
    
    double getDelay();
    
    int getID();
    
    String getInfo();
    
    InetAddress getIP();
    
    double getPrice();
    
    void setDelay(double newDelay);
    
    void setID(int newID);
    
    void setInfo(String newInfo);
    
    void setIP(String ip);
    
    void setPrice(double newPrice);
    
    ArrayList<PathElement> getConnections();

    boolean checkCon(PathElement parent);

}
