/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cryptanalysis.ciphers;

/**
 *
 * @author milla
 */
public class VigenereCipher {

    public String makeKey(String text, String key) {

        String newKey = "";
        int j = 0;

        for (int i = 0; i < text.length(); i++) {

            if (j > key.length() - 1) {
                j = 0;
            }
            char c = key.charAt(j);
            newKey += c;
            j++;

        }

        return newKey;
    }

    public String encryption(String text, String key) {
        String encrypted = "";
        int n = 0;
        String keyGenerated = this.makeKey(text, key);

        for (int i = 0; i < text.length(); i++) {

            if (Character.isLowerCase(text.charAt(i))) {
                if ((int) text.charAt(i) == 32) {
                    char c = (char) ((int) text.charAt(i));
                    encrypted = encrypted + c;
                } else {
                    n = (int) keyGenerated.charAt(i) - 97;
                    char c = (char) (((int) text.charAt(i)
                            + n - 97) % 26 + 97);
                    encrypted = encrypted + c;
                }

            } else {
                if ((int) text.charAt(i) == 32) {
                    char c = (char) ((int) text.charAt(i));
                    encrypted = encrypted + c;
                } else {
                    n = (int) keyGenerated.charAt(i) - 65;
                    char ch = (char) (((int) text.charAt(i)
                            + n - 65) % 26 + 65);
                    encrypted = encrypted + ch;
                }

            }
        }
        return encrypted;
    }

}
