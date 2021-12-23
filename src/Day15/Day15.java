package Day15;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Day15 {

    //private static int[][] map;
    private static int[][] riskMap;
    private static int[][] fieldsToEnd;
    private static int[][] fieldsFromStart;
    private static Feld[][] felder;
    public static final int xLength = 100;
    public static final int yLength = 100;

    public static void main(String[] args) throws IOException {
        //map = new int[100][100];
        riskMap = new int[yLength][xLength];
        fieldsToEnd = new int[yLength][xLength];
        fieldsFromStart = new int[yLength][xLength];
        felder = new Feld[yLength][xLength];
        List<String> sonarNumbers = Files.readAllLines(Path.of("Data15.txt"));

        for(int y=0;y<yLength;y++){
            String line = sonarNumbers.get(y);
            for(int x=0;x<xLength;x++){
                //map[y][x] =  Integer.parseInt(String.valueOf(line.charAt(x)));
                int value = Integer.parseInt(String.valueOf(line.charAt(x)));
                felder[y][x] = new Feld(x,y,value);
            }
        }

        System.out.println(aStar(felder[0][0] , felder[yLength-1][xLength -1]));

    }


    private static int aStar(Feld start, Feld target){
        ArrayList<Feld> open = new ArrayList<>();
        open.add(start);
        ArrayList<Feld> closed = new ArrayList<>();
        Feld current;

        while (true){
            current = getLowestFCost(open);
            open.remove(current);
            closed.add(current);

            if(current.equals(target)) return current.getfCost();

            //for each neighbour
            ArrayList<Feld> neighbours = getNeighbours(current);
            for (Feld neighbour:neighbours){
                //only do if not in closed list
                if(!closed.contains(neighbour)){
                    //calc new Path
                    int path = current.getGCost() + neighbour.getRisk();

                    //check if new path is closer
                    if(path < neighbour.getGCost() || !open.contains(neighbour)){
                        //update some things
                        neighbour.setGCost(path);

                        //add neighbour to open
                        if(!open.contains(neighbour)) open.add(neighbour);
                    }
                }
            }
        }

    }

    private static ArrayList<Feld> getNeighbours(Feld current){
        ArrayList<Feld> neighbours = new ArrayList<>();
        int x = current.getX();
        int y = current.getY();

        if(y>0) neighbours.add(felder[y-1][x]);
        if(x>0) neighbours.add(felder[y][x-1]);
        if(y<yLength-1) neighbours.add(felder[y+1][x]);
        if(x<xLength-1) neighbours.add(felder[y][x+1]);

        return neighbours;
    }

    private static Feld getLowestFCost(ArrayList<Feld> open){
        Feld lowest = open.get(0);
        for(Feld feld:open){
            if(lowest.getfCost() > feld.getfCost()) lowest = feld;
        }
        return lowest;
    }



    private static void calculateFields(){
        for(int y=0;y<100;y++){
            for(int x=0;x<100;x++){
                fieldsFromStart[y][x] = calculateFieldsFromStart(x,y);
                fieldsToEnd[y][x] = calculateFieldsToEnd(x, y);
            }
        }
    }

    private static int calculateFieldsFromStart(int x, int y){
        return x+y;
    }

    private static int calculateFieldsToEnd(int x, int y){
        return xLength-x + yLength-y;
    }

    private static void step(int xZiel, int yZiel, int xPos, int yPos){
        //int oldRisk = map[yPos][xPos];

        //up
        if(yPos>0){
            //int newRisk = map[yPos+1][xPos] + 1;

        }

        //right

        //down

        //left
    }
}
