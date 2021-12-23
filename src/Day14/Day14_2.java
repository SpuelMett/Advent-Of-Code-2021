package Day14;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Day14_2 {

    private static HashMap<String, String> map;
    private static HashMap<String, Long> polymer;


    public static void main(String[] args) throws IOException {
        map = new HashMap<>();
        polymer = new HashMap<>();

        List<String> sonarNumbers = Files.readAllLines(Path.of("Data14.txt"));

        //initialise Polymer
        String firstLine = sonarNumbers.get(0);
        int k=0;
        while (k<firstLine.length()-1){
            //create String
            String x = String.valueOf(firstLine.charAt(k));
            x+= String.valueOf(firstLine.charAt(k+1));

            addToPolymer(x, 1, polymer);

            k++;
        }

        //initialise Map
        for(int i=2;i<sonarNumbers.size();i++){
            String line = sonarNumbers.get(i);

            String x = String.valueOf(line.charAt(0));
            x += String.valueOf(line.charAt(1));

            map.put(x, String.valueOf(line.charAt(6)));
        }

        //make n iterations
        for(int n=0;n<40;n++){
            iteration();
            System.out.println(n);
        }

        System.out.println(calculateResult());

    }

    private static void iteration(){
        HashMap<String, Long> polymer2 = new HashMap<>();

        //Collection<String> keySet = polymer.keySet();

        for (String s: polymer.keySet()){
            long count = polymer.get(s);
            String s1 = s.substring(0,1);
            String s2 = s.substring(1,2);
            String mapped = map.get(s);

            String newS1 = s1 + mapped;
            String newS2 = mapped + s2;

            addToPolymer(newS1, count, polymer2);
            addToPolymer(newS2, count, polymer2);
        }

        polymer = polymer2;
    }

    private static void addToPolymer(String s, long value,HashMap<String, Long> hashMap){
        if(hashMap.containsKey(s)){
            long x = hashMap.get(s) + value;
            hashMap.put(s, x);
        }
        else{
            hashMap.put(s, value);
        }
    }

    private static long calculateResult(){
        HashMap<String, Long> characterValues = new HashMap<>();

        for(String s: polymer.keySet()){
            String s1 = s.substring(0,1);
            //String s2 = s.substring(1,2);

            long value = polymer.get(s);

            addToPolymer(s1, value, characterValues);
            //addToPolymer(s2, value, characterValues);
        }

        //convert to Set
        Collection<Long> valueList =  characterValues.values();

        //find largest and smallest
        long smallest = Long.MAX_VALUE;
        long largest = 0;

        for(long value:valueList){
            if(value < smallest) smallest = value;
            if(value > largest) largest = value;
        }
        return largest - smallest;
    }
}
