package Day4;

import java.util.StringTokenizer;

public class Bingo {

    int[][] fields;
    boolean finished;
    int boardnumber;

    public Bingo(String one, String two, String three, String four, String five, int number){
        fields = new int[5][5];
        finished = false;
        boardnumber = number;

        addLine(one,0);
        addLine(two,1);
        addLine(three,2);
        addLine(four,3);
        addLine(five,4);
    }

    public int getBoardnumber(){
        return boardnumber;
    }

    private void addLine(String line, int i){
        StringTokenizer tokenizer = new StringTokenizer(line);
        int x = 0;
        while (tokenizer.hasMoreElements()){
            String buffer = tokenizer.nextToken();
            int bufferInt = Integer.parseInt(buffer);
            fields[i][x] = bufferInt;
            x++;
        }
    }

    //return if finished
    public boolean mark(int number){
        if(!finished){
            for(int j=0;j<5;j++){
                for(int x=0;x<5;x++){
                    if(fields[j][x] == number) {
                        fields[j][x] = -1;
                    }
                }
            }
            return checkBingo();
        }
        else return true;
    }

    //return true if finished
    public boolean checkBingo(){
        boolean marked = true;
        //check horizontal
        for(int j=0;j<5;j++){
            marked = true;
            for(int x=0;x<5;x++){
                if(fields[j][x] != -1) marked = false;
            }
            if(marked == true) {
                finished = true;
                return true;
            }
        }

        //check vertical
        for(int x=0;x<5;x++){
            marked = true;
            for(int j=0;j<5;j++){
                if(fields[j][x] != -1) marked = false;
            }
            if(marked == true) {
                finished = true;
                return true;
            }
        }
        return false;
    }

    public int getSum(){
        int sum = 0;
        for(int j=0;j<5;j++){
            for(int x=0;x<5;x++){
                if(fields[j][x] != -1){
                    sum += fields[j][x];
                }
            }
        }
        return sum;
    }
}
