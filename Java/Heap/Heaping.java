public class Heaping {
    public static void main(String[] args) {
        int[] data = {12, 8, 5, 15, 7, 2, 9};
        Heap<Integer> hp = new BinaryHeap<>();
        
        for(int item : data)
            hp.insert(item);


        System.out.println(hp); 
        hp.deleteMin();
        System.out.println(hp);
    }
}
