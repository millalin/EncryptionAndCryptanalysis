/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cryptanalysis.cipherTest;

import cryptanalysis.braking.BreakingVigenereCipher;
import cryptanalysis.ciphers.VigenereCipher;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author milla
 */
public class VigenereTest {

    VigenereCipher vigenere = new VigenereCipher();
    BreakingVigenereCipher breaking = new BreakingVigenereCipher();
    String testText = "this is secret test text";
    String key = "key";

    public VigenereTest() {

    }

    @Test
    public void vigenereCipherEncryptsTextRightLowercase() {

        String result = vigenere.encryption(testText, key);
        assertEquals("dlgc mq ciabir diqd xchx", result);
    }

    @Test
    public void vigenereCipherEncryptsTextRightUppercase() {

        testText = testText.toUpperCase();
        String result = vigenere.encryption(testText, key);
        assertEquals("DLGC MQ CIABIR DIQD XCHX", result);
    }

    @Test
    public void vigenereCipherDecryptsTextRightUppercase() {
        testText = "DLGC MQ CIABIR DIQD XCHX";
        String result = vigenere.decryption(testText, key);
        assertEquals("THIS IS SECRET TEST TEXT", result);
    }
    
    @Test
    public void vigenereCipherDecryptsTextRightLowercase() {
        testText = "dlgc mq ciabir diqd xchx";
        String result = vigenere.decryption(testText, key);
        assertEquals("this is secret test text", result);
    }

    @Test
    public void vigenereCipherEncryptionChancesWhenKeyDifferent() {
        boolean same = true;
        String encryptedTextWithshortKey = vigenere.encryption(testText, key);
        key = "keytwo";
        String encryptedTextWithLongerKey = vigenere.encryption(testText, key);
        if (encryptedTextWithshortKey != encryptedTextWithLongerKey) {
            same = false;
        }
        Assert.assertFalse(same);
    }

    @Test
    public void vigenereCipherCountsKeyLenghtKeyof3() {

        testText = "THEREARETWOWAYSOFCONSTRUCTINGASOFTWAREDESIGNONEWAY\n"
                + "SYSTEMSYSTEMSYSTEMSYSTEMSYSTEMSYSTEMSYSTEMSYSTEMSY\n"
                + "LFWKIMJCLPSISWKHJOGLKMVGURAGKMKMXMAMJCVXWUYLGGIISW\n"
                + "ISTOMAKEITSOSIMPLETHATTHEREAREOBVIOUSLYNODEFICIENC\n"
                + "STEMSYSTEMSYSTEMSYSTEMSYSTEMSYSTEMSYSTEMSYSTEMSYST\n"
                + "ALXAEYCXMFKMKBQBDCLAEFLFWKIMJCGUZUGSKECZGBWYMOACFV\n"
                + "IESANDTHEOTHERWAYISTOMAKEITSOCOMPLICATEDTHATTHEREA\n"
                + "EMSYSTEMSYSTEMSYSTEMSYSTEMSYSTEMSYSTEMSYSTEMSYSTEM\n"
                + "MQKYFWXTWMLAIDOYQBWFGKSDIULQGVSYHJAVEFWBLAEFLFWKIM\n"
                + "RENOOBVIOUSDEFICIENCIESTHEFIRSTMETHODISFARMOREDIFF\n"
                + "SYSTEMSYSTEMSYSTEMSYSTEMSYSTEMSYSTEMSYSTEMSYSTEMSY\n"
                + "JCFHSNNGGNWPWDAVMQFAAXWFZCXBVELKWMLAVGKYEDEMJXHUXD";
        String encrypted = vigenere.encryption(testText, key);
        int x = breaking.analyzingText(encrypted);
        assertEquals(3, x);
    }

    @Test
    public void vigenereCipherCountsKeyLenghtKeyOf5() {

        testText = "THEREARETWOWAYSOFCONSTRUCTINGASOFTWAREDESIGNONEWAY\n"
                + "SYSTEMSYSTEMSYSTEMSYSTEMSYSTEMSYSTEMSYSTEMSYSTEMSY\n"
                + "LFWKIMJCLPSISWKHJOGLKMVGURAGKMKMXMAMJCVXWUYLGGIISW\n"
                + "ISTOMAKEITSOSIMPLETHATTHEREAREOBVIOUSLYNODEFICIENC\n"
                + "STEMSYSTEMSYSTEMSYSTEMSYSTEMSYSTEMSYSTEMSYSTEMSYST\n"
                + "ALXAEYCXMFKMKBQBDCLAEFLFWKIMJCGUZUGSKECZGBWYMOACFV\n"
                + "IESANDTHEOTHERWAYISTOMAKEITSOCOMPLICATEDTHATTHEREA\n"
                + "EMSYSTEMSYSTEMSYSTEMSYSTEMSYSTEMSYSTEMSYSTEMSYSTEM\n"
                + "MQKYFWXTWMLAIDOYQBWFGKSDIULQGVSYHJAVEFWBLAEFLFWKIM\n"
                + "RENOOBVIOUSDEFICIENCIESTHEFIRSTMETHODISFARMOREDIFF\n"
                + "SYSTEMSYSTEMSYSTEMSYSTEMSYSTEMSYSTEMSYSTEMSYSTEMSY\n"
                + "JCFHSNNGGNWPWDAVMQFAAXWFZCXBVELKWMLAVGKYEDEMJXHUXD";
        key = "milla";
        String encrypted = vigenere.encryption(testText, key);
        int x = breaking.analyzingText(encrypted);
        assertEquals(5, x);
    }
}
