package Problems.set32;

public class REmovePilndrom {

        static boolean isPalindrome(String str)
        {
            int i = 0, j = str.length() - 1;

            // traversing from both the ends
            while (i < j)
            {
                // not palindrome
                if (str.charAt(i++) != str.charAt(j--))
                    return false;
            }
            // palindrome
            return true;
        }

        // function to remove all the palindromic words
        // from the given sentence
        static String removePalinWords(String str)
        {

            // 'final_str' to store the final string and
            // 'word' to one by one store each word of 'str'
            String final_str = "", word = "";

            // add space at the end of 'str'
            str = str + " ";
            int n = str.length();

            // traversing 'str'
            for (int i = 0; i < n; i++)
            {

                // accumulating characters of the current word
                if (str.charAt(i) != ' ')
                    word = word + str.charAt(i);

                else
                {

                    // if 'word' is not palindrome then a
                    // add it to 'final_str'
                    if (!(isPalindrome(word)))
                        final_str += word + " ";

                    // reset
                    word = "";
                }
            }

            // required final string
            return final_str;
        }

        // Driver code
        public static void main (String[] args)
        {
            String str = "Text contains malayalam and level words";
            System.out.print(removePalinWords(str));
        }
    }
