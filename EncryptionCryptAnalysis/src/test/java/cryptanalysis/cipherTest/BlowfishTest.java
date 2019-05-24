/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cryptanalysis.cipherTest;

import cryptanalysis.blowfish.Blowfish;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author milla
 */
public class BlowfishTest {

    Blowfish bf = new Blowfish("testthis", "test");

    @Test
    public void lengthOfEncryptionIsRight() {

        String encrypt = bf.encryption();
        int l = encrypt.length();

        assertEquals(12, l);
    }

    @Test
    public void bytesChangedToFourCharsWhenZero() {
        byte[] threeBytes = new byte[3];
        threeBytes[0] = 00;
        threeBytes[1] = 00;
        threeBytes[2] = 00;
        String abc = bf.bitsToChar(threeBytes);

        assertEquals(abc, "====");
    }
    
       @Test
    public void abcChangedToFourCharsYWJj() {
        byte[] threeBytes = new byte[3];
        threeBytes[0] = 'a';
        threeBytes[1] = 'b';
        threeBytes[2] = 'c';
        String abc = bf.bitsToChar(threeBytes);

        assertEquals(abc, "YWJj");
    }
}
