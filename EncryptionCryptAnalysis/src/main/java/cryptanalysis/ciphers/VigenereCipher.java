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

        String newKey = "";
        int j = 0;
        char c = ' ';

        for (int i = 0; i < text.length(); i++) {

            if (j > key.length() - 1) {
                j = 0;
            }
            if ((int) text.charAt(i) == 32) {
                c = text.charAt(i);
            } else {
                c = key.charAt(j);
                j++;
            }

            newKey += c;

        }

        return newKey;
    }

    /**
     * Encrypts given text.
     *
     * @param text plaintext that will be encrypted
     * @param key key of encryption
     * @return encrypted text
     */
    public String encryption(String text, String key) {
        String encrypted = "";
        int n = 0;
        String keyGenerated = this.makeKey(text, key);

        for (int i = 0; i < text.length(); i++) {

            if (Character.isLowerCase(keyGenerated.charAt(i))) {
                n = (int) keyGenerated.charAt(i) - 97;
            } else {
                n = (int) keyGenerated.charAt(i) - 65;
            }

            if (Character.isLowerCase(text.charAt(i))) {

                char c = (char) (((int) text.charAt(i)
                        + n - 97) % 26 + 97);
                encrypted = encrypted + c;

            } else {
                if ((int) text.charAt(i) == 32) {
                    char c = (char) ((int) text.charAt(i));
                    encrypted = encrypted + c;
                } else {

                    char ch = (char) (((int) text.charAt(i)
                            + n - 65) % 26 + 65);
                    encrypted = encrypted + ch;
                }

            }
        }
        return encrypted;
    }

    /**
     * Decrypts given text.
     *
     * @param text crypt text that will be decrypted
     * @param key key of encrypted text
     * @return decrypted text
     */
    public String decryption(String text, String key) {
        String decrypted = "";
        int n = 0;
        String keyGenerated = this.makeKey(text, key);
        char c = ' ';

        for (int i = 0; i < text.length(); i++) {

            if (Character.isLowerCase(keyGenerated.charAt(i))) {
                n = 26 - ((int) keyGenerated.charAt(i) - 97);
            } else {
                n = 26 - ((int) keyGenerated.charAt(i) - 65);
            }

            if (Character.isLowerCase(text.charAt(i))) {
                    c = (char) (((int) text.charAt(i)
                            + n - 97) % 26 + 97);

                decrypted = decrypted + c;

            } else {
                if ((int) text.charAt(i) == 32) {
                    c = (char) ((int) text.charAt(i));
                } else {

                    c = (char) (((int) text.charAt(i)
                            + n - 65) % 26 + 65);
                }
                decrypted = decrypted + c;

            }
        }
        return decrypted;
    }

}
