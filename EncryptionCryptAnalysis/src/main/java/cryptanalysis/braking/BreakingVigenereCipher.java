/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cryptanalysis.braking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/**
 *
 * @author milla
 */
public class BreakingVigenereCipher {

    public BreakingVigenereCipher() {
        double[] prob = {0.08167, 0.01492, 0.02782, 0.04253,
            0.12702, 0.02228, 0.02015, 0.06094, 0.06966, 0.00153,
            0.00772, 0.04025, 0.02406, 0.06749, 0.07507, 0.01929,
            0.00095, 0.05987, 0.06327, 0.09056, 0.02758, 0.00978,
            0.02360, 0.00150, 0.01974, 0.00074};
    }

    public void removeSpacing(String text) {

    }

    public int analyzingText(String text) {
        HashMap<String, ArrayList<Integer>> blocks = new HashMap();
        ArrayList<String> list = new ArrayList();
        for (int i = 0; i < text.length() - 2; i++) {
            char a = (char) text.charAt(i);
            char b = (char) text.charAt(i + 1);
            char c = (char) text.charAt(i + 2);
            String set = "" + a + b + c;

            if (blocks.containsKey(set)) {
                // int count = blocks.get(set);

                blocks.get(set).add(i); //listalle indeksit missä eri 3 kirj yhdistelmät esiintyvät
            } else {
                blocks.put(set, new ArrayList());
                blocks.get(set).add(i);
                list.add(set);
            }

        }

        HashMap<Integer, Integer> erot = new HashMap<Integer, Integer>();

        for (int i = 0; i < list.size(); i++) {
            String set = list.get(i);
            ArrayList<Integer> indexes = blocks.get(set);
            System.out.println("jono: " + indexes.toString());
            if (indexes.size() == 1) {
                //älä tee mtn
            } else {
                for (int j = 0; j < indexes.size() - 1; j++) { //yhden 3 kirj yhdistelmän välit
                    int difference = indexes.get(j + 1) - indexes.get(j); //yksi ero 
                    System.out.println(set + "  ero  " + difference);
                    ArrayList<Integer> factors = listFactors(difference);

                    for (Integer fact : factors) {
                        if (erot.containsKey(fact)) {
                            Integer temp = erot.get(fact);
                            temp++;
                            erot.put(fact, temp); //tekijä ja monta kpl on tekijää
                        } else {
                            erot.put(fact, 1);
                        }
                    }

                }
            }

        }
        System.out.println("erot  " + erot.toString());
        return countedKeyLength(erot);
    }

    public static int countedKeyLength(HashMap<Integer, Integer> erot) {
        Set<Integer> keys = erot.keySet();
        
        int keyL = 0;
        int suurin = 0;

        for (int key : keys) {
            if (key == 1 || key == 2) {
                //ohi
            } else {
                int luku = erot.get(key);
                if (luku > suurin) {
                    suurin = luku;
                    keyL = key;
                }
            }

        }

        return keyL;

    }

    public ArrayList<Integer> listFactors(int x) {
        ArrayList<Integer> factors = new ArrayList<Integer>();

        for (int i = 1; i <= (int) Math.sqrt(x); i++) {
            if (x % i == 0) {
                factors.add(i);
            }
        }
        int size = factors.size();
        for (int i = size - 1; i >= 0; i--) {
            factors.add(x / factors.get(i));
        }

        return factors;
    }
}
