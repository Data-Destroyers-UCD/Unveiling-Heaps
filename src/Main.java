// Import the required packages
import java.io.BufferedReader; // Reads text from a character-input stream, buffering characters 
import java.io.BufferedWriter;
import java.io.FileReader; // Convenience class for reading character files
import java.io.FileWriter;
import java.io.IOException; // Convenience class for Input/Output exceptions 
import java.util.ArrayList; // List class in the form of arrays
import java.util.Arrays;	// Class for handling Array Like structures
import java.util.List;		// Class for creating List Structures


// Main class for getting data and executing the algorithms
public class Main {
	// Entry point for java compiler
	public static void main(String[] args) throws IOException {
		
	

		// Create records list of list string to store csv data
		List<List<String>> records = new ArrayList<>();
		// Read the csv file
		// Columns = [ Index,Date,Open,High,Low,Close,Adj Close,Volume ]
		try (BufferedReader br = new BufferedReader(new FileReader("data/AAPL.csv"))) {
		    String line;
		    // Get each line
		    while ((line = br.readLine()) != null) {
		    	// Split line by ,
		        String[] values = line.split(",");
		        // Store the values
		        records.add(Arrays.asList(values));
		    }
		}
		
		// Column index to read 2 => "Open"
		int readColumnIndex = 2;
		
		// Array list for storing specified csv data
		ArrayList<Float> arrL = new ArrayList<>();
		// Start from 2nd row (1 index) to the end of the records
		for(int i = 1; i < records.size(); ++i) {
			// Get the specified value at i row and readColumnIndex column
			String numStr = records.get(i).get(readColumnIndex);
			// Check if it is a number
			if(Main.isNumeric(numStr))
				// Add to the array
				arrL.add(Float.parseFloat(numStr));
		}
		
		// Store the array list in a regular java array
		float[] arr = new float[arrL.size()];
		for(int i = 0; i < arrL.size(); ++i) {
			arr[i] = arrL.get(i);
		}
		
		// Create Running median classes for Heap based, Sorted, UNsorted Priority Queues respectively
		RunningMedian heapRM = new RunningMedian(RunningMedian.HEAPPQ);
		RunningMedian sortedRM = new RunningMedian(RunningMedian.SORTEDPQ);
		RunningMedian unsortedRM = new RunningMedian(RunningMedian.UNSORTEDPQ);
		
		// Time Heap based Priority queue
		// Requires less time as the heap is stored an arraylist compared to other
		//	Sorted and Unsorted queues are stored as Linked Lists
		System.out.println("Heap PQ");
		long startTime = System.currentTimeMillis(); // Start the timer
		float[] medians = heapRM.Calculate(arr);
		long opTime = System.currentTimeMillis() - startTime; // calculate the time
		System.out.println("Insert all data Time: " + opTime + "ms");
		startTime = System.currentTimeMillis(); // Start the timer
		heapRM.sizeTimeTest();
		opTime = System.currentTimeMillis() - startTime; // calculate the time
		System.out.println("Check Size Time: " + opTime + "ms");
		startTime = System.currentTimeMillis(); // Start the timer
		heapRM.peekTimeTest();
		opTime = System.currentTimeMillis() - startTime; // calculate the time
		System.out.println("Check Min Time: " + opTime + "ms");
		startTime = System.currentTimeMillis(); // Start the timer
		heapRM.pollAllTimeTest();
		opTime = System.currentTimeMillis() - startTime; // calculate the time
		System.out.println("Remove All Min Time: " + opTime + "ms");
		// Write medians to file
		Main.writeToFile("output/HeapPQMedians.txt", medians);
		
		// Time Sorted Priority queue
		System.out.println("\nSorted PQ");
		startTime = System.currentTimeMillis(); // Start the timer
		medians = sortedRM.Calculate(arr);
		opTime = System.currentTimeMillis() - startTime; // calculate the time
		System.out.println("Insert all data Time: " + opTime + "ms");
		startTime = System.currentTimeMillis(); // Start the timer
		sortedRM.sizeTimeTest();
		opTime = System.currentTimeMillis() - startTime; // calculate the time
		System.out.println("Check Size Time: " + opTime + "ms");
		startTime = System.currentTimeMillis(); // Start the timer
		sortedRM.peekTimeTest();
		opTime = System.currentTimeMillis() - startTime; // calculate the time
		System.out.println("Check Min Time: " + opTime + "ms");
		startTime = System.currentTimeMillis(); // Start the timer
		sortedRM.pollAllTimeTest();
		opTime = System.currentTimeMillis() - startTime; // calculate the time
		System.out.println("Remove All Min Time: " + opTime + "ms");
		// Write medians to file
		Main.writeToFile("output/SortedPQMedians.txt", medians);
		
		// Time Unsorted Priority queue
		System.out.println("\nUnSorted PQ");
		startTime = System.currentTimeMillis(); // Start the timer
		medians = unsortedRM.Calculate(arr);
		opTime = System.currentTimeMillis() - startTime; // calculate the time
		System.out.println("Insert all data Time: " + opTime + "ms");
		startTime = System.currentTimeMillis(); // Start the timer
		unsortedRM.sizeTimeTest();
		opTime = System.currentTimeMillis() - startTime; // calculate the time
		System.out.println("Check Size Time: " + opTime + "ms");
		startTime = System.currentTimeMillis(); // Start the timer
		unsortedRM.peekTimeTest();
		opTime = System.currentTimeMillis() - startTime; // calculate the time
		System.out.println("Check Min Time: " + opTime + "ms");
		startTime = System.currentTimeMillis(); // Start the timer
		unsortedRM.pollAllTimeTest();
		opTime = System.currentTimeMillis() - startTime; // calculate the time
		System.out.println("Remove All Min Time: " + opTime + "ms");
		// Write medians to file
		Main.writeToFile("output/UnsortedPQMedians.txt", medians);
		
		// Execution done
		System.out.println("\nExecution completed successfully!");
	}
	
	// Method for checking if the input string is a number
	public static boolean isNumeric(String strNum) {
	    if (strNum == null) {
	        return false;
	    }
	    try {
	        Double.parseDouble(strNum);
	    } catch (NumberFormatException nfe) {
	        return false;
	    }
	    return true;
	}
	
	// Method to write x data to outFilename file
	public static void writeToFile (String outFilename, float[] x) throws IOException{
		BufferedWriter outputWriter = new BufferedWriter(new FileWriter(outFilename));
		for (int i = 0; i < x.length; i++) {
		    outputWriter.write(Float.toString(x[i]));
		    outputWriter.newLine();
		}
		outputWriter.flush();  
		outputWriter.close();  
	}
}
