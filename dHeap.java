
/*
 * Name: Zhiyu Gao
 * PID:  A17245309
 */

import java.util.*;

/**
 * 
 * D-ary Heaps using a heap-structured array to represent a heap.
 * 
 * @author Zhiyu Gao
 * @since 8/30/2021
 *
 * @param <T> Generic type
 */
public class dHeap<T extends Comparable<? super T>> implements dHeapInterface<T> {

    private T[] heap; // heap array
    private int d; // branching factor
    private int nelems; // number of elements
    private boolean isMaxHeap; // boolean to indicate whether heap is max or min
    private final int DEFAULT_SIZE = 6;

    /**
     * Initializes a binary max heap with capacity = 6
     */
    @SuppressWarnings("unchecked")
    public dHeap() {
        this(2, 6, true);
    }

    /**
     * Initializes a binary max heap with a given initial capacity.
     *
     * @param heapSize The initial capacity of the heap.
     */
    @SuppressWarnings("unchecked")
    public dHeap(int heapSize) {
        this(2, heapSize, true);
    }

    /**
     * Initializes a d-ary heap (with a given value for d), with a given initial
     * capacity.
     *
     * @param d         The number of child nodes each node in the heap should have.
     * @param heapSize  The initial capacity of the heap.
     * @param isMaxHeap indicates whether the heap should be max or min
     * @throws IllegalArgumentException if d is less than one.
     */
    @SuppressWarnings("unchecked")
    public dHeap(int d, int heapSize, boolean isMaxHeap) throws IllegalArgumentException {
        if (d < 1)
            throw new IllegalArgumentException();
        heap = (T[]) new Comparable[DEFAULT_SIZE];
        this.d = d;
        this.isMaxHeap = isMaxHeap;
    }

    @Override
    public int size() {
        return nelems;
    }

    @Override
    public void add(T data) throws NullPointerException {
        if (data == null)
            throw new NullPointerException();
        if (nelems == heap.length)
            resize();
        heap[nelems] = data;
        bubbleUp(nelems);
        ++nelems;
    }

    @Override
    public T remove() throws NoSuchElementException {
        if (nelems == 0)
            throw new NoSuchElementException();
        T re = heap[0];
        heap[0] = heap[--nelems];
        trickleDown(0);
        return re;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void clear() {
        nelems = 0;
    }

    public T element() throws NoSuchElementException {
        if (nelems == 0)
            throw new NoSuchElementException();
        return heap[0];
    }

    private void trickleDown(int index) {
        if (index * d + 1 < nelems) {
            if (isMaxHeap) {
                int maxIndex = index, i, end = (index + 1) * d < nelems ? (index + 1) * d : nelems - 1;
                for (i = index * d + 1; i <= end; ++i) {
                    if (heap[i].compareTo(heap[maxIndex]) > 0) {
                        maxIndex = i;
                    }
                }
                if (maxIndex != index) {
                    T temp = heap[index];
                    heap[index] = heap[maxIndex];
                    heap[maxIndex] = temp;
                    trickleDown(maxIndex);
                }
            } else {
                int minIndex = index, i, end = (index + 1) * d < nelems ? (index + 1) * d : nelems - 1;
                for (i = index * d + 1; i <= end; ++i) {
                    if (heap[i].compareTo(heap[minIndex]) < 0) {
                        minIndex = i;
                    }
                }
                if (minIndex != index) {
                    T temp = heap[index];
                    heap[index] = heap[minIndex];
                    heap[minIndex] = temp;
                    trickleDown(minIndex);
                }
            }
        }
    }

    private void bubbleUp(int index) {
        if (index > 0) {
            T temp;
            int parentIndex = parent(index);
            if (isMaxHeap) {
                if (heap[index].compareTo(heap[parentIndex]) > 0) {
                    temp = heap[index];
                    heap[index] = heap[parentIndex];
                    heap[parentIndex] = temp;
                    bubbleUp(parentIndex);
                }
            } else {
                if (heap[index].compareTo(heap[parentIndex]) < 0) {
                    temp = heap[index];
                    heap[index] = heap[parentIndex];
                    heap[parentIndex] = temp;
                    bubbleUp(parentIndex);
                }
            }
        }

    }

    @SuppressWarnings("unchecked")
    private void resize() {
        T[] temp = (T[]) new Comparable[2 * heap.length];
        for (int i = 0; i < heap.length; ++i) {
            temp[i] = heap[i];
        }
        heap = temp;
    }

    private int parent(int index) {
        return (index - 1) / d;
    }

}
