package cryptanalysis.breaking;

/**
 * Class that will find out key used in encryption that is encrypted with Caesar Cipher using brute
 * force.
 *
 */
public class BreakingCaesarCipher {

    /**
     * Goes through all options with every possible key and lists them according
     * to key
     *
     * @param text that you want to decrypt
     * @return key number and suggestion of decrypted text
     */
    public String breakingAllOptions(String text) {
        String breaked = "";
        char [] bre = new char[text.length()];
        int key = 1;

        for (int round = 1; round <= 26; round++) {

            for (int i = 0; i < text.length(); i++) {
                if ((text.charAt(i)) > 96 && (text.charAt(i)) < 123) {

                    char ch = (char) (((int) text.charAt(i)
                            + (26 - round) - 97) % 26 + 97);
                    bre[i] = ch;

                } else if ((int) text.charAt(i) > 64 && (int) text.charAt(i) < 91) {

                    char ch = (char) (((int) text.charAt(i)
                            + (26 - round) - 65) % 26 + 65);
                      bre[i] = ch;

                } else {
                    char ch = (char) ((int) text.charAt(i));
                      bre[i] = ch;
                }
            }
            breaked = breaked + "Key " + key + ": " + String.valueOf(bre) + "\n";
            key++;

        }
        return breaked;
    }

}
