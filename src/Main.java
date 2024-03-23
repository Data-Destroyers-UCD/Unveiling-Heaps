
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



public class Main {

	public static void main(String[] args) throws IOException {
//		float[] arr = { 12, 4, 5, 3, 8, 7 };
		
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
		
		System.out.println("Heap PQ");
		RunningMedian.Calculate(arr, RunningMedian.HEAPPQ);
		long startTime = System.currentTimeMillis(); // Start the timer
		RunningMedian.insertTimeTest(0.5f);
		long opTime = System.currentTimeMillis() - startTime; // calculate the time
		System.out.println("Insert Time: " + opTime);
		startTime = System.currentTimeMillis(); // Start the timer
		RunningMedian.sizeTimeTest();
		opTime = System.currentTimeMillis() - startTime; // calculate the time
		System.out.println("Size Time: " + opTime);
		startTime = System.currentTimeMillis(); // Start the timer
		RunningMedian.peekTimeTest();
		opTime = System.currentTimeMillis() - startTime; // calculate the time
		System.out.println("Min Time: " + opTime);
		startTime = System.currentTimeMillis(); // Start the timer
		RunningMedian.pollTimeTest();
		opTime = System.currentTimeMillis() - startTime; // calculate the time
		System.out.println("Remove Min Time: " + opTime);
		
		System.out.println("\nSorted PQ");
		RunningMedian.Calculate(arr, RunningMedian.SORTEDPQ);
		startTime = System.currentTimeMillis(); // Start the timer
		RunningMedian.insertTimeTest(0.5f);
		opTime = System.currentTimeMillis() - startTime; // calculate the time
		System.out.println("Insert Time: " + opTime);
		startTime = System.currentTimeMillis(); // Start the timer
		RunningMedian.sizeTimeTest();
		opTime = System.currentTimeMillis() - startTime; // calculate the time
		System.out.println("Size Time: " + opTime);
		startTime = System.currentTimeMillis(); // Start the timer
		RunningMedian.peekTimeTest();
		opTime = System.currentTimeMillis() - startTime; // calculate the time
		System.out.println("Min Time: " + opTime);
		startTime = System.currentTimeMillis(); // Start the timer
		RunningMedian.pollTimeTest();
		opTime = System.currentTimeMillis() - startTime; // calculate the time
		System.out.println("Remove Min Time: " + opTime);
		
		System.out.println("\nUnSorted PQ");
		RunningMedian.Calculate(arr, RunningMedian.UNSORTEDPQ);
		startTime = System.currentTimeMillis(); // Start the timer
		RunningMedian.insertTimeTest(0.5f);
		opTime = System.currentTimeMillis() - startTime; // calculate the time
		System.out.println("Insert Time: " + opTime);
		startTime = System.currentTimeMillis(); // Start the timer
		RunningMedian.sizeTimeTest();
		opTime = System.currentTimeMillis() - startTime; // calculate the time
		System.out.println("Size Time: " + opTime);
		startTime = System.currentTimeMillis(); // Start the timer
		RunningMedian.peekTimeTest();
		opTime = System.currentTimeMillis() - startTime; // calculate the time
		System.out.println("Min Time: " + opTime);
		startTime = System.currentTimeMillis(); // Start the timer
		RunningMedian.pollTimeTest();
		opTime = System.currentTimeMillis() - startTime; // calculate the time
		System.out.println("Remove Min Time: " + opTime);
		
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
