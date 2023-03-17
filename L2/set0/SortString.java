// Java program to sort a string according to the
// order defined by a pattern string

class SortString {

	static int MAX_CHAR = 26;

	// Sort str according to the order defined by pattern.
	static void sortByPattern(char[] str, char[] pat)
	{
		// Create a count array store
		// count of characters in str.
		int count[] = new int[MAX_CHAR];

		// Count number of occurrences of
		// each character in str.
		for (int i = 0; i < str.length; i++) {
			count[str[i] - 'a']++;
		}

		// Traverse the pattern and print every characters
		// same number of times as it appears in str. This
		// loop takes O(m + n) time where m is length of
		// pattern and n is length of str.
		int index = 0;
		for (int i = 0; i < pat.length; i++) {
			for (int j = 0; j < count[pat[i] - 'a']; j++) {
				str[index++] = pat[i];
			}
		}
	}

	// Driver code
	public static void main(String args[])
	{
		char[] pat = "bca".toCharArray();
		char[] str = "abc".toCharArray();
		sortByPattern(str, pat);
		System.out.println(String.valueOf(str));
	}
}

// This code has been contributed by 29AjayKumar
