package set24;// JAVA Code to print
// the diamond shape

class Diamond
{

    // Prints diamond pattern
    // with 2n rows
    static void printDiamond(int n) {
        int space = n - 1;
        int star = 1;

        for (int i = 1; i < 2 * n; i++) {
            for (int j = 1; j <= space; j++)
                System.out.print(" ");
            for (int j = 1; j <= star; j++)
                System.out.print("* ");
            if (i <2* n / 2) {
                space--;
                star++;
            } else {
                space++;
                star--;
            }
            System.out.println();
        }

    }
    // Driver Code
    public static void main(String[] args)
    {
        printDiamond(5);
    }
}

// This code is contributed
// by Arnav Kr. Mandal.
