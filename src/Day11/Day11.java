package Day11;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Day11 {

    private static Dumbo[][] dumboArray;
    private static long result;

    public static void main(String[] args) throws IOException {
        dumboArray = new Dumbo[10][10];
        result = 0;
        List<String> sonarNumbers = Files.readAllLines(Path.of("Data11.txt"));

        //initialize dumbo
        for (int x = 0; x < 10; x++) {
            String line = sonarNumbers.get(x);
            for (int y = 0; y < 10; y++) {
                dumboArray[x][y] = new Dumbo(Integer.parseInt(String.valueOf(line.charAt(y))));
            }
        }

        //run 100
        for(int i=0;i<100;i++){
            run();
        }
        System.out.println(result);

    }

    private static void run(){
        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 10; y++) {
 ;              if(dumboArray[x][y].runRound()){
                    result++;
                    fireAdjacent(x,y);
                }
            }
        }

        //reset
        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 10; y++) {
                dumboArray[x][y].roundEnd();
            }
        }
    }

    private static void fireAdjacent(int x, int y){
        //top left
        if(x>0 && y>0){
            if(dumboArray[x-1][y-1].runRound()){
                fireAdjacent(x-1,y-1);
                result++;
            }
        }
        //top
        if(x>0){
            if(dumboArray[x-1][y].runRound()){
                fireAdjacent(x-1,y);
                result++;
            }
        }
        //top right
        if(x>0 && y < 9){
            if(dumboArray[x-1][y+1].runRound()){
                fireAdjacent(x-1,y+1);
                result++;
            }
        }
        //right
        if(y < 9){
            if(dumboArray[x][y+1].runRound()){
                fireAdjacent(x,y+1);
                result++;
            }
        }
        //right down
        if(x< 9 && y < 9){
            if(dumboArray[x+1][y+1].runRound()){
                fireAdjacent(x+1,y+1);
                result++;
            }
        }
        //down
        if(x< 9){
            if(dumboArray[x+1][y].runRound()){
                fireAdjacent(x+1,y);
                result++;
            }
        }
        //left down
        if(x<9 && y>0){
            if(dumboArray[x+1][y-1].runRound()){
                fireAdjacent(x+1,y-1);
                result++;
            }
        }
        //left
        if(y>0){
            if(dumboArray[x][y-1].runRound()){
                fireAdjacent(x,y-1);
                result++;
            }
        }
    }

}
