package Day15;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Day15_2 {

    private static Feld[][] felder;
    public static final int xLength = 100;
    public static final int yLength = 100;

    public static void main(String[] args) throws IOException {
        felder = new Feld[yLength*5][xLength*5];
        List<String> sonarNumbers = Files.readAllLines(Path.of("Data15.txt"));

        for(int y=0;y<yLength;y++){
            String line = sonarNumbers.get(y);
            for(int x=0;x<xLength;x++){
                int value = Integer.parseInt(String.valueOf(line.charAt(x)));
                felder[y][x] = new Feld(x,y,value);
            }
        }

        calc5Matrix();

        System.out.println(aStar(felder[0][0] , felder[yLength*5-1][xLength*5 -1]));
    }

    private static void calc5Matrix(){
        for(int y=0;y<yLength;y++){
            for(int x=0;x<xLength;x++){
                int curval = felder[y][x].getRisk();

                for(int yy=0;yy<5;yy++){
                    for(int xx=0;xx<5;xx++){
                        //not for 00;
                        //int nextVal = (curval + yy + xx) % 9;
                        int nextVal = curval + yy + xx;
                        if(nextVal > 9) nextVal -= 9;
                        int nextX = xLength*xx + x;
                        int nextY = yLength*yy + y;
                        felder[nextY][nextX] = new Feld(nextX, nextY, nextVal);
                    }
                }
            }
        }
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

            if(current.equals(target)) return current.getgCost();

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
                        neighbour.setPrevious(current);

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
        if(y<yLength*5-1) neighbours.add(felder[y+1][x]);
        if(x<xLength*5-1) neighbours.add(felder[y][x+1]);

        return neighbours;
    }

    private static Feld getLowestFCost(ArrayList<Feld> open){
        Feld lowest = open.get(0);
        for(Feld feld:open){
            if(lowest.getfCost() > feld.getfCost()) lowest = feld;
        }
        return lowest;
    }
}
