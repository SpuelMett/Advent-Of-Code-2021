package Day7;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Day7_2 {

    private static ArrayList<Integer> horizontal;
    private static ArrayList<CrabUboot> crabs;
    private static int maxHorizontal;

    public static void main(String[] args) throws IOException {
        horizontal = new ArrayList<>();
        crabs = new ArrayList<>();
        maxHorizontal = 0;
        String line = Files.readString(Path.of("Data7.txt"));

        StringTokenizer tokenizer = new StringTokenizer(line);
        while (tokenizer.hasMoreElements()){
            String buffer = tokenizer.nextToken(", ");
            int value = Integer.parseInt(buffer);
            horizontal.add(value);
            crabs.add(new CrabUboot(value));
            if(value>maxHorizontal) maxHorizontal = value;
        }

        //Brute Force each possible combination
        long minFuel = Long.MAX_VALUE;
        for(int i=0;i<maxHorizontal+1;i++){
            long fuel = calculateFuel(i); //for each possible values
            if(fuel<minFuel) minFuel = fuel;
        }
        System.out.println(minFuel);
    }

    private static long calculateFuel(int newPos){
        long fuelCost = 0;
        for(CrabUboot crab:crabs){
            fuelCost += crab.calculateFuel(newPos);
        }
        return fuelCost;
    }

    private static int averageValue(){
        int sum = 0;
        for(int value:horizontal){
            sum += value;
        }
        return sum / horizontal.size();
    }
    
}
