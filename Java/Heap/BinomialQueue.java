@SuppressWarnings("unchecked")
public class BinomialQueue<E extends Comparable<? super E>> implements Heap<E> {
    static class BinomialNode<E> {
        E data;
        BinomialNode<E> leftChild, nextSibling;

        public BinomialNode(E data) {
            this(data, null, null);
        }

        BinomialNode(E data, BinomialNode<E> left, BinomialNode<E> next) {
            this.data = data;
            leftChild = left;
            nextSibling = next;
        }
    }
    
    private static final int DEFAULT_TREES = 1;
    private int numItems;
    private BinomialNode<E>[] trees;

    public BinomialQueue() {
        trees = new BinomialNode[DEFAULT_TREES];
        makeEmpty();
    }

    public BinomialQueue(E item) {
        numItems = 1;
        trees = new BinomialNode[1];
        trees[0] = new BinomialNode<>(item);
    }

    public void merge(BinomialQueue<E> rhs) {
        if(this == rhs) return;
        
        numItems += rhs.numItems;
        
        if(numItems > capacity()) {
            int maxLen = Math.max(trees.length, rhs.trees.length) + 1;
            expandTheTrees(maxLen);
        }

        BinomialNode<E> carry = null;
        for(int i = 0, j = 1; j <= numItems; i++, j *= 2) {
            BinomialNode<E> t1 = trees[i];
            BinomialNode<E> t2 = i < rhs.trees.length ? rhs.trees[i] : null;

            int whichCase = t1 == null ? 0 : 1;
            whichCase += t2 == null ? 0 : 2;
            whichCase += carry == null ? 0 : 4;

            switch(whichCase) {
                case 0:
                case 1:
                    break;
                case 2:
                    trees[i] = t2;
                    rhs.trees[i] = null;
                    break;
                case 4:
                    trees[i] = carry;
                    carry = null;
                    break;
                case 3:
                    carry = combineTrees(t1, t2);
                    trees[i] = rhs.trees[i] = null;
                    break;
                case 5:
                    carry = combineTrees(t1, carry);
                    trees[i] = null;
                    break;
                case 6:
                    carry = combineTrees(t2, carry);
                    rhs.trees[i] = null;
                    break;
                case 7:
                    trees[i] = carry;
                    carry = combineTrees(t1, t2);
                    rhs.trees[i] = null;
                    break;
            }
        }   

        for(int k = 0; k < rhs.trees.length; k++)
            rhs.trees[k] = null;
        rhs.numItems = 0;
    }

    @Override
    public void insert(E x) {
        merge(new BinomialQueue<>(x));
    }

    @Override
    public E findMin() {
        if(isEmpty())
            throw new IllegalStateException();
            
        int minIndex = findMinIndex();
        E minItem = trees[minIndex].data;
        return minItem;
    }

    @Override
    public E deleteMin() {
        if(isEmpty())
            throw new IllegalStateException();

        int minIndex = findMinIndex();
        E minItem = trees[minIndex].data;

        BinomialNode<E> deletedTree = trees[minIndex].leftChild;

        BinomialQueue<E> deletedQueue = new BinomialQueue<>();
        deletedQueue.expandTheTrees(minIndex + 1);

        deletedQueue.numItems = (1 << minIndex) - 1;
        for(int j = minIndex - 1; j >= 0; j--) {
            deletedQueue.trees[j] = deletedTree;
            deletedTree = deletedTree.nextSibling;
            deletedQueue.trees[j].nextSibling = null;
        }

        trees[minIndex] = null;
        numItems -= deletedQueue.numItems + 1;

        merge(deletedQueue);

        return minItem;
    }

    @Override
    public boolean isEmpty() {
        return numItems == 0;
    }

    @Override
    public void makeEmpty() {
        numItems = 0;
        for(int i = 0; i < trees.length; i++)
            trees[i] = null;
    }

    public void print() {
        System.out.println("Binomial Heap:");
        for(BinomialNode<E> tree : trees)
            print(tree);
        System.out.println();
    }
    
    private void expandTheTrees(int newNumTrees) {
        BinomialNode<E>[] old = trees;
        int oldNumTrees = trees.length;

        trees = new BinomialNode[newNumTrees];
        for(int i = 0; i < oldNumTrees; i++) 
            trees[i] = old[i];
        for(int i = oldNumTrees; i < newNumTrees; i++)
            trees[i] = null;
    }

    private BinomialNode<E> combineTrees(BinomialNode<E> t1, BinomialNode<E> t2) {
        if(t1.data.compareTo(t2.data) > 0) 
            return combineTrees(t2, t1);
        t2.nextSibling = t1.leftChild;
        t1.leftChild = t2;
        return t1;
    }

    private int capacity() {
        return (1 << trees.length) - 1;
    }

    private int findMinIndex() {
        int min = 0;
        int i;
        for(i = 0; trees[i] == null; i++) {}
        for(min = i; i < trees.length; i++) 
            if(trees[i] != null && trees[i].data.compareTo(trees[min].data) < 0)
                min = i;
        return min;
    }

    private void print(BinomialNode<E> r) {
        if(r == null) return;
        System.out.print(r.data + " ");
        print(r.nextSibling);
        print(r.leftChild);    
    }
}
