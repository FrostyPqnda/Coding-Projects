public class Hashing {
    public static void main(String[] args) {
        String[] fruits = {
            "APPLE", "KIWI", "PEAR", "ORANGE", "GRAPE", "WATERMELON",
            "LIME", "LEMON", "BANANA", "COCONUT", "STRAWBERRY"
        };
        HashTable<String> ht = new SeparateChainingHashTable<>(fruits.length);
        for(String fruit : fruits)
            ht.insert(fruit);
        ht.display();
    }
}
