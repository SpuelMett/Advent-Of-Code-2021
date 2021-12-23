package Day7;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Day7 {

    private static ArrayList<Integer> horizontal;

    public static void main(String[] args) throws IOException {
        horizontal = new ArrayList<>();
        String line = Files.readString(Path.of("Data7.txt"));
        //
        StringTokenizer tokenizer = new StringTokenizer(line);
        while (tokenizer.hasMoreElements()){
            String buffer = tokenizer.nextToken(", ");
            int value = Integer.parseInt(buffer);
            horizontal.add(value);
        }

        //
        int minFuel = 1476452;
        int maxValue = maxValue();
        for(int i=0;i<maxValue+1;i++){
            int fuel = calculateFuel(i);
            if(fuel<minFuel) minFuel = fuel;
        }
        System.out.println(minFuel);

        //horizontal.sort(Integer::compareTo);
        //int size = horizontal.size();
    }

    private static int calculateFuel(int newPos){
        int fuelCost = 0;
        for(int oldPos:horizontal){
            int delta = newPos - oldPos;
            if(delta < 0) delta = delta * -1;
            fuelCost += delta;
        }
        return fuelCost;
    }

    private static int maxValue(){
        int max = 0;
        for(int value:horizontal){
            if(value > max) max = value;
        }
        return max;
    }

    private static int averageValue(){
        int sum = 0;
        for(int value:horizontal){
            sum += value;
        }
        return sum / horizontal.size();
    }
}
