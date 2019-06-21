/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cryptanalysis.blowfish;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import sun.misc.BASE64Encoder;

/**
 * java version of Blowfish encryption. This class is to test how it compares
 * with my own implementation. There are own data structures of Java in this
 * class but these are not used in actual program, only for testing and
 * comparing speed and encryption functions.
 *
 * @author milla
 */
public class TestJavaOwnBlowfish {

    public TestJavaOwnBlowfish() throws Exception {

    }

    /**
     * java version of Blowfish encryption.
     *
     * @param text text to encrypted
     * @return encrypted text
     * @throws Exception throws exception
     */
    public String te(String text) throws Exception {
        String hex = "";
        String key = "sosecretkeyhello";
        byte[] keyData = key.getBytes();
        SecretKeySpec ks = new SecretKeySpec(keyData, "Blowfish");
        Cipher cipher = Cipher.getInstance("blowfish");
        cipher.init(Cipher.ENCRYPT_MODE, ks);

        String input = text;
        long alku = System.currentTimeMillis();
        byte[] encrypt = cipher.doFinal(input.getBytes());

        hex = this.changeToHex(encrypt);
        long loppu = System.currentTimeMillis();

        long aika = loppu - alku;
        System.out.println("java time only encryption " + aika);

        return hex;
    }

    public String changeToHex(byte[] bytes) {
        StringBuffer hexb = new StringBuffer();
        for (int i = 0; i < bytes.length; i++) {
            hexb.append(byteToHex(bytes[i]));
        }
        return hexb.toString();
    }

    public String byteToHex(byte num) {
        char[] hexDigits = new char[2];
        hexDigits[0] = Character.forDigit((num >> 4) & 0xF, 16);
        hexDigits[1] = Character.forDigit((num & 0xF), 16);
        return new String(hexDigits);
    }

}
