/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cryptanalysis.cipherTest;

import cryptanalysis.blowfish.Blowfish;
import cryptanalysis.datastructures.MyArrayList;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author milla
 */
public class BlowfishTest {

    Blowfish bf = new Blowfish("testmessage","blowfish");

    @Test
    public void lengthOfEncryptionIsRight() {

        String encrypt = bf.encryption();
        int l = encrypt.length();

        assertEquals(32, l);
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
    public void to() {
        String salattu = "b6ff8274954a2c24";
        String avattu = bf.decryption(salattu);

        assertEquals(avattu, "blowfish");
    }

}
