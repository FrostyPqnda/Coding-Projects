@SuppressWarnings("unchecked")
public class LinearProbingHashTable<E> implements HashTable<E> {
    private class LinearProbingHashingUtility implements HashingUtility<E> {
        @Override
        public int find(E x) {
            int idx = hash(x);
            while(table[idx] != null && !table[idx].equals(x)) 
                idx = (idx + 1) % table.length;
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
            if(numItems < table.length) return;

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
    HashingUtility<E> lphu;

    public LinearProbingHashTable() {
        this(DEFAULT_SIZE);
    }

    public LinearProbingHashTable(int capacity) {
        lphu = new LinearProbingHashingUtility();
        numItems = 0;
        this.capacity = capacity;
        table = (E[]) new Object[lphu.nextPrime(capacity)];
    }

    @Override
    public void insert(E x) {
        lphu.rehash();
        int idx = lphu.find(x);
        if(lphu.isActive(idx)) return;
        table[idx] = x;
        numItems++;
        return;
    }

    @Override
    public void remove(E x) {
        int idx = lphu.find(x);
        table[idx] = null;
        numItems--;
    }

    @Override
    public void display() {
        for(int i = 0; i < table.length; i++) 
            System.out.printf("%s\n", table[i]);
    }
}