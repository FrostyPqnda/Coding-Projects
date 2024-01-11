import java.util.Arrays;

class ArraySorting {
    public static void main(String[] args) {
        HeapSort<Integer> hs = new HeapSort<>();
        Integer[] a = {3, 4, 1, 5, 2};
        hs.sort(a);
        System.out.println(Arrays.toString(a));
    }


}