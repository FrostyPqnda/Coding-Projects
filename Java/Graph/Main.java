import java.util.*;

public class Main {
    public static void main(String[] args) {
        Graph<Character> g = new Graph<Character>();
        g.addEdge('A', 'B');
        g.addEdge('B', 'C');
        g.addEdge('B', 'D');
        g.addEdge('C', 'E');
        g.addEdge('D', 'E');
        g.addEdge('E', 'F');

        System.out.println(g + "\n");
        g.printAdjacencyMatrix();


    }   
}
