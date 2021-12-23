package Day13;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Day13 {

    private static boolean[][] paper;
    private static int xLength;
    private static int yLength;

    public static void main(String[] args) throws IOException {
        List<String> sonarNumbers = Files.readAllLines(Path.of("Data13.txt"));

        int instructionPos = 0;

        //find max Values
        int i=0;
        int maxX = 0;
        int maxY = 0;
        while(!sonarNumbers.get(i).equals("")){
            StringTokenizer st = new StringTokenizer(sonarNumbers.get(i));
            int x = Integer.parseInt(st.nextToken(","));
            int y = Integer.parseInt(st.nextToken(","));
            if(x > maxX) maxX = x;
            if(y > maxY) maxY = y;
            i++;
            instructionPos++;
        }
        maxX++;
        maxY++;
        instructionPos++;

        paper = new boolean[maxX][maxY];
        xLength = maxX;
        yLength = maxY;

        //insert input into paper
        i=0;
        while(!sonarNumbers.get(i).equals("")){
            StringTokenizer st = new StringTokenizer(sonarNumbers.get(i));
            int x = Integer.parseInt(st.nextToken(","));
            int y = Integer.parseInt(st.nextToken(","));
            paper[x][y] = true;
            i++;
        }

        while(instructionPos < sonarNumbers.size()){
            String line = sonarNumbers.get(instructionPos);
            String[] lineArray = line.split("=");
            int value = Integer.parseInt(lineArray[1]);
            if(lineArray[0].charAt(11) == 'x') foldX(value);
            else foldY(value);
            instructionPos++;
        }

        //foldX(655);
        printPaper();
        System.out.println(countDots());

    }

    private static void foldX(int xFold){
        for(int y = 0;y<yLength;y++){
            for(int x = xFold+1;x<xLength;x++){
                if(paper[x][y]){
                    int delta = x - xFold;
                    int xNew = xFold - delta;
                    paper[xNew][y] = true;
                    paper[x][y] = false;
                }
            }
        }
        xLength = xFold;
    }

    private static void foldY(int yFold){
        for(int y=yFold+1;y<yLength;y++){
            for(int x=0;x<xLength;x++){
                if(paper[x][y]){
                    int delta = y -yFold;
                    int yNew = yFold - delta;
                    paper[x][yNew] = true;
                    paper[x][y] = false;
                }
            }
        }
        yLength = yFold;
    }

    private static int countDots(){
        int count = 0;
        for(int y = 0;y<yLength;y++){
            for (int x=0;x<xLength;x++){
                if(paper[x][y]) count++;
            }
        }
        return count;
    }

    private static void printPaper(){
        for(int y = 0;y<yLength;y++){
            String line = "";
            for (int x=0;x<xLength;x++){
                if(paper[x][y]) line += "#";
                else line+= ".";
            }
            System.out.println(line);
        }
    }
}
