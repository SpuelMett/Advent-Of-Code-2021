package Day9;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Day9_2 {

    private static int[][] numbers;
    private static int[] largest;

    public static void main(String[] args) throws IOException {
        numbers = new int[100][100];
        largest = new int[3];
        List<String> sonarNumbers = Files.readAllLines(Path.of("Data9.txt"));

        for(int j=0;j<100;j++){
            String line = sonarNumbers.get(j);
            for(int i=0;i<100;i++){
                numbers[j][i] =  Integer.parseInt(String.valueOf(line.charAt(i)));
            }
        }

        //check every number
        ArrayList<Integer> basinLength = new ArrayList<>();
        for(int j=0;j<100;j++){
            for(int i=0;i<100;i++){
                if(numbers[j][i] < 9){
                    int l= tiefenSuche(j,i);
                    if (l!=0)basinLength.add(l);
                }
            }
        }
        basinLength.sort(Comparator.naturalOrder());
        int size = basinLength.size();
        long result = basinLength.get(size-1) * basinLength.get(size-2) * basinLength.get(size -3);
        System.out.println(result);
    }

    private static int tiefenSuche(int j, int i){
        //check if in array
        if(i<100 && j<100 && i>=0 && j>=0){
            if(numbers[j][i] < 9 ){
                //mark this number
                numbers[j][i] = 10;
                int sum = 1;
                if(i<99) sum += tiefenSuche(j, i+1);
                if(j<99) sum += tiefenSuche(j+1, i);
                if(i>0) sum += tiefenSuche(j, i-1);
                if(j>0) sum += tiefenSuche(j-1, i);
                return sum;
            }
        }
        //smaller 10 and 9 or out of bounds
        return 0;
    }
}
