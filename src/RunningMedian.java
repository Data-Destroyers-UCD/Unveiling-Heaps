// Import the necessary packages
import java.util.Comparator;
import datastructures.*;

// Class for calculating the running median
public class RunningMedian {

	// Var to keep track of the median
	private float median = 0;
	
	// min and max PQ
	private PriorityQueue<Float> minPQ;
	private PriorityQueue<Float> maxPQ;
	
	// Constants for initiaing the required Priority Queue
	final public static int HEAPPQ = 0;
	final public static int UNSORTEDPQ = 1;
	final public static int SORTEDPQ = 2;
	
	// Constructor for initiating the required PQ
	public RunningMedian(int queueNumber) {
		this.median = 0;
		this.initialiseQueue(queueNumber);
	}
	
	// Method that calculates the median from the given list
	public float Calculate(float[] numbersList) {
		
		// Loop through eaech number
		for(int i = 0; i < numbersList.length; ++i) {
			// Check if the current number is less than median
			if(numbersList[i] < median) {
				// Apply changes to max PQ
				this.applyToLeftQueue(
						numbersList[i], 
						this.maxPQ, 
						this.minPQ);
			} else {
				// Otherwise apply changes to the min PQ
				this.applyToLeftQueue(
						numbersList[i], 
						this.minPQ, 
						this.maxPQ);
			}
			
			// Calculate the median
			median = getMedian();
			
			//System.out.println(median);
		}
		
		// Return the median
		return median;
	}
	
	// Test function to insert data in into max PQ
	public void insertTimeTest(float data) {
		this.maxPQ.offer(data);
	}
	
	// Test function to check the size of the max PQ
	public int sizeTimeTest() {
		return this.maxPQ.size();
	}
	
	// Test function to get the "min"/top/highest element from the max PQ
	public float peekTimeTest() {
		return this.maxPQ.peek();
	}
	
	// Test function to remove "min"/top/highest element from the max PQ
	public float pollTimeTest() {
		return this.maxPQ.poll();
	}
	
	// Test function to remove all the elements from the min and the max PQ
	public void pollAllTimeTest() {

		while(this.minPQ.size() > 0 || this.maxPQ.size() > 0) {
			if (this.minPQ.size() > 0) {
				this.minPQ.poll();
			}
			if(this.maxPQ.size() > 0) {
				this.maxPQ.poll();
			}
		}
	}

	// Generic function to insert data into the left PQ while balancing the right PQ
	private void applyToLeftQueue(
			float number, 
			PriorityQueue<Float> leftQueue,
			PriorityQueue<Float> rightQueue) {
		// Insert data into left PQ
		leftQueue.offer(number);
		
		// Get the sizes of the left and right PQs
		int leftHeapSize = leftQueue.size();
		int rightHeapSize = rightQueue.size();
		
		// If the left PQ size and right PQ size difference is greater than 1
		if ((leftHeapSize - rightHeapSize) > 1) {
			// Move data from Left PQ to Right PQ
			rightQueue.offer(leftQueue.poll());
		}
	}
	
	// Method to initialise the class Priorty Queue
	private void initialiseQueue(int queueNumber) {
		// Based on the constants declared above inialise the queue
		switch(queueNumber) {
		case RunningMedian.HEAPPQ:
			this.minPQ = new HeapPriorityQueue<Float>();
			// Reverse the order of priority for the max PQ so that the max element will have the highest priority
			this.maxPQ = new HeapPriorityQueue<Float>(Comparator.reverseOrder());
			break;
		case RunningMedian.UNSORTEDPQ:
			this.minPQ = new UnsortedPriorityQueue<Float>();
			// Reverse the order of priority for the max PQ so that the max element will have the highest priority
			this.maxPQ = new UnsortedPriorityQueue<Float>(Comparator.reverseOrder());
			break;
		case RunningMedian.SORTEDPQ:
			this.minPQ = new SortedPriorityQueue<Float>();
			// Reverse the order of priority for the max PQ so that the max element will have the highest priority
			this.maxPQ = new SortedPriorityQueue<Float>(Comparator.reverseOrder());
			break;
		}
	}
	
	// Method to calculate the median based on the current min and max PQs
	private float getMedian() {
		// Get the sizes of min and max PQs
		int minHeapSize = this.minPQ.size();
		int maxHeapSize = this.maxPQ.size();
		// If min PQ size is equal to the maxPQ size
		if(minHeapSize == maxHeapSize) {
			// Get the mid of the top of both queues
			return (this.minPQ.peek() + 
					this.maxPQ.peek()) / 2; 
		} else if (minHeapSize > maxHeapSize) { // if min heap size is greater than max heap size
			// Get the top of the min PQ
			return this.minPQ.peek();
		}
		// Previous conditions not satisfied
		// Return the top of the max PQ
		return this.maxPQ.peek();
	}
}
