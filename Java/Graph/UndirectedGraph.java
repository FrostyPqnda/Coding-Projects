import MyImplementations.*;
public class UndirectedGraph<E> implements MyGraph<E> {
    class Vertex {
        E data;
        List<Vertex> adjList;
        Vertex path;

        private Vertex(E data) {
            this.data = data;
            adjList = new LinkedList<>();
        }

        private void connect(Vertex v) {
            adjList.add(v);
            v.adjList.add(this);

            v.path = this;
            path = v;
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

    public UndirectedGraph() {
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

    private int indexOf(E v) {
        for(int i = 0; i < vertices.size(); i++)
            if(vertices.get(i).data.equals(v))
                return i;
        return -1;
    }
}
