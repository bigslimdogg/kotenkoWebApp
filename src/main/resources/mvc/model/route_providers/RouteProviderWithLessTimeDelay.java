
package mvc.model.route_providers;

import mvc.model.my_exceptions.AccessException;
import mvc.model.my_exceptions.ElementNotFoundException;
import mvc.model.network.Network;
import mvc.model.pe_model.PathElement;

import java.util.ArrayList;
import java.util.HashMap;


public class RouteProviderWithLessTimeDelay implements RouteProvider {

    @Override
    public void getDescription(PathElement el) {
        System.out.println(el.toString());
    }
    
    public class Root{
        double price = Double.POSITIVE_INFINITY;
        PathElement parentPE = null;
        boolean isUsed = false;
        public Root(double price) {
            this.price = price;
        }
        public Root(){
        }
    }

    public PathElement getElemWithMinDelay(ArrayList<PathElement> arr){
        if(!arr.isEmpty()){
            double min = arr.get(0).getDelay();
            PathElement minChild = arr.get(0);
            for(PathElement elem : arr){
                    if(elem.getDelay() < min){
                    minChild = elem;
                    }
            }
            return minChild;
        }
        else
            return null;
    }
    
    
    @Override
    public ArrayList<PathElement> getRouteID(int id1, int id2, Network net) throws Exception {
        ArrayList<PathElement> path = new ArrayList<PathElement>();//нужный маршрут от id1 до id2
        for(PathElement par : net.getPathElements().keySet()){
            for(PathElement elem : par.getConnections()){
                if(elem.checkCon(par) == false){
                    throw new AccessException();
                }
            }
        }
        HashMap<PathElement,Root> roots = new HashMap<PathElement,Root>();
        PathElement start = null;
        PathElement next = null;
        PathElement end = null;
        for(PathElement elem : net.getPathElements().keySet()){//заносим элементы из сети в roots и помечаем стартовый и конечный узел
            if(elem.getID() == id1){
                roots.put(elem, new Root(0.0));
                start = elem;
            }
            else{
                if(elem.getID() == id2){
                    end = elem;
                    roots.put(elem, new Root());
                }
                else
                    roots.put(elem, new Root());
            }
        }
        if(start == null || end == null || start == end){//если их нет то бросаем исключение или они равны
            throw new ElementNotFoundException();
        }
        for(;;){//цикл работает пока остались необработанные вершины
            if(roots.get(start).isUsed == true){
               ArrayList<PathElement> arrOfUnusedNeighb = new ArrayList<>();
               for(PathElement elem : start.getConnections()){
                   if(roots.get(elem).isUsed == false){
                        arrOfUnusedNeighb.add(elem);
                   }
               }
               start = getElemWithMinDelay(arrOfUnusedNeighb);
               if(start == null){
                   ArrayList<PathElement> arrOfUnused = new ArrayList<>();
                   for(PathElement elem : roots.keySet()){
                       if(roots.get(elem).isUsed == false)
                           arrOfUnused.add(elem);
                   }
                   if(arrOfUnused.isEmpty())
                       break;
                   else
                       start = arrOfUnused.get(0);
               }
            }
            for(PathElement elem : start.getConnections()){
                next = elem;
                if(roots.get(next).price > roots.get(start).price + next.getDelay()){
                    roots.get(next).price = roots.get(start).price + next.getDelay();
                }
            }
            roots.get(start).isUsed = true;//помечаем его как посещенную
        }
        for(PathElement elem : roots.keySet()){//выясняем родителей каждого узла
            for(PathElement connectedWithElem : elem.getConnections()){
                if(roots.get(elem).price == elem.getDelay() + roots.get(connectedWithElem).price){
                    roots.get(elem).parentPE = connectedWithElem;
                }
                
            }
        }
        next = end;
        path.add(end);
        while(next.getID() != id1){
            path.add(roots.get(next).parentPE);
            next = roots.get(next).parentPE;
        }
        return path; 
        }
    }

    
    
    
