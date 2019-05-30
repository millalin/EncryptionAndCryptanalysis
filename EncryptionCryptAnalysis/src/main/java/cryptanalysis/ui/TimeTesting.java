/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cryptanalysis.ui;

import cryptanalysis.blowfish.Blowfish;
import cryptanalysis.blowfish.testB;
import cryptanalysis.braking.BreakingVigenereCipher;
import cryptanalysis.braking.BreakingVigenereNormalArrayList;
import cryptanalysis.ciphers.VigenereCipher;
import java.io.File;
import java.util.Scanner;

/**
 *
 * @author milla
 */
public class TimeTesting {

    BreakingVigenereCipher breaking = new BreakingVigenereCipher();
    BreakingVigenereNormalArrayList arrayBreaking = new BreakingVigenereNormalArrayList();
    VigenereCipher vigenere = new VigenereCipher();
    testB b = new testB();
    String rivi = "";

    public TimeTesting() throws Exception {
        Scanner tiedosto = new Scanner(new File("test2.txt"));

        while (tiedosto.hasNextLine()) {
            rivi += tiedosto.nextLine();
            // System.out.println(rivi);
        }

        tiedosto.close();

        vigenere.encryption(rivi, "blowfkey");
    }

    public long VigenereTimeArray() {

        long start = System.currentTimeMillis();
        int x = arrayBreaking.analyzingText(rivi);
        arrayBreaking.guessingKey(rivi, x);
        long stop = System.currentTimeMillis();

        return stop - start;
    }

    public long VigenereTimeOwnArray() {

        long start = System.currentTimeMillis();
        int x = breaking.analyzingText(rivi);
        breaking.guessingKey(rivi, x);
        long stop = System.currentTimeMillis();

        return stop - start;
    }

    public void testBf() {

        Blowfish bl = new Blowfish("blowfish");
        long alku = System.currentTimeMillis();
        String salattu = bl.encryption("isitsame");
        long loppu = System.currentTimeMillis();
        long aika = loppu - alku;
        System.out.println("Salattu: " + salattu);
        int pituus1 = salattu.length();
        System.out.println("pituus: " + pituus1);
        System.out.println("aika: " + aika);

        System.out.println("takaisin: " + bl.decryption("80e2e9231e5a07e8")); //Thisissecretmessage
    }


}
