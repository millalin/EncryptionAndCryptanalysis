
package cryptanalysis.breaking;

import cryptanalysis.datastructures.MyArrayList;
import cryptanalysis.datastructures.MyHashMap;

/**
 * Class that will break and decrypt text that has been encrypted with Vigenère
 * cipher. Finds out the key length and then guesses used key word.
 *
 */
public class BreakingVigenereCipher {

    FrequencyAnalysis analysis = new FrequencyAnalysis();

    /**
     * Probabilities of English alphabet
     */
    public BreakingVigenereCipher() {
        double[] prob = {0.08167, 0.01492, 0.02782, 0.04253,
            0.12702, 0.02228, 0.02015, 0.06094, 0.06966, 0.00153,
            0.00772, 0.04025, 0.02406, 0.06749, 0.07507, 0.01929,
            0.00095, 0.05987, 0.06327, 0.09056, 0.02758, 0.00978,
            0.02360, 0.00150, 0.01974, 0.00074};
    }

    /**
     * Makes set of 3 from letters in text and counts length differences. Puts
     * set (3 letter combinations) indexes to the list.
     *
     * @param text encrypted text
     * @return best guess of the key length used in encryption
     */
    public int analyzingText(String text) {
        //String text = removeSpaces(input);
        MyHashMap<String, MyArrayList<Integer>> blocks = new MyHashMap();
        MyArrayList list = new MyArrayList();
        for (int i = 0; i < text.length() - 2; i++) {
            char a = (char) text.charAt(i);
            char b = (char) text.charAt(i + 1);
            char c = (char) text.charAt(i + 2);
            String set = "" + a + b + c;
            if (blocks.containsKey(set)) {
                blocks.get(set).add(i);
            } else {
                blocks.put(set, new MyArrayList());
                blocks.get(set).add(i);
                list.add(set);
            }
        }

        return countDiff(list, blocks);
    }

    /**
     * Counts length differences of 3 letter sets. Uses sets of 3 letters that
     * appears more than once. Counts differences of each 3 letter combination.
     * Puts factors and how many time they appear to HashMap.
     *
     * @param list list of 3 letter sets
     * @param blocks map of 3 letter sets and their length differences
     * @return countedKeyLehgth
     */
    public int countDiff(MyArrayList<String> list, MyHashMap<String, MyArrayList<Integer>> blocks) {
        MyHashMap<Integer, Integer> differences = new MyHashMap();

        for (int i = 0; i < list.size(); i++) {
            String set = list.get(i);
            MyArrayList<Integer> indexes = blocks.get(set);
            if (indexes.size() > 1) {
                for (int j = 0; j < indexes.size() - 1; j++) {
                    int difference = indexes.get(j + 1) - indexes.get(j);
                    MyArrayList<Integer> factors = listFactors(difference);

                    for (int k = 0; k < factors.size(); k++) {
                        int factor = factors.get(k);

                        if (differences.containsKey(factor)) {
                            Integer v = differences.get(factor);
                            v++;
                            differences.put(factor, v);
                        } else {
                            differences.put(factor, 1);
                        }
                    }
                }
            }
        }
        return countedKeyLength(differences);
    }

    /**
     * Counts key length that is most probable based on factors.
     *
     * @param differences HashMap where is factors and how many of each factors
     * there is
     * @return suggested key length
     */
    public static int countedKeyLength(MyHashMap<Integer, Integer> differences) {
        MyArrayList<Integer> keys = differences.keys();

        int keyL = 0;
        int biggest = 0;

        for (int i = 0; i < keys.size(); i++) {
            int key = keys.get(i);
            if (key == 1 || key == 2) {
                key = 1; //passed
            } else {
                int luku = differences.get(key);
                if (luku > biggest) {
                    biggest = luku;
                    keyL = key;
                }
            }
        }
        return keyL;

    }

    /**
     * Counts factors.
     *
     * @param x difference from same set of letters
     * @return factors
     */
    public MyArrayList<Integer> listFactors(int x) {
        MyArrayList<Integer> factors = new MyArrayList<Integer>();

        for (int i = 1; i <= (int) Math.sqrt(x); i++) {
            if (x % i == 0) {
                factors.add(i);
            }
        }
        int size = factors.size();
        for (int i = size - 1; i >= 0; i--) {
            factors.add(x / factors.get(i));
        }

        return factors;
    }

    /**
     * Goes through text and counts frequencies for each set of letters. Then
     * shifts each letter of most probable letter on that part.
     *
     * @param text encrypted text
     * @param keyLength length of the keyword
     * @return guessed key
     */
    public String guessingKey(String text, int keyLength) {

        String textBasedOnKeyIndex[] = new String[keyLength];
        char[] keyGuess = new char[keyLength];

        for (int i = 0; i < keyLength; i++) {
            textBasedOnKeyIndex[i] = "";

        }

        for (int i = 0; i < text.length(); i += keyLength) {

            for (int j = 0; j < keyLength; j++) {
                if (i + j > text.length() - 1) {
                    continue;
                } else {
                    textBasedOnKeyIndex[j] += text.charAt(i + j);
                }
            }
        }

        for (int i = 0; i < keyLength; i++) {
            int shift = analysis.countFrequencies(textBasedOnKeyIndex[i]);
            if ('a' + shift < 123) {
                keyGuess[i] = (char) ('a' + shift);
            } else {
                keyGuess[i] = (char) ('a' - 26 + shift);
            }
        }

        return String.valueOf(keyGuess);
    }

}
