// Java program to check the sum after
// Removing all zeroes is true or not
public class GfG {
	
	// Function to remove zeroes
	public static int removeZero(int n)
	{
		// Initialize result to zero holds the
		// Result after removing zeroes from no
		int res = 0;
	
		// Initialize variable d to 1 that holds
		// digits of no
		int d = 1;
	
		// Loop while n is greater than zero
		while (n > 0) {
	
			// Check if n mod 10 is not equal to
			// zero
			if (n % 10 != 0) {
	
				// store the result by removing zeroes
				// And increment d by 10
				res += (n % 10) * d;
				d *= 10;
			}
	
			// Go to the next digit
			n /= 10;
		}
	
		// Return the result
		return res;
	}
	
	// Function to check if sum is true after
	// Removing all zeroes.
	public static boolean isEqual(int a, int b)
	{
		// Call removeZero() for both sides
		// and check whether they are equal
		// After removing zeroes.
	
		if (removeZero(a) + removeZero(b) == removeZero(a + b))
			return true;
	
		return false;
	}
	
	public static void main(String []args){
		
		int a = 105, b = 106;
		
		if (isEqual(a, b) == true)
			System.out.println("Yes");
		else
			System.out.println("No");
	}
}

// This code is contributed by Rituraj Jain
