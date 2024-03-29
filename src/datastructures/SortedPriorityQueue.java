package datastructures;

// Import the package
import java.util.Comparator;

// Implementation of Sorted Priority Queue
public class SortedPriorityQueue<T> extends AbstractPriorityQueue<T> {
	// A doubly linked list to store the data
    private PositionalList<T> list = new LinkedPositionalList<>();

    // Constructor to create a simple priority queue
    public SortedPriorityQueue() { super(); }

    // Constructor to create a  priority queue based on the comparator
    public SortedPriorityQueue(Comparator<T> comp) { super(comp); }

    // Insert a value in the Priority Queue
    // Data is appended according to its position in the queue
    @Override
    public T offer(T value) {
    	// Check if the value is valid
        checkKey(value);
        // Get the last node in the list
        Position<T> walk = list.last();
        // Walk backwards to get the position at which the new value can be inserted
        while (walk != null && compare(value, walk.getElement()) < 0)
            walk = list.before(walk);
        // If no other data is present add to the first
        if (walk == null)
            list.addFirst(value);
        else // Otherwise add after the found walk position
            list.addAfter(walk, value);
        // Return the value inserted
        return value;
    }

    // Get the top priority value of the queue
    @Override
    public T peek() {
        if (list.isEmpty()) return null;
        return list.first().getElement();
    }

    // Get and remove the top priority value of the queue
    @Override
    public T poll() {
        if (list.isEmpty()) return null;
        return list.remove(list.first());
    }

    // Get the size of the Priority Queue
    @Override
    public int size() { return list.size(); }
}

