package Day10;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Stack;

public class Day10_2 {

    public static void main(String[] args) throws IOException {
        List<String> sonarNumbers = Files.readAllLines(Path.of("Data10.txt"));
        ArrayList<Long> totalScoreList = new ArrayList<>();


        for(String line:sonarNumbers){
            int length = line.length();
            int i=0;
            Stack<Character> stack = new Stack<>();
            boolean isIncomplete = true;

            while(i<length){
                Character c = line.charAt(i);
                if(c == '(' || c == '[' || c == '{' || c == '<') {
                    if(c == '(') stack.push(')');
                    if(c == '[') stack.push(']');
                    if(c == '{') stack.push('}');
                    if(c == '<') stack.push('>');
                }
                else {
                    Character removedChar = stack.pop();

                    //invalid chunk
                    if(!(removedChar == c)){
                        if(c == ')') isIncomplete = false;
                        if(c == ']') isIncomplete = false;
                        if(c == '}') isIncomplete = false;
                        if(c == '>') isIncomplete = false;
                        i = length;
                    }
                }
                i++;
            }

            //
            if(isIncomplete == true){
                totalScoreList.add(calculateScore(stack));
            }
        }

        totalScoreList.sort(Comparator.naturalOrder());
        int l = totalScoreList.size();
        System.out.println(totalScoreList.get(l/2));
    }

    private static long calculateScore(Stack<Character> stack){
        long score = 0;

        while (!stack.empty()){
            score *= 5;
            Character c = stack.pop();
            if(c == ')') score += 1;
            if(c == ']') score += 2;
            if(c == '}') score += 3;
            if(c == '>') score += 4;
        }
        return score;
    }
}
