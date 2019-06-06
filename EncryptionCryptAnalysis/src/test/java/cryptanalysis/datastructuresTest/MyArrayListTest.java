/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cryptanalysis.datastructuresTest;

import cryptanalysis.datastructures.MyArrayList;
import java.util.ArrayList;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author milla
 */
public class MyArrayListTest {

    ArrayList <Integer>lista = new ArrayList();
    MyArrayList <Integer>omalista = new MyArrayList();

    @Before
    public void setUp() {

        lista.add(1);
        lista.add(2);
        omalista.add(1);
        omalista.add(2);
    }

    @Test
    public void listSizeSameAsJavaArrayList() {

        int listakoko = lista.size();
        int omalistakoko = omalista.size();

        assertEquals(listakoko, omalistakoko);
    }

    @Test
    public void findsRight() {
        int first = lista.get(0);
        int ownFirst = omalista.value(0);

        assertEquals(first, ownFirst);
    }
    
        @Test
    public void removesRight() {
        omalista.remove(1);
        int nowFirst = omalista.value(0);

        assertEquals(nowFirst, 2);
    }
}
