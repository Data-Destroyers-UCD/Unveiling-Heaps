
import java.io.IOException;



public class Main {

	public static void main(String[] args) throws IOException {
		
		float[] arr = { 12, 4, 5, 3, 8, 7 };
		RunningMedian.Calculate(arr, RunningMedian.HEAPPQ);
		
		// Execution done
		System.out.println("\nExecution completed successfully!");
	}
}
