import java.util.Comparator;
import datastructures.*;

public class RunningMedian {

	private static PriorityQueue<Float> minHeap;
	private static PriorityQueue<Float> maxHeap;
	
	final public static int HEAPPQ = 0;
	final public static int UNSORTEDPQ = 1;
	final public static int SORTEDPQ = 2;
		
	public static float Calculate(float[] numbersList, int queueNumber) {
		
		RunningMedian.initialiseQueue(queueNumber);
	
		float median = 0;
		for(int i = 0; i < numbersList.length; ++i) {
			if(numbersList[i] < median) {
				RunningMedian.maxHeap.offer(numbersList[i]);
				
				int minHeapSize = RunningMedian.minHeap.size();
				int maxHeapSize = RunningMedian.maxHeap.size();
				
				if ((maxHeapSize - minHeapSize) > 1) {
					RunningMedian.minHeap.offer(RunningMedian.maxHeap.poll());
				}
				
			} else {
				RunningMedian.minHeap.offer(numbersList[i]);
				
				int minHeapSize = RunningMedian.minHeap.size();
				int maxHeapSize = RunningMedian.maxHeap.size();
				
				if((minHeapSize - maxHeapSize) > 1) {
					RunningMedian.maxHeap.offer(RunningMedian.minHeap.poll());
				}
			}
			
			
			median = getMedian();
			System.out.println(median);
		}
		
		
		return median;
	}
	
	private static void initialiseQueue(int queueNumber) {
		switch(queueNumber) {
		case RunningMedian.HEAPPQ:
			RunningMedian.minHeap = new HeapPriorityQueue<Float>();
			RunningMedian.maxHeap = new HeapPriorityQueue<Float>(Comparator.reverseOrder());
			break;
		case RunningMedian.UNSORTEDPQ:
			RunningMedian.minHeap = new UnsortedPriorityQueue<Float>();
			RunningMedian.maxHeap = new UnsortedPriorityQueue<Float>(Comparator.reverseOrder());
			break;
		case RunningMedian.SORTEDPQ:
			RunningMedian.minHeap = new SortedPriorityQueue<Float>();
			RunningMedian.maxHeap = new SortedPriorityQueue<Float>(Comparator.reverseOrder());
			break;
		}
	}
	
	private static float getMedian() {
		int minHeapSize = RunningMedian.minHeap.size();
		int maxHeapSize = RunningMedian.maxHeap.size();
		if(minHeapSize == maxHeapSize) {
			return (RunningMedian.minHeap.peek() + 
					RunningMedian.maxHeap.peek()) / 2; 
		} else if (minHeapSize > maxHeapSize) {
			return RunningMedian.minHeap.peek();
		}
		
		return RunningMedian.maxHeap.peek();
	}
}
