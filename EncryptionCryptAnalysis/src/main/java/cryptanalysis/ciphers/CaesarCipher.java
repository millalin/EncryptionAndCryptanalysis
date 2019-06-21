
package cryptanalysis.ciphers;

/**
 * Class that creates encryption and decryption using Caesar Cipher
 *
 * @author milla
 */
public class CaesarCipher {

    /**
     * Encrypts given text with Caesar Cipher
     *
     * @param text text that will be encrypted
     * @param n key length
     * @return encrypted text
     */
    public String encryption(String text, int n) {
        char[] chars = new char[text.length()];

        for (int i = 0; i < text.length(); i++) {

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
     * Decrypts given text with Caesar Cipher
     *
     * @param text text that will be decrypted
     * @param n key length
     * @return decrypted text
     */
    public String decryption(String text, int n) {
        char[] chars = new char[text.length()];
        n = 26 - n;

        for (int i = 0; i < text.length(); i++) {

            if ((text.charAt(i)) > 96 && (text.charAt(i)) < 123) {

                char ch = (char) (((int) text.charAt(i)
                        + n - 97) % 26 + 97);
                chars[i] = ch;

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
}
