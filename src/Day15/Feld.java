package Day15;

public class Feld {

    private int x;
    private int y;
    private int risk;
    private int hCost;
    private int gCost;
    private int fCost;
    private int combinedRisk;
    private Feld previous;

    public Feld(int x, int y, int risk){
        this.x = x;
        this.y = y;
        this.risk = risk;
        gCost = (x+y) * 9;
        //gCost = Integer.MAX_VALUE;
        hCost = Day15.xLength-1-x + Day15.yLength-1-y;
        this.fCost = gCost + hCost + risk;
    }

    public void setPrevious(Feld previous){
        this.previous = previous;
    }

    public void setGCost(int value){
        gCost = value;
        fCost = gCost + hCost;
    }
    public int getGCost(){
        return gCost;
    }

    public int getfCost() {
        return fCost;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    public int getRisk() {
        return risk;
    }

    public int gethCost() {
        return hCost;
    }

    public int getgCost() {
        return gCost;
    }

}
