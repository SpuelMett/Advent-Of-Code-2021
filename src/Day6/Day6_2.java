package Day6;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.StringTokenizer;

public class Day6_2 {

    private static long[] counts;
    public static void main(String[] args) throws IOException {
        counts = new long[9];

        String line = Files.readString(Path.of("Data6.txt"));
        //
        StringTokenizer tokenizer = new StringTokenizer(line);
        while (tokenizer.hasMoreElements()){
            String buffer = tokenizer.nextToken(", ");
            int value = Integer.parseInt(buffer);
            counts[value]++;
        }
        for(int i=0;i<256;i++){
            runDay();
        }
        //add all counts
        long sum = 0;
        for(int i=0;i<9;i++){
               sum += counts[i];
        }
        System.out.println(sum);
    }

    private static void runDay(){
        long buffer = counts[0];
        for(int i=0;i<8;i++) {
            counts[i] = counts[i + 1];
        }
        counts[6] += buffer;
        counts[8] = buffer;
    }
}
