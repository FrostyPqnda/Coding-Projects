package MyImplementations;

@SuppressWarnings("unchecked")
public class ArrayList<E> implements List<E> {
    E[] arr;
    int numItems;
    final static int CAPACITY = 100;

    public ArrayList() {
        this(CAPACITY);
    }

    public ArrayList(int capacity) {
        arr = (E[]) new Object[capacity];
        numItems = 0;
    }

    @Override
    public boolean add(E x) {
        ensureCapacity();
        arr[numItems++] = x;
        return true;
    }

    @Override
    public void clear() {
        arr = (E[]) new Object[CAPACITY];
        numItems = 0;
    }

    @Override
    public boolean contains(E x) {
        return indexOf(x) >= 0;
    }

    @Override
    public E get(int index) {
        if(index < 0 || index >= numItems)
            throw new ArrayIndexOutOfBoundsException();
        return arr[index];
    }

    @Override
    public int indexOf(E x) {
        for(int i = 0; i < numItems; i++)
            if(arr[i].equals(x))
                return i;
        return -1;
    }

    @Override
    public boolean isEmpty() {
        return numItems <= 0;
    }

    @Override
    public boolean remove(E x) {
        int delIdx = indexOf(x);
        if(delIdx < 0)
            return false;
        System.arraycopy(arr, delIdx + 1, arr, delIdx, numItems - delIdx - 1);
        numItems--;
        return true;
    }

    @Override
    public E set(int index, E x) {
        if(index < 0 || index >= numItems)
            throw new ArrayIndexOutOfBoundsException();
        E old = arr[index];
        arr[index] = x;
        return old;
    }

    @Override
    public int size() {
        return numItems;
    }

    public String toString() {
        if(isEmpty())
            return "[]";
        String str = "";
        for(int i = 0; i < numItems; i++)
            str += (arr[i] + " ");
        return '[' + str.trim().replaceAll(" ", ", ") + ']';
    }
    
    private void ensureCapacity() {
        if(numItems < arr.length)
            return;
        E[] alloc = (E[]) new Object[numItems * 2];
        for(int i = 0; i < numItems; i++)
            alloc[i] = arr[i];
        arr = alloc;
    }
}
