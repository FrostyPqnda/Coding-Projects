public class Heaping {
    public static void main(String[] args) {
        int[] data = {12, 8, 5, 15, 7, 2, 9};
        Heap<Integer> hp = new BinomialQueue<>();
        
        for(int item : data)
            hp.insert(item);

        hp.print();     
        System.out.println(hp.deleteMin());
        hp.print();       
    }
}
