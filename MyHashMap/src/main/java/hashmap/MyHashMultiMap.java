package hashmap;

import java.util.LinkedList;
import java.util.List;

public class MyHashMultiMap<K, V> {
    private static final int INITIAL_CAPACITY = 16;
    private Node<K, List<V>>[] table;
    private int size;

    public MyHashMultiMap() {
        table = new Node[INITIAL_CAPACITY];
        size = 0;
    }

    public void put(K key, V value) {
        int index = getIndex(key);
        Node<K, List<V>> node = table[index];

        if (node == null) {
            table[index] = new Node<>(key, new LinkedList<>());
            node = table[index];
        }

        node.value.add(value);
        size++;
    }

    public List<V> get(K key) {
        int index = getIndex(key);
        Node<K, List<V>> node = table[index];

        if (node != null) {
            return node.value;
        }
        return null;
    }

    public void remove(K key) {
        int index = getIndex(key);
        Node<K, List<V>> node = table[index];

        if (node != null) {
            table[index] = null;
            size--;
        }
    }

    public int size() {
        return size;
    }

    private int getIndex(K key) {
        if (key == null) return 0;
        return key.hashCode() % table.length;
    }

    private static class Node<K, V> {
        K key;
        V value;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
