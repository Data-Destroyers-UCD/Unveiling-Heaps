import java.util.Comparator;
import datastructures.*;

public class RunningMedian {

	private float median = 0;
	
	private PriorityQueue<Float> minPQ;
	private PriorityQueue<Float> maxPQ;
	
	final public static int HEAPPQ = 0;
	final public static int UNSORTEDPQ = 1;
	final public static int SORTEDPQ = 2;
	
	public RunningMedian(int queueNumber) {
		this.median = 0;
		this.initialiseQueue(queueNumber);
	}
		
	public float Calculate(float[] numbersList) {
		
		
		for(int i = 0; i < numbersList.length; ++i) {
			if(numbersList[i] < median) {
				this.applyToLeftQueue(
						numbersList[i], 
						this.maxPQ, 
						this.minPQ);
				
			} else {
				this.applyToLeftQueue(
						numbersList[i], 
						this.minPQ, 
						this.maxPQ);
			}
			
			
			median = getMedian();
			//System.out.println(median);
		}
		
		
		return median;
	}
	
	public void insertTimeTest(float data) {
		this.maxPQ.offer(data);
	}
	
	public int sizeTimeTest() {
		return this.maxPQ.size();
	}
	
	public float peekTimeTest() {
		return this.maxPQ.peek();
	}
	
	public float pollTimeTest() {
		return this.maxPQ.poll();
	}
	
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

	private void applyToLeftQueue(
			float number, 
			PriorityQueue<Float> leftQueue,
			PriorityQueue<Float> rightQueue) {
		leftQueue.offer(number);
		
		int leftHeapSize = leftQueue.size();
		int rightHeapSize = rightQueue.size();
		
		if ((leftHeapSize - rightHeapSize) > 1) {
			rightQueue.offer(leftQueue.poll());
		}
	}
	
	private void initialiseQueue(int queueNumber) {
		switch(queueNumber) {
		case RunningMedian.HEAPPQ:
			this.minPQ = new HeapPriorityQueue<Float>();
			this.maxPQ = new HeapPriorityQueue<Float>(Comparator.reverseOrder());
			break;
		case RunningMedian.UNSORTEDPQ:
			this.minPQ = new UnsortedPriorityQueue<Float>();
			this.maxPQ = new UnsortedPriorityQueue<Float>(Comparator.reverseOrder());
			break;
		case RunningMedian.SORTEDPQ:
			this.minPQ = new SortedPriorityQueue<Float>();
			this.maxPQ = new SortedPriorityQueue<Float>(Comparator.reverseOrder());
			break;
		}
	}
	
	
	private float getMedian() {
		int minHeapSize = this.minPQ.size();
		int maxHeapSize = this.maxPQ.size();
		if(minHeapSize == maxHeapSize) {
			return (this.minPQ.peek() + 
					this.maxPQ.peek()) / 2; 
		} else if (minHeapSize > maxHeapSize) {
			return this.minPQ.peek();
		}
		
		return this.maxPQ.peek();
	}
}
