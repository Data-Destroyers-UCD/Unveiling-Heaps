package datastructures;

import java.util.ArrayList;
import java.util.Comparator;

public class HeapPriorityQueue<T> extends AbstractPriorityQueue<T> {
    protected ArrayList<T> heap = new ArrayList<>();

    public HeapPriorityQueue() { super(); }

    public HeapPriorityQueue(Comparator<T> comp) { super(comp); }

    // protected utilities
    protected int parent(int j) { return (j-1) / 2; }
    protected int left(int j) { return 2*j + 1; }
    protected int right(int j) { return 2*j + 2; }
    protected boolean hasLeft(int j) { return left(j) < heap.size(); }
    protected boolean hasRight(int j) { return right(j) < heap.size(); }

    
    /** Exchanges the entries at indices i and j of the array list. */
    protected void swap(int i, int j) {
        T temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    /** Moves the entry at index j higher, if necessary, to restore the heap property. */
    protected void upheap(int j) {
        while (j > 0) {
            int p = parent(j);
            if (compare(heap.get(j), heap.get(p)) >= 0) break;
            swap(j, p);
            j = p;
        }
    }

    /** Moves the entry at index j lower, if necessary, to restore the heap property. */
    protected void downheap(int j) {
        while (hasLeft(j)) {
            int leftIndex = left(j);
            int smallChildIndex = leftIndex;
            if (hasRight(j)) {
                int rightIndex = right(j);
                if (compare(heap.get(leftIndex), heap.get(rightIndex)) > 0)
                    smallChildIndex = rightIndex;
            }
            if (compare(heap.get(smallChildIndex), heap.get(j)) >= 0)
                break;
            swap(j, smallChildIndex);
            j = smallChildIndex;
        }
    }
    
    // Performs a bottom-up construction of the heap in linear time.
    protected void heapify() {
        int startIndex = parent(size() - 1); // start at PARENT of last entry
        for (int j = startIndex; j >= 0; j--) // loop until processing the root
            downheap(j);
    }

    @Override
    public int size() { return heap.size(); }

    @Override
    public T offer(T value) {
        checkKey(value);
        heap.add(value);
        upheap(heap.size() - 1);
        return value;
    }

    @Override
    public T peek() {
        if (heap.isEmpty()) return null;
        return heap.get(0);
    }

    @Override
    public T poll() {
        if (heap.isEmpty()) return null;
        T answer = heap.get(0);
        swap(0, heap.size() - 1);
        heap.remove(heap.size() - 1);
        downheap(0);
        return answer;
    }
    
    // Used for debugging purposes only
    private void sanityCheck() {
        for (int j = 0; j < heap.size(); j++) {
            int left = left(j);
            int right = right(j);
            if (left < heap.size() && compare(heap.get(left), heap.get(j)) < 0)
                System.out.println("Invalid left child relationship");
            if (right < heap.size() && compare(heap.get(right), heap.get(j)) < 0)
                System.out.println("Invalid right child relationship");
        }
    }
}
