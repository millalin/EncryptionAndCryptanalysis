/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cryptanalysis.ui;

import cryptanalysis.blowfish.Blowfish;
import cryptanalysis.blowfish.TestJavaOwnBlowfish;
import cryptanalysis.breaking.BreakingVigenereCipher;
import cryptanalysis.breaking.BreakingVigenereNormalArrayList;
import cryptanalysis.ciphers.VigenereCipher;
import java.io.File;
import java.util.Scanner;

/**
 * class for testing time of ciphers
 *
 * @author milla
 */
public class TimeTesting {

    BreakingVigenereCipher breaking = new BreakingVigenereCipher();
    BreakingVigenereNormalArrayList arrayBreaking = new BreakingVigenereNormalArrayList();
    VigenereCipher vigenere = new VigenereCipher();
    TestJavaOwnBlowfish b = new TestJavaOwnBlowfish();
    String rivi = "";

    public TimeTesting() throws Exception {
        Scanner tiedosto = new Scanner(new File("./files/bftext.txt"));

        while (tiedosto.hasNextLine()) {
            rivi += tiedosto.nextLine();
            // System.out.println(rivi);
        }

        tiedosto.close();
rivi= "hello world can you see any other differences i would like to see howthisworksandhowwecanhelloworld";
        String sala =vigenere.encryption(rivi, "key");
        
        
        
    }

    /**
     * Testing time in vigenere with own ArrayList implemention
     *
     * @return
     */
    public long vigenereTimeArray() {

        long start = System.currentTimeMillis();
        int x = arrayBreaking.analyzingText(rivi);
        arrayBreaking.guessingKey(rivi, x);
        long stop = System.currentTimeMillis();

        return stop - start;
    }

    /**
     * Testing time in vigenere with Java ArrayList
     *
     * @return
     */
    public long vigenereTimeOwnArray() {

        long start = System.currentTimeMillis();
        int x = breaking.analyzingText(rivi);
        breaking.guessingKey(rivi, x);
        long stop = System.currentTimeMillis();

        return stop - start;
    }

    // Testing blowfish cipher and encryption time
    public void testBf() {

        Blowfish bl = new Blowfish(rivi, "sosecretkeyhorse");
        long alku = System.currentTimeMillis();
        String salattu = bl.encryption();
        long loppu = System.currentTimeMillis();
        long aika = loppu - alku;
       //  System.out.println("Salattu oma: " + salattu);
        int pituus1 = salattu.length();
        System.out.println("pituus oma : " + pituus1);
        System.out.println("aika oma: " + aika);

        long alku2 = System.currentTimeMillis();
        String avattu = bl.decryption(salattu);
        long loppu2 = System.currentTimeMillis();
        long aika2 = loppu2 - alku2;
        System.out.println("Aika decryption oma: " + aika2);

//        System.out.println("takaisin: " + bl.decryption(salattu)); //helloworld
    }

    public void testb() throws Exception {
        long alku = System.currentTimeMillis();
        String en = b.te(rivi);
        long loppu = System.currentTimeMillis();
        long aika = loppu - alku;
         //  System.out.println("java salattu: "+en);
        System.out.println("Aika java bf: " + aika);
        int pituus1 = en.length();
        System.out.println("pituus: " + pituus1);
    }

}
