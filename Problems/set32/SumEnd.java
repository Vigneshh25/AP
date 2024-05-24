package Problems.set32;

public class SumEnd {
    public static void main(String[] args) {
        int[] numbers = {1, 2, 3, 7, 6};

        int leftSum = 0;
        int rightSum = 0;

        for (int i = 0; i < numbers.length; i++) {
            rightSum += numbers[i];
        }

        for (int i = 0; i < numbers.length; i++) {
            rightSum -= numbers[i];
            if (leftSum == rightSum) {
                System.out.println("Found number with equal sum of elements on either side: " + numbers[i]);
                break;
            }
            leftSum += numbers[i];
        }

    }
}
