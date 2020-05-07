/*
 * An implementation of a hash table using open addressing with quadratic probing as a collision
 * resolution technique
 *
 * In this implementation we are using the following probing function:
 * H(k, x) = h(k) + f(x) mod 2^n
 *
 * where h(k) is the hash for the given key, f(x) = (x + x^2) / 2 and n is a natural number
 */

package com.bhavesh.DataStructures.HashTable;

public class HashTableQuadraticProbing<K, V> extends HashTableOpenAddressingBase<K, V> {

    public HashTableQuadraticProbing() {
        super();
    }

    public HashTableQuadraticProbing(int capacity) {
        super(capacity);
    }

    public HashTableQuadraticProbing(int capacity, double loadFactor) {
        super(capacity, loadFactor);
    }

    // Given a number this method finds the next power of two greater that this value
    private static int nextPowerOfTwo(int n) {
        return Integer.highestOneBit(n) << 1;
    }

    @Override
    protected void setupProbing(K key) {}

    @Override
    protected int probe(int x) {
        return (x * x + x) >> 1;
    }

    // Increase the capacity of the hashtable to the next power of two.
    @Override
    protected void increaseCapacity() {
        capacity = nextPowerOfTwo(capacity);
    }

    // Adjust the capacity of the hashtable to be a power of two.
    @Override
    protected void adjustCapacity() {
        int pow2 = Integer.highestOneBit(capacity);
        if (capacity == pow2) return;
        increaseCapacity();
    }
}