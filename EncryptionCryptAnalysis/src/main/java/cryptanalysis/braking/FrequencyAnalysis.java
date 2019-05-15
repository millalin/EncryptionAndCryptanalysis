/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cryptanalysis.braking;

/**
 *
 * @author milla
 */
public class FrequencyAnalysis {

    int letters[];

    public FrequencyAnalysis() {

    }

    /**
     * Counts how many time each alphabet occurs in text. Counts upperCase
     * letters to lowerCase
     *
     * @param text text to be analyzed
     * @return countKey()  key that decrypts text
     */
    public int countFrequencies(String text) {
        letters = new int[123]; //"ABCDEFGHIJKLMNOPQRSTUWXYZ";

        for (int i = 0; i < text.length(); i++) {

            // char c = (char)((int) text.charAt(i));
            int ch = ((int) text.charAt(i));

            if (Character.isLowerCase(text.charAt(i))) {
                if (ch > 96 && ch < 123) {
                    letters[ch] += 1;
                }
            } else {
                if (ch > 64 && ch < 91) {
                    letters[ch + 32] += 1;
                }
            }

        }
        return countKey();
    }

    /**
     * Finds out what letter comes up most often in the text and counts key using that information.
     *
     * @return key key number that will decrypt the text
     */
    public int countKey() {
        int key = 0;
        int biggest = 0;
        int mostUsed = 0;

        for (int i = 96; i < letters.length; i++) {
            int letter = letters[i];
            if (letter > biggest) {
                biggest = letter;
                mostUsed = i;
            }

        }
        if (mostUsed == 101) {
            key = 26;
        } else if (mostUsed < 101) {
            key = 26 + mostUsed - 101;
        } else {
            key = mostUsed - 101; //value of e =101
        }

        return key;
    }

    public int[] freq() {

        return letters;
    }

}
