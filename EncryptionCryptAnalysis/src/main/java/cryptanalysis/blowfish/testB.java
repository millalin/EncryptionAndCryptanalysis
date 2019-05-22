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
// JAVAN VALMIS OMA SALAUS

    public String te(String text) throws Exception {
        // KeyGenerator generator = KeyGenerator.getInstance("blowfish");
        //     SecretKey secretKey = generator.generateKey();

        String Key = "blowfish";
        byte[] KeyData = Key.getBytes();
        SecretKeySpec KS = new SecretKeySpec(KeyData, "Blowfish");
        Cipher cipher = Cipher.getInstance("Blowfish");
        cipher.init(Cipher.ENCRYPT_MODE, KS);

        //Cipher cipher = Cipher.getInstance("blowfish");
        //    cipher.init(cipher.ENCRYPT_MODE, secretKey);
        String input = text;
        byte[] encrypt = cipher.doFinal(input.getBytes());
        //   String e = new String(encrypt);

        cipher.init(Cipher.DECRYPT_MODE, KS);

        // decrypt message
        byte[] decrypted = cipher.doFinal(encrypt);
        String e = new String(encrypt.toString());
        e = new BASE64Encoder().encode(encrypt);
        String d = new String(decrypted);
        return e + " de:  " + d;
    }

}
