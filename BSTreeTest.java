

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import org.junit.Test;

public class BSTreeTest {
    @Test
    public void constructorTest() {
        assertEquals(null, new BSTree<>().getRoot());
        assertEquals(0, new BSTree<>().getSize());
        assertEquals(-1, new BSTree<>().findHeight());
    }

    @Test
    public void methodTest() {
        final int length = 50;
        ArrayList<Integer> arr = createRandomArray(length, 200);
        int[] arr1 = { 15, 6, 18, 3, 7, 17, 20, 2, 4, 13, 9 };
        BSTree<Integer> b = new BSTree<>();
        assertEquals(0, b.getSize());
        assertEquals(null, b.getRoot());
        assertEquals(-1, b.findHeight());
        assertEquals("", b.printPreOrder());
        assertEquals("", b.printInOrder());
        assertEquals("", b.printPostOrder());
        for (Integer i : arr) {
            assertEquals(true, b.insert(i));
        }
        assertEquals(length, b.getSize());
        Random rd = new Random();
        int rand = rd.nextInt();
        assertEquals(true, b.contains(arr.get(rand >= 0 ? rand % length : -rand % length)));
        rand = rd.nextInt();
        assertEquals(true, b.contains(arr.get(rand >= 0 ? rand % length : -rand % length)));
        rand = rd.nextInt();
        assertEquals(true, b.contains(arr.get(rand >= 0 ? rand % length : -rand % length)));
        assertEquals(true, b.remove(arr.get(rand >= 0 ? rand % length : -rand % length)));
        arr.remove(arr.get(rand >= 0 ? rand % length : -rand % length));
        assertEquals(length - 1, b.getSize());
        BSTree<Integer> b1 = new BSTree<>();
        for (int i : arr1) {
            b1.insert(i);
        }
        assertEquals((Integer) 15, b1.getRoot().getKey());
        assertEquals(4, b1.findHeight());
        assertEquals("2 3 4 6 7 9 13 15 17 18 20", b1.printInOrder());
        assertEquals("15 6 3 2 4 7 13 9 18 17 20", b1.printPreOrder());
        assertEquals("2 4 3 9 13 7 6 17 20 18 15", b1.printPostOrder());
        b1.remove(15);
        assertEquals((Integer) 17, b1.getRoot().getKey());
        assertEquals(4, b1.findHeight());
        Iterator<Integer> it=b.iterator();
        String expString=new String();
        while (it.hasNext()) {
            expString+=(it.next()+" ");
        }
        assertEquals(expString.substring(0,expstring.length()-1), b.printInOrder());
        assertEquals("2 3 4 6 7 9 13 17 18 20", b1.printInOrder());
        assertEquals("17 6 3 2 4 7 13 9 18 20", b1.printPreOrder());
        assertEquals("2 4 3 9 13 7 6 20 18 17", b1.printPostOrder());
    }

    @Test
    public void iteratorTest() {
        BSTree<Integer> b=new BSTree<>();
        int[] arr = { 15, 6, 18, 3, 7, 17, 20, 2, 4, 13, 9 };
        for (int i : arr) {
            b.insert(i);
        }
        assertEquals(true, b.iterator().hasNext());
        assertEquals((Integer)2, b.iterator().next());
        Iterator<Integer> it=b.iterator();
        assertEquals(true, it.hasNext());
        assertEquals((Integer)2, it.next());
        assertEquals((Integer)3, it.next());
    }

    public ArrayList<Integer> createRandomArray(int length, int mod) {
        Random rd = new Random();
        ArrayList<Integer> al = new ArrayList<>();
        int rand, i = 0;
        while (i < length) {
            rand = rd.nextInt();
            rand = rand > 0 ? rand % mod : -rand % mod;
            if (!al.contains(rand)) {
                al.add(rand);
                ++i;
            }
        }
        return al;
    }
}
