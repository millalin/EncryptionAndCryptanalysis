/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cryptanalysis.ciphers;

/**
 * Class that will create encryption and decryption from given text with
 * Vigenère cipher
 *
 */
public class VigenereCipher {

    /**
     * Creates key of which length is equal to plaintext. Iqnores spaces.
     *
     * @param text plaintext that will be encrypted
     * @param key key of encryption
     * @return new key to help execute encryption
     */
    public String makeKey(String text, String key) {

        char[]newKey=new char[text.length()];
        int j = 0;
        char c = ' ';

        for (int i = 0; i < text.length(); i++) {

            if (j > key.length() - 1) {
                j = 0;
            }
            if ((int) text.charAt(i) == 32) {
                c = text.charAt(i);
                newKey[i]=c;
            } else {
                c = key.charAt(j);
                newKey[i]=c;
                j++;
            }


        }

        return String.valueOf(newKey);
    }

    /**
     * Encrypts given text.
     *
     * @param text plaintext that will be encrypted
     * @param key key of encryption
     * @return encrypted text
     */
    public String encryption(String text, String key) {
        int n = 0;
        String keyGenerated = this.makeKey(text, key);
        //  char[] keychar = new  char[keyGenerated.length()];
        char[] chars = new char[text.length()];

        for (int i = 0; i < text.length(); i++) {

            if ((keyGenerated.charAt(i)) > 96 && (keyGenerated.charAt(i)) < 123) {
                n = (int) keyGenerated.charAt(i) - 97;
            } else {
                n = (int) keyGenerated.charAt(i) - 65;
            }

            if ((text.charAt(i)) > 96 && (text.charAt(i)) < 123) {

                char c = (char) (((int) text.charAt(i)
                        + n - 97) % 26 + 97);
                chars[i] = c;

            } else if ((int) text.charAt(i) > 64 && (int) text.charAt(i) < 91) {

                char ch = (char) (((int) text.charAt(i)
                        + n - 65) % 26 + 65);
                chars[i] = ch;

            } else {
                char c = (char) ((int) text.charAt(i));
                chars[i] = c;
            }
        }

        return String.valueOf(chars);
    }

    /**
     * Decrypts given text.
     *
     * @param text crypt text that will be decrypted
     * @param key key of encrypted text
     * @return decrypted text
     */
    public String decryption(String text, String key) {
        int n = 0;
        String keyGenerated = this.makeKey(text, key);
        char[] chars = new char[text.length()];
        char c = ' ';

        for (int i = 0; i < text.length(); i++) {

            if ((keyGenerated.charAt(i)) > 96 && (keyGenerated.charAt(i)) < 123) {
                n = 26 - ((int) keyGenerated.charAt(i) - 97);
            } else {
                n = 26 - ((int) keyGenerated.charAt(i) - 65);
            }

            if ((text.charAt(i)) > 96 && (text.charAt(i)) < 123) {
                c = (char) (((int) text.charAt(i)
                        + n - 97) % 26 + 97);

                chars[i] = c;

            } else if ((int) text.charAt(i) > 64 && (int) text.charAt(i) < 91) {

                c = (char) (((int) text.charAt(i)
                        + n - 65) % 26 + 65);

                chars[i] = c;

            } else {
                c = (char) ((int) text.charAt(i));
                chars[i] = c;
            }
        }
        return String.valueOf(chars);
    }

}
