package DSC30.PA4;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class BSTSetTest {
    @Test
    public void constructorTest() {
        assertEquals(true, new BSTSet<>().isEmpty());
        assertEquals(0, new BSTSet<>().size());
        assertEquals(false, new BSTSet<Integer>().remove(2));
    }

    @Test
    public void methodTest() {
        BSTSet<Integer> bs1 = new BSTSet<>(), bs2 = new BSTSet<>(), bs = new BSTSet<>();
        assertEquals(0, bs1.size());
        assertEquals(true, bs1.isEmpty());
        int[] arr1 = { 34, 32, 12, 4, 0, 23, 1 }; // set 1
        int[] arr2 = { 1, 2, 3, 7, 10, 12, 23 }; // set 2
        int[] arr = { 0, 1, 2, 3, 4, 7, 10, 12, 23, 32, 34 }; // universal set
        for (int i : arr1) {
            assertEquals(true, bs1.insert(i));
        }
        assertEquals(arr1.length, bs1.size());
        assertEquals(true, bs1.contains(arr1[1]));
        assertEquals(false, bs1.insert(arr1[0]));
        assertEquals(false, bs1.isEmpty());
        for (int i : arr2) {
            assertEquals(true, bs2.insert(i));
        }
        assertEquals(arr2.length, bs2.size());
        assertEquals(true, bs2.contains(arr2[1]));
        assertEquals(false, bs2.insert(arr2[0]));
        assertEquals(false, bs2.isEmpty());
        assertEquals(true, bs2.union(bs1).equal(bs1.union(bs2)));
        assertEquals(true, bs2.intersection(bs1).equal(bs1.intersection(bs2)));
        for (int i : arr) {
            assertEquals(true, bs.insert(i));
        }
        assertEquals(arr.length, bs.size());
        assertEquals(true, bs.contains(arr[4]));
        assertEquals(false, bs.isEmpty());
        // de morgen law
        assertEquals(true, bs1.union(bs2).complement(bs).equal(bs1.complement(bs).intersection(bs2.complement(bs))));
        assertEquals(true, bs1.intersection(bs2).complement(bs).equal(bs1.complement(bs).intersection(bs2.complement(bs))));
        // distributive law
        assertEquals(true, bs.intersection(bs1.union(bs2)).equal(bs.intersection(bs1).union(bs.intersection(bs2))));
        assertEquals(true, bs.union(bs1.intersection(bs2)).equal(bs.union(bs1).intersection(bs.union(bs2))));
    }

    @Test(expected = NullPointerException.class)
    public void name() {
        BSTSet<String> bs = new BSTSet<>();
        bs.insert(null);
        bs.remove(null);
    }
}
