package cryptanalysis.braking;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author milla
 */
public class BreakingCeasarCipher {

    /**
     * Goes through all options with every possible key and lists them according to key
     * @param text that you want to decrypt
     * @return key number and suggestion of decrypted text  
     */
    public String breakingAllOptions(String text) {
        String breaked = "";
        String b = "";
        int key = 1;

        for (int round = 1; round <= 26; round++) {
            for (int i = 0; i < text.length(); i++) {
                if (Character.isLowerCase(text.charAt(i))) {
                    if ((int) text.charAt(i) == 32) {
                        char ch = (char) ((int) text.charAt(i));
                        breaked = breaked + ch;
                    } else {
                        char ch = (char) (((int) text.charAt(i)
                                + (26 - round) - 97) % 26 + 97);
                        breaked = breaked + ch;
                    }
                } else {
                    if ((int) text.charAt(i) == 32) {
                        char ch = (char) ((int) text.charAt(i));
                        breaked = breaked + ch;
                    } else {
                        char ch = (char) (((int) text.charAt(i)
                                + (26 - round) - 65) % 26 + 65);
                        breaked = breaked + ch;
                    }
                }
            }
            b = b + "Key " + key + ": " + breaked + "\n";
            breaked = "";
            key++;

        }
        return b;
    }

}
