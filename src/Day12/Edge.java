package Day12;

public class Edge {

    private Vertice v1;
    private Vertice v2;

    public Edge(Vertice v1, Vertice v2){
        this.v1 = v1;
        this.v2 = v2;
    }

    public Vertice getOther(Vertice v){
        if(v1.equals(v)) return v2;
        else if(v2.equals(v)) return v1;
        else return null;
    }
}
