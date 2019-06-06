package cryptanalysis.datastructures;

/**
 * Own implemention of HashMap.
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

    public V get(K key) {

        int hashValue = Math.abs(key.hashCode() % this.keyValues.length);
        if (this.keyValues[hashValue] == null) {
            return null;
        }
        MyArrayList<KeyValue<K, V>> indexValues = this.keyValues[hashValue];

        for (int i = 0; i < indexValues.size(); i++) {
            if (indexValues.value(i).getKey().equals(key)) {
                return indexValues.value(i).getValue();
            }
        }

        return null;

    }

    public void put(K key, V value) {

        MyArrayList<KeyValue<K, V>> indexValues = getListOfKey(key);

        int ind = getKeyIndex(indexValues, key);

        if (ind < 0) {

            indexValues.add(new KeyValue<>(key, value));
            this.values++;
            keys.add(key);

        } else {
            indexValues.value(ind).setValue(value);
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
            if (list.value(i).getKey().equals(key)) {
                return i;
            }
        }

        return -1;

    }

    private void growSize() {

        MyArrayList<KeyValue<K, V>>[] newList = new MyArrayList[this.keyValues.length * 2];

        for (int i = 0; i < keyValues.length; i++) {
            copyList(newList, i);
        }

        keyValues = newList;

    }

    private void copyList(MyArrayList<KeyValue<K, V>>[] newList, int ind) {
        if (this.keyValues[ind] == null) {
            //ei mtn
        } else {
            for (int i = 0; i < this.keyValues[ind].size(); i++) {
                KeyValue<K, V> value = this.keyValues[ind].value(i);
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

        KeyValue<K, V> kv = indexValues.value(ind);

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
            if (indexValues.value(i).getKey().equals(key)) {
                return true;
            }
        }

        return false;
    }

    public MyArrayList keys() {

        return keys;
    }
}
