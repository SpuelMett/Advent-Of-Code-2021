package Day7;

public class CrabUboot {

    private int pos;

    public CrabUboot(int pos){
        this.pos = pos;
    }

    public long calculateFuel(int newValue){
        int n = Math.abs(newValue - pos);
        return (n*(n+1) /2);
    }

}
