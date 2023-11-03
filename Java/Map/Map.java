import MyImplementations.*;

public interface Map<K, V> {
    void clear();
    boolean containsKey(K key);
    boolean containsValue(V value);
    V get(K key);
    boolean isEmpty();
    Set<K> keySet();
    V put(K key, V value);
    V remove(K key);
    int size();
    boolean equals(Map<K, V> o);
}