import java.util.Comparator;
import datastructures.*;

public class RunningMedian {

	private static PriorityQueue<Float> minPQ;
	private static PriorityQueue<Float> maxPQ;
	
	final public static int HEAPPQ = 0;
	final public static int UNSORTEDPQ = 1;
	final public static int SORTEDPQ = 2;
		
	public static float Calculate(float[] numbersList, int queueNumber) {
		
		RunningMedian.initialiseQueue(queueNumber);
	
		float median = 0;
		for(int i = 0; i < numbersList.length; ++i) {
			if(numbersList[i] < median) {
				RunningMedian.applyToLeftQueue(
						numbersList[i], 
						RunningMedian.maxPQ, 
						RunningMedian.minPQ);
				
			} else {
				RunningMedian.applyToLeftQueue(
						numbersList[i], 
						RunningMedian.minPQ, 
						RunningMedian.maxPQ);
			}
			
			
			median = getMedian();
			System.out.println(median);
		}
		
		
		return median;
	}
	
	private static void applyToLeftQueue(
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
	
	private static void initialiseQueue(int queueNumber) {
		switch(queueNumber) {
		case RunningMedian.HEAPPQ:
			RunningMedian.minPQ = new HeapPriorityQueue<Float>();
			RunningMedian.maxPQ = new HeapPriorityQueue<Float>(Comparator.reverseOrder());
			break;
		case RunningMedian.UNSORTEDPQ:
			RunningMedian.minPQ = new UnsortedPriorityQueue<Float>();
			RunningMedian.maxPQ = new UnsortedPriorityQueue<Float>(Comparator.reverseOrder());
			break;
		case RunningMedian.SORTEDPQ:
			RunningMedian.minPQ = new SortedPriorityQueue<Float>();
			RunningMedian.maxPQ = new SortedPriorityQueue<Float>(Comparator.reverseOrder());
			break;
		}
	}
	
	private static float getMedian() {
		int minHeapSize = RunningMedian.minPQ.size();
		int maxHeapSize = RunningMedian.maxPQ.size();
		if(minHeapSize == maxHeapSize) {
			return (RunningMedian.minPQ.peek() + 
					RunningMedian.maxPQ.peek()) / 2; 
		} else if (minHeapSize > maxHeapSize) {
			return RunningMedian.minPQ.peek();
		}
		
		return RunningMedian.maxPQ.peek();
	}
}
