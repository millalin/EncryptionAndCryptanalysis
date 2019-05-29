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

    public void add(T arvo) {

        if (this.v == this.values.length) {
            grow();
        }

        this.values[this.v] = arvo;
        v++;
    }

    private void grow() {

        T[] uusi = (T[]) new Object[this.values.length * 3 / 2 + 1];

        for (int i = 0; i < this.values.length; i++) {
            uusi[i] = this.values[i];
        }
        this.values = uusi;

    }

    public boolean contains(T arvo) {

        return ind(arvo) >= 0;
    }

    public void remove(T arvo) {

        int arvonIndeksi = ind(arvo);

        if (arvonIndeksi < 0) {
            return;
        }

        moveLeft(arvonIndeksi);
        this.v--;

    }

    public int ind(T value) {

        for (int i = 0; i < this.v; i++) {

            if (this.values[i] == value) {
                return i;
            }
        }
        return -1;

    }

    private void moveLeft(int ind) {

        for (int i = ind; i < this.v - 1; i++) {
            this.values[i] = this.values[i + 1];
        }
    }

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
