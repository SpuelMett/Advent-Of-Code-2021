import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Day4 {

    private static List<String> input;
    private static ArrayList<Integer> numbersCalled;
    private static int[][][] bingoSlots;

    public static void main(String[] args) throws IOException {
        numbersCalled = new ArrayList<>();
        input = Files.readAllLines(Path.of("Data4.txt"));
        bingoSlots = new int[100][5][5];
        getNumbersCalled();
        getBingoSlots();
        run();
    }


    private static void run(){
        int i = 0;
        int finishedNumber;
        int lastNumber;

        while (true){
            lastNumber = numbersCalled.get(i);
            mark(lastNumber);
            finishedNumber = isFinished();
            if(finishedNumber == -1) i++;
            else break;
        }

        //found finisher board:
        int sum = 0;
        for(int j=0;j<5;j++){
            for(int x=0;x<5;x++){
                if(bingoSlots[finishedNumber][j][x] != -1){
                    sum += bingoSlots[finishedNumber][j][x];
                }
            }
        }

        int result = sum*lastNumber;
        System.out.println(sum);
        System.out.println(lastNumber);
        System.out.println(result);
    }

    private static void mark(int number){
        for(int i=0;i<100;i++){
            for(int j=0;j<5;j++){
                for(int x=0;x<5;x++){
                    if(bingoSlots[i][j][x] == number) {
                        bingoSlots[i][j][x] = -1;
                    }
                }
            }
        }
    }

    private static void getNumbersCalled(){
        String line = input.get(0);
        StringTokenizer tokenizer = new StringTokenizer(line);

        while (tokenizer.hasMoreElements()){
            String buffer = tokenizer.nextToken(", ");
            int bufferInt = Integer.parseInt(buffer);
            numbersCalled.add(bufferInt);
        }
    }

    private static void getBingoSlots(){
        for(int i=0;i<100;i++){
            for(int j=0;j<5;j++){
                String line = input.get(i*6+j+2);
                StringTokenizer tokenizer = new StringTokenizer(line);
                int x = 0;
                while (tokenizer.hasMoreElements()){
                    String buffer = tokenizer.nextToken();
                    int bufferInt = Integer.parseInt(buffer);
                    bingoSlots[i][j][x] = bufferInt;
                    x++;
                }
            }
        }
    }

    //marked number is -1
    private static int isFinished(){
        boolean marked = true;
        for (int i=0;i<100;i++){

            //check horizontal
            for(int j=0;j<5;j++){
                marked = true;
                for(int x=0;x<5;x++){
                    if(bingoSlots[i][j][x] != -1) marked = false;
                }
                if(marked == true) return i;
            }

            //check vertical
            for(int x=0;x<5;x++){
                marked = true;
                for(int j=0;j<5;j++){
                    if(bingoSlots[i][j][x] != -1) marked = false;
                }
                if(marked == true) return i;
            }
        }
        return -1;
    }
}
