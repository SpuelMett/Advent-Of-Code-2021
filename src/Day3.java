import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Day3 {

    public static void main(String[] args) throws IOException {
        List<String> sonarNumbers = Files.readAllLines(Path.of("Data3.txt"));

        //task1(sonarNumbers);
        task2Prep(sonarNumbers);
    }

    private static void task1(List<String> sonarNumbers){
        int[] arrayCounter = new int[12];
        int counter = 0;

        for(String line: sonarNumbers){
            for(int i=0;i<12;i++){
                if(line.charAt(i) == '1') arrayCounter[i]++;
            }
            counter++;
        }

        //create numbers
        String gamma = "";
        String epsilon = "";
        int treshhold = (int) counter / 2;
        for(int i=0;i<12;i++){
            if(arrayCounter[i] <= treshhold) {
                gamma += "0";
                epsilon += "1";
            }
            else {
                gamma += "1";
                epsilon += "0";
            }
        }

        int gammaNumber = Integer.parseInt(gamma, 2);
        int epsilonNumber = Integer.parseInt(epsilon, 2);

        int result = gammaNumber * epsilonNumber;
        System.out.println(result);
    }

    private static void task2Prep(List<String> list1){
        List<String> list2 = new ArrayList<>(list1);

        int oxygenNumber = task2(list1, 0, 0);
        int co2ScrubberNumber = task2(list2, 1, 0);

        int result = oxygenNumber * co2ScrubberNumber;
        System.out.println(result);
    }

    private static int task2(List<String> numbers, int type, int n){
        System.out.println("" + numbers.size() + " " + type + " " + n );
        if(numbers.size() == 1) return Integer.parseInt(numbers.get(0),2);

        int count0 = 0;
        int count1 = 0;

        //cylce list for count
        for(int i=0;i< numbers.size();i++){
            Character c = numbers.get(i).charAt(n);
            if(c == '1'){
                count1 += 1;
            }
            else count0 +=  1;
        }

        Character keepChar = '0';
        //type 0 is Oxygen // Keep most common
        //type 1 is co2Scrubber
        if(type == 0){
            if(count0<=count1) keepChar = '1';
        }
        else{
            if(count0>count1) keepChar = '1';
        }

        //delete elements
        for (int i=0;i< numbers.size();i++){
            Character c = numbers.get(i).charAt(n);
            if(c == keepChar) {
                numbers.remove(i);
                i--;
            }
        }

        //rekurisve
        return task2(numbers, type, n+1);
    }
}
