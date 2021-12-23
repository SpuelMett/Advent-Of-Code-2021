import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Day6 {

    private static ArrayList<Integer> fishList;

    public static void main(String[] args) throws IOException {
        fishList = new ArrayList<>();
        String line = Files.readString(Path.of("Data6.txt"));
        //
        StringTokenizer tokenizer = new StringTokenizer(line);
        while (tokenizer.hasMoreElements()){
            String buffer = tokenizer.nextToken(", ");
            fishList.add(Integer.parseInt(buffer));
        }
        for(int i=0;i<256;i++){
            runDay();
        }
        System.out.println(fishList.size());
    }

    private static void runDay(){
        int i = 0;
        while (i < fishList.size()){
            int value = fishList.get(i);
            if(value <= 0){
                fishList.set(i, 6);
                fishList.add(9);
            }
            else{
                fishList.set(i, value -1);
            }
            i++;
        }
    }
}
