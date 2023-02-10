package set33;

public class Dayscount {
    public static void main(String[] args) {
        int[] dt1 = {10, 3, 2020};
        int[] dt2 = {10, 3, 2021};

        int day1 = dt1[0];
        int month1 = dt1[1];
        int year1 = dt1[2];

        int day2 = dt2[0];
        int month2 = dt2[1];
        int year2 = dt2[2];

        int days = 0;
        while (year1 < year2 || (year1 == year2 && (month1 < month2 || (month1 == month2 && day1 < day2)))) {
            day1++;
            if (day1 > getDaysInMonth(month1, year1)) {
                day1 = 1;
                month1++;
                if (month1 > 12) {
                    month1 = 1;
                    year1++;
                }
            }
            days++;
        }

        System.out.println("Number of days: " + days);

    }

    private static int getDaysInMonth(int month, int year) {
            switch (month) {
                case 1:
                case 3:
                case 5:
                case 7:
                case 8:
                case 10:
                case 12:
                    return 31;
                case 4:
                case 6:
                case 9:
                case 11:
                    return 30;
                case 2:
                    if (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0)) {
                        return 29;
                    } else {
                        return 28;
                    }
                default:
                    return 0;
            }
        }

}
