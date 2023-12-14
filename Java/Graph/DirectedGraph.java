import MyImplementations.*;

public class DirectedGraph<E> implements MyGraph<E> {
    class Vertex {
        E data;
        List<Vertex> adjList;
        Vertex path;
        int indegree;
        boolean known;
        int dist;

        private Vertex(E data) {
            this.data = data;
            adjList = new LinkedList<>();
            indegree = 0;
        }

        private void connect(Vertex v) {
            adjList.add(v);
            v.path = this;
            v.indegree++;
        }

        private void print() {
            System.out.print(this + ": ");
            for(int i = 0; i < adjList.size(); i++)
                System.out.print(adjList.get(i) + " ");
            System.out.println();
        }

        public String toString() {
            return data.toString();
        }
    }
    List<Vertex> vertices;

    public DirectedGraph() {
        vertices = new ArrayList<>();
    }

    @Override
    public void addEdge(E src, E dest) {
        if(hasEdge(src, dest)) return;
        addVertex(src); 
        addVertex(dest); 
        int srcIndex = indexOf(src);
        int destIndex = indexOf(dest);
        vertices.get(srcIndex).connect(vertices.get(destIndex)); 
    }

    @Override
    public void addVertex(E data) {
        if(hasVertex(data)) return;
        vertices.add(new Vertex(data));
    }

    @Override
    public boolean hasVertex(E data) {
        return indexOf(data) >= 0;
    }

    @Override
    public boolean hasEdge(E src, E dest) {
        if(!hasVertex(src) || !hasVertex(dest))
            return false;
        int srcIndex = indexOf(src);
        int destIndex = indexOf(dest);

        Vertex srcV = vertices.get(srcIndex);
        Vertex destV = vertices.get(destIndex);

        return srcV.adjList.contains(destV) && destV.adjList.contains(srcV);
    }

    @Override
    public void print() {
        for(int i = 0; i < vertices.size(); i++)
            vertices.get(i).print();
    }

    public void topsort() {
        Queue<Vertex> q = new Queue<>();
        int counter = 0;

        for(int i = 0; i < vertices.size(); i++)
            if(vertices.get(i).indegree == 0)
                q.enqueue(vertices.get(i));

        while(!q.isEmpty()) {
            Vertex v = q.dequeue();
            System.out.print(v + " ");

            ++counter;

            for(int i = 0; i < v.adjList.size(); i++)
                if(--v.adjList.get(i).indegree == 0)
                    q.enqueue(v.adjList.get(i));
        }

        if(counter != vertices.size())
            throw new IllegalStateException("Cycle found!");
    }

    public void unweighted(E s) {
        if(indexOf(s) < 0)
            throw new IllegalStateException("Vertex not found!");
        
        Queue<Vertex> q = new Queue<>();

        for(int i = 0; i < vertices.size(); i++) {
            vertices.get(i).dist = Integer.MAX_VALUE;
        }

        Vertex startVertex = vertices.get(indexOf(s));
        startVertex.dist = 0;
        q.enqueue(startVertex);

        while(!q.isEmpty()) {
            Vertex v = q.dequeue();
            for(int i = 0; i < v.adjList.size(); i++) {
                Vertex w = vertices.get(i);
                if(w.dist == Integer.MAX_VALUE) {
                    w.dist = v.dist + 1;
                    w.path = v;
                    q.enqueue(w);
                }
            }
        }
    }

    public void printPath(E v) {
        int idx = indexOf(v);
        if(idx < 0)
            throw new IllegalStateException("Vertex not found!");

        Vertex k = vertices.get(idx);
        if(k.path != null) {
            printPath(k.path.data);
            System.out.print(" to ");
        }
        System.out.print(k);
    }

    private int indexOf(E v) {
        for(int i = 0; i < vertices.size(); i++)
            if(vertices.get(i).data.equals(v))
                return i;
        return -1;
    }
}
