
package mvc.model.route_providers;

import mvc.model.network.Network;
import mvc.model.pe_model.PathElement;

import java.util.ArrayList;


public interface RouteProvider {

    public void getDescription(PathElement el);

    public ArrayList<PathElement> getRouteID(int id1, int id2, Network net)throws Exception;

}
