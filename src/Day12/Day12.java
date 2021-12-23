package Day12;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Day12 {

    //private static TreeMap<String, Integer> treeMap;
    private static Graph2 graph;

    public static void main(String[] args) throws IOException {
        graph = new Graph2();
        List<String> sonarNumbers = Files.readAllLines(Path.of("Data12.txt"));

        for (String line:sonarNumbers){
            StringTokenizer stringTokenizer = new StringTokenizer(line);
            String v1 = "";
            String v2 = "";
            if(stringTokenizer.hasMoreElements()){
                v1 = stringTokenizer.nextToken("-");
                graph.addVertice(v1);
            }
            if(stringTokenizer.hasMoreElements()){
                v2 = stringTokenizer.nextToken();
                graph.addVertice(v2);
            }
            graph.addEdge(v1, v2);
        }
        int result = graph.calculateWays("start", "end", new ArrayList<>());
        System.out.println(result);
    }
}
