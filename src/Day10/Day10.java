package Day10;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Stack;

public class Day10 {

    public static void main(String[] args) throws IOException {
        List<String> sonarNumbers = Files.readAllLines(Path.of("Data10.txt"));

        int result = 0;

        for(String line:sonarNumbers){
            int length = line.length();
            int i=0;
            Stack<Character> stack = new Stack<>();

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
                        if(c == ')') result += 3;
                        if(c == ']') result += 57;
                        if(c == '}') result += 1197;
                        if(c == '>') result += 25137;
                        i = length;
                    }
                }
                i++;
            }
        }
        System.out.println(result);
    }
}
