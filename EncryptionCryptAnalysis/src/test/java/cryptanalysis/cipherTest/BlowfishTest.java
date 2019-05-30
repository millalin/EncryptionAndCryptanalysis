/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cryptanalysis.cipherTest;

import cryptanalysis.blowfish.Blowfish;
import cryptanalysis.dataStructures.MyArrayList;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author milla
 */
public class BlowfishTest {

    Blowfish bf = new Blowfish("test");

    @Test
    public void lengthOfEncryptionIsRight() {

        String encrypt = bf.encryption("testmessage");
        int l = encrypt.length();

        assertEquals(32, l);
    }

 

 

    @Test
    public void splitsStringsRight() {
        MyArrayList<String> testlist = new MyArrayList();
        String test = "teststringsplit1";
        testlist = bf.splitToParts(test, 8);
        String first = testlist.get(0);

        assertEquals(first, "teststri");
    }

    @Test
    public void toLongChangesLong() {
        String test = "test";

        long x = bf.changingToLong(test);

        assertEquals(x, 1952805748);
    }

    @Test
    public void changesTo8BytesTexttestAndFirstByteMatches() {
        // 1952805748 is test which is first 4 bytes so first is t and byte 116
        byte[] b = new byte[8];
        b = bf.toBytes(1952805748, 0);
        int x = b[0]; 

        assertEquals(x, 116);
    }
}
