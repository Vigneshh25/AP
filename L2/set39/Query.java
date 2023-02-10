package set39;

public class Query {
    public static void queryResult(String field, String condition, int[][] matrix) {
        String[] parts = condition.split("[<=>]");
        String fieldToCompare = parts[0];
        String operator = condition.substring(fieldToCompare.length(), fieldToCompare.length() + 1);
        int value = Integer.parseInt(parts[1]);

        int fieldIndex = -1;
        if (fieldToCompare.equals("A")) {
            fieldIndex = 1;
        } else if (fieldToCompare.equals("B")) {
            fieldIndex = 2;
        } else if (fieldToCompare.equals("C")) {
            fieldIndex = 3;
        }

        for (int[] row : matrix) {
            boolean conditionSatisfied = false;
            if (operator.equals(">")) {
                conditionSatisfied = row[fieldIndex] > value;
            } else if (operator.equals("<")) {
                conditionSatisfied = row[fieldIndex] < value;
            } else if (operator.equals("=")) {
                conditionSatisfied = row[fieldIndex] == value;
            }

            if (conditionSatisfied) {
                if (field.equals("*")) {
                    for (int i = 0; i < row.length; i++) {
                        System.out.print(row[i] + " ");
                    }
                    System.out.println();
                } else if (field.equals("A")) {
                    System.out.println(row[1]);
                } else if (field.equals("B")) {
                    System.out.println(row[2]);
                } else if (field.equals("C")) {
                    System.out.println(row[3]);
                }
            }
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 56, 67, 89},
                {2, 89, 54, 90},
                {3, 78, 91, 83},
                {4, 69, 72, 95}
        };

        queryResult("*", "A>70", matrix);
// prints:
// 2 89 54 90
// 3 78 91 83
// 4 69 72 95

        queryResult("A", "C<90", matrix);
// prints:
// 56
// 78

    }
}
