package datastructures;

//Import the package
import java.util.Comparator;

// Implementation of UnSorted Priority Queue
public class UnsortedPriorityQueue<T> extends AbstractPriorityQueue<T> {
	// A doubly linked list to store the data
    private PositionalList<T> list = new LinkedPositionalList<>();
    // Constructor to create a simple priority queue
    public UnsortedPriorityQueue() { super(); }
    // Constructor to create a  priority queue based on the comparator
    public UnsortedPriorityQueue(Comparator<T> comp) { super(comp); }

    // Method to find the top priority of an element in the priority queue
    private Position<T> findMin() {
    	// Get the first position of the queue
        Position<T> small = list.first();
        // Walk through each position in the list
        for (Position<T> walk : list.positions())
        	// If the current position priority is less than the small position priority
            if (compare(walk.getElement(), small.getElement()) < 0)
            	// Set small position to current walk position
                small = walk;
        // Return the small position
        return small;
    }

    // Insert a value in the Priority Queue
    // data is appended at the last position
    @Override
    public T offer(T value) {
        checkKey(value);
        list.addLast(value);
        return value;
    }

    // Get the top priority value of the queue
    @Override
    public T peek() {
        if (list.isEmpty()) return null;
        // List is not sorted so need to find the highest priority element from the list
        return findMin().getElement();
    }

    // Get and remove the top priority value of the queue
    @Override
    public T poll() {
        if (list.isEmpty()) return null;
        return list.remove(findMin());
    }

    // Get the size of the Priority Queue
    @Override
    public int size() { return list.size(); }
}

