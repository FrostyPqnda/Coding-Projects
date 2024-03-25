public class Main {
    public static void main(String[] args) {
        MyGraph<Integer> graph = new UndirectedGraph<>();

        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(1, 4);

        graph.addEdge(2, 4);
        graph.addEdge(2, 5);

        graph.addEdge(3, 6);

        graph.addEdge(4, 3);  
        graph.addEdge(4, 6);
        graph.addEdge(4, 7);  

        graph.addEdge(5, 4);
        graph.addEdge(5, 7);

        graph.addEdge(7, 6);

        graph.print();
    }   
}
