/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cryptanalysis.cipherTest;

import cryptanalysis.blowfish.Blowfish;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author milla
 */
public class BlowfishTest {

    Blowfish bf = new Blowfish("testmessage", "test");

    @Test
    public void lengthOfEncryptionIsRight() {

        String encrypt = bf.encryption();
        int l = encrypt.length();

        assertEquals(24, l);
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

    @Test
    public void splitsStringsRight() {
        List<String> testlist = new ArrayList();
        String test = "teststringsplit1";
        testlist = bf.splitToBytes(test, 8);
        String first = testlist.get(0);

        assertEquals(first, "teststri");
    }

    @Test
    public void toLongChangesLong() {
        String test = "test";

        long x = bf.tohexLong(test);

        assertEquals(x, 1952805748);
    }

    @Test
    public void changesTo8BytesTexttestAndFirstByteMatches() {
        // 1952805748 is test which is first 4 bytes so first is t and byte 116
        byte[] b = new byte[8];
        b = bf.longtobyte(1952805748, 0);
        int x = b[0]; 

        assertEquals(x, 116);
    }
}
