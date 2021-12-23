import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.StringTokenizer;

public class Day2 {

    public static void main(String[] args) throws IOException {
        List<String> sonarNumbers = Files.readAllLines(Path.of("Data2.txt"));
        task2(sonarNumbers);
    }

    private static void task1(List<String> sonarNumbers){
        int horizontal = 0;
        int depth = 0;

        int number = 0;
        String direction = "";

        for(String text : sonarNumbers){
            //Tokenize
            StringTokenizer tokenizer = new StringTokenizer(text);
            if(tokenizer.hasMoreElements()) direction = tokenizer.nextToken();
            if(tokenizer.hasMoreElements()) number = Integer.parseInt(tokenizer.nextToken());

            if(direction.equals("forward")) horizontal += number;
            else if(direction.equals("down")) depth += number;
            else if(direction.equals("up")) depth -= number;
            System.out.println("test");
        }
        System.out.println(horizontal*depth);
    }

    private static void task2(List<String> sonarNumbers){
        int horizontal = 0;
        int depth = 0;
        int aim = 0;

        int number = 0;
        String direction = "";

        for(String text : sonarNumbers){
            //Tokenize
            StringTokenizer tokenizer = new StringTokenizer(text);
            if(tokenizer.hasMoreElements()) direction = tokenizer.nextToken();
            if(tokenizer.hasMoreElements()) number = Integer.parseInt(tokenizer.nextToken());

            if(direction.equals("forward")){
                horizontal += number;
                depth += aim*number;
            }
            else if(direction.equals("down")) aim += number;
            else if(direction.equals("up")) aim -= number;
        }
        System.out.println(horizontal*depth);
    }
}
