package set24;

public class frequency {

        public static void main(String[] args) {
            int[] numbers = {1, 2, 3, 3, 4, 4, 5};
            int target = 3;
            int count = countOccurrences(numbers, target);
            System.out.println(count);  // Output: 2
        }

        public static int countOccurrences(int[] numbers, int target) {
            int first = findFirst(numbers, target);
            if (first == -1) {
                return 0;
            }
            int last = findLast(numbers, target);
            return last - first + 1;
        }

        public static int findFirst(int[] numbers, int target) {
            int left = 0;
            int right = numbers.length - 1;
            while (left < right) {
                int mid = left + (right - left) / 2;
                if (numbers[mid] < target) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
            return numbers[left] == target ? left : -1;
        }

        public static int findLast(int[] numbers, int target) {
            int left = 0;
            int right = numbers.length - 1;
            while (left < right) {
                int mid = left + (right - left + 1) / 2;
                if (numbers[mid] > target) {
                    right = mid - 1;
                } else {
                    left = mid;
                }
            }
            return numbers[right] == target ? right : -1;
        }


}
