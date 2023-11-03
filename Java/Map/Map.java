import MyImplementations.*;

public interface Map<K, V> {
    interface Entry<K, V> {
        K getKey();
        V getValue();
        boolean equals(Entry<K, V> o);
    }
    void clear();
    boolean containsKey(K key);
    boolean containsValue(V value);
    Set<Entry<K, V>> entrySet();
    V get(K key);
    boolean isEmpty();
    Set<K> keySet();
    V put(K key, V value);
    V remove(K key);
    int size();
    boolean equals(Map<K, V> o);
}