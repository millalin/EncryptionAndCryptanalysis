package cryptanalysis.dataStructures;

/**
 * My own implemention of ArrayList.
 *
 * @author milla
 */
public class MyArrayList<T> {

    public T[] values;
    public int v = 0;

    public MyArrayList() {
        this.values = (T[]) new Object[10];
    }

    /**
     * Adds object to list
     *
     * @param value
     */
    public void add(T value) {

        if (this.v == this.values.length) {
            grow();
        }

        this.values[this.v] = value;
        v++;
    }

    /**
     * If list gets full grows its size
     */
    private void grow() {

        T[] uusi = (T[]) new Object[this.values.length * 3 / 2 + 1];

        for (int i = 0; i < this.values.length; i++) {
            uusi[i] = this.values[i];
        }
        this.values = uusi;

    }

    /**
     * Checks if list contains value
     *
     * @param value
     * @return true if it contains value else false
     */
    public boolean contains(T value) {

        return ind(value) >= 0;
    }

    /**
     * Removes value from list.
     *
     * @param value
     */
    public void remove(T value) {

        int index = ind(value);

        if (index < 0) {
            return;
        }

        moveLeft(index);
        this.v--;

    }

    /**
     * Finds out index of some value
     *
     * @param value
     * @return index if there is one, else -1
     */
    public int ind(T value) {

        for (int i = 0; i < this.v; i++) {

            if (this.values[i] == value) {
                return i;
            }
        }
        return -1;

    }

    /**
     * Moves other values when some value is removed
     *
     * @param ind
     */
    private void moveLeft(int ind) {

        for (int i = ind; i < this.v - 1; i++) {
            this.values[i] = this.values[i + 1];
        }
    }

    /**
     * Finds out value from the list based on index
     *
     * @param index
     * @return value
     */
    public T get(int index) {

        if (index < 0 || index >= this.v) {
            throw new ArrayIndexOutOfBoundsException("Indeksi " + index + " alueen ulkopuolella.");
        }
        return this.values[index];
    }

    public int size() {
        return this.v;
    }

}
