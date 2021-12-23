package Day14;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class Day14 {

    private static HashMap<String, Character> map;
    private static ArrayList<Character> polymer;

    public static void main(String[] args) throws IOException {
        map = new HashMap<>();
        polymer = new ArrayList<>();

        List<String> sonarNumbers = Files.readAllLines(Path.of("Data14.txt"));

        //initialise Polymer
        String firstLine = sonarNumbers.get(0);
        for(int i=0;i<firstLine.length();i++){
            polymer.add(firstLine.charAt(i));
        }

        //initialise Map
        for(int i=2;i<sonarNumbers.size();i++){
            String line = sonarNumbers.get(i);

            String x = String.valueOf(line.charAt(0));
            x += String.valueOf(line.charAt(1));

            map.put(x, line.charAt(6));
        }

        //make n iterations
        for(int n=0;n<40;n++){
            iteration();
            System.out.println(n);
        }

        System.out.println(calculateResult());

    }

    private static void iteration(){
        int i=0;
        while (i<polymer.size()-1){
            //create String
            String x = String.valueOf(polymer.get(i));
            x+= String.valueOf(polymer.get(i+1));
            Character newChar = map.get(x);
            polymer.add(i+1, newChar);
            i += 2;
        }
    }

    private static long calculateResult(){
        Character[] chars = new Character[map.size()];

        HashMap<Character, Integer> charMapping = new HashMap<>();

        for(int i=0;i<polymer.size();i++){
            //check if Char already exists
            Character c = polymer.get(i);
            if(charMapping.containsKey(c)){
                int value = charMapping.get(c);
                value++;
                charMapping.put(c,value);

            }
            else{
                charMapping.put(c,1);
            }
        }

        //convert to Set
        Collection<Integer> valueList =  charMapping.values();

        //find largest and smallest
        long smallest = Long.MAX_VALUE;
        long largest = 0;

        for(int value:valueList){
            if(value < smallest) smallest = value;
            if(value > largest) largest = value;
        }
        return largest - smallest;
    }
}
