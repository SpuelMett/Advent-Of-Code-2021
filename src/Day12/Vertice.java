package Day12;

public class Vertice {

    private boolean isCapital;
    private String name;

    public Vertice(String name){
        this.name = name;
        if(name.toUpperCase().equals(name)) isCapital = true;
        else isCapital = false;
    }
    public String getName(){
        return name;
    }
    public boolean canVisitTwice(){
        return isCapital;
    }
}
