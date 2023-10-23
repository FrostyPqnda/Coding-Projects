interface HashingUtility<E> {
    int find(E x);
    boolean isActive(int idx);
    int hash(E x);
    void rehash();
    int nextPrime(int n);
    boolean isPrime(int n);
}
