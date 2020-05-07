/*
 * Base class for hash tables with open addressing collision resolution techniques
 * such as linear probing, quadratic probing and double hashing
 */

package com.bhavesh.DataStructures.HashTable;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
public abstract class HashTableOpenAddressingBase<K, V> {

    protected double loadFactor;
    protected int capacity, threshold;

    // To keep track of the total number of used buckets inside the
    // hash table (includes cells marked as deleted)
    protected int usedBuckets;

    // To keep track of the number of unique keys currently inside the hash table
    protected int keyCount;

    // These arrays store the key-value pairs
    protected K[] keys;
    protected V[] values;

    // Special marker token used to indicate the deletion of a key-value pair
    protected final K TOMBSTONE = (K) (new Object());

    private static final int DEFAULT_CAPACITY = 7;
    private static final double DEFAULT_LOAD_FACTOR = 0.65;

    protected HashTableOpenAddressingBase() {
        this(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR);
    }

    protected HashTableOpenAddressingBase(int capacity) {
        this(capacity, DEFAULT_LOAD_FACTOR);
    }

    protected HashTableOpenAddressingBase(int capacity, double loadFactor) {
        if (capacity <= 0) throw new IllegalArgumentException("Invalid capacity");

        if (loadFactor <= 0 || Double.isNaN(loadFactor) || Double.isInfinite(loadFactor))
            throw new IllegalArgumentException("Invalid load factor");

        this.loadFactor = loadFactor;
        this.capacity = Math.max(DEFAULT_CAPACITY, capacity);
        adjustCapacity();
        threshold = (int) (this.capacity * loadFactor);

        keys = (K[]) new Object[this.capacity];
        values = (V[]) new Object[this.capacity];
    }


    // These three methods are used to dictate how the probing is to actually
    // occur for whatever open addressing scheme you are implementing
    protected abstract void setupProbing(K key);

    protected abstract int probe(int x);

    // Adjusts the capacity of the hash table after it has been made larger.
    // This is important to be overridden because the size of the hashtable
    // controls the functionality of the probing
    protected abstract void adjustCapacity();

    // Increases the capacity of the hash table
    protected void increaseCapacity() {
        capacity = (2 * capacity) + 1;
    }

    public void clear() {
        for (int i = 0; i < capacity; i++) {
            keys[i] = null;
            values[i] = null;
        }
        keyCount = usedBuckets = 0;
    }

    // Returns the number of keys inside the hash table
    public int size() {
        return keyCount;
    }

    // Returns the capacity of the hashtable
    public int getCapacity() {
        return capacity;
    }

    // Returns true/false depending on whether the hash table is empty
    public boolean isEmpty() {
        return keyCount == 0;
    }

    public V put(K key, V value) {
        return insert(key, value);
    }

    public V add(K key, V value) {
        return insert(key, value);
    }

    // Returns true/false depending on whether a given key exists within the hash table
    public boolean containsKey(K key) {
        return hasKey(key);
    }

    // Returns a list of keys present in the hash table
    public List<K> keys() {
        List<K> hashTableKeys = new ArrayList<>(size());
        for (int i = 0; i < capacity; i++) {
            if (keys[i] != null && keys[i] != TOMBSTONE)
                hashTableKeys.add(keys[i]);
        }
        return hashTableKeys;
    }

    // Returns a list of non-unique values present in the hash table
    public List<V> values() {
        List<V> hashTableValues = new ArrayList<>(size());
        for (int i = 0; i < capacity; i++) {
            if (keys[i] != null && keys[i] != TOMBSTONE)
                hashTableValues.add(values[i]);
        }
        return hashTableValues;
    }

    // Double the size of the hash table
    protected void resizeTable() {
        increaseCapacity();
        adjustCapacity();

        threshold = (int) (capacity * loadFactor);

        K[] oldKeyTable = (K[]) new Object[capacity];
        V[] oldValueTable = (V[]) new Object[capacity];

        // Perform key table pointer  swap
        K[] keyTableTmp = keys;
        keys = oldKeyTable;
        oldKeyTable = keyTableTmp;

        // Perform value table pointer swap
        V[] valueTableTmp = values;
        values = oldValueTable;
        oldValueTable = valueTableTmp;

        // Reset the key count and buckets used since we are about to
        // re-insert all the keys into the hash table
        keyCount = usedBuckets = 0;

        for (int i = 0; i < oldKeyTable.length; i++) {
            if (oldKeyTable[i] != null && oldKeyTable[i] != TOMBSTONE)
                insert(oldKeyTable[i], oldValueTable[i]);
            oldKeyTable[i] = null;
            oldValueTable[i] = null;
        }
    }


    // Converts hash value to an index. Essentially, this strips the
    // negative sign and places the hash value in the domain [0, capacity)
    protected final int normalizeIndex(int keyHash) {
        return (keyHash & 0x7FFFFFFF) % capacity;
    }

    protected static final int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }

    // Place a key-value pair in the hash table. If the value already
    // exists inside the hash table then the value is updated
    public V insert(K key, V value) {
        if (key == null) throw new IllegalArgumentException("Null key");
        if (usedBuckets >= threshold) resizeTable();

        setupProbing(key);
        final int offset = normalizeIndex(key.hashCode());

        for (int i = offset, j = -1, x = 1; ; normalizeIndex(offset + probe(x++))) {

            // The current slot was previously deleted
            if (keys[i] == TOMBSTONE) {
                if (j == -1) j = i;
            }

            // The current cell already contains a key
            else if (keys[i] != null) {

                // The key we are trying to insert already exists in the hash table,
                // so update its value with the most recent value
                if (keys[i].equals(key)) {

                    V oldValue = values[i];
                    if (j == -1) {
                        values[i] = value;
                    }
                    else {
                        keys[i] = TOMBSTONE;
                        values[i] = null;
                        keys[j] = key;
                        values[j] = value;
                    }
                    return oldValue;
                }
            }

            // Current cell is null so an insertion/update can take place
            else {

                // No previously encountered deleted buckets
                if (j == -1) {
                    usedBuckets++;
                    keyCount++;
                    keys[i] = key;
                    values[i] = value;
                }

                // Previously seen deleted bucket. Instead of inserting
                // the new element at i where the null element is
                // insert it where the deleted token was found
                else {
                    keyCount++;
                    keys[j] = key;
                    values[j] = value;
                }

                return null;
            }
        }
    }

    // Returns true/false depending on whether a given key exists in the hash table
    public boolean hasKey(K key) {
        if (key == null) throw new IllegalArgumentException("Null key");

        setupProbing(key);
        final int offset = normalizeIndex(key.hashCode());

        // Start at the original hash value and probe until we find a spot where our key
        // is or hit a null element in which case our element does not exists
        for (int i = offset, j = -1, x = 1; ; i = normalizeIndex(offset + probe(x++))) {

            // Ignore deleted cells, but record where the first index
            // of a deleted cell is found to perform lazy relocation later
            if (keys[i] == TOMBSTONE) {
                if (j == -1) j = i;
            }

            // We hit a non-null key, perhaps it's the one we are looking for
            else if (keys[i] != null) {

                if (keys[i].equals(key)) {

                    // If j != -1 this means we previously encountered a deleted cell.
                    // We can perform an optimization by swapping the entries in cells
                    // i and j so that the next time we search for this key it will be
                    // found faster.
                    if (j != -1) {
                        keys[j] = keys[i];
                        values[j] = values[i];
                        keys[i] = TOMBSTONE;
                        values[i] = null;
                    }
                    return true;
                }
            }

            // Key was not found in the hash table
            else
                return false;
        }
    }

    // Get the value associated with the input key
    public V get(K key) {
        if (key == null) throw new IllegalArgumentException("Null key");

        setupProbing(key);
        final int offset = normalizeIndex(key.hashCode());

        // Start at the original hash value and probe until we find a spot where our key
        // is or we hit a null element in which case our element does not exist.
        for (int i = offset, j = -1, x = 1; ; i = normalizeIndex(offset + probe(x++))) {

            // Ignore deleted cells, but record where the first index
            // of a deleted cell is found to perform lazy relocation later
            if (keys[i] == TOMBSTONE) {
                if (j == -1) j = i;
            }

            // We hit a non-null key, perhaps it's the one we are looking for
            else if (keys[i] != null) {

                if (keys[i].equals(key)) {

                    // If j != -1 this means we previously encountered a deleted cell.
                    // We can perform an optimization by swapping the entries in cells
                    // i and j so that the next time we search for this key it will be
                    // found faster.
                    if (j != -1) {
                        keys[j] = keys[i];
                        values[j] = values[i];
                        keys[i] = TOMBSTONE;
                        values[i] = null;
                        return values[j];
                    } else {
                        return values[i];
                    }
                }
            }

            // Element was not found in the hash table
            else
                return null;
        }
    }


    // Removes a key from the hash table and returns the value
    public V remove(K key) {
        if (key == null) throw new IllegalArgumentException("Null key");

        setupProbing(key);
        final int offset = normalizeIndex(key.hashCode());

        // Starting at the original hash probe until we find a spot where our key is
        // or we hit a null element in which case our element does not exist
        for (int i = offset, x = 1; ; i = normalizeIndex(offset + probe(x++))) {

            // Ignore deleted cells
            if (keys[i] == TOMBSTONE) continue;

            // Key was not found in the hash table
            if (keys[i] == null) return null;

            // The key we want to remove is present in the hash table
            if (keys[i].equals(key)) {
                keyCount--;
                V oldValue = values[i];
                keys[i] = TOMBSTONE;
                values[i] = null;
                return oldValue;
            }
        }
    }

    // Return a String representation of this hash-table.
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("{");
        for (int i = 0; i < capacity; i++)
            if (keys[i] != null && keys[i] != TOMBSTONE) sb.append(keys[i] + " => " + values[i] + ", ");
        sb.append("}");

        return sb.toString();
    }
}
