/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cryptanalysis.ui;

import cryptanalysis.blowfish.Blowfish;
import cryptanalysis.blowfish.testB;
import cryptanalysis.breaking.BreakingVigenereCipher;
import cryptanalysis.breaking.BreakingVigenereNormalArrayList;
import cryptanalysis.ciphers.VigenereCipher;
import java.io.File;
import java.util.Scanner;

/**
 * class for testing time of ciphers
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

    /**
     * Testing time in vigenere with own ArrayList implemention
     * @return 
     */
    public long VigenereTimeArray() {

        long start = System.currentTimeMillis();
        int x = arrayBreaking.analyzingText(rivi);
        arrayBreaking.guessingKey(rivi, x);
        long stop = System.currentTimeMillis();

        return stop - start;
    }

    /**
     * Testing time in vigenere with Java ArrayList
     * @return 
     */
    public long VigenereTimeOwnArray() {

        long start = System.currentTimeMillis();
        int x = breaking.analyzingText(rivi);
        breaking.guessingKey(rivi, x);
        long stop = System.currentTimeMillis();

        return stop - start;
    }

    // Testing blowfish cipher and encryption time
    public void testBf() {

        Blowfish bl = new Blowfish(rivi,"Blowfish");
        long alku = System.currentTimeMillis();
        String salattu = bl.encryption();
        long loppu = System.currentTimeMillis();
        long aika = loppu - alku;
        System.out.println("Salattu: " + salattu);
        int pituus1 = salattu.length();
        System.out.println("pituus: " + pituus1);
        System.out.println("aika oma: " + aika);

        System.out.println("takaisin: " + bl.decryption("7d867072c98910af4abc69c2eb9dffab")); //helloworld
    }

    public void testb() throws Exception {
        long alku = System.currentTimeMillis();
        String en = b.te(rivi);
        long loppu = System.currentTimeMillis();
        long aika = loppu - alku;
        System.out.println("java salattu: "+en);
        System.out.println("Aika java bf: "+aika);
        int pituus1 = en.length();
        System.out.println("pituus: " + pituus1);
    }

}
