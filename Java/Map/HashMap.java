import MyImplementations.*;

@SuppressWarnings("unchecked")
public class HashMap<K, V> implements Map<K, V> {
    class HashEntry implements Map.Entry<K, V> {
        K key;
        V value;
        HashEntry next;

        public HashEntry(K key, V value) {
            this.key = key;
            this.value = value;
            next = null;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public boolean equals(Map.Entry<K, V> o) {
            return key.equals(o.getKey()) && value.equals(o.getValue());
        }

        public String toString() {
            return key + "=" + value;
        }
    }
    
    HashEntry[] map;
    int numItems;
    int prime;
    static final int DEFAULT_SIZE = 101;
    
    public HashMap() {
        this(DEFAULT_SIZE);
    }

    public HashMap(int initCapacity) {
        numItems = 0;
        map = new HashMap.HashEntry[nextPrime(2 * initCapacity)];
    }

    @Override
    public void clear() {
        numItems = 0;
        for(int i = 0; i < map.length; i++)
            map[i] = null;
    }

    @Override
    public boolean containsKey(K key) {
        for(int i = 0; i < map.length; i++) 
            if(map[i] != null)
                if(map[i].getKey().equals(key))
                    return true;
        return false;
    }

    @Override
    public boolean containsValue(V value) {
        for(int i = 0; i < map.length; i++) 
            if(map[i] != null)
                if(map[i].getValue().equals(value))
                    return true;
        return false;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        Set<Entry<K, V>> set = new Set<>();
        for(HashEntry me : map)
            if(me != null)
                set.add(me);
        return set;
    }

    @Override
    public V get(K key) {
        int idx = find(key);
        return map[idx] == null ? null : map[idx].getValue();
    }

    @Override
    public boolean isEmpty() {
        return numItems == 0;
    }

    @Override 
    public Set<K> keySet() {
        Set<K> keys = new Set<>();
        for(HashEntry me : map)
            if(me != null)
                keys.add(me.getKey());
        return keys;
    }

    @Override
    public V put(K key, V value) {      
        rehash();
        int idx = find(key);
        V val = map[idx] != null ? map[idx].getValue() : null;
        HashEntry e = new HashEntry(key, value);
        e.next = map[idx];
        map[idx] = e;
        numItems++;
        return val;
    }

    @Override
    public V remove(K key) {
        if(!containsKey(key)) return null;
        int idx = find(key);
        V old = map[idx].getValue();
        map[idx] = null;
        return old;
    }

    @Override
    public int size() {
        return numItems;
    }

    @Override
    public boolean equals(Map<K, V> o) {
        if(o.size() != size() || !(o instanceof HashMap))
            return false;
        HashMap<K, V> cmp = (HashMap<K, V>)o;
        for(int i = 0; i < map.length; i++)
            if(map[i] != null && !map[i].equals(cmp.map[i]))
                return false;
        return true;
    }

    public final String toString() {
        if(isEmpty())
            return "{}";
        
        String str = "";
        for(HashEntry me : map)
            if(me != null)
                str += (me + " ");
        return '{' + str.trim().replaceAll(" ", ", ") + '}';
    }

    private final int find(K key) {
        int h1 = hash1(key);
        int h2 = hash2(key);
        while(map[h1] != null && !map[h1].getKey().equals(key)) {
            h1 += h2;
            h1 %= map.length;
        }
        return h1;
    }

    private final int hash1(K key) {
        int hashCode = key.hashCode();
        hashCode %= map.length;
        if(hashCode < 0) hashCode += map.length;
        return hashCode;
    }

    private final int hash2(K key) {
        prime = nextPrime(map.length);
        return prime - hash1(key) % prime;
    }

    private final int nextPrime(int n) {
        if(n < 2) return 2;
        while(!isPrime(n))
            n++;
        return n; 
    }

    private final boolean isPrime(int n) {
        if(n < 2) return false;
        for(int i = 2; i <= n / 2; i++)
            if(n % i == 0)
                return false;
        return true;
    }

    private final void rehash() {
        if(numItems < map.length) return;
        HashEntry[] old = map;
        map = new HashMap.HashEntry[nextPrime(2 * old.length)];

        for(HashEntry me : old)
            if(me != null)
                put(me.getKey(), me.getValue());
        
        return;
    }
}

