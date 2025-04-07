class CustomHashMap<K, V> {
    static class Entry<K, V> {
        K key;
        V value;
        Entry<K, V> next;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private final int SIZE = 16;
    private Entry<K, V>[] table;

    @SuppressWarnings("unchecked")
    public CustomHashMap() {
        table = new Entry[SIZE];
    }

    private int hash(K key) {
        return Math.abs(key.hashCode()) % SIZE;
    }

    public void put(K key, V value) {
        int index = hash(key);
        Entry<K, V> newEntry = new Entry<>(key, value);
        if (table[index] == null) {
            table[index] = newEntry;
        } else {
            Entry<K, V> current = table[index];
            Entry<K, V> prev = null;
            while (current != null) {
                if (current.key.equals(key)) {
                    current.value = value;
                    return;
                }
                prev = current;
                current = current.next;
            }
            prev.next = newEntry;
        }
    }

    public V get(K key) {
        int index = hash(key);
        Entry<K, V> current = table[index];
        while (current != null) {
            if (current.key.equals(key)) return current.value;
            current = current.next;
        }
        return null;
    }

    public void remove(K key) {
        int index = hash(key);
        Entry<K, V> current = table[index];
        Entry<K, V> prev = null;

        while (current != null) {
            if (current.key.equals(key)) {
                if (prev == null) table[index] = current.next;
                else prev.next = current.next;
                return;
            }
            prev = current;
            current = current.next;
        }
    }

    public void display() {
        for (int i = 0; i < SIZE; i++) {
            Entry<K, V> current = table[i];
            System.out.print("Bucket " + i + ": ");
            while (current != null) {
                System.out.print("[" + current.key + "=" + current.value + "] -> ");
                current = current.next;
            }
            System.out.println("null");
        }
    }

    public static void main(String[] args) {
        CustomHashMap<String, Integer> map = new CustomHashMap<>();
        map.put("apple", 1);
        map.put("banana", 2);
        map.put("orange", 3);
        map.put("banana", 4); 

        map.display();
        System.out.println("Get banana: " + map.get("banana"));
        map.remove("apple");
        map.display();
    }
}
