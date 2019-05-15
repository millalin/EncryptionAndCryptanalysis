/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cryptanalysis.cipherTest;

import cryptanalysis.braking.FrequencyAnalysis;
import cryptanalysis.ciphers.CaesarCipher;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author milla
 */
public class CiphersTest {

    FrequencyAnalysis freq;
    CaesarCipher caesar;
    String testText;

    public CiphersTest() {
        freq = new FrequencyAnalysis();
        caesar = new CaesarCipher();
        testText = "ABCDEFGHIJKLMN";
    }

    @Before
    public void setUp() {

    }

    @Test
    public void ceasarCipherEncryptsTextRightKey1() {

        String result = caesar.encryption(testText, 1);

        assertEquals("BCDEFGHIJKLMNO", result);
    }

    @Test
    public void ceasarCipherEncryptsTextRightKey25() {

        String result = caesar.encryption(testText, 25);

        assertEquals("ZABCDEFGHIJKLM", result);
    }

    @Test
    public void ceasarCipherDecryptsTextRightKey1() {

        String result = caesar.decryption(testText, 1);

        assertEquals("ZABCDEFGHIJKLM", result);
    }

    /**
     * Works only with long text that implements the rule that 'e' is the most common letter.  
     */
    @Test
    public void findsOutKeyWithFreqAnalysis() {

        String encrypt = caesar.encryption("AabBbCdddEEEEeeeeee", 3);
        int result = freq.countFrequencies(encrypt);

        assertEquals(3, result);
    }
}
