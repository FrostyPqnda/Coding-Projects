class InsertionSort<E extends Comparable<? super E>> {
    void sort(E[] a) {
        for(int i = 1; i < a.length; i++) {
            E key = a[i];
            int j = i - 1;  

            while(j >= 0 && key.compareTo(a[j]) < 0) {
                a[j + 1] = a[j];
                j--;
            }
            a[j + 1] = key;
        }
    }
}
