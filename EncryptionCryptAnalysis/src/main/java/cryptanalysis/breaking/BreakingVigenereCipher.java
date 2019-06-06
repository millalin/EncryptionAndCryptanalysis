/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
     * Probabilities of english alphabet
     */
    public BreakingVigenereCipher() {
        double[] prob = {0.08167, 0.01492, 0.02782, 0.04253,
            0.12702, 0.02228, 0.02015, 0.06094, 0.06966, 0.00153,
            0.00772, 0.04025, 0.02406, 0.06749, 0.07507, 0.01929,
            0.00095, 0.05987, 0.06327, 0.09056, 0.02758, 0.00978,
            0.02360, 0.00150, 0.01974, 0.00074};
    }

    public void removeSpacing(String text) {

    }

    /**
     * Makes set of 3 from letters in text and counts length differences.
     *
     * @param input encrypted text
     * @return best guess of the key length used in encryption
     */
    public int analyzingText(String text) {
     //   String text = removeSpaces(input);
        MyHashMap<String, MyArrayList<Integer>> blocks = new MyHashMap();
        MyArrayList list = new MyArrayList();
        for (int i = 0; i < text.length() - 2; i++) {
            char a = (char) text.charAt(i);
            char b = (char) text.charAt(i + 1);
            char c = (char) text.charAt(i + 2);
            String set = "" + a + b + c;

            if (blocks.containsKey(set)) {
                blocks.get(set).add(i); //listalle indeksit missä eri 3 kirj yhdistelmät esiintyvät
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
     * appears more than once.
     *
     * @param list list of 3 letter sets
     * @param blocks map of 3 letter sets and their length differences
     * @return countedKeyLehgth
     */
    public int countDiff(MyArrayList<String> list, MyHashMap<String, MyArrayList<Integer>> blocks) {
        MyHashMap<Integer, Integer> differences = new MyHashMap();

        for (int i = 0; i < list.size(); i++) {
            String set = list.value(i);
            MyArrayList<Integer> indexes = blocks.get(set);
            if (indexes.size() > 1) {
                for (int j = 0; j < indexes.size() - 1; j++) { //yhden 3 kirj yhdistelmän välit
                    int difference = indexes.value(j + 1) - indexes.value(j); //yksi ero 
                    MyArrayList<Integer> factors = listFactors(difference);

                    for (int k = 0; k < factors.size(); k++) {
                        int factor = factors.value(k);
                        if (differences.containsKey(factor)) {
                            Integer v = differences.get(factor);
                            v++;
                            differences.put(factor, v); //tekijä ja monta kpl on tekijää
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
     * Counts key length that is most probable based on
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
            int key = keys.value(i);
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
     * Counts factors
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
            factors.add(x / factors.value(i));
        }

        return factors;
    }

    public String guessingKey(String text, int keyLength) {
      //  String text = removeSpaces(input);
        String textBasedOnKeyIndex[] = new String[keyLength];
        String guessedKey = "";

        for (int i = 0; i < keyLength; i++) {
            textBasedOnKeyIndex[i] = "";

        }
        for (int i = 0; i < text.length(); i+=keyLength) {
            
            for (int j = 0; j < keyLength; j++) {
                //String string = textBasedOnKeyIndex[j];
                if (i + j > text.length() -1)  {
                    continue;
                } else  {
                     textBasedOnKeyIndex[j] += text.charAt(i + j);
                    
                }
               
                
            }
           // textBasedOnKeyIndex[i % keyLength] += text.charAt(i);
            
        }

        for (int i = 0; i < keyLength; i++) {
            int shift = analysis.countFrequencies(textBasedOnKeyIndex[i]);
            System.out.println("text " + textBasedOnKeyIndex[i]);
            System.out.println("shift " + shift);
            if ('a'+shift <123)     {
                guessedKey += (char) ('a' + shift);
            } else  {
                guessedKey += (char) ('a' -26 + shift);
            }
            
        }

        return guessedKey;
    }

    public String removeSpaces(String input) {
        String text = "";

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i); 
            if (c > 64 && c < 91) {
                text += c;
            }
            if (c > 96 && c < 123) {
                text += c;
            }

        }
        return text;
    }

}
