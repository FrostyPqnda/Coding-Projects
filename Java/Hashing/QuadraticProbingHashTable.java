@SuppressWarnings("unchecked")
public class QuadraticProbingHashTable<E> implements HashTable<E> {
    private class QuadraticProbingHashingUtility implements HashingUtility<E> {
        @Override
        public int find(E x) {
            int idx = hash(x);
            int offset = 1;
            while(table[idx] != null && !table[idx].equals(x)) {
                idx += offset;
                offset += 2;
                if(idx >= table.length)
                    idx -= table.length;
            }
            return idx;
        }

        @Override
        public boolean isActive(int idx) {
            return table[idx] != null;
        }

        @Override
        public int hash(E x) {
            int hashCode = x.hashCode();
            hashCode %= table.length;
            if(hashCode < 0) 
                hashCode += table.length;
            return hashCode;
        }

        @Override
        public void rehash() {
            if(numItems < table.length / 2) return;
            E[] old = table;
            table = (E[]) new Object[nextPrime(2 * old.length)];
            numItems = 0;
            for(E item : old) 
                if(item != null) 
                    insert(item);
        }

        @Override
        public int nextPrime(int n) {
            if(n <= 2) return 2;
            while(!isPrime(n))
                n++;
            return n;
        }

        @Override
        public boolean isPrime(int n) {
            if(n < 2) return false;
            for(int i = 2; i <= n / 2; i++)
                if(n % i == 0) 
                    return false;
            return true;
        } 
    }
    static final int DEFAULT_SIZE = 101;
    int numItems, capacity;
    E[] table;
    HashingUtility<E> qphu;

    public QuadraticProbingHashTable() {
        this(DEFAULT_SIZE);
    }

    public QuadraticProbingHashTable(int capacity) {
        qphu = new QuadraticProbingHashingUtility();
        numItems = 0;
        this.capacity = capacity;
        table = (E[]) new Object[qphu.nextPrime(capacity)];
    }

    @Override
    public void insert(E x) {
        qphu.rehash();
        int idx = qphu.find(x);
        if(qphu.isActive(idx)) return;
        table[idx] = x;
        numItems++;
        return;
    }

    @Override
    public void remove(E x) {
        int idx = qphu.find(x);
        table[idx] = null;
        numItems--;
    }

    @Override
    public void display() {
        for(int i = 0; i < table.length; i++) 
            System.out.printf("%s\n", table[i]);
    }
}
