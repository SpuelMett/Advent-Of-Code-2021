package Day8;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Day8_2 {

    private static ArrayList<LineDigits> lineDigitsList;

    public static void main(String[] args) throws IOException {
        lineDigitsList = new ArrayList<LineDigits>();
        List<String> sonarNumbers = Files.readAllLines(Path.of("Data8.txt"));

        int sum = 0;

        for(int i=0;i< sonarNumbers.size();i++){
            sum +=  new LineDigits(sonarNumbers.get(i)).decode();

        }
        System.out.println(sum);
    }
}
