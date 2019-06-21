/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cryptanalysis.cipherTest;

import cryptanalysis.blowfish.Blowfish;
import cryptanalysis.datastructures.MyArrayList;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author milla
 */
public class BlowfishTest {

    Blowfish bf = new Blowfish("hellotestmessage", "blowfish");


    @Test
    public void lengthOfEncryptionIsRight() {

        String encrypt = bf.encryption();
        int l = encrypt.length();

        assertEquals(32, l);
    }

    @Test
    public void deryptsRightAfterEnccryption() {

        String encrypt = bf.encryption();
        String decryption = bf.decryption(encrypt);

        assertEquals("hellotestmessage", decryption);
    }

    @Test
    public void encryptionDifferentWithDifferentKey() {

        String encrypt = bf.encryption();
        Blowfish bf = new Blowfish("hellotestmessage", "blowfis");
        String encrypt2 = bf.encryption();

        boolean same = true;

        if (encrypt.equals(encrypt2)) {
            same = true;
        } else  {
            same = false;
        }

        assertEquals(same, false);

    }

    @Test
    public void changesTo8BytesTexttestAndFirstByteMatches() {
        // 1952805748 is test which is first 4 bytes so first is t and byte 116
        byte[] b = new byte[8];
        b = bf.toBytes(1952805748, 0);
        int x = b[0];

        assertEquals(x, 116);
    }

    @Test
    public void toByteFromHex() {
        String test = "41";

        byte[] b = bf.hexStringToBytes(test);
        byte onebyte = b[0];
        char a = (char) onebyte;

        assertEquals(a, 'A');
    }

    @Test
    public void decryptsRight() {
        String encrypted = "b6ff8274954a2c24";
        String decrypted = bf.decryption(encrypted);

        assertEquals(decrypted, "blowfish");
    }

}
