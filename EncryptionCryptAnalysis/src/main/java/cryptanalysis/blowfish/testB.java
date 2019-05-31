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
public class testB {

    public testB() throws Exception {

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
        String Key = "Blowfish";
        byte[] KeyData = Key.getBytes();
        SecretKeySpec KS = new SecretKeySpec(KeyData, "Blowfish");
        Cipher cipher = Cipher.getInstance("Blowfish");
        cipher.init(Cipher.ENCRYPT_MODE, KS);

        //Cipher cipher = Cipher.getInstance("blowfish");
        //    cipher.init(cipher.ENCRYPT_MODE, secretKey);
        String input = text;
        byte[] encrypt = cipher.doFinal(input.getBytes());
        //   String e = new String(encrypt);

        hex = this.changeToHex(encrypt);
       // cipher.init(Cipher.DECRYPT_MODE, KS);

        // decrypt message
       // byte[] decrypted = cipher.doFinal(encrypt);

     //   String e = new String(encrypt.toString());
      //  e = new BASE64Encoder().encode(encrypt);
        //String d = new String(decrypted);
        return hex;// + " de:  " + d;
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
