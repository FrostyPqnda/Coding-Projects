@SuppressWarnings("unchecked")
public class MergeSort<E extends Comparable<? super E>> {
    void sort(E[] a) {
        sort(a, 0, a.length - 1);
    }

    private void sort(E[] a, int left, int right) {
        if(left >= right) return;
        int mid = (left + right) / 2;
        sort(a, left, mid);
        sort(a, mid + 1, right);
        merge(a, left, mid, right);
    }

    private void merge(E[] a, int left, int mid, int right) {
        int i = left, j = mid + 1, k = 0;
        E[] tmp = (E[]) new Comparable[right - left + 1];

        while(i <= mid && j <= right)
            tmp[k++] = (a[i].compareTo(a[j]) <= 0) ? a[i++] : a[j++];

        while(i <= mid) 
            tmp[k++] = a[i++];
        
        while(j <= right) 
            tmp[k++] = a[j++];

        for(i = left; i <= right; i++) 
            a[i] = tmp[i - left];
    }
}
