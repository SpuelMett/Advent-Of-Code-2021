package Day12;

import java.util.*;

public class Graph2 {

    private HashMap<String, Vertice> verticeMap;
    private ArrayList<Edge> edgeList;

    public Graph2(){
        verticeMap = new HashMap<>();
        edgeList = new ArrayList<>();
    }

    public void addVertice(String name){
        if(!verticeMap.containsKey(name)) verticeMap.put(name, new Vertice(name));
    }
    public void addEdge(String name1, String name2){
        Vertice v1 = verticeMap.get(name1);
        Vertice v2 = verticeMap.get(name2);
        Edge e = new Edge(v1, v2);
        edgeList.add(e);
    }

    public int calculateWays(String start, String end, ArrayList<Vertice> smallList){
        if(start.equals(end)) return 1;
        else{
            int result = 0;
            Vertice startV = verticeMap.get(start);

            //if current vertice is not in smallList add it if needed
            if(startV.canVisitTwice() == false && smallList.contains(startV) == false) smallList.add(startV);

            Queue<Vertice> verticeQueue = new LinkedList<Vertice>();
            verticeQueue.add(startV);

            //for each Vertice adjacent to start
            ArrayList<Vertice> adjacentList = getAdjacentVertice(startV);
            for(Vertice v:adjacentList){
                //only do when we can visit the Vertice
                if(canVisit(v,smallList)){
                    ArrayList<Vertice> newSmallList = new ArrayList<>(smallList);
                    if(v.canVisitTwice() == false) newSmallList.add(v);
                    result += calculateWays(v.getName(), end, newSmallList);
                }
            }
            return result;
        }

    }

    public boolean canVisit(Vertice v, ArrayList<Vertice> smallList){
        String vName = v.getName();
        if(v.canVisitTwice()) return true;
        if((vName.equals("start") || vName.equals("end")) && smallList.contains(v)) return false;

        //check if v is in the List two time
        int count = isListUnique2(smallList);
        if(count == 0) return true;
        if(count > 0 && smallList.contains(v)) return false;

        return true;
    }

    public int isListUnique2(ArrayList<Vertice> smallList){
        int count = 0;
        for(int i=0;i< smallList.size()-1;i++){
            for(int j=i+1;j< smallList.size();j++){
                if(smallList.get(i).equals(smallList.get(j))) count++;
            }
        }
        return count;
    }

    public ArrayList<Vertice> getAdjacentVertice(Vertice v){
        ArrayList<Vertice> resultList = new ArrayList<>();
        for(Edge e:edgeList){
            Vertice vAdjaent = e.getOther(v);
            if(vAdjaent != null) resultList.add(vAdjaent);
        }
        return resultList;
    }
}
