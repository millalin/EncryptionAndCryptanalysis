package cryptanalysis.datastructures;

/**
 * Own implementation of HashMap.Using help of MyArrayList implementation.
 *
 * @author milla
 */
public class MyHashMap<K, V> {

    private MyArrayList<KeyValue<K, V>>[] keyValues;
    private int values;
    public MyArrayList keys;

    public MyHashMap() {

        this.keyValues = new MyArrayList[32];
        this.values = 0;
        keys = new MyArrayList();
    }

    /**
     * Finds values of specific key. 
     * @param key key  of which values is searched
     * @return list of value(s)
     */
    public V get(K key) {

        int hashValue = Math.abs(key.hashCode() % this.keyValues.length);
        if (this.keyValues[hashValue] == null) {
            return null;
        }
        MyArrayList<KeyValue<K, V>> indexValues = this.keyValues[hashValue];

        for (int i = 0; i < indexValues.size(); i++) {
            if (indexValues.get(i).getKey().equals(key)) {
                return indexValues.get(i).getValue();
            }
        }
        return null;
    }

    /**
     * Puts key and value to HashMap;
     *
     * @param key key to be added
     * @param value value to be added
     */
    public void put(K key, V value) {

        MyArrayList<KeyValue<K, V>> indexValues = getListOfKey(key);

        int ind = getKeyIndex(indexValues, key);

        if (ind < 0) {

            indexValues.add(new KeyValue<>(key, value));
            this.values++;
            keys.add(key);

        } else {
            indexValues.get(ind).setValue(value);
            keys.add(key);
        }

        if (1.0 * this.values / this.keyValues.length > 0.75) {
            growSize();
        }
    }

    private MyArrayList<KeyValue<K, V>> getListOfKey(K key) {

        int hashValue = Math.abs(key.hashCode() % keyValues.length);

        if (keyValues[hashValue] == null) {
            keyValues[hashValue] = new MyArrayList<>();
        }

        return keyValues[hashValue];

    }

    private int getKeyIndex(MyArrayList<KeyValue<K, V>> list, K key) {

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getKey().equals(key)) {
                return i;
            }
        }

        return -1;

    }

    /**
     * Makes new list and copies old ones values when there is need for new space.
     */
    private void growSize() {

        MyArrayList<KeyValue<K, V>>[] newList = new MyArrayList[this.keyValues.length * 2];

        for (int i = 0; i < keyValues.length; i++) {
            copyList(newList, i);
        }

        keyValues = newList;

    }

    private void copyList(MyArrayList<KeyValue<K, V>>[] newList, int ind) {
        if (this.keyValues[ind] == null) {
            System.out.println("Nothing to copied");
        } else {
            for (int i = 0; i < this.keyValues[ind].size(); i++) {
                KeyValue<K, V> value = this.keyValues[ind].get(i);
                int hashValue = Math.abs(value.getKey().hashCode() % newList.length);
                if (newList[hashValue] == null) {
                    newList[hashValue] = new MyArrayList<>();
                }

                newList[hashValue].add(value);
            }
        }
    }

    public V remove(K key) {

        MyArrayList<KeyValue<K, V>> indexValues = getListOfKey(key);

        if (indexValues == null || indexValues.size() == 0) {
            return null;
        }

        int ind = getKeyIndex(indexValues, key);

        if (ind < 0) {
            return null;
        }

        KeyValue<K, V> kv = indexValues.get(ind);

        indexValues.remove(kv);

        return kv.getValue();

    }

    public boolean containsKey(K key) {

        int hashValue = Math.abs(key.hashCode() % this.keyValues.length);

        if (this.keyValues[hashValue] == null) {

            return false;

        }

        MyArrayList<KeyValue<K, V>> indexValues = this.keyValues[hashValue];

        for (int i = 0; i < indexValues.size(); i++) {
            if (indexValues.get(i).getKey().equals(key)) {
                return true;
            }
        }

        return false;
    }

    public MyArrayList keys() {

        return keys;
    }
}
