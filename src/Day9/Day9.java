package Day9;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Day9 {

    private static int[][] numbers;

    public static void main(String[] args) throws IOException {
        numbers = new int[100][100];
        List<String> sonarNumbers = Files.readAllLines(Path.of("Data9.txt"));

        for(int j=0;j<100;j++){
            String line = sonarNumbers.get(j);
            for(int i=0;i<100;i++){
                Character c =  line.charAt(i);
                numbers[j][i] =  Integer.parseInt(String.valueOf(c));
            }
        }

        //check every number
        int sum = 0;
        boolean isLowest = true;
        for(int j=0;j<100;j++){
            for(int i=0;i<100;i++){
                int current = numbers[j][i];
                isLowest = true;

                //check left
                if(i>0){
                    if(numbers[j][i-1] <= current) isLowest = false;
                }
                //check right
                if(i<99){
                    if(numbers[j][i+1] <= current) isLowest = false;
                }
                //check top
                if(j>0){
                    if(numbers[j-1][i] <= current) isLowest = false;
                }
                //check bottom
                if(j<99){
                    if(numbers[j+1][i] <= current) isLowest = false;
                }

                if(isLowest){
                    sum += current + 1;
                }
            }
        }
        System.out.println(sum);

    }
}
