package datastructures;

// Import the packages
import java.util.ArrayList;
import java.util.Comparator;

// Implementation of Heap Priority Queue
public class HeapPriorityQueue<T> extends AbstractPriorityQueue<T> {
	// Array list variable to store data
    protected ArrayList<T> heap = new ArrayList<>();

    // Constructor to create a simple heap
    public HeapPriorityQueue() { super(); }

    // Constructor to create a heap based on the comparator
    public HeapPriorityQueue(Comparator<T> comp) { super(comp); }

    // Get the parent of j index
    protected int parent(int j) { return (j-1) / 2; }
    // Get the left sibling of j index
    protected int left(int j) { return 2*j + 1; }
    // Get the right sibling of j index
    protected int right(int j) { return 2*j + 2; }
    // Check if j index has a left sibling
    protected boolean hasLeft(int j) { return left(j) < heap.size(); }
    // Check if j index has a right sibling
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
        	// Get the parent of j
            int p = parent(j);
            // If the parent's value is less equal in priority than j's value break
            if (compare(heap.get(j), heap.get(p)) >= 0) break;
            // Otherwise swap parent and j values
            swap(j, p);
            // Set j as the parent and move up
            j = p;
        }
    }

    /** Moves the entry at index j lower, if necessary, to restore the heap property. */
    protected void downheap(int j) {
    	// Check if j index has a left sibling
        while (hasLeft(j)) {
        	// get the left sibling index
            int leftIndex = left(j);
            
            int smallChildIndex = leftIndex;
            // Check if j has a right sibling
            if (hasRight(j)) {
            	// Get the right sibling index
                int rightIndex = right(j);
                // If the left child's priority is greater than right child priority
                if (compare(heap.get(leftIndex), heap.get(rightIndex)) > 0)
                	// Set small child index as right child index
                    smallChildIndex = rightIndex;
            }
            // If small child index priority is greater equal to j's priority break the loop
            if (compare(heap.get(smallChildIndex), heap.get(j)) >= 0)
                break;
            // Otherwise swap the j index and the small child index values
            swap(j, smallChildIndex);
            // Set j as the small child index
            j = smallChildIndex;
        }
    }
    
    // Performs a bottom-up construction of the heap in linear time.
    protected void heapify() {
        int startIndex = parent(size() - 1); // start at PARENT of last entry
        for (int j = startIndex; j >= 0; j--) // loop until processing the root
            downheap(j);
    }

    // Get the size of the Heap
    @Override
    public int size() { return heap.size(); }

    // Put a value in the heap and sort the heap
    @Override
    public T offer(T value) {
        checkKey(value);
        heap.add(value);
        upheap(heap.size() - 1);
        return value;
    }

    // Get the top priority value of the heap
    @Override
    public T peek() {
        if (heap.isEmpty()) return null;
        return heap.get(0);
    }

    // Get and remove the top priority value of the heap
    @Override
    public T poll() {
        if (heap.isEmpty()) return null;
        T answer = heap.get(0);
        swap(0, heap.size() - 1);
        heap.remove(heap.size() - 1);
        downheap(0);
        return answer;
    }
    
    // Check the consistency of the heap
    // Used for debugging purposes only
    public void sanityCheck() {
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
