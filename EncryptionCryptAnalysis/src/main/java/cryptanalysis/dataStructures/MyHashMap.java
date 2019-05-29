
package cryptanalysis.dataStructures;

/**
 * Own implemention of HashMap.
 * @author milla
 */

public class MyHashMap<K, V> {

    private MyArrayList<KeyValue<K, V>>[] keyvalues;
    private int values;
 
    public MyHashMap() {

        this.keyvalues = new MyArrayList[32];
        this.values = 0;
    }

}
