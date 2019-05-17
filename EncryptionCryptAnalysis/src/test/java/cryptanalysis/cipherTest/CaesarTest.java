/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cryptanalysis.cipherTest;

import cryptanalysis.braking.BreakingCaesarCipher;
import cryptanalysis.braking.FrequencyAnalysis;
import cryptanalysis.ciphers.CaesarCipher;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author milla
 */
public class CaesarTest {

    FrequencyAnalysis freq;
    CaesarCipher caesar;
    BreakingCaesarCipher breaking;
    String testText;

    public CaesarTest() {
        freq = new FrequencyAnalysis();
        caesar = new CaesarCipher();
        testText = "ABCDEF GHIJKLMN";
        breaking = new BreakingCaesarCipher();
    }

    @Before
    public void setUp() {

    }

    @Test
    public void ceasarCipherEncryptsTextRightKey1() {

        String result = caesar.encryption(testText, 1);

        assertEquals("BCDEFG HIJKLMNO", result);
    }

    @Test
    public void ceasarCipherEncryptsTextRightKey25() {

        String result = caesar.encryption(testText, 25);

        assertEquals("ZABCDE FGHIJKLM", result);
    }

    @Test
    public void ceasarCipherDecryptsTextRightKey1() {

        String result = caesar.decryption(testText, 1);

        assertEquals("ZABCDE FGHIJKLM", result);
    }

    @Test
    public void ceasarCipherDecryptsTextRightLowerCase() {
        testText = testText.toLowerCase();
        String result = caesar.decryption(testText, 1);

        assertEquals("zabcde fghijklm", result);
    }

    /**
     * Works only with long text that implements the rule that 'e' is the most
     * common letter.
     */
    @Test
    public void findsOutKeyWithFreqAnalysis() {

        String encrypt = caesar.encryption("AabBbCdddEEEEeeeeee", 1);
        int result = freq.countFrequencies(encrypt);

        assertEquals(1, result);
    }

    @Test
    public void findsOutKeyCornerCase() {

        String encrypt = caesar.encryption("seeecreeet", 26);
        int result = freq.countFrequencies(encrypt);

        assertEquals(26, result);
    }

    @Test
    public void findsOutKeyCornerCase2() {

        String encrypt = caesar.encryption("seeecreeet", 25);
        int result = freq.countFrequencies(encrypt);

        assertEquals(25, result);
    }

    @Test
    public void ceasarCipherAllOptions() {
        String text = "hello WORLD";
        String breaked = "";
        String b = "";
        int key = 1;

        for (int round = 1; round <= 26; round++) {
            for (int i = 0; i < text.length(); i++) {
                if (Character.isLowerCase(text.charAt(i))) {

                    char ch = (char) (((int) text.charAt(i)
                            + (26 - round) - 97) % 26 + 97);
                    breaked = breaked + ch;

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

        String options = breaking.breakingAllOptions(text);

        assertEquals(b, options);
    }

}
