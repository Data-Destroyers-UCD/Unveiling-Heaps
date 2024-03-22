package datastructures;

import java.util.Comparator;

public abstract class AbstractPriorityQueue<T> implements PriorityQueue<T> {
	  

	
	  // instance variable for an AbstractPriorityQueue
	  /** The comparator defining the ordering of keys in the priority queue. */
	  private Comparator<T> comp;

	  /**
	   * Creates an empty priority queue using the given comparator to order keys.
	   * @param c comparator defining the order of keys in the priority queue
	   */
	  protected AbstractPriorityQueue(Comparator<T> c) { comp = c; }

	  /** Creates an empty priority queue based on the natural ordering of its keys. */
	  protected AbstractPriorityQueue() { this(new DefaultComparator<T>()); }

	  /** Method for comparing two entries according to key */
	  protected int compare(T a, T b) {
	    return comp.compare(a, b);
	  }

	  /** Determines whether a key is valid. */
	  protected boolean checkKey(T key) throws IllegalArgumentException {
	    try {
	      return (comp.compare(key,key) == 0);  // see if key can be compared to itself
	    } catch (ClassCastException e) {
	      throw new IllegalArgumentException("Incompatible key");
	    }
	  }

	  /**
	   * Tests whether the priority queue is empty.
	   * @return true if the priority queue is empty, false otherwise
	   */
	  @Override
	  public boolean isEmpty() { return size() == 0; }
	}
