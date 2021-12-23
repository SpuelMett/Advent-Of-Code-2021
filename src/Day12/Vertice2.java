package Day12;

public class Vertice2 {

    private boolean isCapital;
    private int visitCounter;
    private String name;

    public Vertice2(String name){
        this.name = name;
        if(name.toUpperCase().equals(name)) isCapital = true;
        else{
            isCapital = false;
            if(name.equals("start")) visitCounter = 1;
            else if(name.equals("end")) visitCounter = 1;
            else visitCounter = 2;
        }
    }
    public String getName(){
        return name;
    }
    public boolean canVisitTwice(){
        return isCapital;
    }
    public boolean canVisit(){
        boolean result;
        if(isCapital) result = true;
        else if(visitCounter > 0) result = true;
        else result = false;

        visitCounter--;
        return result;
    }
}
