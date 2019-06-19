/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cryptanalysis.ui;

import cryptanalysis.blowfish.Blowfish;
import cryptanalysis.blowfish.TestJavaOwnBlowfish;
import cryptanalysis.ciphers.VigenereCipher;
import java.io.File;
import java.util.Scanner;

/**
 * class just for testing time of my own Blowfish and Java Blowfish differences.
 * You can test these by deleting comments on CryptUi.java class and adding info
 * in this class and/or deleting comments.
 *
 * @author milla
 */
public class TimeTesting {

    VigenereCipher vigenere = new VigenereCipher();
    TestJavaOwnBlowfish b = new TestJavaOwnBlowfish();
    String rivi = "";

    public TimeTesting() throws Exception {
        /*    Scanner tiedosto = new Scanner(new File("./files/file2.txt"));

        while (tiedosto.hasNextLine()) {
            rivi += tiedosto.nextLine();
        }

        tiedosto.close(); */

    }

    // Testing blowfish cipher and encryption time
    public void testBf() {

        long alku = System.currentTimeMillis();
        Blowfish bl = new Blowfish(rivi, "sosecretkeyhello");
        long alku2 = System.currentTimeMillis();
        String salattu = bl.encryption();
        long loppu = System.currentTimeMillis();
        long loppu2 = System.currentTimeMillis();
        long aika = loppu - alku;
        int pituus1 = salattu.length();
        //   System.out.println("pituus oma : " + pituus1);
        System.out.println("time own: " + aika);

        long aika2 = loppu2 - alku2;
        System.out.println("Time only encryption own: " + aika2);

    }

    public void testb() throws Exception {
        long alku = System.currentTimeMillis();
        String en = b.te(rivi);
        long loppu = System.currentTimeMillis();
        long aika = loppu - alku;
        System.out.println("Time java bf: " + aika);
        int pituus1 = en.length();
        //    System.out.println("pituus: " + pituus1);
    }

}
