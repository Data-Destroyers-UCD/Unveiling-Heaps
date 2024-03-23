
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



public class Main {

	public static void main(String[] args) throws IOException {

		
		List<List<String>> records = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader("data/indexData.csv"))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		        String[] values = line.split(",");
		        records.add(Arrays.asList(values));
		    }
		}
		
		ArrayList<Float> arrL = new ArrayList<>();
		
		for(int i = 1; i < records.size(); ++i) {

			String numStr = records.get(i).get(2);
			if(Main.isNumeric(numStr))
				arrL.add(Float.parseFloat(numStr));
		}
		
		float[] arr = new float[arrL.size()];
		for(int i = 0; i < arrL.size(); ++i) {
			arr[i] = arrL.get(i);
		}
		
//		float[] arr = { 12, 4, 5, 3, 8, 7 };
		
		RunningMedian heapRM = new RunningMedian(RunningMedian.HEAPPQ);
		RunningMedian sortedRM = new RunningMedian(RunningMedian.SORTEDPQ);
		RunningMedian unsortedRM = new RunningMedian(RunningMedian.UNSORTEDPQ);
		
		
		System.out.println("Heap PQ");
		long startTime = System.currentTimeMillis(); // Start the timer
		heapRM.Calculate(arr);
		long opTime = System.currentTimeMillis() - startTime; // calculate the time
		System.out.println("Insert Time: " + opTime);
		startTime = System.currentTimeMillis(); // Start the timer
		heapRM.sizeTimeTest();
		opTime = System.currentTimeMillis() - startTime; // calculate the time
		System.out.println("Size Time: " + opTime);
		startTime = System.currentTimeMillis(); // Start the timer
		heapRM.peekTimeTest();
		opTime = System.currentTimeMillis() - startTime; // calculate the time
		System.out.println("Min Time: " + opTime);
		startTime = System.currentTimeMillis(); // Start the timer
		heapRM.pollAllTimeTest();
		opTime = System.currentTimeMillis() - startTime; // calculate the time
		System.out.println("Remove All Min Time: " + opTime);
		
		System.out.println("\nSorted PQ");
		startTime = System.currentTimeMillis(); // Start the timer
		sortedRM.Calculate(arr);
		opTime = System.currentTimeMillis() - startTime; // calculate the time
		System.out.println("Insert Time: " + opTime);
		startTime = System.currentTimeMillis(); // Start the timer
		sortedRM.sizeTimeTest();
		opTime = System.currentTimeMillis() - startTime; // calculate the time
		System.out.println("Size Time: " + opTime);
		startTime = System.currentTimeMillis(); // Start the timer
		sortedRM.peekTimeTest();
		opTime = System.currentTimeMillis() - startTime; // calculate the time
		System.out.println("Min Time: " + opTime);
		startTime = System.currentTimeMillis(); // Start the timer
		sortedRM.pollAllTimeTest();
		opTime = System.currentTimeMillis() - startTime; // calculate the time
		System.out.println("Remove All Min Time: " + opTime);
		
		System.out.println("\nUnSorted PQ");
		startTime = System.currentTimeMillis(); // Start the timer
		unsortedRM.Calculate(arr);
		opTime = System.currentTimeMillis() - startTime; // calculate the time
		System.out.println("Insert Time: " + opTime);
		startTime = System.currentTimeMillis(); // Start the timer
		unsortedRM.sizeTimeTest();
		opTime = System.currentTimeMillis() - startTime; // calculate the time
		System.out.println("Size Time: " + opTime);
		startTime = System.currentTimeMillis(); // Start the timer
		unsortedRM.peekTimeTest();
		opTime = System.currentTimeMillis() - startTime; // calculate the time
		System.out.println("Min Time: " + opTime);
		startTime = System.currentTimeMillis(); // Start the timer
		unsortedRM.pollAllTimeTest();
		opTime = System.currentTimeMillis() - startTime; // calculate the time
		System.out.println("Remove All Min Time: " + opTime);
		
		// Execution done
		System.out.println("\nExecution completed successfully!");
	}
	
	public static boolean isNumeric(String strNum) {
	    if (strNum == null) {
	        return false;
	    }
	    try {
	        double d = Double.parseDouble(strNum);
	    } catch (NumberFormatException nfe) {
	        return false;
	    }
	    return true;
	}
}
