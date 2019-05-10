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
public class CaesarCipher {

    public static String encryption(String text, int n) {
        String encrypted = "";

        for (int i = 0; i < text.length(); i++) {
            if (Character.isLowerCase(text.charAt(i))) {
                char ch = (char) (((int) text.charAt(i)
                        + n - 97) % 26 + 97);
                encrypted = encrypted + ch;
            } else {
                char ch = (char) (((int) text.charAt(i)
                        + n - 65) % 26 + 65);
                encrypted = encrypted + ch;
            }
        }
        return encrypted;
    }

    public static String decryption(String text, int n) {
        String decrypted = "";
       n = 26 -n ;
        
        for (int i = 0; i < text.length(); i++) {
            if (Character.isLowerCase(text.charAt(i))) {
                char ch = (char) (((int) text.charAt(i)
                        + n - 97 ) % 26 + 97  );
                decrypted = decrypted + ch;
            } else {
                char ch = (char) (((int) text.charAt(i)
                        + n - 65 ) % 26 + 65  );
                decrypted = decrypted + ch;
            }
        }

        return decrypted;
    }
}
