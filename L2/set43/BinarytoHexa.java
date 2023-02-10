package set43;// Java implementation to convert a
// binary number to hexadecimal number
import java.util.*;

class BinarytoHExa {

    // Function to create map between binary
// number and its equivalent hexadecimal
    static void createMap(Map<String, Character> um)
    {
        um.put("0000", '0');
        um.put("0001", '1');
        um.put("0010", '2');
        um.put("0011", '3');
        um.put("0100", '4');
        um.put("0101", '5');
        um.put("0110", '6');
        um.put("0111", '7');
        um.put("1000", '8');
        um.put("1001", '9');
        um.put("1010", 'A');
        um.put("1011", 'B');
        um.put("1100", 'C');
        um.put("1101", 'D');
        um.put("1110", 'E');
        um.put("1111", 'F');
    }

    // Function to find hexadecimal
// equivalent of binary
    static String convertBinToHex(String bin)
    {
        int l = bin.length();
        int t = bin.indexOf('.');

        // Length of string before '.'
        int len_left = t != -1 ? t : l;

        // Add min 0's in the beginning to make
        // left substring length divisible by 4
        for(int i = 1;
            i <= (4 - len_left % 4) % 4;
            i++)
            bin = '0' + bin;

        // If decimal point exists
        if (t != -1)
        {

            // Length of string after '.'
            int len_right = l - len_left - 1;

            // Add min 0's in the end to make right
            // substring length divisible by 4
            for(int i = 1;
                i <= (4 - len_right % 4) % 4;
                i++)
                bin = bin + '0';
        }

        // Create map between binary and its
        // equivalent hex code
        Map<String,
                Character> bin_hex_map = new HashMap<String,
                Character>();
        createMap(bin_hex_map);

        int i = 0;
        String hex = "";

        while (true)
        {

            // One by one extract from left, substring
            // of size 4 and add its hex code
            hex += bin_hex_map.get(
                    bin.substring(i, i + 4));
            i += 4;

            if (i == bin.length())
                break;

            // If '.' is encountered add it
            // to result
            if (bin.charAt(i) == '.')
            {
                hex += '.';
                i++;
            }
        }

        // Required hexadecimal number
        return hex;
    }

    // Driver code
    public static void main(String[] args)
    {
        String bin = "1111001010010100001.010110110011011";

        System.out.print("Hexadecimal number = " +
                convertBinToHex(bin));
    }
}

// This code is contributed by jithin
