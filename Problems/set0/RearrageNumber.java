package Problems.set0;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

class RearrageNumber {

    // Driver program to test above
    public static void main(String args[])
    {
        long number = 37821246;

        long oddDigits = 0;
        long evenDigits = 0;
        long placeValue = 1;

        while (number > 0) {
            long digit = number % 10;
            if (digit % 2 == 0) {
                evenDigits = evenDigits*10+digit;
            } else {
                oddDigits = oddDigits*10+digit;
            }
            number = number / 10;
        }

        System.out.println(oddDigits+""+ evenDigits);
        int num1 = 173;
        int num2 = 64228;

        int temp = num2;
        while (temp > 0) {
            num1 *= 10;
            temp /= 10;
        }
        System.out.println(num1);
        int mergedNum = num1 + num2;

        System.out.println(mergedNum);
    } }
