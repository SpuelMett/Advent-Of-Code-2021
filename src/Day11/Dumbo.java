package Day11;

public class Dumbo {

    int value;
    boolean hasFlashed;

    public Dumbo(int value){
        this.value = value;
        hasFlashed = false;
    }

    public boolean runRound(){
        if(!hasFlashed){
            value++;
            if(value > 9) {
                hasFlashed = true;
                value = 0;
                return true;
            }
            else return false;
        }
        else return false;
    }

    public void roundEnd(){
        hasFlashed = false;
    }

    public boolean hasFlashed(){
        return hasFlashed;
    }
}
