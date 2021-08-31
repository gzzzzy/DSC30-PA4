
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 * Set implementation using BST
 */
public class BSTSet<T extends Comparable<? super T>> {
    private BSTree<T> set;

    public BSTSet() {
        set = new BSTree<>();
    }

    public int size() {
        return set.getSize();
    }

    public boolean isEmpty() {
        return set.getSize() == 0;
    }

    public boolean contains(T element) {
        return set.contains(element);
    }

    public boolean insert(T element) {
        return set.insert(element);
    }

    public boolean remove(T element) {
        return set.remove(element);
    }

    public BSTSet<T> union(BSTSet<T> other) {
        ArrayList<T> arr = new ArrayList<>();
        BSTSet<T> re = new BSTSet<>();
        Iterator<T> it = set.iterator();
        T next;
        while (it.hasNext()) {
            arr.add(it.next());
        }
        it = other.set.iterator();
        while (it.hasNext()) {
            next = it.next();
            if (!arr.contains(next)) {
                arr.add(next);
            }
        }
        arr.sort(Comparator.naturalOrder());
        int len=arr.size(),mid=len/2;
        if((len&1)==1){
            re.insert(arr.get(mid));
        }
        while((--mid)>0){
            re.insert(arr.get(mid));
            re.insert(arr.get(len-mid-1));
        }
        return re;
    }

    public BSTSet<T> intersection(BSTSet<T> other) {
        ArrayList<T> arr1 = new ArrayList<>(), arr2 = new ArrayList<>(), arr = new ArrayList<>();
        BSTSet<T> re = new BSTSet<>(), uniSet = union(other);
        Iterator<T> it = set.iterator();
        while (it.hasNext()) {
            arr1.add(it.next());
        }
        it = other.set.iterator();
        while (it.hasNext()) {
            arr2.add(it.next());
        }
        it = uniSet.set.iterator();
        while (it.hasNext()) {
            arr.add(it.next());
        }
        arr1.addAll(arr2);
        for (T t : arr) {
            arr1.remove(t);
        }
        arr1.sort(Comparator.naturalOrder());
        int len=arr1.size(),mid=len/2;
        if((len&1)==1){
            re.insert(arr1.get(mid));
        }
        while((--mid)>0){
            re.insert(arr1.get(mid));
            re.insert(arr1.get(len-mid-1));
        }
        return re;
    }

    public BSTSet<T> complement(BSTSet<T> other) {
        ArrayList<T> arr = new ArrayList<>();
        BSTSet<T> re = new BSTSet<>();
        Iterator<T> it = set.iterator();
        T next;
        while (it.hasNext()) {
            arr.add(it.next());
        }
        it = other.set.iterator();
        while (it.hasNext()) {
            next = it.next();
            if (arr.contains(next)) {
                arr.remove(next);
            }
        }
        arr.sort(Comparator.naturalOrder());
        int len=arr.size(),mid=len/2;
        if((len&1)==1){
            re.insert(arr.get(mid));
        }
        while((--mid)>0){
            re.insert(arr.get(mid));
            re.insert(arr.get(len-mid-1));
        }
        return re;
    }

    public boolean equal(BSTSet<T> other) {
        return set.equal(other.set);
    }

}
