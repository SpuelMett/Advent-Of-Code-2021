package Day8;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class LineDigits {

    private String[] text;
    private String[] output;
    private Character[] finalMapping;

    private ArrayList[] possibleMappings;

    private ArrayList<Character> p0;
    private ArrayList<Character> p1;
    private ArrayList<Character> p2;
    private ArrayList<Character> p3;
    private ArrayList<Character> p4;
    private ArrayList<Character> p5;
    private ArrayList<Character> p6;



    public LineDigits(String line){
        p0 = new ArrayList<>();
        p1 = new ArrayList<>();
        p2 = new ArrayList<>();
        p3 = new ArrayList<>();
        p4 = new ArrayList<>();
        p5 = new ArrayList<>();
        p6 = new ArrayList<>();


        finalMapping = new Character[7];
        text = new String[10];
        output = new String[4];
        possibleMappings = new ArrayList[7];
        StringTokenizer tokenizer = new StringTokenizer(line);
        int i=0;
        while (tokenizer.hasMoreElements()){
            String buffer = tokenizer.nextToken();
            if(buffer.equals("|")) break;
            text[i] = buffer;
            i++;
        }
        for(int j=0;j<4;j++){
            String buffer = tokenizer.nextToken();
            output[j] = buffer;
            int length = buffer.length();
        }
        createMapping();
        //decode();
        //isMappingPossible();
    }


    public int decode(){
        String outString = "";
        for(String out:output){
            outString += decode(out);
        }
        return Integer.parseInt(outString);
    }
    private int decode(String line){
        ArrayList<Character> charList = new ArrayList<>();

        for(int i=0;i < line.length();i++){
            charList.add(line.charAt(i));
        }
        //Decoding
        if(line.length() == 2) return 1;
        if(line.length() == 3) return 7;
        if(line.length() == 4) return 4;
        if(line.length() == 5){
            if(!charList.contains(finalMapping[5])) return 2;
            else if(!charList.contains(finalMapping[1])) return 3;
            else return 5;
        }
        if(line.length() == 6){
            //check 3
            if(!charList.contains(finalMapping[3])) return 0;
            if(!charList.contains(finalMapping[2])) return 6;
            else return 9;
         }
        if(line.length() == 7) return 8;
        return 0;
    }

    private void createMapping(){
            addAllElements(p0);
            addAllElements(p1);
            addAllElements(p2);
            addAllElements(p3);
            addAllElements(p4);
            addAllElements(p5);
            addAllElements(p6);

        for(String out:text){
            int length = out.length();
            ArrayList<Character> charList = new ArrayList<>();

            for(int i=0;i < out.length();i++){
                charList.add(out.charAt(i));
            }

            //l2 -> 1
            if(length == 2){
                removeWrongElements(p2, charList);
                removeWrongElements(p5, charList);
                removeElements(p1, charList);
                removeElements(p3, charList);
                removeElements(p4, charList);
                removeElements(p6, charList);
                removeElements(p0, charList);
            }
            //l3 --> 7
            else if(length == 3){
                removeWrongElements(p0, charList);
                removeWrongElements(p2, charList);
                removeWrongElements(p5, charList);
                removeElements(p1, charList);
                removeElements(p3, charList);
                removeElements(p4, charList);
                removeElements(p6, charList);
            }
            //l4 --> 4
            else if(length == 4){
                removeWrongElements(p1, charList);
                removeWrongElements(p2, charList);
                removeWrongElements(p3, charList);
                removeWrongElements(p5, charList);
                removeElements(p0, charList);
                removeElements(p4, charList);
                removeElements(p6, charList);
            }
            //l5 --> 2, 3, 5
            else if(length == 5){
                removeWrongElements(p0, charList);
                removeWrongElements(p3, charList);
                removeWrongElements(p6, charList);
            }
            //l6 --> 6, 9, 0
            else if(length == 6){
                removeWrongElements(p0, charList);
                removeWrongElements(p1, charList);
                //removeWrongElements(p3, charList);
                removeWrongElements(p5, charList);
                removeWrongElements(p6, charList);
            }
            //l7 --> 8
        }

        //Now go over and delete if unique
        for(int i=0;i<7;i++){
        if(p0.size() == 1){
            Character c = p0.get(0);
            removeUnique(p1, c);
            removeUnique(p2, c);
            removeUnique(p3, c);
            removeUnique(p4, c);
            removeUnique(p5, c);
            removeUnique(p6, c);
        }
        if(p1.size() == 1){
            Character c = p1.get(0);
            removeUnique(p0, c);
            removeUnique(p2, c);
            removeUnique(p3, c);
            removeUnique(p4, c);
            removeUnique(p5, c);
            removeUnique(p6, c);
        }
        if(p2.size() == 1){
            Character c = p2.get(0);
            removeUnique(p0, c);
            removeUnique(p1, c);
            removeUnique(p3, c);
            removeUnique(p4, c);
            removeUnique(p5, c);
            removeUnique(p6, c);
        }
        if(p3.size() == 1){
            Character c = p3.get(0);
            removeUnique(p0, c);
            removeUnique(p1, c);
            removeUnique(p2, c);
            removeUnique(p4, c);
            removeUnique(p5, c);
            removeUnique(p6, c);
        }
        if(p4.size() == 1){
            Character c = p4.get(0);
            removeUnique(p1, c);
            removeUnique(p2, c);
            removeUnique(p3, c);
            removeUnique(p0, c);
            removeUnique(p5, c);
            removeUnique(p6, c);
        }
        if(p5.size() == 1){
            Character c = p5.get(0);
            removeUnique(p1, c);
            removeUnique(p2, c);
            removeUnique(p3, c);
            removeUnique(p4, c);
            removeUnique(p0, c);
            removeUnique(p6, c);
        }
        if(p6.size() == 1){
            Character c = p6.get(0);
            removeUnique(p1, c);
            removeUnique(p2, c);
            removeUnique(p3, c);
            removeUnique(p4, c);
            removeUnique(p5, c);
            removeUnique(p0, c);
        }
        }
        finalMapping[0] = p0.get(0);
        finalMapping[1] = p1.get(0);
        finalMapping[2] = p2.get(0);
        finalMapping[3] = p3.get(0);
        finalMapping[4] = p4.get(0);
        finalMapping[5] = p5.get(0);
        finalMapping[6] = p6.get(0);
    }

    private void removeUnique(ArrayList<Character> pList, Character c){
        pList.remove(c);
    }

    private void addAllElements(ArrayList<Character> pList){
        pList.add('a');
        pList.add('b');
        pList.add('c');
        pList.add('d');
        pList.add('e');
        pList.add('f');
        pList.add('g');
    }

    /**
     * Removes All Elements not in the c list from p List
     * Bsp: p0: abc   | cList: ab
     * Ergebnis: p0: ab
     * @param pList
     * @param cList
     */
    private void removeWrongElements(ArrayList<Character> pList, ArrayList<Character> cList){
        pList.removeIf(p -> !cList.contains(p));
    }

    /**
     * Removes Elements that are already in an other List
     *  Bsp: p0: abc   | cList: ab
     *      * Ergebnis: p0: c
     * @param pList
     * @param cList
     */
    private void removeElements(ArrayList<Character> pList, ArrayList<Character> cList){
        pList.removeIf(p -> cList.contains(p));
    }

    /*
    public Character[] getMapping(){
        return mapping;
    }


     */
    private void isMappingPossible(){
        System.out.println(p0.size());
        System.out.println(p1.size());
        System.out.println(p2.size());
        System.out.println(p3.size());
        System.out.println(p4.size());
        System.out.println(p5.size());
        System.out.println(p6.size());
    }

}
