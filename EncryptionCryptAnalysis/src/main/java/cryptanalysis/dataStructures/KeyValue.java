
package cryptanalysis.dataStructures;

/**
 *
 * @author milla
 */

public class KeyValue <K, V> {

    private K key;
    private V value;
 
    public KeyValue(K key, V value) {

        this.key = key;
        this.value = value;
    }

    public K getAvain() {

        return key;
    }

 

    public void setKey(K key) {
        this.key = key;
    }


    public V getValue() {

        return value;
    }

    public void setValue(V value) {

        this.value = value;

    }

}
