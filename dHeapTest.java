package DSC30.PA4;

import static org.junit.Assert.assertEquals;

import java.util.NoSuchElementException;
import java.util.Random;

import org.junit.Test;

public class dHeapTest {
    @Test
    public void constructorTest() {
        assertEquals(0, new dHeap<>().size());
        assertEquals(0, new dHeap<>(3, 6, false).size());
        assertEquals(0, new dHeap<>(4).size());
    }

    @Test(expected = NoSuchElementException.class)
    public void constructorExceptionTest() {
        new dHeap<>().remove();
        new dHeap<>(3, 6, true).remove();
        new dHeap<>(4).remove();
        new dHeap<>().clear();
    }

    @Test
    public void minHeapMethodTest() {
        // reversed array
        dHeap<Integer> dh = new dHeap<>(4, 6, false);
        BSTree<Integer> bt = new BSTree<>();
        final int len = 50;
        int i;
        int[] arr = { 1, 3, 5, 9, 4, 6, 2 };
        for (i = 0; i < len; ++i) {
            dh.add(50 - i);
            bt.insert(50 - i);
        }
        assertEquals(len, dh.size());
        String actual = "";
        i = 1;
        while (dh.size() > 0) {
            assertEquals(new Integer(i), dh.element());
            assertEquals(new Integer(i), dh.remove());
            actual += (i + " ");
            i++;
        }
        assertEquals(bt.printInOrder(), actual.substring(0, actual.length() - 1));
        assertEquals(0, dh.size());
        for (int j : arr) {
            dh.add(j);
        }
        assertEquals(arr.length, dh.size());
        dh.clear();
        assertEquals(0, dh.size());
        // assertEquals(expected, actual);
    }

    @Test(expected = NoSuchElementException.class)
    public void methodExceptionTest() {
        int[] arr = { 1, 3, 5, 9, 4, 6, 2 };
        dHeap<Integer> dh = new dHeap<>();
        for (int i : arr) {
            dh.add(i);
        }
        while (dh.size() > 0) {
            dh.remove();
        }
        dh.element();
        dh.remove();
    }

    @Test
    public void maxHeapMethodTest() {
        dHeap<Integer> dh = new dHeap<>(4);
        final int len = 50;
        int i;
        int[] arr = { 1, 3, 5, 9, 4, 6, 2 };
        for (i = 0; i < len; ++i) {
            dh.add(50 - i);
        }
        assertEquals(len, dh.size());
        while (dh.size() > 0) {
            assertEquals(new Integer(i), dh.element());
            assertEquals(new Integer(i--), dh.remove());
        }
        assertEquals(0, dh.size());
        for (int j : arr) {
            dh.add(j);
        }
        assertEquals(arr.length, dh.size());
        String actual = "";
        while (dh.size() > 0) {
            actual += (dh.remove().toString() + " ");
        }
        assertEquals("9 6 5 4 3 2 1", actual.substring(0, actual.length() - 1));
        dh.clear();
        assertEquals(0, dh.size());
    }

}
