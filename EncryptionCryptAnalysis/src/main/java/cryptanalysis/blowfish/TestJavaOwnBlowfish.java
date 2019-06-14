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
 *
 * @author milla
 */
public class TestJavaOwnBlowfish {

    public TestJavaOwnBlowfish() throws Exception {

    }

    /**
     * java version of Blowfish encryption. This class is to test how it
     * compares with my own implementation.
     *
     * @param text text to encrypted
     * @return encrypted text
     * @throws Exception
     */
    public String te(String text) throws Exception {
        // KeyGenerator generator = KeyGenerator.getInstance("blowfish");
        //     SecretKey secretKey = generator.generateKey();
        String hex = "";
        String key = "sosecretkeyhorse";
        byte[] keyData = key.getBytes();
        SecretKeySpec ks = new SecretKeySpec(keyData, "Blowfish");
        Cipher cipher = Cipher.getInstance("Blowfish");
        cipher.init(Cipher.ENCRYPT_MODE, ks);

        //Cipher cipher = Cipher.getInstance("blowfish");
        //    cipher.init(cipher.ENCRYPT_MODE, secretKey);
        String input = text;
        long alku = System.currentTimeMillis();
        byte[] encrypt = cipher.doFinal(input.getBytes());
        //   String e = new String(encrypt);

    //    System.out.println("java väli "+encrypt);
        hex = this.changeToHex(encrypt);
        long loppu = System.currentTimeMillis();
        
        long aika = loppu - alku;
        System.out.println("java vain salausaika "+aika);
    //    cipher.init(Cipher.DECRYPT_MODE, ks);

        // decrypt message
     //   byte[] decrypted = cipher.doFinal(encrypt);

        //   String e = new String(encrypt.toString());
        //  e = new BASE64Encoder().encode(encrypt);
   //     String d = new String(decrypted);
     //   System.out.println("de java " + d);

        return hex; // + " de:  " + d;
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
