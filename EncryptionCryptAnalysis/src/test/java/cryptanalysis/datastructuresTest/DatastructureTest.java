/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cryptanalysis.datastructuresTest;

import cryptanalysis.datastructures.MyArrayList;
import cryptanalysis.datastructures.MyHashMap;
import java.util.ArrayList;
import java.util.HashMap;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author milla
 */
public class DatastructureTest {

    MyArrayList<String> list = new MyArrayList<>();
    MyHashMap<String, String> map = new MyHashMap<>();
    ArrayList<String> javaList = new ArrayList();
    ArrayList<Integer> javaList2 = new ArrayList();
    MyArrayList<Integer> list2 = new MyArrayList();

    @Before
    public void setUp() {
        list.add("hello");
        list.add("world");
        javaList.add("hello");
        javaList.add("world");

        javaList2.add(1);
        javaList2.add(2);
        list2.add(1);
        list2.add(2);

        map.put("hello", "world");
    }

    @Test
    public void listSizeSameAsJavaArrayList() {

        int listSize = javaList2.size();
        int ownListSize = list2.size();

        assertEquals(listSize, ownListSize);
    }

    @Test
    public void findsRight() {
        int first = javaList2.get(0);
        int ownFirst = list2.get(0);

        assertEquals(first, ownFirst);
    }

    @Test
    public void removesRight() {
        list2.remove(1);
        int nowFirst = list2.get(0);

        assertEquals(nowFirst, 2);
    }

    @Test
    public void listsAreSameWhenAddedSameWords() {

        String one = list.get(0) + list.get(1);
        String two = javaList.get(0) + javaList.get(1);

        assertEquals(one, two);
    }

    @Test
    public void listContainsMethodFindsWordRight() {

        boolean contains = false;

        if (list.contains("hello")) {
            contains = true;
        }

        assertEquals(contains, true);
    }

    @Test
    public void listRemovesRight() {

        list.remove("hello");
        boolean contains = false;

        if (list.contains("hello")) {
            contains = true;
        }
        assertEquals(contains, false);
    }

    @Test
    public void listsReturnsRightSize() {

        int size = list.size();

        assertEquals(size, 2);
    }

    @Test
    public void listReturnsRightIndexThatIsSearched() {

        int one = list.index("world");

        assertEquals(one, 1);
    }

    @Test
    public void mapFindsValuesRight() {

        String value = map.get("hello");

        assertEquals(value, "world");
    }

    @Test
    public void mapAddsRight() {

        map.put("one", "two");
        String value = map.get("one");
        assertEquals(value, "two");
    }

    @Test
    public void mapRemovesRight() {

        map.remove("hello");
        String value = map.get("hello");

        assertEquals(value, null);
    }

    @Test
    public void mapFindsKey() {

        boolean contains = false;

        if (map.containsKey("hello")) {
            contains = true;
        }

        assertEquals(contains, true);
    }
}
