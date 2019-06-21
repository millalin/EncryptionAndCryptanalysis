package cryptanalysis.datastructures;

/**
 * My own implementation of ArrayList.
 *
 * @author milla
 */
public class MyArrayList<T> {

    private T[] values;
    private int valuesCount;

    public MyArrayList() {

        this.values = (T[]) new Object[10];
    }

    public void add(T value) {

        if (this.valuesCount == this.values.length) {
            growSize();
        }

        this.values[this.valuesCount] = value;
        valuesCount++;
    }

    /**
     * Grows size of the list by making new list and copies existing values to
     * new list.
     */
    private void growSize() {

        T[] newSize = (T[]) new Object[this.values.length * 3 / 2 + 1];

        for (int i = 0; i < this.values.length; i++) {
            newSize[i] = this.values[i];
        }
        this.values = newSize;
    }

    public boolean contains(T value) {

        return index(value) >= 0;
    }

    /**
     * Removes wanted value from list.
     *
     * @param value value that you want to remove
     */
    public void remove(T value) {

        int valueIndex = index(value);

        if (valueIndex < 0) {
            return;
        }

        moveLeft(valueIndex);

        this.valuesCount--;

    }

    public int index(T value) {

        for (int i = 0; i < this.valuesCount; i++) {
            if (this.values[i] == value) {
                return i;
            }
        }
        return -1;
    }

    /**
     * When removed a value others will be moved to right place in changed list.
     *
     * @param fromIndex
     */
    private void moveLeft(int fromIndex) {

        for (int i = fromIndex; i < this.valuesCount - 1; i++) {
            this.values[i] = this.values[i + 1];
        }
    }

    public T get(int ind) {

        if (ind < 0 || ind >= this.valuesCount) {
            throw new ArrayIndexOutOfBoundsException("Index out of bounds");
        }
        return this.values[ind];
    }

    public int size() {
        return this.valuesCount;
    }

}
