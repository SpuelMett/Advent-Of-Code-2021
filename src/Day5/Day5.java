package Day5;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.StringTokenizer;

public class Day5 {

    private static int[][] field;
    private static int maxElement;

    public static void main(String[] args) throws IOException {
        field = new int[991][991];
        maxElement = 0;
        List<String> sonarNumbers = Files.readAllLines(Path.of("Data5.txt"));

        int maxX = 0;
        int maxY = 0;

        for(String line:sonarNumbers){
            StringTokenizer tokenizer = new StringTokenizer(line);
            //left
            String left = tokenizer.nextToken();
            StringTokenizer leftToken = new StringTokenizer(left);
            String x1String = leftToken.nextToken(",");
            String y1String = leftToken.nextToken();
            int x1 = Integer.parseInt(x1String);
            int y1 = Integer.parseInt(y1String);

            //middle
            tokenizer.nextToken();

            //right
            String right = tokenizer.nextToken();
            StringTokenizer rightToken = new StringTokenizer(right);
            String x2String = rightToken.nextToken(",");
            String y2String = rightToken.nextToken();
            int x2 = Integer.parseInt(x2String);
            int y2 = Integer.parseInt(y2String);

            if(x1 > maxX) maxX = x1;
            if(x2 > maxX) maxX = x2;
            if(y1 > maxY) maxY = y1;
            if(y2 > maxY) maxY = y2;

            //only horizontal and vertical
            if(x1 == x2){
                addHorizontal(x1, y1, y2);
            }
            else if (y1 == y2){
                addVertical(y1, x1, x2);
            }
            else{
                //check for diagonal
                int d1 = x2 - x1;
                if(d1 < 0) d1 = d1 * -1;

                int d2 = y2 - y1;
                if(d2<0) d2 = d2*-1;

                if(d1 == d2){
                    addDiagonal(x1, x2, y1, y2);
                }
            }



            //System.out.println("" + x1 + " " + y1 + " " + x2 + " " + y2);
            System.out.println("" + maxX + " " + maxY);
        }
        System.out.println(countOverlaps());
    }

    //
    private static void addHorizontal(int x, int y1, int y2){
        if(y2 < y1){
            int buffer = y1;
            y1 = y2;
            y2 = buffer;
        }

        //y1 is smaller than y2
        while(y1<=y2){
            field[x][y1] += 1;
            if(field[x][y1] > maxElement) maxElement = field[x][y1];
            y1++;
        }
    }
    private static void addVertical(int y, int x1, int x2){
        if(x2 < x1){
            int buffer = x1;
            x1 = x2;
            x2 = buffer;
        }
        while(x1<=x2){
            field[x1][y] += 1;
            if(field[x1][y] > maxElement) maxElement = field[x1][y];
            x1++;
        }
    }
    private static void addDiagonal(int x1, int x2, int y1, int y2){
        //oben links nach unten rechts (1,1 -> 3,3)
        if(x1 <= x2 && y1 <= y2){
            while (x1<=x2){
                field[x1][y1] += 1;
                x1++;
                y1++;
            }
        }
        //(1,3 -> 3,1)
        else if(x1 <= x2 && y1 > y2){
            while (x1<=x2){
                field[x1][y1] += 1;
                x1++;
                y1--;
            }
        }
        //9,7 -> 7,9
        else if(x1 > x2 && y1 <= y2){
            while (x1>=x2){
                field[x1][y1] += 1;
                x1--;
                y1++;
            }
        }
        else if(x1 > x2 && y1 > y2){
            while (x1>=x2){
                field[x1][y1] += 1;
                x1--;
                y1--;
            }
        }
        else System.out.println("I hope i dont get here");
    }

    private static int countOverlaps(){
        int counter = 0;
        for(int i=0;i<991;i++){
            for(int j=0;j<991;j++){
                if(field[i][j] >= 2) counter++;
            }
        }
        return counter;
    }
}
