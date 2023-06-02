import java.util.*;

public class Graph<E> {
    private Map<E, List<E>> map;
    private boolean bidirectional;
    private int[][] adjMat;
    private List<E> vertices;

    public Graph() {
        this(true);
    }

    public Graph(boolean bidirectional) {
        this.bidirectional = bidirectional;
        map = new HashMap<>();
        adjMat = new int[10000][10000];
        vertices = new ArrayList<E>();
    }

    public void addVertex(E s) {
        map.put(s, new LinkedList<E>());
        vertices.add(s);
    }

    public void addEdge(E src, E dest) {
        if(!map.containsKey(src)) {
            addVertex(src);
            //System.out.println("Index of " + src.toString() + " = " + getIndex(src));
        }

        if(!map.containsKey(dest)) {
            addVertex(dest);
            //System.out.println("Index of " + dest.toString() + " = " + getIndex(dest));
        }

        map.get(src).add(dest);
        //adjMat[getIndex(src)][getIndex(dest)] = 1;
        if(bidirectional) {
            map.get(dest).add(src);
            //adjMat[getIndex(dest)][getIndex(src)] = 1;
        }
    }

    public int getVertexCount() {
        return map.keySet().size();
    }

    public int getEdgeCount() {
        int count = 0;
        for(E v : map.keySet()) {
            count += map.get(v).size();
        }

        if(bidirectional) {
            count /= 2;
        }

        return count;
    }

    public boolean hasVertex(E s) {
        return map.containsKey(s);
    }

    public boolean hasEdge(E s, E d) {
        return map.get(s).contains(d);
    }

    private int getIndex(E find) {
        int index = -1;
        int i = 0;
        for(E v : map.keySet()) {
            if(v.equals(find)) {
                index = i;
                break;
            } else {
                i++;
            }
        }
        return index;
    }

    private void DFSUtil(E v, boolean[] visited, List<E> list) throws IndexOutOfBoundsException {
        int index = getIndex(v);
        visited[index] = true;
        list.add(v);

        Iterator<E> i = map.get(v).listIterator();
        while(i.hasNext()) {
            E n = i.next();
            if(!visited[getIndex(n)]) {
                DFSUtil(n, visited, list);
            }
        }
    }

    public List<E> DFS(E startVertex) {
        List<E> DFSList = new ArrayList<E>();
        boolean[] visited = new boolean[getVertexCount()];
        DFSUtil(startVertex, visited, DFSList);
        return DFSList;
    }

    public List<E> BFS(E startVertex) throws IndexOutOfBoundsException{
        List<E> BFSList = new ArrayList<E>();
        boolean[] visited = new boolean[getVertexCount()];
        int index = getIndex(startVertex);
        visited[index] = true;

        LinkedList<E> queue = new LinkedList<E>();
        queue.add(startVertex);

        while(queue.size() != 0) {
            startVertex = queue.poll();
            BFSList.add(startVertex);
            Iterator<E> i = map.get(startVertex).listIterator();
            while(i.hasNext()) {
                E n = i.next();
                if(!visited[getIndex(n)]) {
                    visited[getIndex(n)] = true;
                    queue.add(n);
                }
            }
        }

        return BFSList;
    }

    private void createMatrix() {
        int[][] mat = new int[vertices.size()][vertices.size()];
        for(E nodeA : vertices) {
            for(E nodeB : vertices) {
                if(hasEdge(nodeA, nodeB)) {
                    mat[getIndex(nodeA)][getIndex(nodeB)] = 1;
                }

                if(hasEdge(nodeB, nodeA)) {
                    mat[getIndex(nodeB)][getIndex(nodeA)] = 1;
                }
            }
        }
        adjMat = mat;
    }

    public void printAdjacencyMatrix() {
        createMatrix();

        System.out.print("  ");
        for(E node : vertices) {
            System.out.print(node.toString() + " ");
        }
        System.out.println();

        /*String mat = "  ";
        for(E node : vertices) {
            mat += (node.toString() + " ");
        }
        mat += "\n";*/

        //System.out.println(mat);
        for(int i = 0; i < adjMat.length; i++) {
            System.out.print(vertices.get(i).toString() + " ");
            //mat += ((char)('A' + i) + " ");
            for(int j = 0; j < adjMat.length; j++) {
                System.out.print(adjMat[i][j] + " ");
                //mat += (adjMat[i][j] + " ");
            }
            System.out.println();
            //mat += "\n";
        }

        //System.out.println(mat);
    }

    // Print graph as adjacency list
    public String toString() {
        String s = "";

        for(E v : map.keySet()) {
            s += (v.toString() + ": " + map.get(v) + "\n").replaceAll(", ", " -> ");
        }

        return s.trim();
    }
}