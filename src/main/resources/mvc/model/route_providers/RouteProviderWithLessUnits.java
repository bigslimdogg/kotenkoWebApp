/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.model.route_providers;

import mvc.model.my_exceptions.AccessException;
import mvc.model.my_exceptions.ElementNotFoundException;
import mvc.model.network.Network;
import mvc.model.pe_model.PathElement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

/**
 *
 * @author Nick
 */
public class RouteProviderWithLessUnits implements RouteProvider {

    @Override
    public void getDescription(PathElement el) {
        System.out.println(el.toString());
    }
    
    public class Root{
        PathElement parentPE = null;
        boolean isUsed = false;
        int destination = 0;
        public Root(){
            
        }
        
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
        ArrayList<PathElement> treatedRoots = new ArrayList<>();
        PathElement start = null;
        PathElement next = null;
        PathElement end = null;
        for(PathElement elem : net.getPathElements().keySet()){//заносим элементы из сети в roots и помечаем стартовый и конечный узел
            if(elem.getID() == id1){
                roots.put(elem, new RouteProviderWithLessUnits.Root());
                start = elem;
            }
            else{
                if(elem.getID() == id2){
                    end = elem;
                    roots.put(elem, new RouteProviderWithLessUnits.Root());
                }
                else
                    roots.put(elem, new RouteProviderWithLessUnits.Root());
            }
        }
        if(start == null || end == null || start == end){//если их нет то бросаем исключение или они равны
            throw new ElementNotFoundException();
        }
        LinkedList<PathElement> q = new LinkedList<PathElement>();
        q.addFirst(start);
        roots.get(start).destination = 0;
        roots.get(start).isUsed = true;
        while(!q.isEmpty()){
            next = q.peekFirst();
            q.pollFirst();
            for(PathElement elem : next.getConnections()){
                if(elem.checkCon(next) == false)
                    continue;
                else {
                    if (roots.get(elem).isUsed == false) {
                        roots.get(elem).destination = roots.get(next).destination + 1;
                        q.add(elem);
                        roots.get(elem).isUsed = true;
                    }
                }
            }
        }
        for(PathElement child : roots.keySet()){//выясняем родителей каждого узла
            PathElement parent = null;
            for(PathElement elem : child.getConnections()){
                if(elem.checkCon(child) != false){
                    parent = elem;
                    break;
                }
            }
            int minDest = roots.get(parent).destination;
            for (PathElement connectedWithChild : child.getConnections()) {
                if (roots.get(connectedWithChild).destination < minDest) {
                    parent = connectedWithChild;
                    minDest = roots.get(parent).destination;
                }
            }
            roots.get(child).parentPE = parent;
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

