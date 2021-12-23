package Day8;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.StringTokenizer;

public class Day8 {

    private static String[][] text;

    public static void main(String[] args) throws IOException {
        text = new String[200][14];
        List<String> sonarNumbers = Files.readAllLines(Path.of("Data8.txt"));
        int counter = 0;

        for(int i=0;i< sonarNumbers.size();i++){
            String line = sonarNumbers.get(i);
            StringTokenizer tokenizer = new StringTokenizer(line);
            while (tokenizer.hasMoreElements()){
                if(tokenizer.nextToken().equals("|")) break;
            }
            for(int j=0;j<4;j++){
                String buffer = tokenizer.nextToken();
                int length = buffer.length();
                if(length == 2 || length == 3 || length == 4 || length == 7 ) counter++;
            }
        }
        System.out.println(counter);
    }
}
