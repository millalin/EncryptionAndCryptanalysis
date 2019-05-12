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

    public int set(String text) {
        letters = new int[123]; //"ABCDEFGHIJKLMNOPQRSTUWXYZ";
        
        for (int i = 0; i < text.length(); i++) {

            // char c = (char)((int) text.charAt(i));
            int ch = ((int) text.charAt(i));
            System.out.println(ch);

            if (Character.isLowerCase(text.charAt(i))) {
                if (ch > 96 && ch < 123) {
                    letters[ch] += 1;
                }
            } else {
                if (ch > 64 && ch < 91) {
                    letters[ch] += 1;
                }
            }

        }
        return mostUsed();
    }

    public int mostUsed() {
        int key = 0;
        int biggest = 0;
        int mostUsed = 0;

        for (int i = 0; i < letters.length; i++) {
            int letter = letters[i];
            if (letter > biggest) {
                biggest = letter;
                mostUsed = i;
            }

        }

       key = mostUsed - 101; //value of e =101

        return key;//mostUsed; //palauttaa indeksin joka eniten käytetty eli taul[3]
    }
    /*
    public int analyze(String text) {
        int key = 0;

        int[] frequencys = new int[26];

        int mostUsed = 0;
        int mostUsedInd = -1;
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) >= 'a' && text.charAt(i) <= 'z') {
                int count = frequencys[text.charAt(i) - 'a']++;

                if (count > mostUsed) {
                    mostUsed = count;
                    mostUsedInd = i;
                }
            }
        }

        key = mostUsedInd - ('e' - 'a'); // Can also directly use 4 instead of 'e' - 'a'

        return key;
    }*/
}
