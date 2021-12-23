import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        List<Integer> sonarNumbers = Files.readAllLines(Path.of("Data.txt")).stream().map(Integer::parseInt).toList();
        task2(sonarNumbers);
    }

    private static void task1(List<Integer> sonarNumbers){
        int count = 0;
        int last = 100000;
        for(int number : sonarNumbers){
            //increase?
            if(number>last){
                count++;
            }
            last = number;
        }
        System.out.println(count);
    }

    private static void task2(List<Integer> sonarNumbers){
        int size = sonarNumbers.size();

        List<Integer> newNumbers = new ArrayList<>();

        int sum;
        for(int i=0;i<=size-3;i++){
            sum = sonarNumbers.get(i);
            sum += sonarNumbers.get(i+1);
            sum += sonarNumbers.get(i+2);
            newNumbers.add(sum);
        }
        task1(newNumbers);
    }
}
