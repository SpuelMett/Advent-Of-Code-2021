package Day4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Day4_2 {

    private static List<String> input;
    private static ArrayList<Integer> numbersCalled;
    private static int[][][] bingoSlots;
    private static int[] slots;

    private static ArrayList<Bingo> bingoList;

    public static void main(String[] args) throws IOException {
        bingoList = new ArrayList<>();
        numbersCalled = new ArrayList<>();
        input = Files.readAllLines(Path.of("Data4.txt"));
        bingoSlots = new int[100][5][5];
        slots = new int[100];
        getNumbersCalled();
        getBingoSlots();

        run();
    }


    private static void run(){
        int i = 0;
        int j=0;
        int lastFinishedNumber;
        int lastNumber = 0;
        System.out.println(bingoList.size());
        while (bingoList.size() > 1){
            lastNumber = numbersCalled.get(j);
            System.out.println(lastNumber);
            j++;
            mark(lastNumber);
            System.out.println("Number: " + lastNumber + "    " + "Size: " + bingoList.size());
        }
        //finish last
        int sum = 0;
        while (bingoList.size() > 0){
            lastNumber = numbersCalled.get(j);
            j++;
            sum = bingoList.get(0).getSum();
            mark(lastNumber);
        }

        int result = sum*lastNumber;
        System.out.println(sum);
        System.out.println(lastNumber);
        System.out.println(result);
    }

    private static void mark(int number){
        int i=0;
        while(i < bingoList.size()){
            if(bingoList.get(i).mark(number)){
                bingoList.remove(i);
            }
            else i++;
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
                String line1 = input.get(i*6+2);
                String line2 = input.get(i*6+3);
                String line3 = input.get(i*6+4);
                String line4 = input.get(i*6+5);
                String line5 = input.get(i*6+6);

                bingoList.add(new Bingo(line1,line2,line3, line4, line5,i));
        }
    }

    //marked number is -1
    //remove number from list
    private static void removeFinished(){
        for(Bingo bingo:bingoList){
            if(bingo.checkBingo()) bingoList.remove(bingo);
        }
    }
}
