/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cryptanalysis.ciphers;

/**
 * Class that creates encryption and decryption using Caesar Cipher
 * @author milla
 */
public class CaesarCipher {
    
    /**
     * Encrypts given text with Caesar Cipher
     * @param text text that will be encrypted
     * @param n key length
     * @return encrypted text
     */
    public String encryption(String text, int n) {
        String encrypted = "";

        for (int i = 0; i < text.length(); i++) {

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
     * Decrypts given text with Caesar Cipher
     * @param text text that will be decrypted
     * @param n key length
     * @return decrypted text
     */
    public String decryption(String text, int n) {
        String decrypted = "";
        n = 26 - n;

        for (int i = 0; i < text.length(); i++) {
            if (Character.isLowerCase(text.charAt(i))) {
               
                    char ch = (char) (((int) text.charAt(i)
                            + n - 97) % 26 + 97);
                    decrypted = decrypted + ch;
                

            } else {
                if ((int) text.charAt(i) == 32) {
                    char c = (char) ((int) text.charAt(i));
                    decrypted = decrypted + c;
                } else {
                    char ch = (char) (((int) text.charAt(i)
                            + n - 65) % 26 + 65);
                    decrypted = decrypted + ch;
                }

            }
        }

        return decrypted;
    }
}
